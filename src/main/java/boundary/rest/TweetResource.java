/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Tweet;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import service.TweetService;

/**
 * Api call where the user makes a request to the backend to receive data by going 
 * to the path 'tweet', through the 'api' path, configured at JAXRSconfig.
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
     * Injecting the tweetservice ensures the loosely coupled architecture and removes
     * the use of the 'new' keyword.
     */
    @Inject
    TweetService tweetService;
    
    /**
     * Retrieving all tweets from the tweetservice. The tweetservice get it's data
     * from the DAO (Data Access Object). In our case the database.
     * 
     * @return a list of all tweets.
     */
    @GET
    public List<Tweet> getAllTweets(){
        return tweetService.getAllTweets();
    }
}
