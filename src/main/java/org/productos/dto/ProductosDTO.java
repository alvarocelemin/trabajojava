package org.productos.dto;

import java.util.List;

public class ProductosDTO {
	public String title ;
	public int price;
	public String description;
	public int categoryId;
	public List<String> images;
	public ProductosDTO(){
		
	}
	public ProductosDTO(String title, int price, String description, int categoryId, List<String> images) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
		this.images = images;
	}
	

	
	
	
	
}
