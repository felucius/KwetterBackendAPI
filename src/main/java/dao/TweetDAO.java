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
 * A stateless DAO does not hold the data for any particular user. The data is
 * been given and after that it does not hold any information in cache.
 *
 * @author M
 */
@Stateless
public interface TweetDAO {

    List<Tweet> getAllTweets();
    
    List<Tweet> getLikes(User user);
    
    boolean postTweet(Tweet tweet);
    
    boolean postTweetMention(Tweet tweet, List<User> user);
    
    List<User> getMentions(Tweet tweet);
    
    List<Tweet> getTweetsOfFollowers(User follower);
    
    
}
