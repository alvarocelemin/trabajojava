package org.productos.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.productos.dto.ProductosDTO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



public class ProductosCliente {
	
	public String listProductos() throws IOException{
		URL url = new URL("https://api.escuelajs.co/api/v1/products");
		List<?> list= null;
		StringBuilder string = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		String line;
		StringBuffer response = new StringBuffer();
		conn.setRequestMethod("GET");
		conn.connect();
		
		int responseCode = conn.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			Scanner scanner = new Scanner(url.openStream());
			while((line = in.readLine()) != null) {
				response.append(line);
			}
			//list.add(response.toString());
			in.close();
		}
		else {
			throw new RuntimeException("Failed: HTPP error " + responseCode);
		}
		return response.toString();
	}
	public String crearProductos(String title,int price, String description, int categoryid,List images) {
		StringBuilder response = new StringBuilder();
		try {
			
			URL url = new URL("https://api.escuelajs.co/api/v1/products");
			Map<String,Object> objetoRequest = new LinkedHashMap<>();
			//ProductosDTO productos = new ProductosDTO();
			objetoRequest.put("title", title);
			objetoRequest.put("price", price);
			objetoRequest.put("description", description);
			objetoRequest.put("categoryId", categoryid);
			objetoRequest.put("images", images);
			StringBuilder postData = new StringBuilder();
			for(Map.Entry<String, Object> parametros:objetoRequest.entrySet()) {
				if(postData.length() != 0) {
					postData.append('&');
				}
				postData.append(URLEncoder.encode(parametros.getKey(),"UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(parametros.getValue()),"UTF-8"));
			}
			byte[] postBytes = postData.toString().getBytes("UTF-8");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("content-Length",String.valueOf(postBytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(postBytes);
			//int responseCode = conn.getResponseCode();
			Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			for(int c= in.read();c != -1; c=in.read()) {
				response.append((char) c);;
			}
			/*if(responseCode == HttpURLConnection.HTTP_OK) {
				productoCreado = true;
			}*/
		}
		catch(Exception e) {
			response.append("error : " + e.getMessage());
		}
		return response.toString();
	}
	
	/*public List<ProductosDTO> listProductos(){
		Response response = null;
		WebTarget webTarget;
		Client client = ClientBuilder.newClient();
		List<ProductosDTO> list= null;
		String responseJson = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			webTarget = client.target("https://api.escuelajs.co/api/v1/products"); 
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.get();
			
			System.out.println("Status: " + response.getStatus());
			
			if(response.getStatus() != 200) {
				throw new RuntimeException("Failed: HTPP error " + response.getStatus());
			}
			responseJson = response.readEntity(String.class);
			
			list  = objectMapper.readValue(responseJson, new TypeReference<List<ProductosDTO>>() {
				
			});
			
		}catch(Exception e){
			System.out.println("error : " + e.getMessage());
		}finally {
			if(response != null) {
				response.close();
			}
		}
		return list;
	}*/
}
