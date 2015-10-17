package com;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Restaurante;
import com.google.appengine.api.datastore.DatastoreService;
import com.persistence.DSF;
import com.persistence.RestauranteUtils;

import java.io.Serializable;
import java.util.List;
 
@ManagedBean
@SessionScoped
public class RestauranteBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private List<Restaurante> restaurantes;
	
	private String email;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String descripcion;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public int getNumberRestaurantes(){
		return RestauranteUtils.getEntries().size();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	public String anadirRestaurante(){
		RestauranteUtils.insert(email, nombre, direccion, telefono, descripcion);
		limpiarCampos();
		cargarRestaurantes();
		return "/index.xhtml";
	}
	
	public void limpiarCampos(){
		this.email = "";
		this.direccion = "";
		this.descripcion = "";
		this.nombre = "";
		this.telefono = "";
	}
	
	public void cargarRestaurantes(){
		restaurantes = RestauranteUtils.getEntries();
	}
	
	@PostConstruct
	public void inicializarDatos(){
		limpiarCampos();
		cargarRestaurantes();
	}
	
 
}