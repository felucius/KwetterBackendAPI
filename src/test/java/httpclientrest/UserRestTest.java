/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclientrest;

import domain.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import domain.Tweet;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;

/**
 *
 * @author M
 */
@Ignore
public class UserRestTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterBackendTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private HttpUriRequest request;
    private ObjectMapper om;

    public UserRestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
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
    public void findUserById() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users/finduser/1");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            User user = om.readValue(response.getEntity().getContent(), User.class);

            assertNotNull(user.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findUserByName() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users/finduserbyname/maxime");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            User user = om.readValue(response.getEntity().getContent(), User.class);
            assertEquals("Maxime", user.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getUsers() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            List<User> resource = om.readValue(response.getEntity().getContent(), 
                    TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));

            assertEquals(10, resource.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void followUser() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users/followuser/hettie/rickert");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            boolean resource = om.readValue(response.getEntity().getContent(), Boolean.class);

            assertTrue(resource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getFollowing() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users/getfollowing/maxime");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);

            List<User> users = om.readValue(response.getEntity().getContent(), 
                    TypeFactory.defaultInstance().constructCollectionType(List.class, String[].class));

            assertEquals(3, users.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getTweetsFromUser() {
        try {
            HttpUriRequest request = new HttpGet("http://localhost:8080/KwetterBackend_Maxime/api/users/gettweetsfromuser/maxime");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            
            List<Tweet> tweets = om.readValue(response.getEntity().getContent(), 
                    TypeFactory.defaultInstance().constructCollectionType(List.class, User[].class));
            
            assertEquals(3, tweets.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
