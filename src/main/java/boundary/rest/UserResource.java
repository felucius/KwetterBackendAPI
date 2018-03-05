/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.TweetService;
import service.UserService;

/**
 * Api call where the user makes a request to the backend to receive data by
 * going to the path 'user', through the 'api' path, configured at JAXRSconfig.
 *
 * Stateless beans is used here, because the request is simple and does not need
 * to be memorized by the server.
 *
 * @author M
 */
@Stateless
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    /**
     * Injecting ensures the creation of the variable or constructor without
     * using the 'new' keyword.
     */
    @Inject
    UserService userService;
    
    @Inject
    TweetService tweetService;

    /**
     * GET request from the userService. Userservice get it's data from the DAO.
     * The DAO (Data Access Object) get it's information from the database,
     * through it's persistence context.
     *
     * @return a list of all users.
     */
    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * POST request to the userService. This method allows a new user to be
     * created Information is send to the userService, to the userDAO and
     * finally to the entity manager.
     *
     * @param username is the object that is created and being persisted to the
     * database.
     */
    @POST
    @Path("createuser/{username}")
    public User createUser(@PathParam("username") String username) {
        User user = new User(username);
        return userService.createUser(user);
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
    @Path("finduser/{id}")
    public User findUser(@PathParam("id") Long id) {
        return userService.findUser(id);
    }

    /**
     * This method allows a user to be found by it's username.
     * 
     * @param name is the name to be searched on as an input parameter.
     * @return the user object.
     */
    @GET
    @Path("finduserbyname/{name}")
    public User findUserByName(@PathParam("name") String name){
        return userService.findUserByName(name);
    }
    
    /**
     * DELETE request to the userService. When this method is called, the user
     * id is given so the account with that specific id can be removed.
     *
     * @param user
     * @param id of the user object or account, that is going to be removed.
     */
    @DELETE
    @Path("removeuser/{id}")
    public boolean removeUser(@PathParam("id") Long id) {
        System.out.println("Rest layer value: " + id);
        return userService.removeUser(userService.findUser(id));
    }
    
    //TODO: Change to dynamic method instead of static testing method
    @POST
    @Path("addtweet/{username}/{tweetcontent}/{tags}/{mention}")
    public boolean addTweet(@PathParam("username") String userName, 
            @PathParam("tweetcontent") String tweetContent,
            @PathParam("tags") String tag, @PathParam("mention") String userMention){
        
        List<User> mentions = new ArrayList();
        List<String> tags = new ArrayList();
        
        tags.add(tag);
        mentions.add(userService.findUserByName(userMention));
        
        Tweet tweet = new Tweet(tweetContent, tags, userService.findUserByName(userName));
        return userService.addTweet(userService.findUserByName(userName), tweet, mentions);
    }
    
    @GET
    @Path("findtweet/{id}")
    public Tweet findTweet(@PathParam("id") Long id){
        return tweetService.findTweet(id);
    }
    
    
    @DELETE
    @Path("removetweet/{id}")
    public boolean removeTweet(@PathParam("id") Long tweetId){
        return userService.removeTweet(tweetService.findTweet(tweetId));
    }

    @GET
    @Path("followuser/{user}/{followuser}")
    public boolean followUser(@PathParam("user") String username, @PathParam("followuser") String followingUser){
        return userService.followUser(userService.findUserByName(username), userService.findUserByName(followingUser));
    }

    @GET
    @Path("unfollowuser/{user}/{unfollowuser}")
    public boolean unfollowUser(@PathParam("user") String username, @PathParam("unfollowuser") String unfollowingUser){
        return userService.unfollowUser(userService.findUserByName(username), userService.findUserByName(unfollowingUser));
    }

    @GET
    @Path("getfollowers/{getfollowers}")
    public List<User> getFollowingUsers(@PathParam("getfollowers") String userName){
        return userService.getFollowingUsers(userService.findUserByName(userName));
    }

    @GET
    @Path("getfollowing/{getfollowing}")
    public List<User> getFollowers(@PathParam("getfollowing") String userName){
        return userService.getFollowers(userService.findUserByName(userName));
    }

    @GET
    @Path("gettweetsfromuser/{username}")
    public List<Tweet> getTweets(@PathParam("username") String username){
        return userService.getTweetsByUser(userService.findUserByName(username));
    }

    @POST
    @Path("liketweet/{user}/{tweettolike}")
    public boolean likeTweet(@PathParam("user") String userName, @PathParam("tweettolike") Long tweetId){
        return userService.likeTweet(userService.findUserByName(userName), tweetService.findTweet(tweetId));
    }
}
