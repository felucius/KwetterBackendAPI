/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import domain.TweetDTO;
import domain.User;
import domain.UserDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.TweetService;
import service.UserService;
import util.TweetDomainToDto;
import util.UserDomainToDto;

/**
 * Api call where the user makes a request to the backend to receive data by
 * going to the path 'tweet', through the 'api' path, configured at JAXRSconfig.
 *
 * Stateless beans is used here, because the request is simple and does not need
 * to be memorized by the server.
 *
 * TweetResource handles all the incoming requests. This class can be seen as
 * the controller class.
 *
 * @author M
 */
@Stateless
@Path("tweets")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResource {

    /**
     * Injecting the tweetservice ensures the loosely coupled architecture and
     * removes the use of the 'new' keyword.
     */
    @Inject
    TweetService tweetService;

    @Inject
    UserService userService;

    @POST
    @Path("addmention/{tweetId}/{username}")
    public Response addMention(@PathParam("tweetId") Integer tweetId, @PathParam("username") String userName) {
        //return tweetService.addMention(findTweet(tweetId), userService.findUserByName(userName));
        return null;
    }

    @GET
    @Path("gettweetlikes/{tweetId}")
    public Response getLikes(@PathParam("tweetId") Integer tweetId) {
        List<UserDTO> dto = UserDomainToDto.USER_LIKES_TO_DTO(tweetService.getLikes(tweetService.findTweet(tweetId)));
        return Response.ok(dto).build();
        //return tweetService.getLikes(tweetService.findTweet(tweetId));
    }

    @GET
    @Path("findtweetbycontent/{content}")
    public Response findTweetByContent(@PathParam("content") String content) {
        List<TweetDTO> dto = TweetDomainToDto.TWEETS_TO_DTO(tweetService.findTweetByContent(content));
        return Response.ok(dto).build();
        //return tweetService.findTweetByContent(content);
    }

    @GET
    @Path("findtagbycontent/{content}")
    public Response findTagByContent(@PathParam("content") String content) {
        List<TweetDTO> dto = TweetDomainToDto.TWEETS_TO_DTO(tweetService.findTagByContent(content));
        return Response.ok(dto).build();
        //return tweetService.findTagByContent(content);
    }

    @GET
    @Path("findtweet/{id}")
    public Response findTweet(@PathParam("id") Integer id) {
        TweetDTO dto = TweetDomainToDto.TWEET_TO_DTO(tweetService.findTweet(id));
        return Response.ok(dto).build();
        //return tweetService.findTweet(id);
    }

    @GET
    @Path("gettweetmentions/{tweetId}")
    public List<User> getMentions(@PathParam("tweetId") Integer tweetId) {
        return tweetService.getMentions(tweetService.findTweet(tweetId));
    }

    @GET
    @Path("gettweetsfollowing/{userName}")
    public Response getTweetsOfFollowingUsers(@PathParam("userName") String userName) {
        List<TweetDTO> dto = TweetDomainToDto.TWEETS_FOLLOWING_TO_DTO(tweetService.getTweetsOfFollowingUsers(userService.findUserByName(userName)));
        return Response.ok(dto).build();
        //return tweetService.getTweetsOfFollowingUsers(userService.findUserByName(userName));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatetweet")
    public Tweet updateTweetContent(Tweet tweet) {
        return tweetService.updateTweet(tweet);
    }

    @GET
    public Response getAllTweets() {
        List<TweetDTO> dto = TweetDomainToDto.TWEETS_TO_DTO(tweetService.getAllTweets());
        return Response.ok(dto).build();
        //return tweetService.getAllTweets();
    }
}
