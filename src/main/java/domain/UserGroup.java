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
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.groupName);
        hash = 89 * hash + Objects.hashCode(this.users);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserGroup other = (UserGroup) obj;
        if (!Objects.equals(this.groupName, other.groupName)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }
    
    
}
