/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
public class UserDTO implements Serializable {

    private String picture = null;
    private String website = null;
    private String name = null;
    private String bio = null;
    private String location = null;
    private String email = null;
    private String password = null;
    private UserRole userRole = null;

    private String followingUri = null;
    private String followersUri = null;
    private String tweetsUri = null;

    public UserDTO() {

    }

    public UserDTO(String picture, String website, String name, String bio, 
            String location, String email, String password, UserRole userRole,
            String followingUri, String followersUri, String tweetsUri) {
        this.picture = "picturePath";
        this.website = website;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        
        this.followingUri = followingUri;
        this.followersUri = followersUri;
        this.tweetsUri = tweetsUri;
        this.userRole = UserRole.USER;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getTweetsUri() {
        return tweetsUri;
    }

    public void setTweetsUri(String tweetsUri) {
        this.tweetsUri = tweetsUri;
    }

    public String getFollowingUri() {
        return followingUri;
    }

    public void setFollowing(String following) {
        this.followingUri = following;
    }

    public String getFollowersUri() {
        return followersUri;
    }

    public void setFollowersUri(String followersUri) {
        this.followersUri = followersUri;
    }
    
}
