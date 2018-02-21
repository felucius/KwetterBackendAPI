/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * *A stateless DAO does not hold the data for any particular user. The data is 
 * been given and after that it does not hold any information in cache.
 * 
 * @author M
 */
@Stateless
public class UserDAO {
    /**
     * Persisting the context of the entity manager. By doing this methods data
     * can be requested or persisted to the database.
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * Requesting all users through the Entity manager. The entity manager makes a
     * call to the database with the Persistence Context.
     * 
     * @return all the users.
     */
    public List<User> getAllUsers(){
        return em.createNamedQuery("User.getAllUsers").getResultList();
    }
    
    /**
     * This method allows a new user to be created and be stored into the database
     * 
     * @param user is the object that is going to be persisted to the database.
     */
    public void createUser(User user){
        em.persist(user);
    }
    
    /**
     * This method allows a user that exists in the database, to be removed
     * 
     * @param id is the id of the user object that is going to be removed 
     * from the database.
     */
    public void removeUser(Long id){
        em.remove(id);
    }
    
    /**
     * This method allows a user to be found from the User entity with it's 
     * given id.
     * 
     * @param id is the parameter that is going to be needed to find a specific
     * user from the database
     * @return the value as a User object.
     */
    public User findUser(Long id){
        return em.find(User.class, id);
    }
}
