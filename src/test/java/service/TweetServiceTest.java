/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContainerInitializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author M
 */
public class TweetServiceTest {

    private TweetService tweetService = null;
    private UserService userService = null;

    private List<Tweet> tweets = null;
    private List<String> tags = null;
    private List<User> users = null;
    private Tweet tweet1 = null;
    private Tweet tweet2 = null;
    private User user1 = null;
    private User user2 = null;
    private User user3 = null;

    public TweetServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tweetService = mock(TweetService.class);
        userService = mock(UserService.class);

        users = new ArrayList();
        tags = new ArrayList();
        tags.add("#JEA");
        tags.add("#Cool");
        tweets = new ArrayList();

        user1 = userService.createUser(new User("picURL", "webURL", "Maxime", "Men", "Geldrop", "maxime@hotmail.com", "pass"));

        user2 = userService.createUser(new User("picURL", "webURL", "Karel", "Men", "Veldhoven", "Karel@hotmail.com", "pass"));
        users.add(user2);
        user3 = userService.createUser(new User("picURL", "webURL", "Karel", "Men", "Veldhoven", "Karel@hotmail.com", "pass"));
        users.add(user3);

        tweet1 = new Tweet("Hello message", tags, user1);
        tweet2 = new Tweet("Tweeting now!!", tags, user2);
        tweetService.addMention(tweet1, user1);
        userService.addTweet(user1, tweet1, users);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddMention() {
        System.out.println("Test add mention on - TweetService layer");
        User user1 = mock(User.class);
        Tweet tweet1 = mock(Tweet.class);
        tweetService.addMention(tweet1, user1);
        assertEquals(false, tweetService.addMention(tweet1, user1));
    }

    @Test
    public void testGetLikes() {
        System.out.println("Test get likes on - TweetService layer");
        assertEquals(0, tweetService.getLikes(tweet1).size());
        userService.likeTweet(user1, tweet1);
        assertEquals(0, tweetService.getLikes(tweet1).size());
    }

    @Test
    public void testGetMentions() {
        System.out.println("Test get mentions on - TweetService layer");
        assertEquals(0, tweetService.getMentions(tweet1).size());
        tweetService.addMention(tweet1, user1);
        assertEquals(0, tweetService.getMentions(tweet1).size());
    }

    @Test
    public void testGetTweetsOfFollowingUsers() {
        System.out.println("Test get tweets of following users on - TweetService layer");
        assertEquals(0, tweetService.getTweetsOfFollowingUsers(user1).size());
    }

    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets of all users on - TweetService layer");
        userService.addTweet(user1, tweet1, users);
        userService.addTweet(user2, tweet2, users);
        assertEquals(0, userService.getTweetsByUser(user1).size());
    }

}
