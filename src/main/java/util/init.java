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

    @Inject @JPA
    TweetDAO tweetDAO;
    @Inject @JPA
    UserDAO userDAO;
    
    //TweetDAOImplementation tweetDAOImpl;
    //UserDAOImplementation userDAOImpl;

    @PostConstruct
    public void init() {
        //tweetDAOImpl = new TweetDAOImplementation();
        //userDAOImpl = new UserDAOImplementation();
        
        System.out.println("Initializing basic database information....");
        List<String> tags = new ArrayList();
        tags.add("#cool");
        tags.add("#like");
        
        List<User> mentions = new ArrayList();
        
        User user1 = new User("PictureURL", "Website.com", "Maxime", "Men", "Geldrop", "maxime@hotmail.com", "Pass");
        User user2 = new User("PictureURL", "Website.com", "Maxime", "Men", "Geldrop", "maxime@hotmail.com", "Pass");
        User user3 = new User("PictureURL", "Website.com", "Maxime", "Men", "Geldrop", "maxime@hotmail.com", "Pass");
        
        mentions.add(user2);
        mentions.add(user3);
        
        Tweet tweet = new Tweet("Message Hello", tags, user1);

        userDAO.createUser(user1);
        //userDAO.addTweet(user1, tweet, mentions);
    }
}
