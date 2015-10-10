package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.entities.Restaurante;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class RestauranteUtils {
	
	private static final int FETCH_MAX_RESULTS = 10;
	
	public static void insert(final String nombre, final String direccion) {
		
		final DatastoreService datastoreService = DSF.getDatastoreService();
		final Restaurante restaurante = new Restaurante(nombre, direccion);		
		datastoreService.put(restaurante.getEntity());
		
	}
	
	public static List<Restaurante> getEntries() {
		final DatastoreService datastoreService = DSF.getDatastoreService();
		final Query query = configureQuery();
		final FetchOptions fetchOptions = configureFetchOptions();

		final List<Restaurante> restaurantes = new ArrayList<Restaurante>();
	
		for (Entity entity: datastoreService.prepare(query).asList(fetchOptions)) {
			restaurantes.add(convertEntityToRestaurante (entity));	
		}		
		return restaurantes;
	}
	
	private static Restaurante convertEntityToRestaurante (final Entity entity) {
		final String nombre = (String) entity.getProperty(Restaurante.NOMBRE);
		final String direccion = (String) entity.getProperty(Restaurante.DIRECCION);
		return new Restaurante(nombre, direccion);
	}
	
	private static Query configureQuery () {
		final Query query = new Query(Restaurante.TUTORIAL_ENTITY);
		Filter filtro = new FilterPredicate("nombre",
                FilterOperator.NOT_EQUAL,
                null);
		query.setFilter(filtro);
		query.addSort(Restaurante.NOMBRE, SortDirection.DESCENDING);		
		return query;
	}
	
	private static FetchOptions configureFetchOptions () {
		return Builder.withLimit(FETCH_MAX_RESULTS);	
	}

}
