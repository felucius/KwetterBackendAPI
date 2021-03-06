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
import domain.UserGroup;
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
    private List<User> noMentions = new ArrayList();
    private List<User> users = new ArrayList();

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

    private Tweet tweet1 = null;
    private Tweet tweet2 = null;
    private Tweet tweet3 = null;
    private Tweet tweet4 = null;
    private Tweet tweet5 = null;
    private Tweet tweet6 = null;
    private Tweet tweet7 = null;
    private Tweet tweet8 = null;
    private Tweet tweet9 = null;
    private Tweet tweet10 = null;
    private Tweet tweet11 = null;
    private Tweet tweet12 = null;
    private Tweet tweet13 = null;

    @PostConstruct
    public void init() {
        System.out.println("Initializing basic database information....");
        createUsers();
        createTweets();
        createUserGroup();
    }

    public void createUsers() {
        user1 = new User("PictureURL", "Website.com", "Maxime", "Men", "Geldrop", "Maxime@hotmail.com", "Pass");
        user2 = new User("PictureURL", "Website.com", "Jaap-joris", "Men", "Veldhoven", "JaapJoris@hotmail.com", "Pass");
        user3 = new User("PictureURL", "Website.com", "Ferdinand", "Men", "Eindhoven", "Ferdinand@hotmail.com", "Pass");
        user4 = new User("PictureURL", "Website.com", "Ricardo", "Men", "Eindhoven", "Ricardo@hotmail.com", "Pass");
        user5 = new User("PictureURL", "Website.com", "Rickert", "Men", "Geldrop", "Rickert@hotmail.com", "Pass");
        user6 = new User("PictureURL", "Website.com", "Hettie", "Women", "Geldrop", "Hettie@hotmail.com", "Pass");
        user7 = new User("PictureURL", "Website.com", "Janetoine", "Women", "Veldhoven", "Janetoine@hotmail.com", "Pass");
        user8 = new User("PictureURL", "Website.com", "Angelika", "Women", "Eindhoven", "Angelika@hotmail.com", "Pass");
        user9 = new User("PictureURL", "Website.com", "Gerard", "Men", "Eindhoven", "Gerard@hotmail.com", "Pass");
        user10 = new User("PictureURL", "Website.com", "Freek", "Men", "Eindhoven", "Freek@hotmail.com", "Pass");

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

        tweet1 = new Tweet("This is my first Tweet Hello from Maxime", "#Cool", user1);
        tweet2 = new Tweet("This is my second Tweet AWESOME", "#Like", user1);
        tweet3 = new Tweet("This is my third Tweet MUCH C00l", "#Dope", user1);

        tweet4 = new Tweet("Hey everyone Look at me NO HANDS!", "#Friendly", user2);
        tweet5 = new Tweet("WHOA what was that all about!", "#Smile", user2);

        tweet6 = new Tweet("Welcome to my channel piepol", "#Kwetter", user3);
        tweet7 = new Tweet("Hellow Hello", "#Happy", user4);
        tweet8 = new Tweet("Wilkommen damen und herren", "#Kwetter", user5);
        tweet9 = new Tweet("Dikke BMW HAHA", "#Kwetter", user6);
        tweet10 = new Tweet("Waarom tweet ik? ik verveel me!", "#Smile", user7);
        tweet11 = new Tweet("This is a message from KOREA", "#Angry", user8);
        tweet12 = new Tweet("SPAIN everyone!? someone SPANISH?", "#Like", user9);
        tweet13 = new Tweet("Yes finally a highscore from a low score..", "#Cool", user10);

        userDAO.addTweet(user1, tweet1, noMentions);
        userDAO.addTweet(user1, tweet2, noMentions);
        userDAO.addTweet(user1, tweet3, noMentions);

        userDAO.addTweet(user2, tweet4, noMentions);
        userDAO.addTweet(user1, tweet5, noMentions);

        userDAO.addTweet(user3, tweet6, noMentions);
        userDAO.addTweet(user4, tweet7, noMentions);
        userDAO.addTweet(user5, tweet8, noMentions);
        userDAO.addTweet(user6, tweet9, noMentions);
        userDAO.addTweet(user7, tweet10, noMentions);
        userDAO.addTweet(user8, tweet11, noMentions);
        userDAO.addTweet(user9, tweet12, noMentions);
        userDAO.addTweet(user10, tweet13, noMentions);
        
        tweetDAO.addMention(tweet1, user10);
        tweetDAO.addMention(tweet1, user9);
        tweetDAO.addMention(tweet1, user8);
        tweetDAO.addMention(tweet1, user2);
        tweetDAO.addMention(tweet1, user3);

        userDAO.likeTweet(user1, tweet1);
        userDAO.likeTweet(user2, tweet1);
        userDAO.likeTweet(user3, tweet2);
        userDAO.likeTweet(user4, tweet3);

        userDAO.followUser(user1, user2);
        userDAO.followUser(user1, user3);
        userDAO.followUser(user1, user4);

        userDAO.followUser(user2, user3);
        userDAO.followUser(user2, user4);

        userDAO.promoteUser(user1);
        userDAO.promoteUser(user1);
        userDAO.promoteUser(user3);
    }

    public void createUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("user");

        UserGroup moderatorGroup = new UserGroup();
        moderatorGroup.setGroupName("moderator");

        UserGroup adminGroup = new UserGroup();
        adminGroup.setGroupName("admin");

        //group.addUser(user1);
        userDAO.addUserGroup(userGroup);
        userDAO.addUserGroup(moderatorGroup);
        userDAO.addUserGroup(adminGroup);

        user1.addGroup(userGroup);
        user2.addGroup(userGroup);
        user3.addGroup(userGroup);
        user4.addGroup(userGroup);
        user5.addGroup(userGroup);
        user6.addGroup(userGroup);
        user7.addGroup(userGroup);
        user8.addGroup(userGroup);
        user9.addGroup(userGroup);
        user10.addGroup(userGroup);

        user1.addGroup(moderatorGroup);
        user3.addGroup(moderatorGroup);

        user1.addGroup(adminGroup);
    }
}
