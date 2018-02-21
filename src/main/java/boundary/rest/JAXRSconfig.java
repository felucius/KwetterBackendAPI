/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * @author M
 * 
 * Base application path configures and listen to 'api'.
 * The path 'api' is necessary to access other files within this application. 
 */
@ApplicationPath("api")
public class JAXRSconfig extends Application{
    
}
