package org.productos.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.productos.client.ProductosCliente;
import org.productos.dto.ProductosDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class ProductosController {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listarProductos() throws IOException{
		HashMap<String, String> m = new HashMap<>();
		ProductosCliente products = new ProductosCliente();
		StringBuilder stringbuilder = new StringBuilder();
		
		/*List<ProductosDTO> list = products.listProductos();
		for(ProductosDTO product: list) {
			m.put("Pid", String.valueOf(product.getPid()));
			m.put("Name", product.getName());
			m.put("Description", product.getDescription());
			m.put("PriceFinal", String.valueOf(product.getPriceFinal()));
		}*/
		return Response.ok(products.listProductos()).build();
	}
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearProductos(@Valid ProductosDTO producto) throws IOException{
		//HashMap<String, String> m = new HashMap<>();
		/*ProductosDTO productosget = new ProductosDTO();
		productosget.setTitle(producto.getTitle());
		productosget.setPrice(producto.getPrice());
		productosget.setDescription(producto.getDescription());
		productosget.setCategoryId(producto.getCategoryId());
		productosget.setImages(producto.getImages());
		*/
		ProductosCliente products = new ProductosCliente();
		//StringBuilder stringbuilder = new StringBuilder();
		String response = products.crearProductos(producto.title,producto.price,
				producto.description,producto.categoryId,producto.images);
		return Response.ok(response).build();
	}
}
