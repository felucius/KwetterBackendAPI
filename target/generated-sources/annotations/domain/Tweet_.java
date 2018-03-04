package domain;

import domain.User;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-04T01:03:34")
@StaticMetamodel(Tweet.class)
public class Tweet_ { 

    public static volatile ListAttribute<Tweet, User> mentions;
    public static volatile SingularAttribute<Tweet, User> tweetedBy;
    public static volatile SingularAttribute<Tweet, Date> published;
    public static volatile SingularAttribute<Tweet, Long> id;
    public static volatile SingularAttribute<Tweet, String> message;
    public static volatile SingularAttribute<Tweet, List> tags;
    public static volatile ListAttribute<Tweet, User> likes;

}