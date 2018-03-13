/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
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
     * Creates a new user object
     *
     * @param user is the object that is going to be created.
     * @return the new created user object.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createuser")
    public User createUser(User user) {
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
    public User findUserByName(@PathParam("name") String name) {
        return userService.findUserByName(name);
    }

    /**
     * DELETE request to the userService. When this method is called, the user
     * id is given so the account with that specific id can be removed.
     *
     * @param id of the user object or account, that is going to be removed.
     */
    /*
    @DELETE
    @Path("removeuser/{id}") // Not working on old existing users. Does work on new created users.
    public boolean removeUser(@PathParam("id") Long id) {
        System.out.println("Rest layer value: " + id);
        return userService.removeUser(userService.findUser(id));
    }*/
    
    /**
     * Allows a tweet to be added from a user with content, tags and mentions.
     * 
     * @param userName user to add the tweet.
     * @param tweetContent content of tweet.
     * @param tag short messages.
     * @param userMention user to mentioned on a tweet.
     * @return true if the creation of a tweet has been successful or false
     * when the action could not have taken place.
     */
    /*
    @POST
    @Path("addtweet/{username}/{tweetcontent}/{tags}/{mention}")
    public boolean addTweet(@PathParam("username") String userName,
            @PathParam("tweetcontent") String tweetContent,
            @PathParam("tags") String tag, @PathParam("mention") String userMention) {

        List<User> mentions = new ArrayList();
        List<String> tags = new ArrayList();

        tags.add(tag);
        mentions.add(userService.findUserByName(userMention));

        Tweet tweet = new Tweet(tweetContent, tags, userService.findUserByName(userName));
        return userService.addTweet(userService.findUserByName(userName), tweet, mentions);
    }*/
    
    /**
     * 
     * 
     * @param id of the user to be used to post a message
     * @param tweet object to be added from the user.
     * @return true if the action has been successful or false when the
     * action could not succeed.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addtweet/{id}")
    public boolean addTweet(@PathParam("id") Long id, Tweet tweet){
        User user = userService.findUser(id);
        return userService.addTweet(user, tweet, null);
    }

    /**
     * Find a single tweet by it's id.
     *
     * @param id is used to search the tweet.
     * @return a tweet object matching the given id.
     */
    @GET
    @Path("findtweet/{id}")
    public Tweet findTweet(@PathParam("id") Long id) {
        return tweetService.findTweet(id);
    }

    /**
     * Removal of a single tweet based on it's id.
     *
     * @param tweet is used to find and remove a certain tweet.
     * @return true if the action has been successful or false when the action
     * could not have taken place.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("removetweet")//Check for foreign key on removal!!!!
    public boolean removeTweet(Tweet tweet) {
        return userService.removeTweet(tweet);//tweetService.findTweet(tweet.getId()));
    }

    /**
     * Follow a single user by it's username.
     *
     * @param username is the user who want's to follow another user.
     * @param followingUser is the user who is going to be followed by the
     * 'username' object.
     * @return true if the following has been successful or false when the
     * action could not have been succeeded.
     */
    @POST
    @Path("followuser/{user}/{followuser}")
    public boolean followUser(@PathParam("user") String username, @PathParam("followuser") String followingUser) {
        return userService.followUser(userService.findUserByName(username), userService.findUserByName(followingUser));
    }

    /**
     * Un follow a user by it's username.
     *
     * @param username is the user who is going to un follow a user.
     * @param unfollowingUser is the user who is going to be un followed by the
     * 'username' user.
     * @return true if the user successfully un followed another user or false
     * when this action could not have taken place.
     */
    @POST
    @Path("unfollowuser/{user}/{unfollowuser}")
    public boolean unfollowUser(@PathParam("user") String username, @PathParam("unfollowuser") String unfollowingUser) {
        return userService.unfollowUser(userService.findUserByName(username), userService.findUserByName(unfollowingUser));
    }

    /**
     * Get the followers base from a single user.
     *
     * @param userName is the object where followers are going to be retrieved
     * from.
     * @return a list of followers from a single user object.
     */
    @GET
    @Path("getfollowers/{getfollowers}")
    public List<User> getFollowingUsers(@PathParam("getfollowers") String userName) {
        return userService.getFollowingUsers(userService.findUserByName(userName));
    }

    /**
     * Get the following users from a single user object.
     *
     * @param userName is the user that is going to searched on.
     * @return a list of users that follow the 'userName'
     */
    @GET
    @Path("getfollowing/{getfollowing}")
    public List<User> getFollowers(@PathParam("getfollowing") String userName) {
        return userService.getFollowers(userService.findUserByName(userName));
    }

    /**
     * Get tweets from a single user object by it's username.
     *
     * @param username is the user that is going to retrieve it's tweets.
     * @return a list of tweets from a single user.
     */
    @GET
    @Path("gettweetsfromuser/{username}")
    public List<Tweet> getTweets(@PathParam("username") String username) {
        return userService.getTweetsByUser(userService.findUserByName(username));
    }

    /**
     * Method to like a single tweet by a single user.
     *
     * @param userName is the user object that is going to like a single tweet.
     * @param tweetId is the tweet object that is going to be liked.
     * @return true if the action could have been succeeded or false when it did
     * not succeed.
     */
    @POST
    @Path("liketweet/{user}/{tweettolike}")
    public boolean likeTweet(@PathParam("user") String userName, @PathParam("tweettolike") Long tweetId) {
        return userService.likeTweet(userService.findUserByName(userName), tweetService.findTweet(tweetId));
    }

    /**
     * Promoting a user to a new userRole
     *
     * @param user is the object that is going to be promoted.
     * @return true if the action could have been successful or false when the
     * action could not have taken place.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("promote")
    public boolean promoteUser(User user) {
        return userService.promoteUser(user);
    }

    /**
     * Demoting a user to a new userRole
     *
     * @param user is the object that is going to be demoted.
     * @return true if the action could have been successful or false when the
     * action could not have taken place.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("demote")
    public boolean demoteUser(User user) {
        return userService.demoteUser(user);
    }

    /**
     * Update a user personal information
     *
     * @param user is the object that is going to be modified.
     * @return return a user with modified information.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateuser")
    public User updateUserInformation(User user) {
        return userService.updateUser(user);
    }

}
