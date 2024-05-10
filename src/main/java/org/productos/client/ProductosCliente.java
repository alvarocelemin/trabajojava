package org.productos.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
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

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



public class ProductosCliente {
	
	/*
	 * listado de productos usando jakarta
	 */
	
	public String listProductos() {
		String response = "";
		try {
            Client cliente = ClientBuilder.newClient();

            String url = "https://api.escuelajs.co/api/v1/products";

            Response respuesta = cliente.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();

            int codigoRespuesta = respuesta.getStatus();

            response = respuesta.readEntity(String.class);
            //System.out.println("Respuesta: " + cuerpoRespuesta);

            // Cerrar el cliente
            cliente.close();
        } catch (Exception e) {
           response = "respuesta : " +  e.getMessage();
        }
		return response;
	}
	/*
	 * Listado de productos usando java .net
	 */
	/*public String listProductos() throws IOException{
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
	}*/
	
	/*
	 * Cración de productos usando jakarta para consumir el fake api
	 */
	public String crearProductos(ProductosDTO productos) {
		String responseBody = "";
		try {
			Client cliente = ClientBuilder.newClient();

			 String url = "https://api.escuelajs.co/api/v1/products";

	            Response respuesta = cliente.target(url)
	                .request(MediaType.APPLICATION_JSON)
	                .post(Entity.json(productos));

	            int codigoRespuesta = respuesta.getStatus();
	            responseBody  = respuesta.readEntity(String.class);

	           
	            cliente.close();
	        } catch (Exception e) {
	        	responseBody = e.getMessage();
	        }
		
		return responseBody;
	}
	
	
}
