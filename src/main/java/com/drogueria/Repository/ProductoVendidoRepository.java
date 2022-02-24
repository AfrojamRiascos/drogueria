package com.drogueria.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drogueria.Entity.ProductoVendido;

@Repository("productoVendidoRepository")
public interface ProductoVendidoRepository extends JpaRepository<ProductoVendido, Serializable>{

}
