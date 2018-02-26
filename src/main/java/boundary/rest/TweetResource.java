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
import service.TweetService;

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
@Path("tweet")
@Stateless
public class TweetResource {

    /**
     * Injecting the tweetservice ensures the loosely coupled architecture and
     * removes the use of the 'new' keyword.
     */
    @Inject
    TweetService tweetService;
    
    public TweetResource(){
        tweetService = new TweetService();
    }

    /**
     * POST request to send data.
     * 
     * This method allows a user to be added as a mention.
     * 
     * @param tweet is the object that is going to be used for mentions.
     * @param user is the object that is going to be mentioned on 'tweet' object.
     * @return the tweet object that has a mentioned user on it.
     */
    @POST
    public Tweet addMention(Tweet tweet, User user){
        return tweetService.addMention(tweet, user);
    }

    /**
     * POST request to send data
     * 
     * This method allows a tweet to be liked by a single user.
     * 
     * @param tweet is the object that is going to be liked.
     * @param user is the object that is going to like the tweet.
     * @return true if the tweeted has successfully been liked or false
     * when this action could not have been succeeded.
     */
    @POST
    public boolean likeTweet(Tweet tweet, User user){
        return tweetService.likeTweet(tweet, user);
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
    public List<User> getLikes(Tweet tweet){
        return tweetService.getLikes(tweet);
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
    public List<User> getMentions(Tweet tweet){
        return tweetService.getMentions(tweet);
    }

    /**
     * GET request to receive information.
     * 
     * This method allows all tweets to be retrieved of the following users
     * from a single user.
     * 
     * @param follower
     * @return 
     */
    @GET
    public List<Tweet> getTweetsOfFollowingUsers(User follower){
        return tweetService.getTweetsOfFollowingUsers(follower);
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
