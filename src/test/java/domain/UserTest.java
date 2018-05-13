/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UserTest class tests the users, the followers and their tweets.
 *
 * @author M
 */
public class UserTest {

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

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Setting up the basic information regarding - users - tweets and users
     * that are following other users.
     */
    @Before
    public void setUp() {
        tags = new ArrayList();
        tags.add("#Like");
        tags.add("#Super");

        user1 = new User();
        tweet1 = new Tweet("Message1", "#cool", user1);

        user2 = new User();
        tweet2 = new Tweet("Message1", "#cool", user2);

        user3 = new User();
        tweet3 = new Tweet("Message1", "#cool", user3);

        user4 = new User();
        tweet4 = new Tweet("Message1", "#cool", user4);

        user5 = new User();
        tweet5 = new Tweet("Message1", "#cool", user5);

        user6 = new User();
        tweet6 = new Tweet("Message1", "#cool", user6);

        user7 = new User();
        tweet7 = new Tweet("Message1", "#cool", user7);

        user8 = new User();
        tweet8 = new Tweet("Message1", "#cool", user8);

        user9 = new User();
        tweet9 = new Tweet("Message1", "#cool", user9);

        user10 = new User();
        tweet10 = new Tweet("Message1", "#cool", user10);

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

    /**
     * Test of addTweet method, of class User. Adding a tweet from a single
     * user.
     */
    @Test
    public void testAddTweet() {
        System.out.println("Adding a tweet");
        user1.addTweet(tweet1, null);
        assertEquals("Tweet should not be null", tweet1, user1.getTweets().get(0));
    }

    /**
     * Test of removeTweet method, of class User. Removing a tweet from a user.
     */
    @Test
    public void testRemoveTweet() {
        System.out.println("Removing a tweet");
        // Adding a tweet
        user2.addTweet(tweet2, null);
        assertEquals("Tweet content should not be null", tweet2, user2.getTweets().get(0));

        // Removing a tweet
        user2.removeTweet(tweet2);
        assertEquals("Amount of tweets should be 0", 0, user2.getTweets().size());
    }

    /**
     * Test of followUser method, of class User. Users follow other users.
     * Retrieving the amount of users a certain user follows.
     */
    @Test
    public void testFollowUser() {
        System.out.println("Follow a user");
        // Should be 0 followers.
        assertEquals("Amounnt of followers should be 0", 0, user9.getFollowers().size());

        // Adding a follower to user 10.
        user10.followUser(user9);
        assertEquals("Amount of followers should be 1", 1, user9.getFollowers().size());

    }

    /**
     * Test of getFollowers method, of class User. Retrieving the amount of
     * followers per follower.
     */
    @Test
    public void testGetFollowers() {
        System.out.println("Get amount of followers");
        assertEquals("Amount of followers should be 3", 3, user1.getFollowers().size());
    }

    /**
     * Test of likeTweet method, of class User. Retrieving the amount of likes
     * per tweet.
     */
    @Test
    public void testLikeTweet() {
        System.out.println("Get amount of likes per tweet");
        // No tweet liked.
        assertEquals("Amount of likes should be 0", 0, tweet2.getLikes().size());

        // User liking a tweet
        user1.likeTweet(tweet2);
        assertEquals("Amount of likes should be 1", 1, tweet2.getLikes().size());
    }

    /**
     * Test of getPicture method, of class User. Retrieving the picture or
     * avatar per user.
     */
    @Test
    public void testGetPicture() {
        System.out.println("Get picture path");
        assertEquals("Picture path should not be null", "picturePath", user1.getPicture());
    }

    /**
     * Test of setPicture method, of class User. Setting the avatar or picture
     * per user.
     */
    @Test
    public void testSetPicture() {
        System.out.println("Setting picture path");
        String expResult = "New picture path";
        user1.setPicture("New picture path");
        assertEquals("Picture path should not be null", expResult, user1.getPicture());
    }

    /**
     * Test of getWebsite method, of class User. Retrieving the website per
     * user.
     */
    @Test
    public void testGetWebsite() {
        System.out.println("Getting website url");
        assertEquals("Website should be null", null, user1.getWebsite());
    }

    /**
     * Test of setWebsite method, of class User.
     */
    @Test
    public void testSetWebsite() {
        System.out.println("Setting website url");
        String expResult = "Websaid";
        user2.setWebsite(expResult);
        assertEquals("Website should not be null", expResult, user2.getWebsite());
    }

    /**
     * Test of getName method, of class User. Retrieving the username per user.
     */
    @Test
    public void testGetName() {
        System.out.println("Getting username");
        assertEquals("Username should be null", null, user3.getName());
    }

    /**
     * Test of setName method, of class User. Setting the username per user.
     */
    @Test
    public void testSetName() {
        System.out.println("Setting username");
        String name = "Maxime";
        user3.setName(name);
        assertEquals("Username should not be null", name, user3.getName());
    }

    /**
     * Test of getBio method, of class User. Retrieving the biography per user.
     */
    @Test
    public void testGetBio() {
        System.out.println("Getting biography");
        assertEquals("ZBiography should be null", null, user4.getBio());
    }

    /**
     * Test of setBio method, of class User. Setting the biography per user.
     */
    @Test
    public void testSetBio() {
        System.out.println("Setting biography");
        String bio = "Women";
        user4.setBio(bio);
        assertEquals("Biography should not be null", bio, user4.getBio());
    }

    /**
     * Test of getLocation method, of class User. Retrieving the location from a
     * user.
     */
    @Test
    public void testGetLocation() {
        System.out.println("Getting location from user");
        assertEquals("Location should be null", null, user5.getLocation());
    }

    /**
     * Test of setLocation method, of class User. Setting the location of a
     * single user.
     */
    @Test
    public void testSetLocation() {
        System.out.println("Setting users location");
        String location = "Geldrop";
        user5.setLocation(location);
        assertEquals("Location should not be null", location, user5.getLocation());
    }

    /**
     * Test of getEmail method, of class User. Retrieving the email address of a
     * single user.
     */
    @Test
    public void testGetEmail() {
        System.out.println("Get email from user");
        assertEquals("Email should be null", null, user6.getEmail());
    }

    /**
     * Test of setEmail method, of class User. Setting the email address of a
     * single user.
     */
    @Test
    public void testSetEmail() {
        System.out.println("Set email from user");
        String email = "new email.nl";
        user6.setEmail(email);
        assertEquals("Email should be not null", email, user6.getEmail());
    }

    /**
     * Test of getPassword method, of class User. Retrieving the password of a
     * single user.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Get password from user");
        assertEquals("Result should be null", null, user1.getPassword());
    }

    /**
     * Test of setPassword method, of class User. Setting a new password for the
     * user.
     */
    @Test
    public void testSetPassword() {
        System.out.println("Set password from user");
        String newPassword = "pass1234";
        user1.setPassword(newPassword);
        assertEquals("This test should pass", newPassword, user1.getPassword());
    }

    /**
     * Test if a new list of tweets can be setted by the user.
     */
    @Test
    public void testSetTweets() {
        System.out.println("Set tweets from user");
        List<Tweet> tweets = new ArrayList();
        tweets.add(tweet1);
        user1.setTweets(tweets);
        assertEquals("This test should pass", tweets, user1.getTweets());
    }

    /**
     * Testing the to String method if the value is not null of a new created
     * user object.
     */
    @Test
    public void testToString() {
        System.out.println("To String method from user");
        assertNotNull(user1.toString());
    }
}
