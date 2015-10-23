package com;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.entities.Restaurante;
import com.persistence.RestauranteUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
@ManagedBean
@SessionScoped
public class RestauranteBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Restaurante> restaurantes;
	
	private Restaurante restauranteSeleccionado;
	
	private String email;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String descripcion;
	
	// Getters & Setters

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
	
	public Restaurante getRestauranteSeleccionado() {
		return restauranteSeleccionado;
	}

	public void setRestauranteSeleccionado(Restaurante restauranteSeleccionado) {
		this.restauranteSeleccionado = restauranteSeleccionado;
	}
	
	public void goAddPage() throws IOException{
		limpiarCampos();
		FacesContext.getCurrentInstance().getExternalContext().redirect("anadir.xhtml");
	}
	
	public void goIndex() throws IOException{
		limpiarCampos();
		cargarRestaurantes();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

	public String anadirRestaurante(){
		// comprueba que el email del restaurante no exista
		if(!RestauranteUtils.existe(email) && email != null && email.length()>0){
			// almacena el restaurante en el Datastore
			Restaurante res = RestauranteUtils.insert(email, nombre, direccion, telefono, descripcion);
			// limpia los campos del formulario
			limpiarCampos();
			// Añade el restaurante a la lista del ManagedBean
			restaurantes.add(res);
			return "/index.xhtml";
		}
		return "";
	}
	
	public String eliminarRestaurante(){
		// Obtiene el restaurante a partir del email
		Restaurante res = RestauranteUtils.getRestaurante(email);
		// Comprueba que el restaurante exista
		if(res !=null){
			// Elimina el restaurante del Datastore
			RestauranteUtils.removeRestaurante(res.getEMAIL());
			// Elimina el restaurante de la lista del ManagedBean
			restaurantes.remove(res);
			return "/index.xhtml";
		}else{
			return "";
		}
	}
	
	public String cargaFormularioModificarRestaurante(){
		// Carga los datos del restaurante seleccionado en el formulario
		email = restauranteSeleccionado.getEMAIL();
		nombre = restauranteSeleccionado.getNOMBRE();
		direccion = restauranteSeleccionado.getDIRECCION();
		descripcion = restauranteSeleccionado.getDESCRIPCION();
		telefono = restauranteSeleccionado.getTELEFONO();
		return "/modificar.xhtml";
	}
	
	public void modificarRestaurante() throws IOException{
		// Obtiene el restaurante seleccionado
		restauranteSeleccionado = RestauranteUtils.getRestaurante(email);
		System.out.println("Email:"+email);
		if(restauranteSeleccionado!= null){
			restaurantes.remove(restaurantes.indexOf(restauranteSeleccionado));
			// modifica los campos del restaurante
			restauranteSeleccionado.setNOMBRE(nombre);
			restauranteSeleccionado.setDIRECCION(direccion);
			restauranteSeleccionado.setDESCRIPCION(descripcion);
			restauranteSeleccionado.setTELEFONO(telefono);
			// actualiza el restaurante en el Datastore
			RestauranteUtils.updateRestaurante(restauranteSeleccionado);
			// añade el restaurante modificado a la lista del ManagedBean
			restaurantes.add(restauranteSeleccionado);
			limpiarCampos();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}else{
			FacesContext.getCurrentInstance().getExternalContext().redirect("modificar.xhtml");
		}
	}
	
	public void limpiarCampos(){
		this.email = "";
		this.direccion = "";
		this.descripcion = "";
		this.nombre = "";
		this.telefono = "";
		restauranteSeleccionado = null;
	}
	
	public void cargarRestaurantes(){
		restaurantes = new ArrayList<Restaurante>();
		restaurantes = RestauranteUtils.getEntries();
	}
	
	@PostConstruct
	public void inicializarDatos(){
		limpiarCampos();
		cargarRestaurantes();
		if(email != null){
			restauranteSeleccionado = RestauranteUtils.getRestaurante(email);
		}
	}
	
 
}