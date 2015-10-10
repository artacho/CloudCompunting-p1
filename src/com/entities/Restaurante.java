package com.entities;

import com.google.appengine.api.datastore.Entity;

public class Restaurante {
	
	public static final String TUTORIAL_ENTITY = "Restaurante";
	
	public static final String NOMBRE = "nombre";
	
	public static final String DIRECCION = "direccion";
	
	public static final String EMAIL = "email";
	
	public static final String TELEFONO = "telefono";
	
	public static final String DESCRIPCION = "descripcion";

	private Entity entity = new Entity (TUTORIAL_ENTITY);
	
	// constructores
	
	public Restaurante(final String nombre, final String direccion ){
		entity.setProperty(NOMBRE, nombre);
		entity.setProperty(DIRECCION, direccion);
		
	}
	
	
	// getters & setters
	
	public Entity getEntity () {
		return entity;
	}

	public static String getNombre() {
		return NOMBRE;
	}

	public static String getDireccion() {
		return DIRECCION;
	}

	public static String getEmail() {
		return EMAIL;
	}

	public static String getTelefono() {
		return TELEFONO;
	}

	public static String getDescripcion() {
		return DESCRIPCION;
	}

}
