/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import boundary.rest.UserResource;
import dao.TweetDAOCollection;
import dao.UserDAOCollection;
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
public class UserServiceTest {

    private UserResource userResource = null;

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

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userResource = new UserResource();

        tags = new ArrayList();
        tags.add("#cool");
        tags.add("#like");
        users = new ArrayList();

        user1 = userResource.createUser(new User("pic", "web", "hanzel", "men", "veldhoven", "rick@rick.nl", "pass"));
        users.add(user1);
        tweet1 = new Tweet("message hello", tags, user1);

        user2 = userResource.createUser(new User());
        users.add(user2);
        tweet2 = new Tweet("message hello", tags, user2);

        user3 = userResource.createUser(new User());
        users.add(user3);
        tweet3 = new Tweet("message hello", tags, user3);

        user4 = userResource.createUser(new User());
        users.add(user4);
        tweet4 = new Tweet("message hello", tags, user4);

        user5 = userResource.createUser(new User());
        users.add(user5);
        tweet5 = new Tweet("message hello", tags, user5);

        user6 = userResource.createUser(new User());
        users.add(user6);
        tweet6 = new Tweet("message hello", tags, user6);

        user7 = userResource.createUser(new User());
        users.add(user7);
        tweet7 = new Tweet("message hello", tags, user7);

        user8 = userResource.createUser(new User());
        users.add(user8);
        tweet8 = new Tweet("message hello", tags, user8);

        user9 = userResource.createUser(new User());
        users.add(user9);
        tweet9 = new Tweet("message hello", tags, user9);

        user10 = userResource.createUser(new User());
        users.add(user10);
        tweet10 = new Tweet("message hello", tags, user10);

        userResource.followUser(user1, user2);
        userResource.followUser(user1, user3);

        userResource.followUser(user2, user1);
        userResource.followUser(user2, user3);
        userResource.followUser(user2, user4);

        userResource.followUser(user5, user1);
        userResource.followUser(user5, user6);

        userResource.followUser(user7, user2);

        userResource.followUser(user8, user1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("Test get all users on - UserService layer");
        assertEquals(10, userResource.getAllUsers().size());
    }

    @Test
    public void testCreateUser() {
        System.out.println("Test create user on - UserService layer");
        assertEquals(null, userResource.createUser(user1));
    }

    @Test
    public void testRemoveUser() {
        System.out.println("Test remove user on - UserService layer");
        userResource.removeUser(user1);
        assertEquals(9, userResource.getAllUsers().size());
    }

    @Test
    public void testFindUser() {
        System.out.println("Test find user on - UserService layer");
        user1.setId(1L);

        assertEquals(user1.getId(), userResource.findUser(user1.getId()).getId());
    }

    @Test
    public void testAddTweet() {
        System.out.println("Test add tweet on - UserService layer");
        userResource.addTweet(user1, tweet1, users);
        assertEquals("Tweet should not be null", tweet1, userResource.getTweets(user1).get(0));
    }

    @Test
    public void testRemoveTweet() {
        System.out.println("Test remove tweet on - UserService layer");
        // Adding a tweet
        userResource.addTweet(user2, tweet2, null);
        assertEquals("Tweet content should be 1", 1, userResource.getTweets(user2).size());

        // Removing a tweet
        userResource.removeTweet(tweet2);
        assertEquals("Amount of tweets should be 0", 0, userResource.getTweets(user1).size());
    }

    @Test
    public void testFollowUser() {
        System.out.println("Test follow user on - UserService layer");
        // Should be 0 followers.
        assertEquals("Amounnt of followers should be 0", 0, userResource.getFollowers(user9).size());

        // Adding a follower to user 10.
        userResource.followUser(user10, user9);
        assertEquals("Amount of followers should be 1", 1, userResource.getFollowers(user9).size());
    }

    @Test
    public void testUnfollowUser() {
        System.out.println("Test unfollow user on - UserService layer");
        userResource.unfollowUser(user1, user1);
        assertEquals("Amount of following users should be 2", 2, userResource.getFollowers(user1).size());
    }

    @Test
    public void testGetFollowingUsers() {
        System.out.println("Test get following users on - UserService layer");
        assertEquals("Amount of users that are being followed are 0", 0, 
                userResource.getFollowingUsers(user1).size());
    }

    @Test
    public void testGetFollowers() {
        System.out.println("Test get followers on - UserService layer");
        assertEquals("Amount of followers should be 3", 3, userResource.getFollowers(user1).size());
    }

    @Test
    public void testGetTweets() {
        System.out.println("Test get tweets on - UserService layer");
        userResource.addTweet(user1, tweet1, users);
        assertEquals("Amount of tweets should be 1", 1, userResource.getTweets(user1).size());
    }

    @Test
    public void testLikeTweet() {
        System.out.println("Test get like of a tweet on - Service layer");
        TweetDAOCollection tweetDAO = new TweetDAOCollection();
        System.out.println("Get amount of likes per tweet on - DAO collection");
        // No tweet liked.
        assertEquals("Amount of likes should be 0", 0, tweetDAO.getLikes(tweet1).size());

        // User liking a tweet
        userResource.likeTweet(user1, tweet2);
        assertEquals("Amount of likes should be 1", 1, tweetDAO.getLikes(tweet2).size());
    }
}
