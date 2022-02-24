package com.drogueria.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drogueria.Entity.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends CrudRepository<Producto, Serializable>{
	
	//List<Producto> findById(int id);
	//List<Producto> findByTipoProducto(String tipoProducto);
	Producto findFirstByCodigoProducto(String codigoProducto);
}
