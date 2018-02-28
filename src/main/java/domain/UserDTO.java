/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author M
 */
public class UserDTO {

    private User user = null;

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
