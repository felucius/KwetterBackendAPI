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
     * Setting up the basic information regarding
     * - users
     * - tweets
     * and users that are following other users.
     */
    @Before
    public void setUp() {
        tags = new ArrayList();
        tags.add("#Like");
        tags.add("#Super");
        
        user1 = new User();
        tweet1 = new Tweet("Message1", tags, user1);
        
        user2 = new User();
        tweet2 = new Tweet("Message1", tags, user2);
        
        user3 = new User();
        tweet3 = new Tweet("Message1", tags, user3);
        
        user4 = new User();
        tweet4 = new Tweet("Message1", tags, user4);
        
        user5 = new User();
        tweet5 = new Tweet("Message1", tags, user5);
        
        user6 = new User();
        tweet6 = new Tweet("Message1", tags, user6);
       
        user7 = new User();
        tweet7 = new Tweet("Message1", tags, user7);
        
        user8 = new User();
        tweet8 = new Tweet("Message1", tags, user8);
        
        user9 = new User();
        tweet9 = new Tweet("Message1", tags, user9);
        
        user10 = new User();
        tweet10 = new Tweet("Message1", tags, user10);
        
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
     * Test of addTweet method, of class User.
     * Adding a tweet from a single user.
     */
    @Test
    public void testAddTweet() {
        user1.addTweet(tweet1, null);
        assertEquals(tweet1, user1.getTweets().get(0));
    }

    /**
     * Test of removeTweet method, of class User.
     * Removing a tweet from a user.
     */
    @Test
    public void testRemoveTweet() {
        // Adding a tweet
        user2.addTweet(tweet2, null);
        assertEquals(tweet2, user2.getTweets().get(0));
        
        // Removing a tweet
        user2.removeTweet(tweet2);
        assertEquals(0, user2.getTweets().size());
    }

    /**
     * Test of followUser method, of class User.
     * Users follow other users. Retrieving the amount of users
     * a certain user follows.
     */
    @Test
    public void testFollowUser() {
        // Should be 0 followers.
        assertEquals(0, user9.getFollowers().size());
        
        // Adding a follower to user 10.
        user10.followUser(user9);
        assertEquals(1, user9.getFollowers().size());
        
    }

    /**
     * Test of getFollowers method, of class User.
     * Retrieving the amount of followers per follower.
     */
    @Test
    public void testGetFollowers() {
        assertEquals(3, user1.getFollowers().size());
    }

    /**
     * Test of likeTweet method, of class User.
     * Retrieving the amount of likes per tweet.
     */
    @Test
    public void testLikeTweet() {
        // No tweet liked.
        assertEquals(0, tweet2.getLikes().size());
        
        // User liking a tweet
        user1.likeTweet(tweet2);
        assertEquals(1, tweet2.getLikes().size());
    }

    /**
     * Test of getPicture method, of class User.
     * Retrieving the picture or avatar per user.
     */
    @Test
    public void testGetPicture() {
        assertEquals("picturePath", user1.getPicture());
    }

    /**
     * Test of setPicture method, of class User.
     * Setting the avatar or picture per user.
     */
    @Test
    public void testSetPicture() {
        String expResult = "New picture path";
        user1.setPicture("New picture path");
        assertEquals(expResult, user1.getPicture());
    }

    /**
     * Test of getWebsite method, of class User.
     * Retrieving the website per user.
     */
    @Test
    public void testGetWebsite() {
        assertEquals(null, user1.getWebsite());
    }

    /**
     * Test of setWebsite method, of class User.
     */
    @Test
    public void testSetWebsite() {
        String expResult = "Websaid";
        user2.setWebsite(expResult);
        assertEquals(expResult, user2.getWebsite());
    }

    /**
     * Test of getName method, of class User.
     * Retrieving the username per user.
     */
    @Test
    public void testGetName() {
        assertEquals(null, user3.getName());
    }

    /**
     * Test of setName method, of class User.
     * Setting the username per user.
     */
    @Test
    public void testSetName() {
        String name = "Maxime";
        user3.setName(name);
        assertEquals(name, user3.getName());
    }

    /**
     * Test of getBio method, of class User.
     * Retrieving the biography per user.
     */
    @Test
    public void testGetBio() {
        assertEquals(null, user4.getBio());
    }

    /**
     * Test of setBio method, of class User.
     * Setting the biography per user.
     */
    @Test
    public void testSetBio() {
        String bio = "Women";
        user4.setBio(bio);
        assertEquals(bio, user4.getBio());
    }

    /**
     * Test of getLocation method, of class User.
     * Retrieving the location from a user.
     */
    @Test
    public void testGetLocation() {
        assertEquals(null, user5.getLocation());
    }

    /**
     * Test of setLocation method, of class User.
     * Setting the location of a single user.
     */
    @Test
    public void testSetLocation() {
        String location = "Geldrop";
        user5.setLocation(location);
        assertEquals(location, user5.getLocation());
    }

    /**
     * Test of getEmail method, of class User.
     * Retrieving the email address of a single user.
     */
    @Test
    public void testGetEmail() {
        assertEquals(null, user6.getEmail());
    }

    /**
     * Test of setEmail method, of class User.
     * Setting the email address of a single user.
     */
    @Test
    public void testSetEmail() {
        String email = "new email.nl";
        user6.setEmail(email);
        assertEquals(email, user6.getEmail());
    }

    /**
     * Test of getPassword method, of class User.
     * Retrieving the password of a single user.
     */
    @Test
    public void testGetPassword() {
        assertEquals(null, user1.getPassword());
    }

    /**
     * Test of setPassword method, of class User.
     * Setting a new password for the user.
     */
    @Test
    public void testSetPassword() {
        String newPassword = "pass1234";
        user1.setPassword(newPassword);
        assertEquals(newPassword, user1.getPassword());
    }

}
