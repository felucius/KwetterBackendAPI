/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Tweet;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;
import service.UserService;

/**
 *
 * @author M
 */
@Named
@SessionScoped
public class GetMessagesController implements Serializable{
    @Inject
    private TweetService tweetService;
    
    private List<Tweet> tweets = null;
    
    public GetMessagesController(){
        
    }
    
    @PostConstruct
    public void init(){
        tweets = tweetService.getAllTweets();
    }
    
    public void setTweets(List<Tweet> tweets){
        this.tweets = tweets;
    }
    
    public List<Tweet> getTweets(){
        return this.tweets;
    }
}
