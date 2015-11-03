package com.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;

public class Restaurante implements Serializable, Comparable{

	private static final long serialVersionUID = 1L;
	
	private String nombre, direccion, latitud, longitud, email, telefono, descripcion, flicker;
	
	// constructor
	
	public Restaurante(String email){
		this.email = email;
	}
	
	// getters & setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFlicker() {
		return flicker;
	}

	public void setFlicker(String flicker) {
		this.flicker = flicker;
	}
	

	
	@Override
	public boolean equals(Object r) {
		if (r == null) return false;
	    if (r == this) return true;
	    if (!(r instanceof Restaurante))return false;
	    if (((Restaurante)r).getEmail().equals(this.getEmail()))return true;
	    else return false;
	}

	@Override
	public int hashCode(){
	    return this.getEmail().hashCode();
	  }

	@Override
	public int compareTo(Object arg0) {
		Restaurante res = (Restaurante)arg0;
		return this.getNombre().compareTo(res.getNombre());
	}
	


}
