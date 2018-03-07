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

/**
 * A stateless DAO does not hold the data for any particular user. The data is
 * been given and after that it does not hold any information in cache.
 *
 * @author M
 */
public interface TweetDAO {
    
    boolean addMention(Tweet tweet, User user);
    
    List<User> getLikes(Tweet tweet);
                
    List<User> getMentions(Tweet tweetMentions);
    
    List<Tweet> getTweetsOfFollowingUsers(User followerId);
    
    List<Tweet> getAllTweets();
    
    Tweet findTweet(Long id);
    
    List<Tweet> findTweetByContent(String content);
    
    Tweet createTweet(Tweet tweet);
    
    Tweet updateTweet(Tweet tweet);
}
