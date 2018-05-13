/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author M
 */
public class TweetDTO implements Serializable{

    private String message;
    private String tag;
    private String tagUri;
    private User tweetedBy;
    private String tweetedByUri;
    private String likesUri;

    public TweetDTO() {

    }

    public TweetDTO(String message, String tag, String tagUri, User tweetedBy, String tweetedByUri, String likesUri) {
        this.message = message;
        this.tag = tag;
        this.tagUri = tagUri;
        this.tweetedBy = tweetedBy;
        this.tweetedByUri = tweetedByUri;
        this.likesUri = likesUri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTweetedByUri() {
        return tweetedByUri;
    }

    public void setTweetedByUri(String tweetedBy) {
        this.tweetedByUri = tweetedBy;
    }

    public String getLikesUri() {
        return likesUri;
    }

    public void setLikesUri(String likesUri) {
        this.likesUri = likesUri;
    }

    public String getTagUri() {
        return tagUri;
    }

    public void setTagUri(String tagUri) {
        this.tagUri = tagUri;
    }

    public User getTweetedBy() {
        return tweetedBy;
    }

    public void setTweetedBy(User tweetedBy) {
        this.tweetedBy = tweetedBy;
    }
    
    
}
