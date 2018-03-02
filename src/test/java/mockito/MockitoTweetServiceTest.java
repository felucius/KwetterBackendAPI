/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockito;

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
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import service.TweetService;
import service.UserService;
import static org.mockito.Mockito.*;

/**
 *
 * @author M
 */
public class MockitoTweetServiceTest {

    @Mock
    private TweetService tweetService = null;
    @Mock
    private UserService userService = null;
    @Mock
    private TweetDAO tweetDAO = null;
    @Mock
    private UserDAO userDAO = null;
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

    public MockitoTweetServiceTest() {
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
        tweetDAO = mock(TweetDAOCollection.class);
        userDAO = mock(UserDAOCollection.class);
        
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
        tweetService.addMention(tweet1, user1);
        userService.addTweet(user1, tweet1, users);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddMention() {
        System.out.println("Test add mention on Mockito - TweetService layer");
        
        when(tweetDAO.addMention(tweet1, user1)).thenReturn(tweet1);
        assertEquals(tweet1, tweetDAO.addMention(tweet1, user1));
        tweetService.addMention(tweet1, user1);
        verify(tweetDAO).addMention(tweet1, user1);
    }

    @Test
    public void testGetLikes() {
        System.out.println("Test get likes on Mockito - TweetService layer");

        when(tweetDAO.getLikes(tweet1)).thenReturn(users);
        assertEquals(users, tweetDAO.getLikes(tweet1));
        tweetService.getLikes(tweet1);
        verify(tweetDAO).getLikes(tweet1);
    }

    @Test
    public void testGetMentions() {
        System.out.println("Test get mentions on Mockito - TweetService layer");

        when(tweetDAO.getMentions(tweet1)).thenReturn(users);
        assertEquals(users, tweetDAO.getMentions(tweet1));
        tweetService.getMentions(tweet1);
        verify(tweetDAO).getMentions(tweet1);
    }

    @Test
    public void testGetTweetsOfFollowingUsers() {
        System.out.println("Test get tweets of following users on Mockito - TweetService layer");

        when(tweetDAO.getTweetsOfFollowingUsers(user1)).thenReturn(tweets);
        assertEquals(tweets, tweetDAO.getTweetsOfFollowingUsers(user1));
        tweetService.getTweetsOfFollowingUsers(user1);
        verify(tweetDAO).getTweetsOfFollowingUsers(user1);
    }

    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets of all users on Mockito - TweetService layer");

        when(tweetDAO.getAllTweets()).thenReturn(tweets);
        assertEquals(tweets, tweetDAO.getAllTweets());
        tweetService.getAllTweets();
        verify(tweetDAO).getAllTweets();
    }
}
