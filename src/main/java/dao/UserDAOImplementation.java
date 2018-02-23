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
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author M
 */
@Stateless
public class UserDAOImplementation {
        /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext(unitName = "KwetterBackendPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    /**
     * Requesting all users through the Entity manager. The entity manager makes
     * a call to the database with the Persistence Context.
     *
     * @return all the users.
     */
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = em.createNamedQuery("User.getAllUsers").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }

    /**
     * This method allows a new user to be created and be stored into the
     * database
     *
     * @param user is the object that is going to be persisted to the database.
     */
    public User createUser(User user) {
        
        try {
            em.persist(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * This method allows a user that exists in the database, to be removed
     *
     * @param id is the id of the user object that is going to be removed from
     * the database.
     */
    public boolean removeUser(User user) {
        try {
            em.remove(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method allows a user to be found from the User entity with it's
     * given id.
     *
     * @param id is the parameter that is going to be needed to find a specific
     * user from the database
     * @return the value as a User object.
     */
    public User findUser(Long id) {
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
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
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method allows to remove a single tweet from the user.
     *
     * @param tweet to be removed from the user.
     * @return true if the removal has been successful or false when the tweet
     * could not have been removed.
     */
    public boolean removeTweet(Tweet tweet) {
        try {
            em.remove(tweet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
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
    public boolean followUser(User user, User followingUser) {
        user.followUser(followingUser);
        try {
            em.merge(user);
            em.merge(followingUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
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
    public boolean unfollowUser(User user, User unfollowingUser) {
        user.removeFollower(unfollowingUser);
        try {
            em.merge(user);
            em.merge(unfollowingUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Retrieving all following users from a specific user.
     *
     * @param user that follows other users.
     * @return a list of users that the 'user' object follows.
     */
    public List<User> getFollowingUsers(User user) {
        List<User> users = null;
        try {
            users = em.find(User.class, user.getId()).getFollowing();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }

    /**
     * Retrieving all followers from a specific user.
     *
     * @param user that is going to retrieve all his following users.
     * @return a list of followers from his following base.
     */
    public List<User> getFollowers(User user) {
        List<User> users = null;
        try {
            users = em.find(User.class, user.getId()).getFollowers();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return users;
    }

    public List<Tweet> getTweets() {
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
     * This method allows a single user to like a single tweet.
     *
     * @param user to like a tweet.
     * @param tweetToLike is the tweet to be liked by a single user
     * @return true if the user has liked the tweet or false when the user could
     * not like the specific tweet.
     */
    public boolean likeTweet(User user, Tweet tweetToLike) {
        user.likeTweet(tweetToLike);
        try {
            em.merge(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
