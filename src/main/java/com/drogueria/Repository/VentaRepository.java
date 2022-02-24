package com.drogueria.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drogueria.Entity.Venta;

@Repository("VentaRepository")
public interface VentaRepository extends JpaRepository<Venta, Serializable>{

}
