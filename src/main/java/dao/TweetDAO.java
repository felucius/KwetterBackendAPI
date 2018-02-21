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
        return em.createNamedQuery("Tweet.getAllTweets").getResultList();
    }
    
    /**
     * 
     * @param tweet 
     */
    public void postTweet(Tweet tweet){
        em.persist(tweet);
    }
    
    /**
     * 
     * @param tweet
     * @param user 
     */
    public void postTweetMention(Tweet tweet, List<User> user){
        em.persist(tweet);
    }
    
    //TODO: CHECK IF LIST IS NEEDED OR JUST AN ID.
    /**
     * 
     * @param mentions
     * @return 
     */
    public List<User> getMentions(List<User> mentions){
        return em.createNamedQuery("Tweet.getMentions").getResultList();
    }
    
    /**
     * 
     * @param tweets
     * @param followers
     * @return 
     */
    public List<Tweet> getTweetsOfFollowers(List<Tweet> tweets, List<User> followers){
        return em.createNamedQuery("Tweet.getTweets").getResultList();
    }
}
