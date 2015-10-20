package com.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

public class Restaurante implements Serializable {

	private static final long serialVersionUID = 7162224867946927281L;

	public static String RESTAURANTE_ENTITY = "Restaurante";
	
	public static String NOMBRE = "nombre";
	
	public static String DIRECCION = "direccion";
	
	public static String EMAIL = "email";
	
	public static String TELEFONO = "telefono";
	
	public static String DESCRIPCION = "descripcion";

	private Entity entity = new Entity (RESTAURANTE_ENTITY);
	
	// constructores
	
	public Restaurante(String email, String nombre, String direccion, String telefono, String descripcion){
		entity.setProperty(EMAIL, email);
		entity.setProperty(NOMBRE, nombre);
		entity.setProperty(DIRECCION, direccion);
		entity.setProperty(TELEFONO, telefono);
		entity.setProperty(DESCRIPCION, descripcion);
	}
	
	// getters & setters

	public String getNOMBRE() {
		return (String) entity.getProperty(NOMBRE);
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getDIRECCION() {
		return (String) entity.getProperty(DIRECCION);
	}

	public void setDIRECCION(String dIRECCION) {
		DIRECCION = dIRECCION;
	}

	public String getEMAIL() {
		return (String) entity.getProperty(EMAIL);
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getTELEFONO() {
		return (String) entity.getProperty(TELEFONO);
	}

	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}

	public String getDESCRIPCION() {
		return (String) entity.getProperty(DESCRIPCION);
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	

}
