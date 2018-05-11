package util;

import domain.Tweet;
import domain.TweetDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxime
 */
public class TweetDomainToDto {

    private static String apiUri = "http://localhost:8080/KwetterBackend_Maxime/api/";

    /**
     * ****************************************
     * TWEETS *****************************************
     */
    public static List<TweetDTO> TWEETS_TO_DTO(List<Tweet> tweets) {
        List<TweetDTO> tweetsDTOs = new ArrayList<>();
        if (tweets == null || tweets.isEmpty()) {
            return tweetsDTOs;
        }

        for (Object t : tweets) {
            Tweet tweet = (Tweet) t;
            String hashtag = tweet.getTag();
            String noHashtag = hashtag.substring(1);

            TweetDTO tweetDTO = new TweetDTO(
                    tweet.getMessage(),
                    apiUri + "tweets/" + "findtagbycontent/" + noHashtag,
                    apiUri + "users/" + "finduserbyname/" + tweet.getTweetedBy().getName(),
                    apiUri + "tweets/" + "gettweetlikes/" + tweet.getTweetId());
            tweetsDTOs.add(tweetDTO);
            tweetsDTOs.add(tweetDTO);
        }

        return tweetsDTOs;
    }

    public static TweetDTO TWEET_TO_DTO(Tweet tweet) {
        TweetDTO tweetDTO = new TweetDTO(null, null, null, null);
        if (tweet == null) {
            return tweetDTO;
        }

        String hashtag = tweet.getTag();
        String noHashtag = hashtag.substring(1);

        tweetDTO = new TweetDTO(
                tweet.getMessage(),
                apiUri + "tweets/" + "findtagbycontent/" + noHashtag,
                apiUri + "users/" + "finduserbyname/" + tweet.getTweetedBy().getName(),
                apiUri + "tweets/" + "gettweetlikes/" + tweet.getTweetId());
        return tweetDTO;
    }

    /**
     * ****************************************
     * ` FOLLOWERS AND FOLLOWING *****************************************
     */
    public static List<TweetDTO> TWEETS_FOLLOWING_TO_DTO(List<Tweet> tweets) {
        List<TweetDTO> tweetsDTOs = new ArrayList<>();
        if (tweets == null || tweets.isEmpty()) {
            return tweetsDTOs;
        }

        for (Tweet tweet : tweets) {

            String hashtag = tweet.getTag();
            String noHashtag = hashtag.substring(1);

            TweetDTO tweetDTO = new TweetDTO(
                    tweet.getMessage(),
                    apiUri + "tweets/" + "findtagbycontent/" + noHashtag,
                    apiUri + "users/" + "finduserbyname/" + tweet.getTweetedBy().getName(),
                    apiUri + "tweets/" + "gettweetlikes/" + tweet.getTweetId());
            tweetsDTOs.add(tweetDTO);
        }
        return tweetsDTOs;
    }
}
