/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daodatabase;

import dao.TweetDAOCollection;
import dao.UserDAOCollection;
import domain.Tweet;
import domain.User;
import httpclientrest.UserRestTest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import util.DatabaseCleaner;

/**
 *
 * @author M
 */
@Ignore
public class UserDAOImplementationTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterBackendTestPU");
    private EntityManager em;
    private EntityTransaction et;

    public UserDAOImplementationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserRestTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        emf.createEntityManager();
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createUser() {
        System.out.println("Creating user on - DAO database implementation");

        User user = new User("Maxime");
        et.begin();
        em.persist(user);
        et.commit();

        assertNotNull(em.find(User.class, user.getId()));
    }

    @Test
    public void testAddTweet() {
        System.out.println("Adding a tweet on - DAO database implementation");
        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Maxime");
        Tweet tweet = new Tweet("test", "#cool", user);

        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();

        assertTrue("Tweet should not be null", em.createNamedQuery("Tweet.findTweetByContent").
                setParameter("message", tweet.getMessage()).getResultList().size() == 1);
    }

    @Test
    public void testRemoveTweet() {
        System.out.println("Removing a tweet on - DAO database implementation");
        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Freek");
        Tweet tweet = new Tweet("test", "#cool", user);

        et.begin();
        em.persist(user);
        em.persist(tweet);
        em.remove(tweet);
        et.commit();

        assertEquals(0, em.createNamedQuery("Tweet.findTweetByContent").setParameter("message", tweet.getMessage()).
                getResultList().size());
    }

    @Test
    public void testFollowUser() {
        System.out.println("Follow a user on - DAO database implementation");

        User user = new User("Maxime");
        User userFollowing = new User("Freek");

        user.followUser(userFollowing);

        et.begin();
        em.persist(user);
        em.persist(userFollowing);
        et.commit();

        assertEquals(1, em.createNamedQuery("User.getFollowers").
                setParameter("username", userFollowing.getName()).getResultList().size());
    }

    @Test
    public void testGetFollowers() {
        System.out.println("Get amount of followers on - DAO database implementation");

        User user = new User("Maxime");
        User userFollowing = new User("Freek");

        user.followUser(userFollowing);

        et.begin();
        em.persist(user);
        em.persist(userFollowing);
        et.commit();

        assertEquals(1, em.createNamedQuery("User.getFollowers").
                setParameter("username", userFollowing.getName()).getResultList().size());
    }

    @Test
    public void testLikeTweet() {
        TweetDAOCollection tweetDAO = new TweetDAOCollection();
        System.out.println("Get amount of likes per tweet on - DAO database implementation");

        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Maxime");
        Tweet tweet = new Tweet("test", "#cool", user);

        tweet.likeTweet(user);
        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();

        assertEquals(1, em.createNamedQuery("Tweet.getLikes").
                setParameter("tweetId", tweet.getId()).getResultList().size());
    }

    @Test
    public void testGetUserByID() {
        System.out.println("Get user by name on - DAO database implementation");

        User user = new User("Roderik");

        et.begin();
        em.persist(user);
        et.commit();

        assertNotNull(em.find(User.class, user.getId()));
    }

    @Test
    public void testUnfollowUser() {
        System.out.println("Test unfollow user on - DAO database implementation");

        User user = new User("Maxime");
        User userUnfollow = new User("Freek");

        user.unfollowUser(userUnfollow);

        et.begin();
        em.persist(user);
        em.persist(userUnfollow);
        et.commit();

        assertEquals(0, em.createNamedQuery("User.getFollowers").
                setParameter("username", userUnfollow.getName()).getResultList().size());
    }

    @Test
    public void testGetTweets() {
        System.out.println("Test get tweets on - DAO database implementation");

        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Hans");
        Tweet tweet = new Tweet("testing tweet", "#cool", user);

        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();

        assertEquals(1, em.createNamedQuery("Tweet.getAllTweets")
                .getResultList().size());
    }

}
