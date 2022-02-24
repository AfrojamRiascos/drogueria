package com.drogueria.Model;

import com.drogueria.Entity.Producto;

public class ProductoModel {
	
    private Integer idProducto;
    private String nombreProducto;
    private String tipoProducto;
    private String codigoProducto;
    private float precioProducto;
    private int existenciaProducto;

	public ProductoModel(Integer idProducto, String nombreProducto, String tipoProducto, String codigoProducto,
			float precioProducto, int existenciaProducto) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.codigoProducto = codigoProducto;
		this.precioProducto = precioProducto;
		this.existenciaProducto = existenciaProducto;
	}

	public ProductoModel() {
	}

	public ProductoModel(Producto producto) {
		this.idProducto = producto.getIdProducto();
		this.nombreProducto = producto.getNombreProducto();
		this.tipoProducto = getTipoProducto();
		this.codigoProducto = getCodigoProducto();
		this.precioProducto = getPrecioProducto();
		this.existenciaProducto = getExistenciaProducto();
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}

	public int getExistenciaProducto() {
		return existenciaProducto;
	}

	public void setExistenciaProducto(int existenciaProducto) {
		this.existenciaProducto = existenciaProducto;
	}
}
