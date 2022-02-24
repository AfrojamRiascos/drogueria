package com.drogueria.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto_vendido")
public class ProductoVendido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProductoVendido;
	
    private Float cantidad;
    private Float precioProducto;
    private String nombreProducto;
    private String codigoProducto;
    
    @ManyToOne
    @JoinColumn
    private Venta venta;

	public ProductoVendido(Float cantidad, Float precioProducto, String nombreProducto, String codigoProducto,
			Venta venta) {
		this.cantidad = cantidad;
		this.precioProducto = precioProducto;
		this.nombreProducto = nombreProducto;
		this.codigoProducto = codigoProducto;
		this.venta = venta;
	}
    
	public ProductoVendido() {
		
	}
	
	public Float getTotal() {
        return this.cantidad * this.precioProducto;
    }

	public Integer getIdProductoVendido() {
		return idProductoVendido;
	}

	public void setIdProductoVendido(Integer idProductoVendido) {
		this.idProductoVendido = idProductoVendido;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(Float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}
