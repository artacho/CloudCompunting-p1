package com;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Restaurante;
import com.persistence.RestauranteUtils;

import java.io.Serializable;
import java.util.List;
 
@ManagedBean
@SessionScoped
public class RestauranteBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private List<Restaurante> restaurantes; 
	
	public int getNumberRestaurantes(){
		return RestauranteUtils.getEntries().size();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
 
}