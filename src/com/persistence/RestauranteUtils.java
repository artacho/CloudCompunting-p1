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
import com.entities.Restaurante;
import com.google.gson.Gson;

public class RestauranteUtils {

	private static String myAPIKey = "sc5glAzsaL-ym-qE9jt7jB5R_lRzrCMy";
	private static String base_datos="prueba";
	public static void insert(Restaurante restaurante) throws IOException {
		Gson gson = new Gson();
		String restauranteJson = gson.toJson(restaurante);
		String reply = "", line = "";
		URL url = new URL(
				"https://api.mongolab.com/api/1/databases/"+base_datos+"/collections/restaurantes?apiKey=" + myAPIKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(10000);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Content-Length", String.valueOf(restauranteJson.length()));
		urlConnection.setDoOutput(true);
		byte[] postDataBytes = restauranteJson.getBytes("UTF-8");
		urlConnection.getOutputStream().write(postDataBytes);
		InputStream input = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while ((line = reader.readLine()) != null) {
			reply += line;
		}
		reader.close();
	}

	public static List<Restaurante> getEntries() throws IOException {
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		Gson gson = new Gson();
		String reply = "", line = "";
		URL url = new URL(
				"https://api.mongolab.com/api/1/databases/"+base_datos+"/collections/restaurantes?apiKey=" + myAPIKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(10000);
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		InputStream input = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while ((line = reader.readLine()) != null) {
			reply += line;
		}
		reader.close();
		List<Restaurante> arrayRestaurantes = Arrays.asList(gson.fromJson(reply, Restaurante[].class));
		for(Restaurante r: arrayRestaurantes){
			restaurantes.add(r);
		}
		return restaurantes;
	}

	public static boolean existe(String email) {
		return getRestaurante(email) != null;
	}

	public static Restaurante getRestaurante(String email) {
		Restaurante res = null;
		Gson gson = new Gson();
		String reply = "", line = "";
		try {
			String urlParameters = "q=%7B%22email%22:%22" + email + "%22%7D&apiKey=" + myAPIKey;
			URL url = new URL("https://api.mongolab.com/api/1/databases/"+base_datos+"/collections/restaurantes?" + urlParameters);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(10000);
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setDoOutput(true);
			InputStream input = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			while ((line = reader.readLine()) != null) {
				reply += line;
			}
			reader.close();
		} catch (IOException e) {
			return null;
		}	
		List<Restaurante>restaurantes = Arrays.asList(gson.fromJson(reply, Restaurante[].class));
		// Se supone que no habrá dos restaurantes con el mismo email 
		if(restaurantes != null && !restaurantes.isEmpty()){
			res = restaurantes.get(0);
		}
		return res;
	}
	
	public static boolean removeRestaurante(String id) {
			try {
			URL url = new URL(
					"https://api.mongolab.com/api/1/databases/"+base_datos+"/collections/restaurantes/"+ id+"?apiKey=" + myAPIKey);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(10000);
			urlConnection.setRequestMethod("DELETE");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setDoOutput(true);
			InputStream input = urlConnection.getInputStream();
			} catch (IOException e) {
				return false;
			}
			return true;
	}

	public static void updateRestaurante(Restaurante restaurante)  throws IOException {
		Gson gson = new Gson();
		String restauranteJson = gson.toJson(restaurante);
		String reply = "", line = "";
		URL url = new URL(
				"https://api.mongolab.com/api/1/databases/"+base_datos+"/collections/restaurantes?apiKey=" + myAPIKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setConnectTimeout(10000);
		urlConnection.setRequestMethod("PUT");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Content-Length", String.valueOf(restauranteJson.length()));
		urlConnection.setDoOutput(true);
		byte[] postDataBytes = restauranteJson.getBytes("UTF-8");
		urlConnection.getOutputStream().write(postDataBytes);
		InputStream input = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while ((line = reader.readLine()) != null) {
			reply += line;
		}
		reader.close();
	}

}
