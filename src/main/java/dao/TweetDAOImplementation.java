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
 *
 * @author M
 */
@Stateless
public class TweetDAOImplementation implements TweetDAO {

    /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext
    EntityManager em;

    /**
     * Requesting all tweets through the Entity manager. The entity manager
     * makes a call to the database with the PersistenceContext.
     *
     * @return a list of tweets.
     */
    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = null;
        try {
            tweets = em.createNamedQuery("Tweet.getAllTweets").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return tweets;
    }

    /**
     * This method allows to retrieve all tweets from the Database, through the
     * Entity manager.
     *
     * @param user is the user to retrieve all the likes from.
     * @return a list of likes
     */
    @Override
    public List<Tweet> getLikes(User user) {
        List<Tweet> likes = null;
        try {
            //likes = em.find(Tweet.class, user.getId()).getLikes();
            likes = em.createNamedQuery("Tweet.getLikes").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return likes;
    }

    /**
     * Method to post a tweet.
     *
     * @param tweet to be posted.
     * @return 
     */
    @Override
    public boolean postTweet(Tweet tweet) {
        try {
            em.persist(tweet);
        } catch (Exception ex) {
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
     * @return 
     */
    @Override
    public boolean postTweetMention(Tweet tweet, List<User> user) {
        try {
            em.persist(tweet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method allows all mentions from a single tweet to be retrieved.
     *
     * @param tweet getting mentions from a certain tweet
     * @return list of users that belong to the mention list of that single
     * tweet.
     */
    @Override
    public List<User> getMentions(Tweet tweet) {
        List<User> users = null;
        tweet.getMentions();
        try {
            //users = em.createNamedQuery("Tweet.getMentions").getResultList();
            users = tweet.getMentions();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }

    /**
     * This method retrieves all the tweets of the user's follower base.
     *
     * @param follower object is the user where all the tweets are going to be
     * retrieved.
     * @return a list of tweets from the users follower base
     */
    @Override
    public List<Tweet> getTweetsOfFollowers(User follower) {
        List<User> followers = null;
        List<Tweet> tweets = null;
        try {
            tweets = em.find(User.class, follower.getId()).getTweets();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return tweets;
    }
}
