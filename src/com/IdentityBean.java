package com;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@ManagedBean
@SessionScoped
public class IdentityBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 804554485355670164L;

	public String getLoginUrl()
	{
		UserService us = UserServiceFactory.getUserService();
		return us.createLoginURL("/anadir.jsf");
	}
	
	public String getLogoutURL(String url)
	{
		UserService us = UserServiceFactory.getUserService();
		return us.createLogoutURL(url);
	}
}
