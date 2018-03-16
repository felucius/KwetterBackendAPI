/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import service.TweetService;
import service.UserService;

/**
 *
 * @author M
 */
@Named
@SessionScoped
@DeclareRoles({"ADMIN", "MODERATOR", "USER"})
public class GetMessagesController implements Serializable {

    @Inject
    private TweetService tweetService;
    @Inject
    private UserService userService;

    private List<Tweet> tweets = null;
    private Tweet selectedTweet = null;

    public GetMessagesController() {

    }

    @PostConstruct
    public void init() {
        tweets = tweetService.getAllTweets();
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        if (tweets != null) {
            return tweetService.getAllTweets();
        } else {
            System.out.println("No tweets available");
            return null;
        }
    }

    public void setSelectedTweet(Tweet selectedTweet) {
        if (selectedTweet != null) {
            this.selectedTweet = selectedTweet;
        } else {
            System.out.println("Selected tweet is null and cannot be set");
        }
    }

    public Tweet getSelectedTweet() {
        if (selectedTweet != null) {
            return this.selectedTweet;
        } else {
            System.out.println("Selected tweet is null and cannot be retrieved");
            return null;
        }
    }

    @RolesAllowed({"ADMIN", "MODERATOR"})
    public boolean removeTweet() {
        if (selectedTweet != null) {
            System.out.println("Message of tweet: " + selectedTweet.getMessage() + " ID:" + selectedTweet.getId());
            return userService.removeTweet(selectedTweet);
        } else {
            System.out.println("Tweet cannot be removed, tweet does not exist.");
            return false;
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Tweet Selected", ((Tweet) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
