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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

/**
 *
 * @author M
 */
@Named(value = "usertimeline")
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    private String username;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        String username = user == null ? "No username available" : user.getName();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void loadCurrentUser() {
        this.setUsername(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        User user = userService.findUserByName(username);
        this.setUser(user);
    }

    public String getAvatarURL() {
        String avatarURL = user == null ? "No avatar picture available" : user.getPicture();
        return avatarURL;
    }

    public String getBio() {
        String userBio = user == null ? "No bio available" : user.getBio();
        if (userBio == null || userBio == "") {
            userBio = "-";
        }
        return userBio;
    }

    public List<Tweet> getUserTweets() {
        return this.userService.getTweetsByUser(this.user);
        //return this.user.getTweets();
    }

    public int getFollowersAmount() {
        if (this.user == null) {
            return -1;
        }
        return this.user.getFollowers().size();
    }

    public int getFollowingAmount() {
        if (this.user == null) {
            return -1;
        }
        return this.user.getFollowing().size();
    }
}
