/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *A stateless DAO does not hold the data for any particular user. The data is 
 * been given and after that it does not hold any information in cache.
 * 
 * @author M
 */
@Stateless
public class TweetDAO {
    /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Requesting all tweets through the Entity manager. The entity manager makes a
     * call to the database with the PersistenceContext.
     * 
     * @return a list of tweets.
     */
    public List<Tweet> getAllTweets(){
        List<Tweet> tweets = null;
        try{
            tweets = em.createNamedQuery("Tweet.getAllTweets").getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return tweets;
    }
    
    /**
     * Method to post a tweet.
     * 
     * @param tweet to be posted.
     */
    public boolean postTweet(Tweet tweet){
        try{
            em.persist(tweet);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * This method allows a tweet to be posted from a single user.
     * 
     * @param tweet to be posted by a single user.
     * @param user that is posting a single tweet.
     */
    public boolean postTweetMention(Tweet tweet, List<User> user){
        try {
            em.persist(tweet);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    //TODO: CHECK IF LIST IS NEEDED OR JUST AN ID.
    /**
     * 
     * @param mentions
     * @return 
     */
    public List<User> getMentions(List<User> mentions){
        List<User> users = null;
        try{
            users = em.createNamedQuery("Tweet.getMentions").getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return users;
    }
    
    /**
     * This method retrieves all the tweets of the user's followe base.
     * 
     * @param tweets to be received from a user's followers base.
     * @param followers are retrieved from the user
     * @return 
     */
    public List<Tweet> getTweetsOfFollowers(List<Tweet> tweets, List<User> followers){
        return em.createNamedQuery("Tweet.getTweets").getResultList();
    }
}
