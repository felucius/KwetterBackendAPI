/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M
 */
@XmlRootElement
public class UserDTO {

    @XmlElement
    private String picture = null;
    @XmlElement
    private String website = null;
    @XmlElement
    private String name = null;
    @XmlElement
    private String bio = null;
    @XmlElement
    private String location = null;
    @XmlElement
    private String email = null;
    @XmlElement
    private String password = null;

    public UserDTO(){
        
    }
    
    public UserDTO(String picture, String website, String name, String bio,
            String location, String email, String password) {
        this.picture = picture;
        this.website = website;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public String getWebsite() {
        return website;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
