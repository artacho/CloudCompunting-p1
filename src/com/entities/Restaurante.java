package com.entities;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Restaurante implements Serializable, Comparable {

	private static final long serialVersionUID = 1L;
	
	@SerializedName("_id")
    @Expose
    private com.entities.Id Id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("flicker")
    @Expose
    private String flicker;
    
    public Restaurante(){

    }
    
    public Restaurante(String email){
    	this.email = email;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public com.entities.Id getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(com.entities.Id Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     *     The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return
     *     The direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @param direccion
     *     The direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return
     *     The latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * 
     * @param latitud
     *     The latitud
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * 
     * @return
     *     The longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * 
     * @param longitud
     *     The longitud
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * 
     * @param telefono
     *     The telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * 
     * @return
     *     The descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion
     *     The descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return
     *     The flicker
     */
    public String getFlicker() {
        return flicker;
    }

    /**
     * 
     * @param flicker
     *     The flicker
     */
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
