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
public class UserDAOCollTest {

    // userDAO class collection
    UserDAO userDAO = null;

    private List<String> tags = null;

    private User user1 = null;
    private User user2 = null;
    private User user3 = null;
    private User user4 = null;
    private User user5 = null;
    private User user6 = null;
    private User user7 = null;
    private User user8 = null;
    private User user9 = null;
    private User user10 = null;

    private Tweet tweet1 = null;
    private Tweet tweet2 = null;
    private Tweet tweet3 = null;
    private Tweet tweet4 = null;
    private Tweet tweet5 = null;
    private Tweet tweet6 = null;
    private Tweet tweet7 = null;
    private Tweet tweet8 = null;
    private Tweet tweet9 = null;
    private Tweet tweet10 = null;

    public UserDAOCollTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tags = new ArrayList();
        tags.add("#cool");
        tags.add("#like");

        userDAO = new UserDAO();

        user1 = userDAO.createUser(new User());
        tweet1 = new Tweet("message hello", tags, user1);

        user2 = userDAO.createUser(new User());
        tweet2 = new Tweet("message hello", tags, user2);

        user3 = userDAO.createUser(new User());
        tweet3 = new Tweet("message hello", tags, user3);

        user4 = userDAO.createUser(new User());
        tweet4 = new Tweet("message hello", tags, user4);

        user5 = userDAO.createUser(new User());
        tweet5 = new Tweet("message hello", tags, user5);

        user6 = userDAO.createUser(new User());
        tweet6 = new Tweet("message hello", tags, user6);

        user7 = userDAO.createUser(new User());
        tweet7 = new Tweet("message hello", tags, user7);

        user8 = userDAO.createUser(new User());
        tweet8 = new Tweet("message hello", tags, user8);

        user9 = userDAO.createUser(new User());
        tweet9 = new Tweet("message hello", tags, user9);

        user10 = userDAO.createUser(new User());
        tweet10 = new Tweet("message hello", tags, user10);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void createUser(){
        System.out.println("Creating user on - DAO collection");
        assertEquals(null, userDAO.createUser(user1));
    }

    /**
     * Test of addTweet method, of class User. Adding a tweet from a single
     * user.
     */
    @Test
    public void testAddTweet() {
        System.out.println("Adding a tweet on - DAO collection");
        userDAO.addTweet(tweet1, null);
        assertEquals("Tweet should not be null", tweet1, userDAO.getTweets().get(0));
    }

    /**
     * Test of removeTweet method, of class User. Removing a tweet from a user.
     */
    @Test
    public void testRemoveTweet() {
        System.out.println("Removing a tweet on - DAO collection");
        // Adding a tweet
        userDAO.addTweet(tweet1, null);
        assertEquals("Tweet content should not be null", tweet2, userDAO.getTweets().get(0));

        // Removing a tweet
        userDAO.removeTweet(tweet2);
        assertEquals("Amount of tweets should be 0", 0, userDAO.getTweets().size());
    }

    /**
     * Test of followUser method, of class User. Users follow other users.
     * Retrieving the amount of users a certain user follows.
     */
    @Test
    public void testFollowUser() {
        System.out.println("Follow a user on - DAO collection");
        // Should be 0 followers.
        assertEquals("Amounnt of followers should be 0", 0, userDAO.getFollowers(user9).size());

        // Adding a follower to user 10.
        user10.followUser(user9);
        assertEquals("Amount of followers should be 1", 1, userDAO.getFollowers(user9).size());

    }

    /**
     * Test of getFollowers method, of class User. Retrieving the amount of
     * followers per follower.
     */
    @Test
    public void testGetFollowers() {
        System.out.println("Get amount of followers on - DAO collection");
        assertEquals("Amount of followers should be 3", 3, userDAO.getFollowers(user1).size());
    }

    /**
     * Test of likeTweet method, of class User. Retrieving the amount of likes
     * per tweet.
     */
    @Test
    public void testLikeTweet() {
        TweetDAO tweetDAO = new TweetDAO();
        System.out.println("Get amount of likes per tweet on - DAO collection");
        // No tweet liked.
        assertEquals("Amount of likes should be 0", 0, tweetDAO.getLikes(user1).size());

        // User liking a tweet
        userDAO.likeTweet(user1, tweet2);
        assertEquals("Amount of likes should be 1", 1, tweetDAO.getLikes(user2).size());
    }
    
    /**
     * This method retrieves a user by it's ID throughout the entity manager
     */
    @Test
    public void testGetUserByID(){
        System.out.println("Get user by name on - DAO collection");
        user1.setId(1L);
        user2.setId(2L);
        
        assertEquals(user1, userDAO.findUser(user1.getId()));
        assertEquals(user2, userDAO.findUser(user2.getId()));
    }
}
