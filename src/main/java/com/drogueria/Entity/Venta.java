package com.drogueria.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.drogueria.Util.Util;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVenta")
	private Integer idVenta;
	
	@Column(name = "fecha_hora")
	private String fechaHora;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private Set<ProductoVendido> productos;
	
	public Venta() {
		this.fechaHora = Util.obtenerFechaYHoraActual();
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Set<ProductoVendido> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoVendido> productos) {
		this.productos = productos;
	}
	
	public Float getTotal() {
		Float total = 0f;
		for (ProductoVendido productoVendido : this.productos) {
			total += productoVendido.getTotal();
		}
		return total;
	}
	
}
