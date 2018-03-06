/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
 * going to the path 'tweet', through the 'api' path, configured at JAXRSconfig.
 *
 * Stateless beans is used here, because the request is simple and does not need
 * to be memorized by the server.
 *
 * TweetResource handles all the incoming requests. This class can be seen as
 * the controller class.
 *
 * @author M
 */
@Stateless
@Path("tweets")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResource {

    /**
     * Injecting the tweetservice ensures the loosely coupled architecture and
     * removes the use of the 'new' keyword.
     */
    @Inject
    TweetService tweetService;

    @Inject
    UserService userService;

    /**
     * POST request to send data.
     *
     * This method allows a user to be added as a mention.
     *
     * @param tweetId is the object that is going to be used for mentions.
     * @param userName is the object that is going to be mentioned on 'tweet'
     * object
     * @return the tweet object that has a mentioned user on it.
     */
    @POST
    @Path("addmention/{tweetId}/{username}")
    public boolean addMention(@PathParam("tweetId") Long tweetId, @PathParam("username") String userName) {
        return tweetService.addMention(findTweet(tweetId), userService.findUserByName(userName));
    }

    /**
     * GET request to retrieve data
     *
     * This method retrieves all likes from a single tweet.
     *
     * @param tweetId is the id of the tweet that is going to be searched on
     * @return a list of users that likes a certain tweet.
     */
    @GET
    @Path("gettweetlikes/{tweetId}")
    public List<User> getLikes(@PathParam("tweetId") Long tweetId) {
        return tweetService.getLikes(tweetService.findTweet(tweetId));
    }

    /**
     * GET request to find a tweet based on it's content
     *
     * @param content is the content to be searched on
     * @return a tweet object.
     */
    @GET
    @Path("findtweetbycontent/{content}")
    public List<Tweet> findTweetByContent(@PathParam("content") String content) {
        return tweetService.findTweetByContent(content);
    }

    /**
     * GET request to find a tweet based on it's id
     *
     * @param id is the id to be searched on
     * @return a tweet object.
     */
    @GET
    @Path("findtweet/{id}")
    public Tweet findTweet(@PathParam("id") Long id) {
        return tweetService.findTweet(id);
    }

    /**
     * GET request to retrieve data.
     *
     * This method allows to retrieve mentions from a single tweet.
     *
     * @param tweetId is the tweet where all mentions are retrieved from.
     * @return a list of users that are mentioned in the tweet.
     */
    @GET
    @Path("gettweetmentions/{tweetId}")
    public List<User> getMentions(@PathParam("tweetId") Long tweetId) {
        return tweetService.getMentions(tweetService.findTweet(tweetId));
    }

    /**
     * GET request to receive information.
     *
     * This method allows all tweets to be retrieved of the following users from
     * a single user.
     *
     * @param userName is the user that is going to be searched on to retrieve his
     * following users and their tweets.
     * @return a list of tweets that the user is following.
     */
    @GET
    @Path("gettweetsfollowing/{userName}")
    public List<Tweet> getTweetsOfFollowingUsers(@PathParam("userName") String userName) {
        return tweetService.getTweetsOfFollowingUsers(userService.findUserByName(userName));
    }

    /**
     * GET request to retrieve information.
     *
     * Retrieving all tweets from the tweetservice. The tweetservice get it's
     * data from the DAO (Data Access Object). In our case the database.
     *
     * @return a list of all tweets.
     */
    @GET
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }
}
