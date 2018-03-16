/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.User;
import domain.UserRole;
import java.io.Serializable;
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
public class LoginController implements Serializable{
    @Inject
    private UserService userService;
    
    private String name;
    private String password;
    private UserRole userRole;
    private User user = null;
    
    public LoginController(){
        
    }
    
    public void setUserName(String username){
        this.name = username;
    }
    
    public String getUserName(){
        return this.name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String login(){
        user = userService.findUserByName(name);
        if(name.equals(user.getName()) && password.equals(user.getPassword()) 
                && userRole.ADMIN.equals(user.getUserRole())){
            return "adminpanel";
        }else if(name.equals(user.getName()) && password.equals(user.getPassword())
                && userRole.MODERATOR.equals(user.getUserRole())){
            return "moderatorpanel";
        }else if(name.equals(user.getName()) && password.equals(user.getPassword()) 
                && userRole.USER.equals(user.getUserRole())){
            return "account";
        }else{
            return "failure";
        }
    }
}
