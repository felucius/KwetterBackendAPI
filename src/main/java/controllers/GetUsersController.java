/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

/**
 *
 * @author M
 */
@Named
@SessionScoped
public class GetUsersController implements Serializable{
    
    @Inject
    private UserService userService;
    
    private List<User> users = null;
    
    public GetUsersController(){
        
    }
    
    @PostConstruct
    public void init(){
        users = userService.getAllUsers();
    }
    
    public List<User> getAllUsers(){
        return this.users;
    }
    
    public void setUsers(List<User> users){
        this.users = users;
    }
}
