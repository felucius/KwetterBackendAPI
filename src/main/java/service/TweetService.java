/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TweetDAO;
import domain.Tweet;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * The class 'TweetService' provides information about tweets to the user.
 * Data is collected from the tweetDAO and handed out to the tweetResource.
 * 
 * @author M
 */
@Stateless
public class TweetService {
    
    /**
     * By injecting TweetDAO, a constructor does not have to be defined with the
     * keyword 'new'. This provides a loosely coupled architecture. 
     */
    @Inject
    TweetDAO tweetDAO;
    
    /**
     * Get all tweets from the TweetDAO. TweetDAO makes a call to the database
     * and sends it data to the available tweetService.
     * 
     * @return a list of all Tweets.
     */
    public List<Tweet> getAllTweets(){
        return null;//return tweetDAO.getAllTweets();
    }
}
