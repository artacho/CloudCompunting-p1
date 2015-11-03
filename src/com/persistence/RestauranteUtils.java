package com.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.entities.Restaurante;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.Query.FilterOperator;

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
	
	public static List<Restaurante> getEntries(){
		List<Restaurante> restaurantes = null;
		
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
