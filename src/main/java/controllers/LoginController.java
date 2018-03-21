/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


/**
 *
 * @author M
 */
@Named
@Dependent
public class LoginController implements Serializable {

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    public String doLogout() throws IOException {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();

        return "/index.xhtml?faces-redirect=true";
    }

    // Returning principal name
    public String getLoggedInUserName() {
        if (isLoggedIn()) {
            return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        } else {
            return "Not logged in a session.";
        }
    }

    public Date getLoggedInTime() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("time", new Date());
        return (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("time");
    }

}
