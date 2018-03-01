/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Tweet;
import domain.TweetDTO;
import domain.User;
import domain.UserDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
        //User getUser = username.getUser();
        //return userService.createUser(getUser);
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
        //User getUser = removeuser.getUser();
        //return userService.removeUser(getUser);
        return userService.removeUser(userService.findUser(id));
    }
    /*
    @GET
    @Path("user/tweet/mentions")
    public boolean addTweet(@PathParam("user") UserDTO user, @PathParam("tweet") TweetDTO tweet, 
            @PathParam("mentions") List<UserDTO> mentions){
        User getUser = user.getUser();
        Tweet getTweet = tweet.getTweet();
        //List<User> getMentions = (User)mentions;
        return userService.addTweet(getUser, getTweet, mentions);
    }*/
    
    @GET
    @Path("findtweet/{id}")
    public Tweet findTweet(@PathParam("id") Long id){
        return tweetService.findTweet(id);
    }
    
    
    @DELETE
    @Path("removetweet/{id}")
    public boolean removeTweet(@PathParam("id") Long tweetId){
        //Tweet getTweet = tweet.getTweet();
        //return userService.removeTweet(getTweet);
        return userService.removeTweet(tweetService.findTweet(tweetId));
    }

    @GET
    @Path("followuser/{user}/{followuser}")
    public boolean followUser(@PathParam("user") String username, @PathParam("followuser") String followingUser){
        //User getUser = user.getUser();
        //User getFollowingUser = followingUser.getUser();
        //return userService.followUser(getUser, getFollowingUser);
        return userService.followUser(userService.findUserByName(username), userService.findUserByName(followingUser));
    }

    @GET
    @Path("user/unfollowuser")
    public boolean unfollowUser(@PathParam("user") UserDTO user, @PathParam("unfollowuser") UserDTO unfollowingUser){
        User getUser = user.getUser();
        User getUnfollowingUser = unfollowingUser.getUser();
        return userService.unfollowUser(getUser, getUnfollowingUser);
    }

    @GET
    @Path("getfollowing/{getfollowing}")
    public List<User> getFollowingUsers(@PathParam("getfollowing") UserDTO user){
        User getUser = user.getUser();
        return userService.getFollowingUsers(getUser);
    }

    @GET
    @Path("getfollowers/{getfollowers}")
    public List<User> getFollowers(@PathParam("getfollowers") UserDTO user){
        User getUser = user.getUser();
        return userService.getFollowers(getUser);
    }

    @GET
    @Path("gettweets/{gettweets}")
    public List<Tweet> getTweets(@PathParam("gettweets") UserDTO user){
        User getUSer = user.getUser();
        return userService.getTweets(getUSer);
    }

    @POST
    @Path("user/tweettolike")
    public boolean likeTweet(@PathParam("user") UserDTO user, @PathParam("tweettolike") TweetDTO tweetToLike){
        User getUser = user.getUser();
        Tweet getTweet = tweetToLike.getTweet();
        return userService.likeTweet(getUser, getTweet);
    }
}
