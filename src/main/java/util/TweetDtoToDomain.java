package util;

import domain.Tweet;
import domain.TweetDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxime
 */
public class TweetDtoToDomain {

    public static List<Tweet> TWEET_DTO_TO_DOMAIN(List<TweetDTO> tweetDTOs) {
        List<Tweet> tweets = new ArrayList<>();
        if (tweetDTOs == null || tweetDTOs.isEmpty()) {
            return tweets;
        }

        for (TweetDTO t : tweetDTOs) {
            Tweet tweet = new Tweet(t.getMessage(), t.getTag(), null);
            tweets.add(tweet);
        }
        return tweets;
    }

    public static Tweet VEHICLE_DTO_TO_DOMAIN(TweetDTO tweetDTO) {
        if (tweetDTO == null) {
            return new Tweet();
        }
        return new Tweet(tweetDTO.getMessage(), tweetDTO.getTag(), null);
    }
}
