package com.drogueria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproducto")
    private Integer idProducto;
	
    @Column(name = "nombre_producto")
    private String nombreProducto;
    
    @Column(name = "tipo_producto")
    private String tipoProducto;

    @Column(name = "codigo_producto")
    private String codigoProducto;

    @Column(name = "precio_producto")
    private float precioProducto;

    @Column(name = "existencia_producto")
    private int existenciaProducto;
    
    public Producto(Integer idProducto, String nombreProducto, String tipoProducto, String codigoProducto,
			float precioProducto, int existenciaProducto) {
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.codigoProducto = codigoProducto;
		this.precioProducto = precioProducto;
		this.existenciaProducto = existenciaProducto;
	}
    
	public Producto(String nombreProducto, String tipoProducto, String codigoProducto, float precioProducto,
			int existenciaProducto) {
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.codigoProducto = codigoProducto;
		this.precioProducto = precioProducto;
		this.existenciaProducto = existenciaProducto;
	}

	public Producto() {
	}
	
	public boolean sinExistenciaProducto() {
        return this.existenciaProducto <= 0;
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
	
	public void restarExistencia(int existenciaProducto) {
		this.existenciaProducto = existenciaProducto;
	}
}
