/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;
import service.UserService;

/**
 *
 * @author M
 */
@Named(value = "tweettimeline")
@SessionScoped
public class TweetTimeline implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private TweetService tweetService;

    private String username;
    private User user;
    private String newKweetContent;
    private String searchUsername;
    private List<User> foundUsers = new ArrayList();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewTweetMessage() {
        return newKweetContent;
    }

    public void setNewTweetMessage(String newKweetContent) {
        this.newKweetContent = newKweetContent;
    }

    public String getSearchUsername() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public List<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public void loadCurrentUser() {
        String currentUsername = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        this.setUsername(currentUsername);
        User currentUser = userService.findUserByName(this.getUsername());
        this.setUser(currentUser);
    }

    public List<Tweet> getTimelineTweets() {
        if (this.user == null) {
            return Collections.emptyList();
        }
        return userService.getTweetsByUser(user);
    }

    public Tweet createTweet() {
        return tweetService.createTweet(new Tweet(this.getNewTweetMessage(), "#websockets", this.getUser()));
    }

    public void findUsersByUsername() {
        if (this.searchUsername != null && !this.searchUsername.equals("")) {
            if (this.foundUsers != null) {
                //this.foundUsers = new ArrayList<User>();
                User user = this.userService.findUserByName(this.searchUsername);
                this.foundUsers.add(user);
            } else {
                System.out.println("Found users list is null");
            }
        } else {
            this.foundUsers = new ArrayList<User>();
        }
    }

    public void logout() {
        this.searchUsername = "";
        this.foundUsers = new ArrayList<User>();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
    }
}
