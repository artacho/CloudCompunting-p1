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
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class RestauranteUtils {
	
	private static final int FETCH_MAX_RESULTS = 10;
	
	
	public static void insert(Restaurante restaurante){
		final DatastoreService datastoreService = DSF.getDatastoreService();
		datastoreService.put(restaurante.getEntity());
	}
	
	public static List<Restaurante> getEntries() {
		AsyncMemcacheService memcache = MemcacheServiceFactory.getAsyncMemcacheService();
		memcache.clearAll();
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
		Restaurante res = new Restaurante( (String) entity.getProperty(Restaurante.EMAIL));
		res.setNOMBRE((String) entity.getProperty(Restaurante.NOMBRE));
		res.setDESCRIPCION((String) entity.getProperty(Restaurante.DESCRIPCION));
		res.setDIRECCION((String) entity.getProperty(Restaurante.DIRECCION));
		res.setLATITUD((long) entity.getProperty(Restaurante.LATITUD));
		res.setLONGITUD((long) entity.getProperty(Restaurante.LONGITUD));
		res.setTELEFONO((String) entity.getProperty(Restaurante.TELEFONO));
		res.setFLICKER((String) entity.getProperty(Restaurante.FLICKER));
		res.setEntity(entity);
		return res;
	}
	
	private static Query configureQuery () {
		final Query query = new Query(Restaurante.RESTAURANTE_ENTITY);
		Filter filtro = new FilterPredicate("nombre",
                FilterOperator.NOT_EQUAL,
                null);
		query.setFilter(filtro);
		query.addSort(Restaurante.NOMBRE, SortDirection.ASCENDING);		
		return query;
	}
	
	private static FetchOptions configureFetchOptions () {
		return Builder.withLimit(FETCH_MAX_RESULTS);	
	}
	
	public static boolean existe(String email){
		return getRestaurante(email)!=null;
	}
	
	public static Restaurante getRestaurante(String email){
		Restaurante res = null;
		final DatastoreService ds = DSF.getDatastoreService();
		final Query query = new Query(Restaurante.RESTAURANTE_ENTITY);
		Filter filtro = new FilterPredicate("email",
                FilterOperator.EQUAL,
                email);
		query.setFilter(filtro);
		Entity entity = ds.prepare(query).asSingleEntity();
		if(entity != null){
			res = convertEntityToRestaurante(entity);
		}	
		return res;
	}
	
	public static boolean removeRestaurante(String email){
		Restaurante restaurante = getRestaurante(email);
		if(restaurante != null){
			final DatastoreService ds = DSF.getDatastoreService();
			final Query query = new Query(Restaurante.RESTAURANTE_ENTITY);
			Filter filtro = new FilterPredicate("email",
	                FilterOperator.EQUAL,
	                email);
			query.setFilter(filtro);
			Entity entity = ds.prepare(query).asSingleEntity();
			ds.delete(entity.getKey());
			return true;
		}else{
			return false;
		}
	}
	
	public static void updateRestaurante(Restaurante restaurante){
		if(restaurante != null){
			Restaurante res = getRestaurante(restaurante.getEMAIL());
			res.setNOMBRE(restaurante.getNOMBRE());
			res.setDIRECCION(restaurante.getDIRECCION());
			res.setDESCRIPCION(restaurante.getDESCRIPCION());
			res.setEMAIL(restaurante.getEMAIL());
			res.setTELEFONO(restaurante.getTELEFONO());
			res.setLATITUD(restaurante.getLATITUD());
			res.setLONGITUD(restaurante.getLONGITUD());
			final DatastoreService ds = DSF.getDatastoreService();
			ds.put(res.getEntity());
		}
	}
	

}
