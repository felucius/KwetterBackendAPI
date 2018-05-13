/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.TweetService;
import service.UserService;

/**
 *
 * @author M
 */
@Stateless
@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
public class authentication {

    @Inject
    UserService userService;

    @Inject
    TweetService tweetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public User login(User user) {
        return userService.findUserByName(user.getName());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("generatetoken")
    public String generateServerToken(User user) {
        String compactJws = Jwts.builder()
                .setSubject(user.getName())
                .signWith(SignatureAlgorithm.HS512, "MaximeKwetter")
                .compact();
        System.out.println("Token generated: " + compactJws);
        return compactJws;
    }
}
