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
     * @param tweet is the object that is going to be used for mentions.
     * @param username is the object that is going to be mentioned on 'tweet'
     * object
     * @return the tweet object that has a mentioned user on it.
     */
    @POST
    @Path("addmention/{tweetmention}/{username}")
    public Tweet addMention(@PathParam("tweetmention") Long tweetId, @PathParam("username") Long userId) {
        //Tweet getTweet = tweet.getTweet();
        //User getUser = username.getUser();
        //return tweetService.addMention(getTweet, getUser);
        return tweetService.addMention(tweetService.findTweet(tweetId), userService.findUser(userId));
    }

    /**
     * GET request to retrieve data
     *
     * This method retrieves all likes from a single tweet.
     *
     * @param tweet is the object where all the likes are retrieved from.
     * @return a list of users that likes a certain tweet.
     */
    @GET
    @Path("gettweetlikes")
    public List<User> getLikes(@PathParam("gettweetlikes") TweetDTO tweet) {
        Tweet getTweet = tweet.getTweet();
        return tweetService.getLikes(getTweet);
    }

    /**
     * GET request to find a tweet based on it's content
     *
     * @param content is the content to be searched on
     * @return a tweet object.
     */
    @GET
    @Path("findtweetbycontent/{content}")
    public Tweet findTweet(@PathParam("content") String content) {
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
     * @param tweet is the tweet where all mentions are retrieved from.
     * @return a list of users that are mentioned in the tweet.
     */
    @GET
    @Path("gettweetmentions")
    public List<User> getMentions(@PathParam("gettweetmentions") TweetDTO tweet) {
        Tweet getTweet = tweet.getTweet();
        return tweetService.getMentions(getTweet);
    }

    /**
     * GET request to receive information.
     *
     * This method allows all tweets to be retrieved of the following users from
     * a single user.
     *
     * @param follower
     * @return
     */
    @GET
    @Path("gettweetsfollowing")
    public List<Tweet> getTweetsOfFollowingUsers(@PathParam("gettweetsfollowing") UserDTO follower) {
        User getUser = follower.getUser();
        return tweetService.getTweetsOfFollowingUsers(getUser);
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
