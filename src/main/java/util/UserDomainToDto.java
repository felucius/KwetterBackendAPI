/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Tweet;
import domain.User;
import domain.UserDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
public class UserDomainToDto {

    private static String apiUri = "http://localhost:8080/KwetterBackend_Maxime/api/";
    private static List<User> newUsers = new ArrayList();

    /**
     * ****************************************
     * ` USERS *****************************************
     */
    public static List<UserDTO> USERS_TO_DTO(List<User> users) {
        List<UserDTO> userDtos = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return userDtos;
        }

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getPicture(),
                    user.getWebsite(),
                    user.getName(),
                    user.getBio(),
                    user.getLocation(),
                    user.getEmail(),
                    user.getPassword(),
                    apiUri + "users/" + "getfollowing/" + user.getName(),
                    apiUri + "users/" + "getfollowers/" + user.getName(),
                    apiUri + "users/" + "gettweetsfromuser/" + user.getName());
            userDtos.add(userDTO);
        }
        return userDtos;
    }

    public static UserDTO USER_TO_DTO(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getPicture(), user.getWebsite(), user.getName(), 
                user.getBio(), user.getLocation(), user.getEmail(), user.getPassword(), null, null, null);
        if (user == null) {
            return userDTO;
        }

        userDTO = new UserDTO(
                user.getId(),
                user.getPicture(),
                user.getWebsite(),
                user.getName(),
                user.getBio(),
                user.getLocation(),
                user.getEmail(),
                user.getPassword(),
                apiUri + "users/" + "getfollowing/" + user.getName(),
                apiUri + "users/" + "getfollowers/" + user.getName(),
                apiUri + "users/" + "gettweetsfromuser/" + user.getName());
        return userDTO;
    }

    /**
     * ****************************************
     * ` FOLLOWERS AND FOLLOWING *****************************************
     */
    public static List<UserDTO> FOLLOWERS_TO_DTO(List<User> users) {
        List<UserDTO> userDtos = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return userDtos;
        }

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getPicture(),
                    user.getWebsite(),
                    user.getName(),
                    user.getBio(),
                    user.getLocation(),
                    user.getEmail(),
                    user.getPassword(),
                    apiUri + "users/" + "getfollowing/" + user.getName(),
                    apiUri + "users/" + "getfollowers/" + user.getName(),
                    apiUri + "users/" + "gettweetsfromuser/" + user.getName());
            userDtos.add(userDTO);
        }
        return userDtos;
    }

    public static List<UserDTO> FOLLOWINGS_TO_DTO(List<User> users) {
        List<UserDTO> userDtos = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return userDtos;
        }

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getPicture(),
                    user.getWebsite(),
                    user.getName(),
                    user.getBio(),
                    user.getLocation(),
                    user.getEmail(),
                    user.getPassword(),
                    apiUri + "users/" + "getfollowing/" + user.getName(),
                    apiUri + "users/" + "getfollowers/" + user.getName(),
                    apiUri + "users/" + "gettweetsfromuser/" + user.getName());
            userDtos.add(userDTO);
        }
        return userDtos;
    }

    public static List<UserDTO> USER_LIKES_TO_DTO(List<User> users) {
        List<UserDTO> userDtos = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return userDtos;
        }

        for (Object t : users) {
            Tweet tweet = (Tweet) t;

            newUsers = tweet.getLikes();
        }

        for (User user : newUsers) {
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getPicture(),
                    user.getWebsite(),
                    user.getName(),
                    user.getBio(),
                    user.getLocation(),
                    user.getEmail(),
                    user.getPassword(),
                    apiUri + "users/" + "getfollowing/" + user.getName(),
                    apiUri + "users/" + "getfollowers/" + user.getName(),
                    apiUri + "users/" + "gettweetsfromuser/" + user.getName());
            userDtos.add(userDTO);
        }

        /*
        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getPicture(),
                    user.getWebsite(),
                    user.getName(),
                    user.getBio(),
                    user.getLocation(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserRole(),
                    apiUri + "users/" + "getfollowing/" + user.getName(),
                    apiUri + "users/" + "getfollowers/" + user.getName(),
                    apiUri + "users/" + "gettweetsfromuser/" + user.getName());
            userDtos.add(userDTO);
        }*/
        return userDtos;
    }

}
