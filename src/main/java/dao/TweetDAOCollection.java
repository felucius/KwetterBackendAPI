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
public class TweetDAOCollection implements TweetDAO{

    private List<Tweet> tweets = null;
    
    public TweetDAOCollection(){
        this.tweets = new ArrayList();
    }

    @Override
    public List<User> getLikes(User user) {
        if(user != null && tweets != null){
            return tweets.get(user.getId().intValue()).getLikes();
        }else{
            return null;
        }
    }

    @Override
    public List<User> getMentions(Tweet tweet) {
        if(tweet != null){
            return tweet.getMentions();
        }else{
            return null;
        }
    }
    
    @Override
    public List<Tweet> getAllTweets(){
        if(tweets != null){
            return tweets;
        }else{
            return null;
        }
    }

    @Override
    public List<Tweet> getTweetsOfFollowingUsers(User following) {
        if(following != null){
           return following.getTweets();
        }else{
            return null;
        }
    }
    
}
