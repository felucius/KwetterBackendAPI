/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.User;
import domain.UserRole;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.UserService;

/**
 *
 * @author M
 */
@Named
@SessionScoped
@DeclareRoles({"admin", "moderator", "user"})
public class GetUsersController implements Serializable {

    @Inject
    private UserService userService;

    private List<User> users = null;
    private User selectedUser = null;

    public GetUsersController() {

    }

    @PostConstruct
    public void init() {
        users = userService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setSelectedUser(User selectedUser) {
        if (selectedUser != null) {
            this.selectedUser = selectedUser;
        } else {
            System.out.println("Selected user is null");
        }
    }

    public User getSelectedUser() {
        if (selectedUser != null) {
            System.out.println("USER SELECTED: " + selectedUser.getName());
            return this.selectedUser;
        } else {
            System.out.println("Selected user is null");
            return null;
        }
    }
    
    @RolesAllowed("admin")
    public boolean promoteUser(){
        if(selectedUser != null){
            userService.promoteUser(selectedUser);
            init();
            return true;
        }else{
            System.out.println("Cannot promote user, user is not found");
            return false;
        }
    }
    
    @RolesAllowed("admin")
    public boolean demoteUser(){
        if(selectedUser != null){
            userService.demoteUser(selectedUser);
            init();
            return true;
        }else{
            System.out.println("Cannot demote user, user is not found");
            return false;
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("User Selected", ((User) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
