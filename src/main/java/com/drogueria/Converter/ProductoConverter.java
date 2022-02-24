package com.drogueria.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.drogueria.Entity.Producto;
import com.drogueria.Model.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {
	
	public List<ProductoModel> listConverter(List<Producto> producto){
		List<ProductoModel> productoModel = new ArrayList<>();
		
		for(Producto productos: producto) {
			productoModel.add(new ProductoModel(productos));
		}
		return productoModel;		
	}

}
