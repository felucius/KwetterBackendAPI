/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
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
public class TweetTest {
    
    public TweetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMessage method, of class Tweet.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Tweet instance = new Tweet();
        String expResult = "New Tweet";
        instance.setMessage(expResult);
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessage method, of class Tweet.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "hello tweet";
        Tweet instance = new Tweet();
        instance.setMessage(message);
        
        assertNotNull(instance);
        assertNotNull(instance.getMessage());
    }

    /**
     * Test of getTags method, of class Tweet.
     */
    @Test
    public void testGetTags() {
        System.out.println("getTags");
        Tweet instance = new Tweet();
        String tagList = "Like";
        String tagList2 = "Like";
        
        instance.setTag("Like");
        
        String expResult = tagList2;
        String result = instance.getTag();
        
        assertEquals(expResult, result);
        assertNotNull(result);
    }

    /**
     * Test of setTags method, of class Tweet.
     */
    @Test
    public void testSetTags() {
        System.out.println("setTags");
        List<String> tags = new ArrayList();
        tags.add("Like");
        tags.add("Kuddo");
        Tweet instance = new Tweet();
        instance.setTag("Like");
      
        assertNotNull(tags);
        assertEquals(2, tags.size());
    }

    /**
     * Test of getPublished method, of class Tweet.
     */
    @Test
    public void testGetPublished() {
        System.out.println("getPublished");
        Tweet instance = new Tweet();
        Date expResult = new Date();
        instance.setPublished(expResult);
        Date result = instance.getPublished();
        
        assertNotNull(result);
        assertNotNull(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPublished method, of class Tweet.
     */
    @Test
    public void testSetPublished() {
        System.out.println("setPublished");
        Date published = new Date(2018, 3, 10);
        Date published2 = new Date(2017, 3, 10);
        Tweet instance = new Tweet();
        instance.setPublished(published);
        Date expected = instance.getPublished();
        
        assertNotEquals(published, published2);
        assert published == expected;
    }

    /**
     * Test of getId method, of class Tweet.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Tweet instance = new Tweet();
        Integer expResult = 123456789;
        instance.setTweetId(expResult);
        Integer result = instance.getTweetId();
        
        assertNotNull(result);
        assertEquals(expResult, result);
        assert expResult == result;
    }

    /**
     * Test of setId method, of class Tweet.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1234;
        Tweet instance = new Tweet();
        instance.setTweetId(id);
        Integer expResult = instance.getTweetId();
        
        assertNotNull(id);
        assertEquals(expResult, id);
        assert expResult == id;
    }
    
    /**
     * Test if the tweet mentions can be set.
     */
    @Test
    public void testSetMentions(){
        System.out.println("Test setMentions on Tweet");
        
        List<Tweet> mentions = new ArrayList();
        List<User> mentionedUsers = new ArrayList();
        mentionedUsers.add(new User());
        mentionedUsers.add(new User());
        Tweet tweet1 = new Tweet();
        mentions.add(tweet1);
        tweet1.setMentions(mentionedUsers);
        assertNotNull(mentionedUsers);
    }
    
    /**
     * Test if the toString method does not return null value when a new Tweet
     * has been made.
     */
    @Test
    public void testToString(){
        System.out.println("Test toString on Tweet");
        List<String> tags = new ArrayList();
        tags.add("#Cool");
        User user1 = new User("avatarURL", "Website", "Maxime", "Men", "Geldrop", "EmailMaxime", "Pass");
        Tweet tweet = new Tweet("Hello", "#Cool", user1);
        assertNotNull(tweet.toString());
    }
}
