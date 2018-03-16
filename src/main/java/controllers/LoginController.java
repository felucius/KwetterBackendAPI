/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.User;
import domain.UserRole;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
@SessionScoped
@DeclareRoles({"ADMIN", "MODERATOR", "USER"})
public class LoginController implements Serializable {
    //@Resource
    //SessionContext sessionContext;

    @Inject
    private UserService userService;

    private String name;
    private String password;
    private UserRole userRole;
    private User user = null;
    private FacesContext context = null;

    public LoginController() {

    }

    public void setUserName(String username) {
        this.name = username;
    }

    public String getUserName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    //private String getLoggedInUserName(){
    //System.out.println("Logged in user: " + sessionContext.getCallerPrincipal().getName());
    //return sessionContext.getCallerPrincipal().getName();
    //}
    public boolean isLoggedIn() {
        return context.getExternalContext().getSessionMap().get("name") != null;
    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // Returning principal name
    public String getLoggedInUserName() {
        if (user != null) {
            return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("name");
        } else {
            return "ANONYMOUS";
        }
    }

    public Date getLoggedInTime() {
        if (user != null) {
            return (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("time");
        } else {
            return null;
        }
    }

    @PermitAll
    public String login() {
        user = userService.findUserByName(name);

        context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("name", name);
        context.getExternalContext().getSessionMap().put("time", new Date());

        if (isLoggedIn()) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())
                    && userRole.ADMIN.equals(user.getUserRole())) {
                return "adminpanel.xhtml";
            } else if (name.equals(user.getName()) && password.equals(user.getPassword())
                    && userRole.MODERATOR.equals(user.getUserRole())) {
                return "moderatorpanel.xhtml";
            } else if (name.equals(user.getName()) && password.equals(user.getPassword())
                    && userRole.USER.equals(user.getUserRole())) {
                return "account.xhtml";
            } else {
                return "failure";
            }
        } else {
            return "failure";
        }
    }
}
