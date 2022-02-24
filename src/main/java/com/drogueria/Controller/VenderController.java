package com.drogueria.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.drogueria.Entity.Producto;
import com.drogueria.Entity.ProductoParaVenta;
import com.drogueria.Entity.ProductoVendido;
import com.drogueria.Entity.Venta;
import com.drogueria.Repository.ProductoRepository;
import com.drogueria.Repository.ProductoVendidoRepository;
import com.drogueria.Repository.VentaRepository;

@Controller
@RequestMapping(path = "/vender")
public class VenderController {
	
	private static Log LOG = LogFactory.getLog(VenderController.class);

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private ProductoVendidoRepository productoVendidoRepository;

	@PostMapping(value = "/quitar/{indice}")
	public String quitarDeCesta(@PathVariable int indice, HttpServletRequest request) {
		ArrayList<ProductoParaVenta> carrito = this.obtenerCarrito(request);

		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}
		return "redirect:/vender/";
	}

	private void limpiarCarrito(HttpServletRequest request) {
		this.guardarCarrito(new ArrayList<>(), request);
	}

	@GetMapping(value = "/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarCarrito(request);
		redirectAttrs.addFlashAttribute("mensaje", "Venta cancelada").addFlashAttribute("clase", "info");
		return "redirect:/vender/";
	}

	@PostMapping(value = "/terminar")
	public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ArrayList<ProductoParaVenta> carrito = this.obtenerCarrito(request);

		if (carrito == null || carrito.size() <= 0) {
			return "redirect:/vender/";
		}
		Venta venta = ventaRepository.save(new Venta());

		for (ProductoParaVenta productoParaVender : carrito) {

			Producto producto = productoRepository.findById(productoParaVender.getIdProducto()).orElse(null);
			if (producto == null)
				continue;

			producto.restarExistencia((int) productoParaVender.getCantidad());

			productoRepository.save(producto);

			ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(),
					productoParaVender.getPrecioProducto(), productoParaVender.getNombreProducto(),
					productoParaVender.getCodigoProducto(), venta);

			productoVendidoRepository.save(productoVendido);
		}

		this.limpiarCarrito(request);

		redirectAttrs.addFlashAttribute("mensaje", "Venta realizada correctamente").addFlashAttribute("clase",
				"success");
		return "redirect:/vender/";
	}

	@GetMapping(value = "/")
	public String interfazVender(Model model, HttpServletRequest request) {
		model.addAttribute("producto", new Producto());
		float total = 0;
		ArrayList<ProductoParaVenta> carrito = this.obtenerCarrito(request);
		for (ProductoParaVenta p : carrito)
			total += p.getTotal();
		model.addAttribute("total", total);
		return "vender/vender";
	}
	
	private ArrayList<ProductoParaVenta> obtenerCarrito(HttpServletRequest request) {
		ArrayList<ProductoParaVenta> carrito = (ArrayList<ProductoParaVenta>) request.getSession()
				.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		return carrito;
	}
	
	private void guardarCarrito(ArrayList<ProductoParaVenta> carrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", carrito);
	}
	
	@PostMapping(value = "/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ArrayList<ProductoParaVenta> carrito = this.obtenerCarrito(request);
		Producto productoBuscadoPorCodigo = productoRepository.findFirstByCodigoProducto(producto.getCodigoProducto());
		
		if (productoBuscadoPorCodigo == null) {
			redirectAttrs
					.addFlashAttribute("mensaje",
							"El producto con el código " + producto.getCodigoProducto() + " no existe")
					.addFlashAttribute("clase", "warning");
			return "redirect:/vender/";
		}
		if (productoBuscadoPorCodigo.sinExistenciaProducto()) {
			redirectAttrs.addFlashAttribute("mensaje", "El producto está agotado").addFlashAttribute("clase",
					"warning");
			return "redirect:/vender/";
		}
		boolean encontrado = false;
		for (ProductoParaVenta productoParaVenderActual : carrito) {
			LOG.info(productoParaVenderActual.getCodigoProducto());
			if (productoParaVenderActual.getCodigoProducto().equals(productoBuscadoPorCodigo.getCodigoProducto())) {
				productoParaVenderActual.aumentarCantidad();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			carrito.add(new ProductoParaVenta(productoBuscadoPorCodigo.getNombreProducto(),
					productoBuscadoPorCodigo.getCodigoProducto(), productoBuscadoPorCodigo.getPrecioProducto(),
					productoBuscadoPorCodigo.getExistenciaProducto(), productoBuscadoPorCodigo.getIdProducto(), 1f));
		}
		LOG.info("Código: " + productoBuscadoPorCodigo.getCodigoProducto());
		LOG.info("Id: " + productoBuscadoPorCodigo.getIdProducto());
		LOG.info("Nombre: " + productoBuscadoPorCodigo.getNombreProducto());
		LOG.info("Precio: " + productoBuscadoPorCodigo.getPrecioProducto());
		LOG.info("Tipo: " + productoBuscadoPorCodigo.getTipoProducto());
		LOG.info("Existencia: " + productoBuscadoPorCodigo.getExistenciaProducto());
		
		this.guardarCarrito(carrito, request);
		return "redirect:/vender/";
	}
}
