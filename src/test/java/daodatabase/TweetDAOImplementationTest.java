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
import util.DatabaseCleaner;

/**
 *
 * @author M
 */
public class TweetDAOImplementationTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterBackendTestPU");
    private EntityManager em;
    private EntityTransaction et;

    public TweetDAOImplementationTest() {
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
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetMentions() {
        System.out.println("Test get mentions on - Tweet DAO Database implementation");
        User user = new User("Maxime");
        Tweet tweet = new Tweet();
        tweet.addMention(user);
        
        et.begin();
        em.persist(tweet);
        et.commit();
        
        //tweetDAO.addMention(tweet1, user1);
        //tweetDAO.addMention(tweet1, user2);
        //assertEquals(2, tweetDAO.getMentions(tweet1).size());
    }

    @Test
    public void testAddMention() {
        System.out.println("Test add mentions on - Tweet DAO collection");
        //tweetDAO.addMention(tweet1, user1);

        //assertEquals(1, tweetDAO.getMentions(tweet1).size());
    }

    @Test
    public void testGetTweetsOfFollowingUsers() {
        System.out.println("Test get tweets of following users on - Tweet DAO collection");
        //userDAO.addTweet(user1, tweet1, users);
        //assertEquals(1, tweetDAO.getTweetsOfFollowingUsers(user1).size());
    }

    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets on - Tweet DAO collection");
        //assertEquals(0, tweetDAO.getAllTweets().size());
    }
}
