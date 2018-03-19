/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.User;
import domain.UserRole;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import service.UserService;

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
