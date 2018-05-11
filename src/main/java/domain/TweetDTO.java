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
    private String tagUri;
    private String tweetedByUri;
    private String likesUri;

    public TweetDTO() {

    }

    public TweetDTO(String message, String tagUri, String tweetedByUri, String likesUri) {
        this.message = message;
        this.tagUri = tagUri;
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
        return tagUri;
    }

    public void setTag(String tagUri) {
        this.tagUri = tagUri;
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
    
    
}
