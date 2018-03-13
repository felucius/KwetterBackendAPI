/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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
import service.UserService;

/**
 *
 * @author M
 */
public class MockitoUserRestTest {
    
    @Mock
    private UserResource userResource = null;
    @Mock
    private UserService userService = null;
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

    public MockitoUserRestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userResource = mock(UserResource.class);
        userService = mock(UserService.class);

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

        userResource.followUser(user1.getName(), user2.getName());
        userResource.followUser(user1.getName(), user3.getName());

        userResource.followUser(user2.getName(), user1.getName());
        userResource.followUser(user2.getName(), user3.getName());
        userResource.followUser(user2.getName(), user4.getName());

        userResource.followUser(user5.getName(), user1.getName());
        userResource.followUser(user5.getName(), user6.getName());

        userResource.followUser(user7.getName(), user2.getName());

        userResource.followUser(user8.getName(), user1.getName());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("Test get all users on Mockito - UserService layer");

        when(userService.getAllUsers()).thenReturn(users);
        assertEquals(users, userService.getAllUsers());
        userResource.getAllUsers();
        verify(userService).getAllUsers();
    }

    @Test
    public void testCreateUser() {
        System.out.println("Test create user on Mockito - UserService layer");

        when(userService.createUser(user1)).thenReturn(user1);
        assertEquals(user1, userService.createUser(user1));
        userResource.createUser(user1);
        verify(userService).createUser(user1);
    }

    /*
    @Test
    public void testRemoveUser() {
        System.out.println("Test remove user on Mockito - UserService layer");

        when(userService.removeUser(user1)).thenReturn(true);
        assertEquals(true, userService.removeUser(user1));
        userResource.removeUser(user1);
        verify(userService).removeUser(user1);
    }*/

    @Test
    public void testFindUser() {
        System.out.println("Test find user on Mockito - UserService layer");

        when(userService.findUser(user1.getId())).thenReturn(user1);
        assertEquals(user1, userService.findUser(user1.getId()));
        userResource.findUser(user1.getId());
        verify(userService).findUser(user1.getId());
    }

    @Test
    public void testAddTweet() {
        System.out.println("Test add tweet on Mockito - UserService layer");

        when(userService.addTweet(user1, tweet1, users)).thenReturn(true);
        assertEquals(true, userService.addTweet(user1, tweet1, users));
        //userResource.addTweet(user1.getName(), tweet1.getMessage(), tags.get(0), 
                //users.get(user1.getId().intValue()).getName());
        verify(userService).addTweet(user1, tweet1, users);
    }

    @Test
    public void testRemoveTweet() {
        System.out.println("Test remove tweet on Mockito - UserService layer");

        when(userService.removeTweet(tweet1)).thenReturn(true);
        assertEquals(true, userService.removeTweet(tweet1));
        userResource.removeTweet(tweet1);
        verify(userService).removeTweet(tweet1);
    }

    @Test
    public void testFollowUser() {
        System.out.println("Test follow user on Mockito - UserService layer");

        when(userService.followUser(user1, user2)).thenReturn(true);
        assertEquals(true, userService.followUser(user1, user2));
        userResource.followUser(user1.getName(), user2.getName());
        verify(userService).followUser(user1, user2);
    }

    @Test
    public void testUnfollowUser() {
        System.out.println("Test unfollow user on Mockito - UserService layer");

        when(userService.unfollowUser(user1, user2)).thenReturn(true);
        assertEquals(true, userService.unfollowUser(user1, user2));
        userResource.unfollowUser(user1.getName(), user2.getName());
        verify(userService).unfollowUser(user1, user2);
    }

    @Test
    public void testGetFollowingUsers() {
        System.out.println("Test get following users on Mockito - UserService layer");

        when(userService.getFollowingUsers(user1)).thenReturn(users);
        assertEquals(users, userService.getFollowingUsers(user1));
        userResource.getFollowingUsers(user1.getName());
        verify(userService).getFollowingUsers(user1);
    }

    @Test
    public void testGetFollowers() {
        System.out.println("Test get followers on Mockito - UserService layer");

        when(userService.getFollowers(user1)).thenReturn(users);
        assertEquals(users, userService.getFollowers(user1));
        userResource.getFollowers(user1.getName());
        verify(userService).getFollowers(user1);
    }

    @Test
    public void testGetTweets() {
        System.out.println("Test get tweets on Mockito - UserService layer");

        when(userService.getTweetsByUser(user1)).thenReturn(tweets);
        assertEquals(tweets, userService.getTweetsByUser(user1));
        userResource.getTweets(user1.getName());
        verify(userService).getTweetsByUser(user1);
    }

    @Test
    public void testLikeTweet() {
        System.out.println("Test get like of a tweet on Mockito - Service layer");

        when(userService.likeTweet(user1, tweet1)).thenReturn(true);
        assertEquals(true, userService.likeTweet(user1, tweet1));
        userResource.likeTweet(user1.getName(), tweet1.getId());
        verify(userService).likeTweet(user1, tweet1);
    }
}
