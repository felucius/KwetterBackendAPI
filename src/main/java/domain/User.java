/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Entity
@NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u")
public class User implements Serializable {

    private String picture = null;
    private String website = null;
    private String name = null;
    private String bio = null;
    private String location = null;
    private String email = null;
    private String password = null;

    private List<Tweet> tweets = null;
    @OneToMany(fetch = FetchType.EAGER) @JoinTable(name = "USER_FOLLOWING_HOI")
    private List<User> following = null;
    @OneToMany(fetch = FetchType.EAGER) @JoinTable(name = "USER_FOLLOWERS_HOI")
    private List<User> followers = null;
    
    @ManyToMany
    @JoinTable(
        name="FOLLOWING_FOLLOWERS",
        joinColumns=@JoinColumn(name="USER_FOLLOWING_HOI", referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="USER_FOLLOWERS_HOI", referencedColumnName="ID"))
    private List<User> followingEachother;
    
    
    /**
     * ID is automatically generated per persist on the database
     *
     * GeneratedValue provides primary keys to the database table.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE) // Assigning primary keys to the table User.
    private Long id;

    /**
     * Constructor that initiates a new user with an empty list of
     * -new tweets
     * -followers
     * -following users
     */
    public User() {
        this.tweets = new ArrayList();
        this.following = new ArrayList();
        this.followers = new ArrayList();
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
        this.picture = picture;
        this.website = website;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
    }

    /**
     * This method allows a tweet to be added with a certain amount of mentions
     * 
     * @param tweet to be added and posted.
     * @param mentions are the users that are going to be mentioned in the post.
     */
    public void addTweet(Tweet tweet, List<User> mentions){
        tweets.add(tweet);
    }
    
    /**
     * This method allows a tweet to be removed.
     * 
     * @param tweet that is going to be removed.
     */
    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }
    
    /**
     * This method allows a user to be followed.
     * 
     * @param user that is going to be followed.
     */
    public void followUser(User user){
        user.addFollower(user);
    }
    
    /**
     * This method allows a new follower to be added to the user base of 
     * followers.
     * 
     * @param user that is going to follow the current user.
     */
    private void addFollower(User user){
        this.followers.add(user);
    }
    
    /**
     * This method allows a user to be removed.
     * 
     * @param user that is going to be removed from the followers base.
     */
    private void removeFollower(User user){
        this.followers.remove(user);
    }
    
    /**
     * This method returns all the followers per user
     * 
     * @return all followers.
     */
    public List<User> getFollowers(){
        return this.followers;
    }
    
    /**
     * This method allows a tweet to be added to the liked tweets.
     * 
     * @param tweet to be added to the likes.
     */
    public void likeTweet(Tweet tweet){
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
    
    @Override
    public String toString(){
        return "Name: " + this.name + " Biography: " + this.bio + 
                " Email: " + this.email + " Location: " + this.location + 
                " Picture: " + this.picture + "Website: " + this.website;                
    }
}
