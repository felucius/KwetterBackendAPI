/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import domain.UserGroup;
import java.util.List;
import javax.ejb.Stateless;

/**
 * *A stateless DAO does not hold the data for any particular user. The data is
 * been given and after that it does not hold any information in cache.
 *
 * @author M
 */
public interface UserDAO {

    List<User> getAllUsers();
    
    User createUser(User user);
    
    //boolean removeUser(User user);
    
    User findUser(Integer id);
    
    User findUserByName(String name);
        
    boolean addTweet(User user, Tweet tweet, List<User> mentions);
    
    boolean removeTweet(Tweet tweet);
    
    boolean followUser(User user, User followingUser);
    
    boolean unfollowUser(User user, User unfollowingUser);
    
    List<User> getFollowingUsers(User user);
    
    List<User> getFollowers(User user);
    
    List<Tweet> getTweetsByUser(User user);
    
    boolean likeTweet(User user, Tweet tweetToLike);
    
    boolean promoteUser(User user);
        
    boolean demoteUser(User user);
    
    User updateUser(User user);
    
    boolean addUserGroup(UserGroup group);
    
    boolean addUserToGroup(User user, UserGroup group);
}
