/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M
 */
@XmlRootElement
public class TweetDTO {
    private Tweet tweet = null;
    
    public TweetDTO(String json){
        try{
            System.out.println("TWEET JSON: " + json);
        }catch (Exception ex){
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
                    .entity("Couldn't parse JSON string: " + ex.getMessage())
                    .build());
        }
    }
    
    public Tweet getTweet(){
        return tweet;
    }
}
