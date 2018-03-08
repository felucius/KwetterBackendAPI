/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daodatabase;

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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import util.DatabaseCleaner;

/**
 *
 * @author M
 */
@Ignore
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
        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Maxime");
        Tweet tweet = new Tweet("test", tags, user);
        tweet.addMention(user);

        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();

        assertEquals(1, em.find(Tweet.class, tweet.getId()).getMentions().size());
    }

    @Test
    public void testAddMention() {
        System.out.println("Test add mentions on - Tweet DAO database implementation");

        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Maxime");
        Tweet tweet = new Tweet("test", tags, user);
        tweet.addMention(user);

        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();

        assertEquals(1, em.find(Tweet.class, tweet.getId()).getMentions().size());
    }

    @Test
    public void testGetAllTweets() {
        System.out.println("Test get all tweets on - Tweet DAO collection");

        List<String> tags = new ArrayList();
        tags.add("#cool");
        User user = new User("Maxime");
        Tweet tweet = new Tweet("test", tags, user);
        
        et.begin();
        em.persist(user);
        em.persist(tweet);
        et.commit();
        
        
        assertEquals(1, em.createNamedQuery("Tweet.getAllTweets").getResultList().size());
    }
}
