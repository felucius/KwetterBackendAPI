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
import static org.eclipse.persistence.logging.SessionLog.JPA;

/**
 *
 * @author M
 */
@Stateless
@JPA
public class TweetDAOImplementation implements TweetDAO {

    /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext(unitName = "KwetterBackendPU")
    EntityManager em;

    public TweetDAOImplementation() {

    }

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
            return tweets;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows to retrieve all tweets from the Database, through the
     * Entity manager.
     *
     * @param tweet is the tweet that all the likes are going to be retrieved
     * from.
     * @return a list of likes
     */
    @Override
    public List<User> getLikes(Tweet tweet) {
        List<User> users = null;
        try {
            //likes = em.find(Tweet.class, user.getId()).getLikes();
            users = em.createNamedQuery("Tweet.getLikes").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
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
    public List<Tweet> getTweetsOfFollowingUsers(User follower) {
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

    /**
     * This method allows a user to be added as a mention to a single tweet.
     *
     * @param tweet to be posted with a mention of a user.
     * @param user to be mentioned in that single tweet.
     * @return the tweet with the user that is being mentioned in that single
     * tweet.
     */
    @Override
    public Tweet addMention(Tweet tweet, User user) {
        try {
            tweet.likeTweet(user);
            tweet.addMention(user);
            em.persist(tweet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return tweet;
    }

    /**
     * This method allows a tweet to be found by it's id.
     *
     * @param id is the id of the tweet that is going to be looked for.
     * @return a tweet object.
     */
    @Override
    public Tweet findTweet(Long id) {
        Tweet tweet = null;
        try {
            tweet = em.find(Tweet.class, id);
            return tweet;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows a tweet to be found by the content written in it.
     *
     * @param content is the string that is going to be looked for
     * @return the tweet object.
     */
    @Override
    public Tweet findTweetByContent(String message) {
        Tweet tweet = null;
        try {
            tweet = (Tweet) em.createQuery("SELECT t FROM Tweet t where t.message LIKE :message").
                    setParameter("message", "%"+message+"%").
                    getSingleResult();
            return tweet;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Tweet createTweet(Tweet tweet){
        Tweet createdTweet = null;
        try{
            em.persist(tweet);
            return createdTweet;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
