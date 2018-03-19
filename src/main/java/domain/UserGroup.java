/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M
 */
@Entity
@Table(name = "UserGroup")
public class UserGroup implements Serializable {

    @Id
    private String groupName;
    
    @ManyToMany
    @JoinTable(name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "groupName",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "name",
                    referencedColumnName = "name"))
    private final List<User> users = new ArrayList();

    public UserGroup() {

    }
    
    public UserGroup(String groupName){
        this.groupName = groupName;
    }
    
    public String getGroupName(){
        return this.groupName;
    }
    
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    
    public List<User> getUsers(){
        return this.users;
    }
    
    public void addUser(User user){
        users.add(user);
    }
    
    public void removeUser(User user){
        users.remove(user);
    }
}
