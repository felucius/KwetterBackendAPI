/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockito;

import boundary.rest.UserResource;
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
import static org.mockito.Mockito.*;
import service.UserService;

/**
 *
 * @author M
 */
public class MockitoUserServiceTest {

    @Mock
    private UserService userService = null;
    @Mock
    private UserDAO userDAO = null;
    @Mock
    private List<String> tags = null;
    @Mock
    private List<Tweet> tweets = null;
    @Mock
    private List<User> users = null;
    @Mock
    private User user1 = null;
    @Mock
    private User user2 = null;
    @Mock
    private User user3 = null;
    @Mock
    private User user4 = null;
    @Mock
    private User user5 = null;
    @Mock
    private User user6 = null;
    @Mock
    private User user7 = null;
    @Mock
    private User user8 = null;
    @Mock
    private User user9 = null;
    @Mock
    private User user10 = null;

    @Mock
    private Tweet tweet1 = null;
    @Mock
    private Tweet tweet2 = null;
    @Mock
    private Tweet tweet3 = null;
    @Mock
    private Tweet tweet4 = null;
    @Mock
    private Tweet tweet5 = null;
    @Mock
    private Tweet tweet6 = null;
    @Mock
    private Tweet tweet7 = null;
    @Mock
    private Tweet tweet8 = null;
    @Mock
    private Tweet tweet9 = null;
    @Mock
    private Tweet tweet10 = null;

    public MockitoUserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        userDAO = mock(UserDAOCollection.class);

        tags = mock(ArrayList.class);
        tags.add("#cool");
        tags.add("#like");
        users = mock(ArrayList.class);
        tweets = mock(ArrayList.class);

        user1 = mock(User.class);
        users.add(user1);
        tweet1 = mock(Tweet.class);

        user2 = mock(User.class);
        users.add(user2);
        tweet2 = mock(Tweet.class);

        user3 = mock(User.class);
        users.add(user3);
        tweet3 = mock(Tweet.class);

        user4 = mock(User.class);
        users.add(user4);
        tweet4 = mock(Tweet.class);

        user5 = mock(User.class);
        users.add(user5);
        tweet5 = mock(Tweet.class);

        user6 = mock(User.class);
        users.add(user6);
        tweet6 = mock(Tweet.class);

        user7 = mock(User.class);
        users.add(user7);
        tweet7 = mock(Tweet.class);

        user8 = mock(User.class);
        users.add(user8);
        tweet8 = mock(Tweet.class);

        user9 = mock(User.class);
        users.add(user9);
        tweet9 = mock(Tweet.class);

        user10 = mock(User.class);
        users.add(user10);
        tweet10 = mock(Tweet.class);

        userService.followUser(user1, user2);
        userService.followUser(user1, user3);

        userService.followUser(user2, user1);
        userService.followUser(user2, user3);
        userService.followUser(user2, user4);

        userService.followUser(user5, user1);
        userService.followUser(user5, user6);

        userService.followUser(user7, user2);

        userService.followUser(user8, user1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("Test get all users on Mockito - UserService layer");

        when(userDAO.getAllUsers()).thenReturn(users);
        assertEquals(users, userDAO.getAllUsers());
        userService.getAllUsers();
        verify(userDAO).getAllUsers();
    }

    @Test
    public void testCreateUser() {
        System.out.println("Test create user on Mockito - UserService layer");

        when(userDAO.createUser(user1)).thenReturn(user1);
        assertEquals(user1, userDAO.createUser(user1));
        userService.createUser(user1);
        verify(userDAO).createUser(user1);
    }

    @Test
    public void testRemoveUser() {
        System.out.println("Test remove user on Mockito - UserService layer");
        
        when(userDAO.removeUser(user1)).thenReturn(true);
        assertEquals(true, userDAO.removeUser(user1));
        userService.removeUser(user1);
        verify(userDAO).removeUser(user1);
    }

    @Test
    public void testFindUser() {
        System.out.println("Test find user on Mockito - UserService layer");
        
        when(userDAO.findUser(user1.getId())).thenReturn(user1);
        assertEquals(user1, userDAO.findUser(user1.getId()));
        userService.findUser(user1.getId());
        verify(userDAO).findUser(user1.getId());
    }

    @Test
    public void testAddTweet() {
        System.out.println("Test add tweet on Mockito - UserService layer");
        
        when(userDAO.addTweet(user1, tweet1, users)).thenReturn(true);
        assertEquals(true, userDAO.addTweet(user1, tweet1, users));
        userService.addTweet(user1, tweet1, users);
        verify(userDAO).addTweet(user1, tweet1, users);
    }

    @Test
    public void testRemoveTweet() {
        System.out.println("Test remove tweet on Mockito - UserService layer");
        
        when(userDAO.removeTweet(tweet1)).thenReturn(true);
        assertEquals(true, userDAO.removeTweet(tweet1));
        userService.removeTweet(tweet1);
        verify(userDAO).removeTweet(tweet1);
    }

    @Test
    public void testFollowUser() {
        System.out.println("Test follow user on Mockito - UserService layer");
        
        when(userDAO.followUser(user1, user2)).thenReturn(true);
        assertEquals(true, userDAO.followUser(user1, user2));
        userService.followUser(user1, user2);
        verify(userDAO).followUser(user1, user2);
    }

    @Test
    public void testUnfollowUser() {
        System.out.println("Test unfollow user on Mockito - UserService layer");
        
        when(userDAO.unfollowUser(user1, user2)).thenReturn(true);
        assertEquals(true, userDAO.unfollowUser(user1, user2));
        userService.unfollowUser(user1, user2);
        verify(userDAO).unfollowUser(user1, user2);
    }

    @Test
    public void testGetFollowingUsers() {
        System.out.println("Test get following users on Mockito - UserService layer");
        
        when(userDAO.getFollowingUsers(user1)).thenReturn(users);
        assertEquals(users, userDAO.getFollowingUsers(user1));
        userService.getFollowingUsers(user1);
        verify(userDAO).getFollowingUsers(user1);
    }

    @Test
    public void testGetFollowers() {
        System.out.println("Test get followers on Mockito - UserService layer");
        
        when(userDAO.getFollowers(user1)).thenReturn(users);
        assertEquals(users, userDAO.getFollowers(user1));
        userService.getFollowers(user1);
        verify(userDAO).getFollowers(user1);
    }

    @Test
    public void testGetTweets() {
        System.out.println("Test get tweets on Mockito - UserService layer");
        
        when(userDAO.getTweets(user1)).thenReturn(tweets);
        assertEquals(tweets, userDAO.getTweets(user1));
        userService.getTweets(user1);
        verify(userDAO).getTweets(user1);
    }

    @Test
    public void testLikeTweet() {
        System.out.println("Test get like of a tweet on Mockito - Service layer");
        
        when(userDAO.likeTweet(user1, tweet1)).thenReturn(true);
        assertEquals(true, userDAO.likeTweet(user1, tweet1));
        userService.likeTweet(user1, tweet1);
        verify(userDAO).likeTweet(user1, tweet1);
    }
}
