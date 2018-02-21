/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.UserService;

/**
 * Api call where the user makes a request to the backend to receive data by going 
 * to the path 'user', through the 'api' path, configured at JAXRSconfig.
 * 
 * Stateless beans is used here, because the request is simple and does not need
 * to be memorized by the server.
 * @author M
 */
@Path("user")
@Stateless
public class UserResource {
    /**
     * Injecting ensures the creation of the variable or constructor without using
     * the 'new' keyword.
     */
    @Inject
    UserService userService;
    
    /**
     * GET request from the userService. Userservice get it's data from the DAO.
     * The DAO (Data Access Object) get it's information from the database, through
     * it's persistence context.
     * 
     * @return a list of all users.
     */
    @GET
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    
    /**
     * POST request to the userService. This method allows a new user to be created
     * Information is send to the userService, to the userDAO and finally to the
     * entity manager.
     * 
     * @param user is the object that is created and being persisted to the database.
     */
    @POST
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(@PathParam("name") String name){
        // Test user
        System.out.println("TESTING USER CREATION");
        User user = new User();
        user.setName(name);
        userService.CreateUser(user);
    }
    
    /**
     * GET request from the userService. When calling this method, the user id
     * is given to the userService. From there to the DAO and from there to the
     * entity manager to get the data from the database.
     * 
     * @param id of the user to be found.
     * @return the user object.
     */
    @GET
    @Path("{id}")
    public User findUser(@PathParam("id") Long id){
        return userService.findUser(id);
    }
    
    /**
     * DELETE request to the userService. When this method is called, the user id
     * is given so the account with that specific id can be removed.
     * 
     * @param id of the user object or account, that is going to be removed.
     */
    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") Long id){
        userService.removeUser(id);
    }
}
