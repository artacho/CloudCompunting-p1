package com.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

public class Restaurante implements Serializable, Comparable{

	
	private static final long serialVersionUID = 1L;

	public static String RESTAURANTE_ENTITY = "Restaurante";
	
	public static String NOMBRE = "nombre";
	
	public static String DIRECCION = "direccion";
	
	public static String EMAIL = "email";
	
	public static String TELEFONO = "telefono";
	
	public static String DESCRIPCION = "descripcion";

	public static String FLICKER = "flicker";
	
	private Entity entity = new Entity (RESTAURANTE_ENTITY);
	
	// constructores
	
	public Restaurante(String email, String nombre, String direccion, String telefono, String descripcion, String flicker){
		entity.setProperty(EMAIL, email);
		entity.setProperty(NOMBRE, nombre);
		entity.setProperty(DIRECCION, direccion);
		entity.setProperty(TELEFONO, telefono);
		entity.setProperty(DESCRIPCION, descripcion);
		entity.setProperty(FLICKER, flicker);
	}
	
	// getters & setters

	public String getNOMBRE() {
		return (String) entity.getProperty(NOMBRE);
	}

	public void setNOMBRE(String nombre) {
		entity.setProperty(NOMBRE, nombre);
	}

	public String getDIRECCION() {
		return (String) entity.getProperty(DIRECCION);
	}

	public void setDIRECCION(String direccion) {
		entity.setProperty(DIRECCION, direccion);
	}

	public String getEMAIL() {
		return (String) entity.getProperty(EMAIL);
	}

	public void setEMAIL(String email) {
		entity.setProperty(EMAIL, email);
	}

	public String getTELEFONO() {
		return (String) entity.getProperty(TELEFONO);
	}

	public void setTELEFONO(String telefono) {
		entity.setProperty(TELEFONO, telefono);
	}

	public String getDESCRIPCION() {
		return (String) entity.getProperty(DESCRIPCION);
	}

	public void setDESCRIPCION(String descripcion) {
		entity.setProperty(DESCRIPCION, descripcion);
	}
	
	public String getFLICKER() {
		return (String) entity.getProperty(FLICKER);
	}

	public void setFLICKER(String flicker) {
		entity.setProperty(FLICKER, flicker);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public boolean equals(Object r) {
		if (r == null) return false;
	    if (r == this) return true;
	    if (!(r instanceof Restaurante))return false;
	    if (((Restaurante)r).getEMAIL().equals(this.getEMAIL()))return true;
	    else return false;
	}
	
	@Override
	public int hashCode(){
	    return this.getEMAIL().hashCode();
	  }

	@Override
	public int compareTo(Object arg0) {
		Restaurante res = (Restaurante)arg0;
		return this.getNOMBRE().compareTo(res.getNOMBRE());
	}
	

}
