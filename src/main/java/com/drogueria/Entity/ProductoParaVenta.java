package com.drogueria.Entity;

public class ProductoParaVenta extends Producto {

	private float cantidad;

	public ProductoParaVenta(Integer idProducto, String nombreProducto, String tipoProducto, String codigoProducto,
			float precioProducto, int existenciaProducto, float cantidad) {
		super(idProducto, nombreProducto, tipoProducto, codigoProducto, precioProducto, existenciaProducto);
		this.cantidad = cantidad;
	}

	/*
	 * public ProductoParaVenta(String nombreProducto, String tipoProducto, String
	 * codigoProducto, Float precioProducto, int existenciaProducto, Float cantidad)
	 * { super(nombreProducto, tipoProducto, codigoProducto, precioProducto,
	 * existenciaProducto, idProducto); this.cantidad = cantidad; }
	 */

	public ProductoParaVenta(String nombreProducto, String tipoProducto, String codigoProducto, float precioProducto,
			int existenciaProducto, float cantidad) {
		super(nombreProducto, tipoProducto, codigoProducto, precioProducto, existenciaProducto);
		this.cantidad = cantidad;
	}

	public ProductoParaVenta(String nombreProducto, String codigoProducto, float precioProducto, int existenciaProducto,
			Integer idProducto, float f) {
	}

	public void aumentarCantidad() {
		this.cantidad++;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getTotal() {
		return this.getPrecioProducto() * this.cantidad;
	}
}
