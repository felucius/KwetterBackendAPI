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
@JPA
public class UserDAOImplementation implements UserDAO {

    /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext(unitName = "KwetterBackendPU")
    private EntityManager em;

    public UserDAOImplementation() {

    }

    /**
     * Requesting all users through the Entity manager. The entity manager makes
     * a call to the database with the Persistence Context.
     *
     * @return all the users.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = em.createNamedQuery("User.getAllUsers").getResultList();
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows a new user to be created and be stored into the
     * database
     *
     * @param user is the object that is going to be persisted to the database.
     * @return a user if the object has been created successfully.
     */
    @Override
    public User createUser(User user) {
        try {
            em.persist(user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows a user that exists in the database, to be removed
     *
     * @param user is the object that is going to be removed.
     * @return a true statement if the user has successfully been removed or
     * false when the action could not be performed.
     */
    @Override
    public boolean removeUser(User user) {
        try {
            System.out.println("UserDAOImpl: " + user.getId());
            user = em.find(User.class, user.getId());
            em.remove(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows a user to be found from the User entity with it's
     * given id.
     *
     * @param id is the parameter that is going to be needed to find a specific
     * user from the database
     * @return the value as a User object.
     */
    @Override
    public User findUser(Long id) {
        User user = null;
        try {
            user = em.find(User.class, id);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows a tweet to be added from a specific user with mentions
     *
     * @param tweet to be created by a single user.
     * @param mentions are users that are mentioned in this new tweet.
     * @return true if the tweet has been successfully added or false when the
     * addition has not been successful.
     */
    public boolean addTweet(Tweet tweet, List<User> mentions) {
        try {
            em.persist(tweet);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows to remove a single tweet from the user.
     *
     * @param tweet to be removed from the user.
     * @return true if the removal has been successful or false when the tweet
     * could not have been removed.
     */
    @Override
    public boolean removeTweet(Tweet tweet) {
        try {
            em.remove(tweet);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows a user to follow another user.
     *
     * @param user that is going to follow another user.
     * @param followingUser is the user the is going to be followed by the
     * 'user' object or to say, the first user.
     * @return true if the user successfully followed another user or false when
     * the user could not follow another one.
     */
    @Override
    public boolean followUser(User user, User followingUser) {
        user.followUser(followingUser);
        try {
            em.merge(user);
            em.merge(followingUser);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows a user to unfollow an existent user from their
     * followers base
     *
     * @param user that is going to unfollow another user from his follower list
     * @param unfollowingUser is the user that is going to be unfollowed by the
     * 'user' object or so the say the first user.
     * @return true if the 'user' successfully unfollowed a user or false when
     * the user could not been unfollowed.
     */
    @Override
    public boolean unfollowUser(User user, User unfollowingUser) {
        user.removeFollower(unfollowingUser);
        try {
            em.merge(user);
            em.merge(unfollowingUser);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieving all following users from a specific user.
     *
     * @param user that follows other users.
     * @return a list of users that the 'user' object follows.
     */
    @Override
    public List<User> getFollowingUsers(User user) {
        List<User> users = null;
        try {
            users = em.find(User.class, user.getId()).getFollowing();
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieving all followers from a specific user.
     *
     * @param user that is going to retrieve all his following users.
     * @return a list of followers from his following base.
     */
    @Override
    public List<User> getFollowers(User user) {
        List<User> users = null;
        try {
            users = em.find(User.class, user.getId()).getFollowers();
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * This method allows to get all tweets from no specific user. It collects
     * all tweet data. This is necessary for an administrator account.
     *
     * @return all tweets from all users.
     */
    public List<Tweet> getTweets() {
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
     * This method allows a single user to like a single tweet.
     *
     * @param user to like a tweet.
     * @param tweetToLike is the tweet to be liked by a single user
     * @return true if the user has liked the tweet or false when the user could
     * not like the specific tweet.
     */
    @Override
    public boolean likeTweet(User user, Tweet tweetToLike) {
        user.likeTweet(tweetToLike);
        try {
            em.merge(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows a tweet to be added to a certain user with possible
     * mentions.
     *
     * @param user that adds the tweet.
     * @param tweet is the tweet that is going to be added from one single user.
     * @param mentions are other users who are mentioned in this post/tweet.
     * @return
     */
    @Override
    public boolean addTweet(User user, Tweet tweet, List<User> mentions) {
        user.addTweet(tweet, mentions);
        try {
            em.persist(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method allows all tweets to be returned from a single user.
     *
     * @param user is the object where all the tweets are going to be collected
     * from.
     * @return all the tweets from a single user.
     */
    @Override
    public List<Tweet> getTweets(User user) {
        List<Tweet> tweets = null;
        try {
            tweets = user.getTweets();
            return tweets;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
