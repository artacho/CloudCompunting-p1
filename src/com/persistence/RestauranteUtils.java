package com.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import com.entities.Restaurante;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestauranteUtils {
	

	private static String myAPIKey = "QU3xhQLeyPDR_7jYlckdEpRAqyiRq9WL";
	
	public static void insert(Restaurante restaurante) throws IOException{
		Gson gson = new Gson();
		String restauranteJson = gson.toJson(restaurante);
		String reply = "", line = "";
		URL url = new URL("https://api.mongolab.com/api/1/databases/dbprueba/collections/restaurantes?apiKey=" + myAPIKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(10000);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Content-Length", String.valueOf(restauranteJson.length()));
		urlConnection.setDoOutput(true);
		byte[] postDataBytes = restauranteJson.getBytes("UTF-8");
		urlConnection.getOutputStream().write(postDataBytes);
		InputStream input = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader (new InputStreamReader(input));
		while ((line = reader.readLine()) != null) {
			reply += line;
		}
		reader.close();
	}
	
	public static List<Restaurante> getEntries() throws IOException{
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		Gson gson = new Gson();
		String reply = "", line = "";
		URL url = new URL("https://api.mongolab.com/api/1/databases/dbprueba/collections/restaurantes?apiKey=" + myAPIKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(10000);
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		InputStream input = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader (new InputStreamReader(input));
		while ((line = reader.readLine()) != null) {
			reply += line;
		}
		reader.close();
		restaurantes = Arrays.asList(gson.fromJson(reply, Restaurante[].class));
		return restaurantes;
	}
	
	
	public static boolean existe(String email){
		return getRestaurante(email)!=null;
	}
	
	public static Restaurante getRestaurante(String email){
		Restaurante res = null;
		return res;
	}
	
	public static boolean removeRestaurante(String email){
		return true;
	}
	
	public static void updateRestaurante(Restaurante restaurante){
		
	}
	

}
