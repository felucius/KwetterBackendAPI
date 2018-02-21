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
 *
 * @author M
 */
public class UserTest {
    
    private List<User> users = null;
    private List<Tweet> tweets = null;
    private List<String> tags = null;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        users = new ArrayList();
        tweets = new ArrayList();
        tags = new ArrayList();
        
        for(int i = 0; i < 10; i++){
            User user = new User();
            users.add(user);
        }
        
        for (int i = 0; i< users.size(); i ++) {
            tags.add("Like");
            Tweet tweet = new Tweet("Message " + i, tags, users.get(i));
            tweets.add(tweet);
        }
        
        System.out.println("Users: " + users.toString());
        System.out.println("Tweets: " + tweets.toString());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTweet method, of class User.
     */
    @Test
    public void testAddTweet() {
        
    }

    /**
     * Test of removeTweet method, of class User.
     */
    @Test
    public void testRemoveTweet() {

    }

    /**
     * Test of followUser method, of class User.
     */
    @Test
    public void testFollowUser() {

    }

    /**
     * Test of getFollowers method, of class User.
     */
    @Test
    public void testGetFollowers() {

    }

    /**
     * Test of likeTweet method, of class User.
     */
    @Test
    public void testLikeTweet() {

    }

    /**
     * Test of getPicture method, of class User.
     */
    @Test
    public void testGetPicture() {

    }

    /**
     * Test of setPicture method, of class User.
     */
    @Test
    public void testSetPicture() {

    }

    /**
     * Test of getWebsite method, of class User.
     */
    @Test
    public void testGetWebsite() {

    }

    /**
     * Test of setWebsite method, of class User.
     */
    @Test
    public void testSetWebsite() {

    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {

    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {

    }

    /**
     * Test of getBio method, of class User.
     */
    @Test
    public void testGetBio() {

    }

    /**
     * Test of setBio method, of class User.
     */
    @Test
    public void testSetBio() {

    }

    /**
     * Test of getLocation method, of class User.
     */
    @Test
    public void testGetLocation() {

    }

    /**
     * Test of setLocation method, of class User.
     */
    @Test
    public void testSetLocation() {

    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {

    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {

    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {

    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {

    }
    
}
