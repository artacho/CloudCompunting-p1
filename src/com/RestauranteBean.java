package com;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.entities.Restaurante;
import com.persistence.RestauranteUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
@ManagedBean
@RequestScoped
public class RestauranteBean implements Serializable {
 
	/**
	 * 
	 */
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
		if(!RestauranteUtils.existe(email) && email != null && email.length()>0){
			Restaurante res = RestauranteUtils.insert(email, nombre, direccion, telefono, descripcion);
			limpiarCampos();
			restaurantes.add(res);
			return "/index.xhtml";
		}
		return "";
	}
	
	public String eliminarRestaurante(){
		Restaurante res = RestauranteUtils.getRestaurante(email);
		if(res !=null){
			RestauranteUtils.removeRestaurante(res.getEMAIL());
			restaurantes.remove(res);
			return "/index.xhtml";
		}else{
			return "";
		}
	}
	
	
	public void limpiarCampos(){
		this.email = "";
		this.direccion = "";
		this.descripcion = "";
		this.nombre = "";
		this.telefono = "";
	}
	
	public void cargarRestaurantes(){
		restaurantes = new ArrayList<Restaurante>();
		restaurantes = RestauranteUtils.getEntries();
	}
	
	@PostConstruct
	public void inicializarDatos(){
		limpiarCampos();
		cargarRestaurantes();
	}
	
 
}