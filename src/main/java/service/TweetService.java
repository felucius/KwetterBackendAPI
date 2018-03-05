/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.JPA;
import dao.TweetDAO;
import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 * The class 'TweetService' provides information about tweets to the user. Data
 * is collected from the tweetDAO and handed out to the tweetResource.
 *
 * @author M
 */
@Stateless
public class TweetService {

    /**
     * By injecting TweetDAO, a constructor does not have to be defined with the
     * keyword 'new'. This provides a loosely coupled architecture.
     */
    @Inject @JPA
    TweetDAO tweetDAO;
    
    /**
     * This method allows tweet to get a mention by a single user.
     *
     * @param tweet object that is receiving a mention from the 'user' object.
     * @param user object that is going to be mentioned in a single tweet.
     * @return the tweet with the user that is mentioned in it.
     */
    public boolean addMention(Tweet tweet, User user) {
        return tweetDAO.addMention(tweet, user);
    }

    /**
     * This method retrieved all likes from a single tweet.
     *
     * @param tweet object that all likes are going to be retrieved from.
     * @return a list of users that liked the single tweet.
     */
    public List<User> getLikes(Tweet tweet) {
        return tweetDAO.getLikes(tweet);
    }
    
    public List<User> getLikesFromTweet(Long tweetId){
        return tweetDAO.getLikesFromTweet(tweetId);
    }

    /**
     * This method retrieves all mentions from a single tweet.
     *
     * @param tweet object where all mentions are going to be retrieved from.
     * @return a list of users that are mentioned on that single tweet.
     */
    public List<User> getMentions(Tweet tweet) {
        return tweetDAO.getMentions(tweet);
    }

    /**
     * This method retrieved all tweets from different users that one single
     * user follows.
     *
     * @param follower is the object where all tweets are going to be collected
     * from
     * @return a list of tweets that the 'follower' object follows.
     */
    public List<Tweet> getTweetsOfFollowingUsers(User follower) {
        return tweetDAO.getTweetsOfFollowingUsers(follower);
    }

    /**
     * This method retrieves all tweets from all users.
     *
     * @return a list of all Tweets from different users.
     */
    public List<Tweet> getAllTweets() {
        return tweetDAO.getAllTweets();
    }
    
    public Tweet findTweet(Long id){
        return tweetDAO.findTweet(id);
    }
    
    public Tweet findTweetByContent(String content){
        return tweetDAO.findTweetByContent(content);
    }
    
    public Tweet createTweet(Tweet tweet){
        return tweetDAO.createTweet(tweet);
    }
}
