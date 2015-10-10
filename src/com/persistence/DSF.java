package com.persistence;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DSF {

	// Singleton
	private static final DatastoreService dataStoreService = DatastoreServiceFactory
			.getDatastoreService();

	public static DatastoreService getDatastoreService() {
		return dataStoreService;
	}

	private DSF() {
	}
}