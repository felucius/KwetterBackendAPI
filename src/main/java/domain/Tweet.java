/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The entity is a table in the database. In this case it is the table Tweet.
 *
 * The namedquery provides a call to receive all tweet data.
 *
 * @author M
 */
@Model
@Entity
@NamedQueries({
    @NamedQuery(name = "Tweet.getAllTweets", 
            query = "SELECT t FROM Tweet t"),
    @NamedQuery(name = "Tweet.getAllTweetsfromuser", 
            query = "SELECT u.name, t.message\n"
                    + "FROM Tweet t\n"
                    + "INNER JOIN t.tweetedBy tb\n"
                    + "INNER JOIN User u\n"
                    + "WHERE u.id = tb.id\n"
                    + "AND u.name = :userName")
})
public class Tweet implements Serializable {
    
    @OneToMany//(mappedBy = "tweet")
    @JoinTable(name = "Tweet_mentions")
    private List<User> mentions = null;
    private String message = null;
    private List<String> tags = null;
    @ManyToOne
    private User tweetedBy = null;
    @OneToMany
    @JoinTable(name = "Tweet_likes")
    private List<User> likes = null;


    /**
     * To insert a date into the database, the Temporal injection needs to be
     * added.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date published = null;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assigning primary key values to the Tweet table.
    private Long id;

    public Tweet() {

    }

    /**
     * Constructor that initiates a new Tweet
     *
     * @param message the content of the message to be posted
     * @param tags are extra features such as likes and kuddos
     * @param tweetedBy is the person who created the tweet.
     */
    public Tweet(String message, List<String> tags, User tweetedBy) {
        this.message = message;
        this.tags = tags;
        this.tweetedBy = tweetedBy;
        this.published = new Date(System.currentTimeMillis());
        this.likes = new ArrayList();
        this.mentions = new ArrayList();
    }

    /**
     * This method allows a user to like a tweet
     *
     * @param user to be added to the like
     * @return a true or false statement, depending if the person liked the
     * tweet.
     */
    public boolean likeTweet(User user) {
        if (user != null) {
            likes.add(user);
            return true;
        }
        return false;
    }
    
    /**
     * Retrieving all the likes from users.
     * 
     * @return the amount of users that liked the tweet. This can be converted
     * to the specifc user or to a number.
     */
    public List<User> getLikes(){
        return this.likes;
    }

    /**
     * This method allows a user to be added to the mentions of a tweet.
     *
     * @param mentionedUser is the user to be mentioned in the tweet.
     */
    public boolean addMention(User mentionedUser) {
        if(mentionedUser != null){
            mentions.add(mentionedUser);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Retrieving all the mentions
     * 
     * @return all mentions.
     */
    public List<User> getMentions() {
        return this.mentions;
    }

    /**
     * Setting a list of mentions
     * 
     * @param mentions to be setted.
     */
    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    /**
     * Retrieving the tweet message.
     * 
     * @return the message of the tweet.
     */
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieving the tags that is going to be added to the tweet.
     * 
     * @return tags of the tweet.
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Setting the tags of the tweet
     * 
     * @param tags to be setted per tweet.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Retrieving published date.
     * 
     * @return the date of the publishing.
     */
    public Date getPublished() {
        return this.published;
    }
    
    /**
     * Setting the published date.
     * 
     * @param published to be set. 
     */
    public void setPublished(Date published){
        this.published = published;
    }
    
    /**
     * Retrieving the id of the tweet
     * 
     * @return tweet id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setting the id of the tweet.
     * 
     * @param id to be changed.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
