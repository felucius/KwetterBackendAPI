/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author M
 */
@Stateless
public class UserDAOCollection implements UserDAO {

    private List<User> users = null;

    public UserDAOCollection() {
        users = new ArrayList();
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User createUser(User user) {
        if (!users.contains(user)) {
            this.users.add(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUser(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean addTweet(User user, Tweet tweet, List<User> mentions) {
        if (tweet != null) {
            user.addTweet(tweet, mentions);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeTweet(Tweet tweet) {
        if (tweet != null) {
            users.get(0).removeTweet(tweet);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean followUser(User user, User followingUser) {
        if (user != null && followingUser != null) {
            user.followUser(followingUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unfollowUser(User user, User unfollowingUser) {
        if (user != null && unfollowingUser != null) {
            user.unfollowUser(unfollowingUser);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getFollowingUsers(User user) {
        if (user != null) {
            return user.getFollowing();
        } else {
            return null;
        }
    }

    @Override
    public List<User> getFollowers(User user) {
        if (user != null) {
            return user.getFollowers();
        } else {
            return null;
        }
    }

    @Override
    public List<Tweet> getTweetsByUser(User user) {
        if (user != null) {
            return user.getTweets();
        } else {
            return null;
        }
    }

    @Override
    public boolean likeTweet(User user, Tweet tweetToLike) {
        if (user != null && tweetToLike != null) {
            user.likeTweet(tweetToLike);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }
}
