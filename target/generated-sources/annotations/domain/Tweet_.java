package domain;

import domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.springframework.hateoas.Link;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-11T15:11:02")
@StaticMetamodel(Tweet.class)
public class Tweet_ { 

    public static volatile ListAttribute<Tweet, User> mentions;
    public static volatile SingularAttribute<Tweet, User> tweetedBy;
    public static volatile SingularAttribute<Tweet, Link> link;
    public static volatile SingularAttribute<Tweet, String> tag;
    public static volatile SingularAttribute<Tweet, Date> published;
    public static volatile SingularAttribute<Tweet, String> message;
    public static volatile SingularAttribute<Tweet, Integer> tweetId;
    public static volatile ListAttribute<Tweet, User> likes;

}