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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author M
 */
public class TweetDAOImplementationTest {

    // Tweet DAO class collection.
    private TweetDAOCollection tweetDAO = null;
    private UserDAOCollection userDAO = null;

    private List<Tweet> tweets = null;
    private List<String> tags = null;
    private List<User> users = null;
    private Tweet tweet1 = null;
    private User user1 = null;
    private User user2 = null;
    private User user3 = null;

    public TweetDAOImplementationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tweetDAO = new TweetDAOCollection();
        userDAO = new UserDAOCollection();

        users = new ArrayList();
        tags = new ArrayList();
        tags.add("#JEA");
        tags.add("#Cool");
        tweets = new ArrayList();

        user1 = userDAO.createUser(new User("picURL", "webURL", "Maxime", "Men", "Geldrop", "maxime@hotmail.com", "pass"));

        user2 = userDAO.createUser(new User("picURL", "webURL", "Karel", "Men", "Veldhoven", "Karel@hotmail.com", "pass"));
        users.add(user2);
        user3 = userDAO.createUser(new User("picURL", "webURL", "Karel", "Men", "Veldhoven", "Karel@hotmail.com", "pass"));
        users.add(user3);

        tweet1 = new Tweet("Hello message", tags, user1);
    }

    @After
    public void tearDown() {
    }

    /**
     * This method tests if all the likes are retrieved from a single tweet.
     */
    @Test
    public void testGetLikes() {
        System.out.println("Test get likes on - Tweet DAO collection");
        tweetDAO.likeTweet(tweet1, user1);
        tweetDAO.likeTweet(tweet1, user2);
        assertEquals(2, tweetDAO.getLikes(tweet1).size());
    }

    /**
     * This method tests if all mentions can be retrieved from a tweet.
     */
    @Test
    public void testGetMentions() {
        System.out.println("Test get mentions on - Tweet DAO collection");
        tweetDAO.addMention(tweet1, user1);
        tweetDAO.addMention(tweet1, user2);
        assertEquals(2, tweetDAO.getMentions(tweet1).size());
    }

    /**
     * This method tests a tweet to get a mention. A mention is another user
     * that is being mentioned in a tweet.
     */
    @Test
    public void testAddMention() {
        System.out.println("Test add mentions on - Tweet DAO collection");
        Tweet tweet = tweetDAO.addMention(tweet1, user1);

        assertEquals(1, tweet.getMentions().size());
    }

    /**
     * This method tests the retrieval of all tweets from a specific user.
     */
    @Test
    public void testGetTweetsOfFollowingUsers() {
        System.out.println("Test get tweets of following users on - Tweet DAO collection");
        userDAO.addTweet(user1, tweet1, users);
        assertEquals(1, tweetDAO.getTweetsOfFollowingUsers(user1).size());
    }

    /**
     * This method tests the retrieval of all existing tweets from all users.
     */
    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets on - Tweet DAO collection");
        assertEquals(0, tweetDAO.getAllTweets().size());
    }

}
