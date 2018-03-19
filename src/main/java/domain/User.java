/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class is the template of a single user.
 *
 * The entity is the table that is being persisted into the database. The named
 * query executes a query to receive information from the database
 *
 * @author M
 */
@Model
@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAllUsers",
            query = "SELECT u FROM User u")
    ,
    @NamedQuery(name = "User.getFollowingUsers",
            query = "SELECT u2.name, u2.id\n"
            + "FROM User u\n"
            + "INNER JOIN u.following uf \n"
            + "INNER JOIN User u2 \n"
            + "WHERE u2.id = uf.id \n"
            + "AND u.name = :username")
    ,
    @NamedQuery(name = "User.getFollowers",
            query = "SELECT u2.name, u2.id\n"
            + "FROM User u\n"
            + "INNER JOIN u.followers uf \n"
            + "INNER JOIN User u2 \n"
            + "WHERE u2.id = uf.id \n"
            + "AND u.name = :username")
    ,
    @NamedQuery(name = "User.findUserByName",
            query = "SELECT u "
            + "FROM User u "
            + "where u.name = :name")
})

public class User implements Serializable {

    private String picture = null;
    private String website = null;
    private String name = null;
    private String bio = null;
    private String location = null;
    private String email = null;
    private String password = null;
    private UserRole userRole = null;

    // When a user is removed it's tweets are removed as well.
    @OneToMany(mappedBy = "tweetedBy", cascade = CascadeType.ALL)
    private List<Tweet> tweets = null;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_following")
    private List<User> following = null;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_followers")
    private List<User> followers = null;

    @ManyToMany(mappedBy = "users")
    private List<UserGroup> groups;

    /**
     * ID is automatically generated per persist on the database.
     *
     * GeneratedValue provides primary keys to the database table.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assigning primary keys to the table User.
    private Long id;
    @ManyToOne
    private Tweet tweet;

    /**
     * Constructor that initiates a new user with an empty list of -new tweets
     * -followers -following users
     */
    public User() {
        this.picture = "picturePath";
        this.tweets = new ArrayList();
        this.following = new ArrayList();
        this.followers = new ArrayList();
        this.userRole = UserRole.USER;
    }

    /**
     * Constructor that creates a new user
     *
     * @param picture the picture of the profile
     * @param website of choice from the user
     * @param name the name of the user
     * @param bio the biography of the user
     * @param location of residence
     * @param email of the user
     * @param password of account
     */
    public User(String picture, String website, String name, String bio,
            String location, String email, String password) {
        this.website = website;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
        this.userRole = UserRole.USER;

        this.picture = "picturePath";
        this.tweets = new ArrayList();
        this.following = new ArrayList();
        this.followers = new ArrayList();
    }

    public User(String name) {
        this.name = name;
        this.picture = "picturePath";
        this.tweets = new ArrayList();
        this.following = new ArrayList();
        this.followers = new ArrayList();
    }

    /**
     * This method allows a tweet to be added with a certain amount of mentions
     *
     * @param tweet to be added and posted.
     * @param mentions are the users that are going to be mentioned in the post.
     */
    public void addTweet(Tweet tweet, List<User> mentions) {
        tweets.add(tweet);
    }

    /**
     * This method allows a tweet to be removed.
     *
     * @param tweet that is going to be removed.
     */
    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
    }

    /**
     * This method allows a user to be followed.
     *
     * @param user that is going to be followed.
     */
    public void followUser(User user) {
        if (!this.following.contains(user)) {
            user.addFollower(this);
            following.add(user);
        }
    }

    /**
     * This method allows a new follower to be added to the user base of
     * followers.
     *
     * @param user that is going to follow the current user.
     */
    private void addFollower(User user) {
        this.followers.add(user);
    }

    private void removeFollower(User user) {
        this.followers.remove(user);
    }

    /**
     * This method allows a user to be removed.
     *
     * @param user that is going to be removed from the followers base.
     */
    public void unfollowUser(User user) {
        if (this.following.contains(user)) {
            user.removeFollower(this);
            this.following.remove(user);
        }
    }

    /**
     * This method returns all the followers per user
     *
     * @return all followers.
     */
    @JsonbTransient
    public List<User> getFollowers() {
        return followers;
    }

    /**
     * Setting the followers of a single user.
     *
     * @param followers
     */
    @JsonbTransient
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    /**
     * This method returns all the users, that the user object follows.
     *
     * @return all following users from a specific user.
     */
    @JsonbTransient
    public List<User> getFollowing() {
        return following;
    }

    /**
     * Setting the following users of a single user.
     *
     * @param following
     */
    @JsonbTransient
    public void setFollowing(List<User> following) {
        this.following = following;
    }

    /**
     * This method allows a tweet to be added to the liked tweets.
     *
     * @param tweet to be added to the likes.
     */
    public void likeTweet(Tweet tweet) {
        tweet.likeTweet(this);
    }

    /**
     * Retrieving the picture of the user.
     *
     * @return picture of user.
     */
    public String getPicture() {
        return this.picture;
    }

    /**
     * Setting the new picture of the user.
     *
     * @param picture of the user that is going to be set.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Retrieving the website of the user.
     *
     * @return the website.
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * Setting the website of the user.
     *
     * @param website of the users that is going to be set.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Retrieving the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the user.
     *
     * @param name of the user that is going to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieving the bio of the user.
     *
     * @return the bio.
     */
    public String getBio() {
        return this.bio;
    }

    /**
     * Setting the new bio of the user.
     *
     * @param bio that is going to be set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Retrieving the location of the user.
     *
     * @return the location of the user.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Setting the location of the user
     *
     * @param location to be set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retrieving the email of the user.
     *
     * @return email of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setting the email of the user.
     *
     * @param email of the user to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieving the password of the user.
     *
     * @return password of user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setting the password of the user.
     *
     * @param password of the user that is going to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setting new tweets to the list.
     *
     * @param tweets to be added.
     */
    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    /**
     * Retrieving all tweets that the user made.
     *
     * @return all tweets from the user.
     */
    @JsonbTransient
    public List<Tweet> getTweets() {
        return this.tweets;
    }

    /**
     * Setting the id to a new one.
     *
     * @param id to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieving the id of a single user.
     *
     * @return the id of a user.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setting the user role of a single user object.
     *
     * @param userRole to be added to the user.
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Retrieving the user role of a single user object.
     *
     * @return the user object.
     */
    public UserRole getUserRole() {
        return this.userRole;
    }

    /**
     * This method updates a single user object with new account information
     *
     * @param user is the object that received new values.
     * @return a new user object filled with user data.
     */
    public User updateUser(User user) {
        user.setBio(user.getBio());
        user.setEmail(user.getEmail());
        user.setLocation(user.getLocation());
        user.setPassword(user.getPassword());
        user.setPicture(user.getPicture());
        user.setWebsite(user.getWebsite());

        return user;
    }

    /**
     * This method allows a user to be promoted.
     *
     * @param user is the object that is going to be promoted.
     * @return true if the user has successfully been promoted or false when the
     * action could not have been succeeded.
     */
    public UserRole promoteUser(User user) {
        switch (user.getUserRole()) {
            case USER:
                userRole = UserRole.MODERATOR;
                break;
            case MODERATOR:
                userRole = UserRole.ADMIN;
                break;
            case ADMIN:
                // Do Nothing
                break;
            default:
                userRole = UserRole.USER;
        }
        return userRole;
    }

    /**
     * This method allows a user to be demoted.
     *
     * @param user is the object that is going to be demoted.
     * @return true if the user has successfully been demoted or false when the
     * action could not have been succeeded.
     */
    public UserRole demoteUser(User user) {
        switch (user.getUserRole()) {
            case USER:
                // Do nothing
                break;
            case MODERATOR:
                userRole = UserRole.USER;
                break;
            case ADMIN:
                userRole = UserRole.MODERATOR;
                break;
            default:
                return userRole;
        }
        return userRole;
    }
    
    public void addGroup(UserGroup group){
        this.groups.add(group);
        group.addUser(this);
    }
}
