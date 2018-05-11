/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import domain.TweetDTO;
import domain.User;
import domain.UserDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.TweetService;
import service.UserService;

/**
 * JWT authentication
 */
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.ws.rs.core.Response;
import util.TweetDomainToDto;
import util.UserDomainToDto;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public User login(User user) {
        return userService.findUserByName(user.getName());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("generateToken")
    public String generateServerToken() {
        String compactJws = Jwts.builder()
                .setSubject("Maxime")
                .signWith(SignatureAlgorithm.HS512, "MaximeKwetter")
                .compact();
        return compactJws;
    }

    @GET
    public Response getAllUsers() {
        List<UserDTO> dto = UserDomainToDto.USERS_TO_DTO(userService.getAllUsers());
        return Response.ok(dto).build();
        //return userService.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createuser")
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @GET
    @Path("finduser/{id}")
    public Response findUser(@PathParam("id") Integer id) {
        UserDTO dto = UserDomainToDto.USER_TO_DTO(userService.findUser(id));
        return Response.ok(dto).build();
        //return userService.findUser(id);
    }

    @GET
    @Path("finduserbyname/{name}")
    public Response findUserByName(@PathParam("name") String name) {
        UserDTO dto = UserDomainToDto.USER_TO_DTO(userService.findUserByName(name));
        return Response.ok(dto).build();
        //return userService.findUserByName(name);
    }

    /*
    @DELETE
    @Path("removeuser/{id}") // Not working on old existing users. Does work on new created users.
    public boolean removeUser(@PathParam("id") Integer id) {
        System.out.println("Rest layer value: " + id);
        return userService.removeUser(userService.findUser(id));
    }*/
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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addtweet/{id}")
    public boolean addTweet(@PathParam("id") Integer id, Tweet tweet) {
        User user = userService.findUser(id);
        return userService.addTweet(user, tweet, null);
    }

    @GET
    @Path("findtweet/{id}")
    public Tweet findTweet(@PathParam("id") Integer id) {
        return tweetService.findTweet(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("removetweet")//Check for foreign key on removal!!!!
    public boolean removeTweet(Tweet tweet) {
        return userService.removeTweet(tweet);//tweetService.findTweet(tweet.getId()));
    }

    @POST
    @Path("followuser/{user}/{followuser}")
    public boolean followUser(@PathParam("user") String username, @PathParam("followuser") String followingUser) {
        return userService.followUser(userService.findUserByName(username), userService.findUserByName(followingUser));
    }

    @POST
    @Path("unfollowuser/{user}/{unfollowuser}")
    public boolean unfollowUser(@PathParam("user") String username, @PathParam("unfollowuser") String unfollowingUser) {
        return userService.unfollowUser(userService.findUserByName(username), userService.findUserByName(unfollowingUser));
    }

    @GET
    @Path("getfollowers/{getfollowers}")
    public Response getFollowingUsers(@PathParam("getfollowers") String userName) {
        List<UserDTO> dto = UserDomainToDto.FOLLOWERS_TO_DTO(userService.getFollowingUsers(userService.findUserByName(userName)));
        return Response.ok(dto).build();
        //return userService.getFollowingUsers(userService.findUserByName(userName));
    }

    @GET
    @Path("getfollowing/{getfollowing}")
    public Response getFollowers(@PathParam("getfollowing") String userName) {
        List<UserDTO> dto = UserDomainToDto.FOLLOWINGS_TO_DTO(userService.getFollowers(userService.findUserByName(userName)));
        return Response.ok(dto).build();
        //return userService.getFollowers(userService.findUserByName(userName));
    }

    @GET
    @Path("gettweetsfromuser/{username}")
    public Response getTweets(@PathParam("username") String username) {
        List<TweetDTO> dto = TweetDomainToDto.TWEETS_TO_DTO(userService.getTweetsByUser(userService.findUserByName(username)));
        return Response.ok(dto).build();
        //return userService.getTweetsByUser(userService.findUserByName(username));
    }

    @POST
    @Path("liketweet/{user}/{tweettolike}")
    public boolean likeTweet(@PathParam("user") String userName, @PathParam("tweettolike") Integer tweetId) {
        return userService.likeTweet(userService.findUserByName(userName), tweetService.findTweet(tweetId));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("promote")
    public boolean promoteUser(User user) {
        return userService.promoteUser(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("demote")
    public boolean demoteUser(User user) {
        return userService.demoteUser(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updateuser")
    public User updateUserInformation(User user) {
        return userService.updateUser(user);
    }
}
