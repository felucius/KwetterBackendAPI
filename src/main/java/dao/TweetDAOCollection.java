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

/**
 *
 * @author M
 */
public class TweetDAOCollection implements TweetDAO {

    private List<Tweet> tweets = null;
    private Tweet tweet = null;

    public TweetDAOCollection() {
        this.tweets = new ArrayList();
        tweet = new Tweet();
    }

    @Override
    public List<User> getLikes(Tweet tweet) {
        if (tweet != null) {
            return tweet.getLikes();
        } else {
            return null;
        }
    }
    
    @Override
    public Tweet addMention(Tweet tweet, User user){
        if(tweet != null && user != null){
            tweet.addMention(user);
            return tweet;
        }else{
            return null;
        }
    }

    @Override
    public List<User> getMentions(Tweet tweet) {
        if (tweet != null) {
            return tweet.getMentions();
        } else {
            return null;
        }
    }

    @Override
    public List<Tweet> getAllTweets() {
        if (tweets != null) {
            return tweets;
        } else {
            return null;
        }
    }

    @Override
    public List<Tweet> getTweetsOfFollowingUsers(User following) {
        if (following != null) {
            return following.getTweets();
        } else {
            return null;
        }
    }

}
