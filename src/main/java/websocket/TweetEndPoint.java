/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import domain.Tweet;
import domain.TweetDTO;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import service.TweetService;
import service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *
 * @author M
 */
@ServerEndpoint(value = "/tweetendpoint")
public class TweetEndPoint {

    @Inject
    private TweetService tweetService;

    @Inject
    private UserService userService;

    private static final Logger LOG = Logger.getLogger(TweetEndPoint.class.getName());
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        if(peer != null){
            System.out.println("OnOpen: " + peer.getId());
        }
        LOG.info("Connection opened ...");
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        if(peer != null){
            System.out.println("OnClose: " + peer.toString());
        }
        LOG.info("Connection closed ...");
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.INFO, "Error" + t.getMessage());
    }

    @OnMessage
    public void onMessage(final Session client, final String message) {
        if (message != null) {
            System.out.println("OnMessage: " + client.getId() + " Message: " + message);
            Tweet tweet = new Tweet(message, "#websocket", userService.findUserByName("Maxime"));
            TweetDTO jsonTweet = new TweetDTO(tweet.getMessage(), tweet.getTag(), 
                    tweet.getTweetedBy().getName(), String.valueOf(tweet.getTweetId()));
            sendMessage(client, jsonTweet);
            sentToAll(jsonTweet);
        }
    }

    private void sendMessage(Session peer, Object send) {
        try {
            if (peer.isOpen()) {
                if (peer != null && send != null) {
                    System.out.println("Sendmessage: " + peer.getId() + " Object: " + send);
                }
                ObjectMapper mapper = new ObjectMapper();
                peer.getBasicRemote().sendObject(mapper.writeValueAsString(send));
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void sentToAll(Object answer) {
        if(answer != null){
            System.out.println("SentToAll: " + answer.toString());
        }
        for (Iterator<Session> it = peers.iterator(); it.hasNext();) {
            sendMessage(it.next(), answer);
        }
    }
}
