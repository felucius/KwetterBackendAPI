/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * The class 'UserService' provides data of users, collected by the userDAO.
 * 
 * @author M
 */
@Stateless
public class UserService {
    /**
     * Inject the userDAO. By doing this the constructor does not have to be
     * initialized with the 'new' keyword. This provides a loosely coupled architecture.
     */
    @Inject
    UserDAO userDAO;
    
    /**
     * Get all users from the userDAO. UserDao makes a call to the database and
     * sends the data to this method.
     * 
     * @return a list of all available users.
     */
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
    
    /**
     * This method creates a new user and persists the object to the database
     * 
     * @param user is the object that is going to be created and persisted to the
     * database.
     */
    public void CreateUser(User user){
        //userDAO.createUser(user);
    }
    
    
    /**
     * This method finds the user by it's user id. This id is passed on from the
     * UserResource to the UserService.
     * 
     * @param id of the user to be searched.
     * @return the user object.
     */
    public User findUser(Long id){
        return userDAO.findUser(id);
    }
    
    /**
     * This method find the user by it's given id. From there on this information
     * is passed on to the DAO and to the entity manager to be removed from the 
     * database
     * 
     * @param id of the user object that is going to be removed.
     */
    public void removeUser(User user){
        userDAO.removeUser(user);
    }
}
