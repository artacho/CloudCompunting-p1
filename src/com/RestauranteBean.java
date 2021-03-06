package com;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.entities.Restaurante;
import com.flickr.Flickr;
import com.google.gson.Gson;
import com.persistence.RestauranteUtils;
 
@ManagedBean
@ApplicationScoped
public class RestauranteBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Restaurante> restaurantes;
	
	private Restaurante restauranteSeleccionado;
	
	private Restaurante restauranteDetalles;
	
	private String email;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String descripcion;
	
	private List<String> urlFoto;	
	
	private String flicker;
	
	private String latitud;

	private String longitud;
	
	private boolean duplicatedEmail;
	// Getters & Setters

	public String getFlicker() {
		return flicker;
	}

	public void setFlicker(String flicker) {
		this.flicker = flicker;
	}

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
	
	public Restaurante getRestauranteDetalles() {
		return restauranteDetalles;
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

	public void setRestauranteDetalles(Restaurante restauranteDetalles) {
		this.restauranteDetalles = restauranteDetalles;
	}
	
	public List<String> getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(List<String> urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	public boolean isDuplicatedEmail() {
		return duplicatedEmail;
	}

	public void setDuplicatedEmail(boolean duplicatedEmail) {
		this.duplicatedEmail = duplicatedEmail;
	}
 

	public String anadirRestaurante(){
		// comprueba que el email del restaurante no exista
		duplicatedEmail = false;
		if(!RestauranteUtils.existe(email) && email != null && email.length()>0){
			// Crea un restaurante con los datos del formulario
			Restaurante res = new Restaurante(email);
			res.setNOMBRE(nombre);
			res.setDESCRIPCION(descripcion);
			res.setDIRECCION(direccion);
			res.setLATITUD(latitud);
			res.setLONGITUD(longitud);
			res.setTELEFONO(telefono);
			res.setFLICKER(flicker);
			// almacena el restaurante en el Datastore
			RestauranteUtils.insert(res);
			// limpia los campos del formulario
			limpiarCampos();
			// Añade el restaurante a la lista del ManagedBean
			restaurantes.add(res);
			Collections.sort(restaurantes, new CustomComparator());
			return "/index.xhtml";
		}  else {
			duplicatedEmail = true;
		}
		return "";
	}
	
	public class CustomComparator implements Comparator<Restaurante> {
	    @Override
	    public int compare(Restaurante o1, Restaurante o2) {
	        return o1.getNOMBRE().compareTo(o2.getNOMBRE());
	    }
	}
	
	public String eliminarRestaurante(){
		// Obtiene el restaurante a partir del email
		Restaurante res = RestauranteUtils.getRestaurante(restauranteDetalles.getEMAIL());
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
		latitud= restauranteSeleccionado.getLATITUD();
		longitud=restauranteSeleccionado.getLONGITUD();
		telefono = restauranteSeleccionado.getTELEFONO();
		return "/modificar.xhtml";
	}
	
	public void modificarRestaurante() throws IOException{
		// Obtiene el restaurante seleccionado
		restauranteSeleccionado = RestauranteUtils.getRestaurante(email);
		if(restauranteSeleccionado!= null){
			restaurantes.remove(restaurantes.indexOf(restauranteSeleccionado));
			// modifica los campos del restaurante
			restauranteSeleccionado.setNOMBRE(nombre);
			restauranteSeleccionado.setDIRECCION(direccion);
			restauranteSeleccionado.setDESCRIPCION(descripcion);
			restauranteSeleccionado.setLATITUD(latitud);
			restauranteSeleccionado.setLONGITUD(longitud);
			restauranteSeleccionado.setTELEFONO(telefono);
			// actualiza el restaurante en el Datastore
			RestauranteUtils.updateRestaurante(restauranteSeleccionado);
			// añade el restaurante modificado a la lista del ManagedBean
			restaurantes.add(restauranteSeleccionado);
			Collections.sort(restaurantes, new CustomComparator());
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
		this.longitud = "0";
		this.latitud = "0";
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
		if(restaurantes == null || restaurantes.isEmpty()){
			cargarRestaurantes();
		}

		if(email != null){
			restauranteSeleccionado = RestauranteUtils.getRestaurante(email);
		}
	}
	
	public static boolean modulo(int numero, int modulo)
	{
		return numero%4 == 0;
	}
	
	public String consultarRestaurante () {
		Flickr flickr = null;
		try {
			String reply = "", line = "";
			URL url = new URL("https://api.flickr.com/services/rest?method=flickr.photos.search&format=json&api_key=a6044c4da2ccca4c01b958d560bc4c77&tags=malaga&per_page=20");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(10000);
			InputStream input = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader (new InputStreamReader(input));
			while ((line = reader.readLine()) != null) {
				reply += line;
			}
			reader.close();
			reply = reply.replace("jsonFlickrApi(", "");
			reply = reply.substring(0,reply.length()-1);
			Gson gson = new Gson();
			flickr = gson.fromJson(reply, Flickr.class);
			
			urlFoto = new ArrayList<String> ();
			for (int i = 0; i < flickr.getPhotos().getPhoto().size(); i++) {
				String secret = flickr.getPhotos().getPhoto().get(i).getSecret();
				Integer farm = flickr.getPhotos().getPhoto().get(i).getFarm();
				String id = flickr.getPhotos().getPhoto().get(i).getId();
				String server = flickr.getPhotos().getPhoto().get(i).getServer();
				urlFoto.add("https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		email = restauranteDetalles.getEMAIL();
		nombre = restauranteDetalles.getNOMBRE();
		direccion = restauranteDetalles.getDIRECCION();
		descripcion = restauranteDetalles.getDESCRIPCION();
		latitud= restauranteDetalles.getLATITUD();
		longitud=restauranteDetalles.getLONGITUD();
		telefono = restauranteDetalles.getTELEFONO();
		
		return "./restaurante.xhtml";
	}
 
}