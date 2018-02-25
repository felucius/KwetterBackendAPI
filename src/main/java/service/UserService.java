/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAOCollection;
import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * The class 'UserService' provides data of users, collected by the userDAO.
 *
 * @author M
 */
@Stateless
public class UserService {

    /**
     * Inject the userDAO. By doing this the constructor does not have to be
     * initialized with the 'new' keyword. This provides a loosely coupled
     * architecture.
     */
    private UserDAOCollection userDAO;
    
    @Inject
    public UserService(){
        userDAO = new UserDAOCollection();
    }

    /**
     * Get all users from the userDAO. UserDao makes a call to the database and
     * sends the data to this method.
     *
     * @return a list of all available users.
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * This method creates a new user and persists the object to the database
     *
     * @param user is the object that is going to be created and persisted to
     * the database.
     * @return the user object if a creation of a new user has been successful.
     */
    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    /**
     * This method find the user by it's given id. From there on this
     * information is passed on to the DAO and to the entity manager to be
     * removed from the database
     *
     * @param user is the object that is going to be removed.
     * @return true if the user has succesfully been removed or false when the
     * action could not have been completed.
     */
    public boolean removeUser(User user) {
        return userDAO.removeUser(user);
    }

    /**
     * This method finds the user by it's user id. This id is passed on from the
     * UserResource to the UserService.
     *
     * @param id of the user to be searched.
     * @return the user object.
     */
    public User findUser(Long id) {
        return userDAO.findUser(id);
    }

    /**
     * This method allows a tweet to be added with a user, the actual tweet and
     * a list of users that are mentioned in the post.
     *
     * @param user is the user that wrote the tweet.
     * @param tweet is the content of the tweet, written by the user object.
     * @param mentions are the users that are mentioned in this tweet/post.
     * @return return true if the addition of the tweet has been successful or
     * false when this action could not have been completed.
     */
    public boolean addTweet(User user, Tweet tweet, List<User> mentions) {
        return userDAO.addTweet(user, tweet, mentions);
    }

    /**
     * This method allows a single tweet to be removed from a single user.
     *
     * @param tweet that is going to be removed.
     * @return true is the tweet has successfully been removed or false when the
     * action could not have been completed.
     */
    public boolean removeTweet(Tweet tweet) {
        return userDAO.removeTweet(tweet);
    }

    /**
     * This method allows a user to follow another user.
     *
     * @param user is the user that is going to follow another user.
     * @param followingUser is the users that the 'user' object is going to
     * follow.
     * @return true if the 'user' object successfully followed the
     * 'followingUser' object or false when this action did not succeed.
     */
    public boolean followUser(User user, User followingUser) {
        return userDAO.followUser(user, followingUser);
    }

    /**
     * This method allows a user to un follow another user.
     *
     * @param user object that is going to un follow another single user.
     * @param unfollowingUser is the user that is going to be un followed by the
     * 'user' object.
     * @return true if the 'user' successfully un followed the 'unfollowingUser'
     * object or false when this action could not succeed.
     */
    public boolean unfollowUser(User user, User unfollowingUser) {
        return userDAO.unfollowUser(user, unfollowingUser);
    }

    /**
     * This method retrieves all the users that one user follows.
     *
     * @param user object that is going to retrieve all following users.
     * @return a list of users that the 'user' object follows.
     */
    public List<User> getFollowingUsers(User user) {
        return userDAO.getFollowingUsers(user);
    }

    /**
     * This method retrieves all the following users from a single user.
     *
     * @param user that is going to retrieve all it's followers.
     * @return a list of users that follow the 'user' object.
     */
    public List<User> getFollowers(User user) {
        return userDAO.getFollowers(user);
    }

    /**
     * This method retrieves all tweets from a single user.
     *
     * @param user object that all tweets are going to be retrieved from.
     * @return a list of tweets from that single user.
     */
    public List<Tweet> getTweets(User user) {
        return userDAO.getTweets(user);
    }

    /**
     * This method allows a tweet to be liked by a single user.
     *
     * @param user is the user that likes a single tweet.
     * @param tweetToLike is the tweet that is going to be liked by the 'user'
     * object.
     * @return true if the user successfully liked the single tweet or false
     * when this action could not have been succeeded.
     */
    public boolean likeTweet(User user, Tweet tweetToLike) {
        return userDAO.likeTweet(user, tweetToLike);
    }
}
