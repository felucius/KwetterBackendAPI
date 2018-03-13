/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dao.TweetDAO;
import dao.TweetDAOCollection;
import dao.UserDAO;
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
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import service.TweetService;
import service.UserService;

/**
 *
 * @author M
 */
public class MockitoTweetRestTest {

    
    @Mock
    private TweetResource tweetResource = null;
    @Mock
    private UserResource userResource = null;
    @Mock
    private TweetService tweetService = null;
    @Mock
    private UserService userService = null;
    @Mock
    private List<Tweet> tweets = null;
    @Mock
    private List<String> tags = null;
    @Mock
    private List<User> users = null;
    @Mock
    private Tweet tweet1 = null;
    @Mock
    private Tweet tweet2 = null;
    @Mock
    private User user1 = null;
    @Mock
    private User user2 = null;
    @Mock
    private User user3 = null;

    public MockitoTweetRestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tweetResource = mock(TweetResource.class);
        userResource = mock(UserResource.class);
        tweetService = mock(TweetService.class);
        userService = mock(UserService.class);

        users = mock(ArrayList.class);
        tags = mock(ArrayList.class);
        tags.add("#JEA");
        tags.add("#Cool");
        tweets = mock(ArrayList.class);

        user1 = mock(User.class);

        user2 = mock(User.class);
        users.add(user2);
        user3 = mock(User.class);
        users.add(user3);

        tweet1 = mock(Tweet.class);
        tweet2 = mock(Tweet.class);
        tweetResource.addMention(tweet1.getId(), user1.getName());
        //userResource.addTweet(user1.getName(), tweet1.getMessage(), users.get(0).ge);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddMention() {
        System.out.println("Test add mention on Mockito - TweetService layer");

        when(tweetService.addMention(tweet1, user1)).thenReturn(true);
        assertEquals(true, tweetService.addMention(tweet1, user1));
        tweetResource.addMention(tweet1.getId(), user1.getName());
        verify(tweetService).addMention(tweet1, user1);
    }

    @Test
    public void testGetLikes() {
        System.out.println("Test get likes on Mockito - TweetService layer");

        when(tweetService.getLikes(tweet1)).thenReturn(users);
        assertEquals(users, tweetService.getLikes(tweet1));
        tweetResource.getLikes(tweet1.getId());
        verify(tweetService).getLikes(tweet1);
    }

    @Test
    public void testGetMentions() {
        System.out.println("Test get mentions on Mockito - TweetService layer");

        when(tweetService.getMentions(tweet1)).thenReturn(users);
        assertEquals(users, tweetService.getMentions(tweet1));
        tweetResource.getMentions(tweet1.getId());
        verify(tweetService).getMentions(tweet1);
    }

    @Test
    public void testGetTweetsOfFollowingUsers() {
        System.out.println("Test get tweets of following users on - TweetService layer");

        when(tweetService.getTweetsOfFollowingUsers(user1)).thenReturn(tweets);
        assertEquals(tweets, tweetService.getTweetsOfFollowingUsers(user1));
        tweetResource.getTweetsOfFollowingUsers(user1.getName());
        verify(tweetService).getTweetsOfFollowingUsers(user1);
    }

    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets of all users on - TweetService layer");

        when(tweetService.getAllTweets()).thenReturn(tweets);
        assertEquals(tweets, tweetService.getAllTweets());
        tweetResource.getAllTweets();
        verify(tweetService).getAllTweets();
    }
}
