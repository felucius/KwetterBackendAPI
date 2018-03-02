/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author M
 */
public class UserDTO {

    private User user = null;

    /*
    public UserDTO(JsonObject input){
        this.user.setName(input.getString("username"));
        this.user.setBio(input.getString("bio"));
        this.user.setEmail(input.getString("email"));
        this.user.setId(input.getJsonNumber("id").longValue());
        this.user.setLocation(input.getString("location"));
        this.user.setPicture(input.getString("picture"));
        this.user.setWebsite(input.getString("website"));
        this.user.setPassword(input.getString("password"));
    }*/
    
    
    public JsonObject toJSON(){
        return Json.createObjectBuilder().
                add("username", this.user.getName()).
                add("bio", this.user.getBio()).
                add("email", this.user.getEmail()).
                add("id", this.user.getId()).
                add("location", this.user.getLocation()).
                add("picture", this.user.getPicture()).
                add("website", this.user.getWebsite()).
                add("password", this.user.getPassword()).
                build();
    }
    
    
    public UserDTO(String json) {
        try {
            System.out.println("USER JSON: " + json);
        } catch (Exception ex) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Couldn't parse JSON string: " + ex.getMessage())
                    .build());
        }
    }

    public User getUser() {
        return user;
    }
}
