/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.JPA;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author M
 */
@Startup
@Singleton
public class init {

    @Inject
    @JPA
    TweetDAO tweetDAO;
    @Inject
    @JPA
    UserDAO userDAO;

    private List<String> tags = new ArrayList();
    private List<User> mentions = new ArrayList();

    private User user1 = null;
    private User user2 = null;
    private User user3 = null;
    private User user4 = null;
    private User user5 = null;
    private User user6 = null;
    private User user7 = null;
    private User user8 = null;
    private User user9 = null;
    private User user10 = null;
    private Tweet tweet = null;

    @PostConstruct
    public void init() {
        System.out.println("Initializing basic database information....");
        createUsers();
        createTweets();
    }

    public void createUsers() {
        user1 = new User("PictureURL", "Website.com", "Maxime de Lange", "Men", "Geldrop", "Maxime@hotmail.com", "Pass");
        user2 = new User("PictureURL", "Website.com", "Jaap-joris janssen", "Men", "Veldhoven", "JaapJoris@hotmail.com", "Pass");
        user3 = new User("PictureURL", "Website.com", "Ferdinand dn Droak", "Men", "Eindhoven", "Ferdinand@hotmail.com", "Pass");
        user4 = new User("PictureURL", "Website.com", "Ricardo de schilder", "Men", "Eindhoven", "Ricardo@hotmail.com", "Pass");
        user5 = new User("PictureURL", "Website.com", "Rickert batsbak", "Men", "Geldrop", "Rickert@hotmail.com", "Pass");
        user6 = new User("PictureURL", "Website.com", "Hettie de flettie", "Women", "Geldrop", "Hettie@hotmail.com", "Pass");
        user7 = new User("PictureURL", "Website.com", "Janetoine van achter", "Women", "Veldhoven", "Janetoine@hotmail.com", "Pass");
        user8 = new User("PictureURL", "Website.com", "Angelika ruslofski", "Women", "Eindhoven", "Angelika@hotmail.com", "Pass");
        user9 = new User("PictureURL", "Website.com", "Gerard broekmans", "Men", "Eindhoven", "Gerard@hotmail.com", "Pass");
        user10 = new User("PictureURL", "Website.com", "Freek de bleek", "Men", "Eindhoven", "Freek@hotmail.com", "Pass");

        mentions.add(user2);
        mentions.add(user3);

        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        userDAO.createUser(user4);
        userDAO.createUser(user5);
        userDAO.createUser(user6);
        userDAO.createUser(user7);
        userDAO.createUser(user8);
        userDAO.createUser(user9);
        userDAO.createUser(user10);
    }

    public void createTweets() {
        tags.add("#cool");
        tags.add("#like");
        tweet = new Tweet("Message Hello", tags, user1);
        //userDAO.addTweet(user1, tweet, mentions);
    }
}
