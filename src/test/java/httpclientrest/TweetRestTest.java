/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclientrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import domain.Tweet;
import domain.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
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
public class TweetRestTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterBackendTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private HttpUriRequest request;
    private ObjectMapper om;

    public TweetRestTest() {
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
        om = new ObjectMapper();
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addmention() {
        try {
            HttpUriRequest request = new HttpPost("http://localhost:8080/KwetterBackend_Maxime/api/tweets/addmention/2/maxime");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            Boolean result = om.readValue(response.getEntity().getContent(), Boolean.class);

            assertTrue(result);
        } catch (Exception ex) {
            {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void getTweetLikes() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/tweets/gettweetlikes/2");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            List<User> users = om.readValue(response.getEntity().getContent(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, String[].class));

            assertEquals(0, users.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findTweetByContent() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/tweets/findtweetbycontent/hellow");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            List<Tweet> tweets = om.readValue(response.getEntity().getContent(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, String[].class));

            assertEquals(1, tweets.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findTweetById() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/tweets/findtweet/1");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            Tweet tweet = om.readValue(response.getEntity().getContent(), Tweet.class);

            assertNotNull(tweet.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getTweetsOfFollowing() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/tweets/gettweetsfollowing/maxime");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            List<Tweet> tweets = om.readValue(response.getEntity().getContent(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, String[].class));

            assertEquals(3, tweets.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
