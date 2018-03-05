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
public class TweetDAOCollection implements TweetDAO {

    private List<Tweet> tweets = null;
    private Tweet tweet = null;

    public TweetDAOCollection() {
        this.tweets = new ArrayList();
        tweet = new Tweet();
    }

    /**
     * This method retrieves all likes from a single tweet.
     *
     * @param tweet to retrieve all likes from
     * @return a list of users that liked that single tweet.
     */
    @Override
    public List<User> getLikes(Tweet tweet) {
        if (tweet != null) {
            return tweet.getLikes();
        } else {
            return null;
        }
    }

    /**
     * This method allows a mention to be added to a tweet, from a single user.
     *
     * @param tweet is the tweet that is going to be added to a certain mention.
     * @param user is the user that is going to be mentioned in that single
     * tweet.
     * @return the tweet object that is added
     */
    @Override
    public boolean addMention(Tweet tweet, User user) {
        if (tweet != null && user != null) {
            tweet.addMention(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method retrieves all mentions from a single tweet.
     *
     * @param tweet is the tweet where all mentions are going to be retrieved.
     * @return a list users that are being mentioned in that specific tweet.
     */
    @Override
    public List<User> getMentions(Tweet tweet) {
        if (tweet != null) {
            return tweet.getMentions();
        } else {
            return null;
        }
    }

    /**
     * This method retrieves all tweets in general.
     *
     * @return all tweets ever created by all users.
     */
    @Override
    public List<Tweet> getAllTweets() {
        if (tweets != null) {
            return tweets;
        } else {
            return null;
        }
    }

    /**
     * This method retrieves all tweets of a single user.
     *
     * @param following is the user that the tweets are going to be collected
     * from
     * @return a list of tweets from the specific following user.
     */
    @Override
    public List<Tweet> getTweetsOfFollowingUsers(User following) {
        if (following != null) {
            return following.getTweets();
        } else {
            return null;
        }
    }

    /**
     * This method allows a tweet to be searched on it's id.
     *
     * @param id is the id that the tweet object is going to be looked for.
     * @return the tweet object.
     */
    @Override
    public Tweet findTweet(Long id) {
        for (Tweet tweet : tweets) {
            if (tweet.getId().equals(id)) {
                return tweet;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * This method allows a tweet to be found by it's message content.
     *
     * @param content of the message that the tweet is going to be searched on.
     * @return a sinlge tweet object.
     */
    @Override
    public Tweet findTweetByContent(String content) {
        for (Tweet tweet : tweets) {
            if (tweet.getMessage().equals(content)) {
                return tweet;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Tweet createTweet(Tweet tweet) {
        if (!tweets.contains(tweet)) {
            this.tweets.add(tweet);
            return tweet;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getLikesFromTweet(Long tweetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
