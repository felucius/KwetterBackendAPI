package domain;

import domain.Tweet;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-23T14:15:22")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> website;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, User> followers;
    public static volatile ListAttribute<User, User> following;
    public static volatile ListAttribute<User, User> followingEachother;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile ListAttribute<User, Tweet> tweets;
    public static volatile SingularAttribute<User, String> picture;
    public static volatile SingularAttribute<User, String> email;

}