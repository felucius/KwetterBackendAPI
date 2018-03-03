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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author M
 */
public class UserDAOCollTest {

    // userDAO class collection
    private UserDAOCollection userDAO = null;

    private List<String> tags = null;
    private List<User> users = null;

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
        users = new ArrayList();

        userDAO = new UserDAOCollection();
        user1 = userDAO.createUser(new User());
        //user1 = userDAO.createUser(new User("pic", "web", "hanzel", "men", "veldhoven", "rick@rick.nl", "pass"));
        users.add(user1);
        tweet1 = new Tweet("message hello", tags, user1);

        user2 = userDAO.createUser(new User());
        users.add(user2);
        tweet2 = new Tweet("message hello", tags, user2);

        user3 = userDAO.createUser(new User());
        users.add(user3);
        tweet3 = new Tweet("message hello", tags, user3);

        user4 = userDAO.createUser(new User());
        users.add(user4);
        tweet4 = new Tweet("message hello", tags, user4);

        user5 = userDAO.createUser(new User());
        users.add(user5);
        tweet5 = new Tweet("message hello", tags, user5);

        user6 = userDAO.createUser(new User());
        users.add(user6);
        tweet6 = new Tweet("message hello", tags, user6);

        user7 = userDAO.createUser(new User());
        users.add(user7);
        tweet7 = new Tweet("message hello", tags, user7);

        user8 = userDAO.createUser(new User());
        users.add(user8);
        tweet8 = new Tweet("message hello", tags, user8);

        user9 = userDAO.createUser(new User());
        users.add(user9);
        tweet9 = new Tweet("message hello", tags, user9);

        user10 = userDAO.createUser(new User());
        users.add(user10);
        tweet10 = new Tweet("message hello", tags, user10);

        user1.followUser(user2);
        user1.followUser(user3);

        user2.followUser(user1);
        user2.followUser(user3);
        user2.followUser(user4);

        user5.followUser(user1);
        user5.followUser(user6);

        user7.followUser(user2);

        user8.followUser(user1);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void createUser() {
        System.out.println("Creating user on - DAO collection");
        assertEquals(null, userDAO.createUser(user1));
    }

    @Test
    public void testAddTweet() {
        System.out.println("Adding a tweet on - DAO collection");
        userDAO.addTweet(user1, tweet1, users);
        assertEquals("Tweet should not be null", tweet1, userDAO.getTweetsByUser(user1).get(0));
    }

    @Test
    public void testRemoveTweet() {
        System.out.println("Removing a tweet on - DAO collection");
        // Adding a tweet
        userDAO.addTweet(user2, tweet2, null);
        assertEquals("Tweet content should be 1", 1, userDAO.getTweetsByUser(user2).size());

        // Removing a tweet
        userDAO.removeTweet(tweet2);
        assertEquals("Amount of tweets should be 0", 0, userDAO.getTweetsByUser(user1).size());
    }

    @Test
    public void testFollowUser() {
        System.out.println("Follow a user on - DAO collection");
        // Should be 0 followers.
        assertEquals("Amounnt of followers should be 0", 0, userDAO.getFollowers(user9).size());

        // Adding a follower to user 10.
        user10.followUser(user9);
        assertEquals("Amount of followers should be 1", 1, userDAO.getFollowers(user9).size());

    }

    @Test
    public void testGetFollowers() {
        System.out.println("Get amount of followers on - DAO collection");
        assertEquals("Amount of followers should be 3", 3, userDAO.getFollowers(user1).size());
    }

    @Test
    public void testLikeTweet() {
        TweetDAOCollection tweetDAO = new TweetDAOCollection();
        System.out.println("Get amount of likes per tweet on - DAO collection");
        // No tweet liked.
        assertEquals("Amount of likes should be 0", 0, tweetDAO.getLikes(tweet1).size());

        // User liking a tweet
        userDAO.likeTweet(user1, tweet2);
        assertEquals("Amount of likes should be 1", 1, tweetDAO.getLikes(tweet2).size());
    }
    
    @Test
    public void testGetUserByID() {
        System.out.println("Get user by name on - DAO collection");
        user1.setId(1L);
        
        assertEquals(user1.getId(), userDAO.findUser(user1.getId()).getId());
    }
    
    @Test
    public void testUnfollowUser(){
        System.out.println("Test unfollow user on - DAO collection");
        assertEquals(3, userDAO.getFollowers(user1).size());
    }
    
    @Test
    public void testGetTweets(){
        System.out.println("Test get tweets on - DAO collection");
        userDAO.addTweet(user1, tweet1, users);
        assertEquals(1, userDAO.getTweetsByUser(user1).size());
    }
}
