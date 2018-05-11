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
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

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
            query = "SELECT t FROM Tweet t")
    ,
    @NamedQuery(name = "Tweet.getAllTweetsfromuser",
            query = "SELECT t\n"
            + "FROM Tweet t\n"
            + "INNER JOIN t.tweetedBy tb\n"
            + "INNER JOIN User u\n"
            + "WHERE u.id = tb.id\n"
            + "AND u.name = :userName")
    ,
    @NamedQuery(name = "Tweet.getLikes",
            query = "SELECT t\n"
            + "FROM Tweet t\n"
            + "INNER JOIN t.likes til\n"
            + "INNER JOIN User u\n"
            + "WHERE til.id = u.id\n"
            + "AND t.tweetId = :tweetId")
    ,
    @NamedQuery(name = "Tweet.findTweetByContent",
            query = "SELECT t "
            + "FROM Tweet t "
            + "WHERE t.message "
            + "LIKE :message")
    ,
    @NamedQuery(name = "Tweet.findTweetByTag",
            query = "SELECT t "
            + "FROM Tweet t "
            + "WHERE t.tag "
            + "LIKE :tag")
    ,
    @NamedQuery(name = "Tweet.getTweetsOfFollowers",
            query = "SELECT t\n"
            + "FROM User u, Tweet t\n"
            + "INNER JOIN t.tweetedBy tb "
            + "INNER JOIN u.following uf \n"
            + "INNER JOIN User u2 \n"
            + "WHERE tb.id = uf.id\n"
            + "AND u2.id = uf.id \n"
            + "AND u.name = :username")
    ,
    @NamedQuery(name = "Tweet.removeTweet",
            query = "DELETE FROM \n"
            + "Tweet t "
            + "WHERE t.tweetId = :tweetId")
    ,
    @NamedQuery(name = "Tweet.getTweetMentions",
            query = "SELECT tm "
            + "FROM Tweet t, User u "
            + "INNER JOIN t.mentions tm "
            + "WHERE tm.id = t.tweetId "
            + "AND u.name = :name")
})
public class Tweet extends ResourceSupport implements Serializable {

    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "Tweet_mentions")
    private List<User> mentions = null;
    private String message = null;
    private String tag = null;
    @ManyToOne()
    private User tweetedBy = null;
    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "Tweet_likes")
    private List<User> likes = null;

    private Link link = null; // = new Link("http://localhost:8080/KwetterBackend_Maxime/api/users/finduserbyname/Maxime");

    /**
     * To insert a date into the database, the Temporal injection needs to be
     * added.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date published = null;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assigning primary key values to the Tweet table.
    private Integer tweetId;

    public Tweet() {

    }

    /**
     * Constructor that initiates a new Tweet
     *
     * @param message the content of the message to be posted
     * @param tags are extra features such as likes and kuddos
     * @param tweetedBy is the person who created the tweet.
     */
    public Tweet(String message, String tag, User tweetedBy) {
        this.message = message;
        this.tag = tag;
        this.tweetedBy = tweetedBy;
        this.published = new Date(System.currentTimeMillis());
        this.likes = new ArrayList();
        this.mentions = new ArrayList();
        this.link = new Link("http://localhost:8080/KwetterBackend_Maxime/api/tweets");
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
    public List<User> getLikes() {
        return this.likes;
    }

    /**
     * Setting the likes of a tweet.
     *
     * @param likes are the user objects that like a single tweet.
     */
    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    /**
     * This method allows a user to be added to the mentions of a tweet.
     *
     * @param mentionedUser is the user to be mentioned in the tweet.
     */
    public boolean addMention(User mentionedUser) {
        if (mentionedUser != null) {
            mentions.add(mentionedUser);
            return true;
        } else {
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
    public String getTag() {
        return this.tag;
    }

    /**
     * Setting the tags of the tweet
     *
     * @param tag to be setted per tweet.
     */
    public void setTag(String tag) {
        this.tag = tag;
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
    public void setPublished(Date published) {
        this.published = published;
    }

    /**
     * Retrieving the id of the tweet
     *
     * @return tweet id.
     */
    public Integer getTweetId() {
        return tweetId;
    }

    /**
     * Setting the id of the tweet.
     *
     * @param id to be changed.
     */
    public void setTweetId(Integer id) {
        this.tweetId = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    

    public User getTweetedBy() {
        return this.tweetedBy;
    }

    public void setTweetedBy(User tweetedBy) {
        this.tweetedBy = tweetedBy;
    }

    public void removeMention(User user) {
        if (mentions.contains(user)) {
            mentions.remove(user);
        }
    }

    public void removeLike(User user) {
        if (likes.contains(user)) {
            likes.remove(user);
        }
    }

    public void removeTweetedBy(User user) {
        this.tweetedBy = null;
    }
}
