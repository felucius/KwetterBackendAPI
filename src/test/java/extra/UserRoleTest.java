/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import domain.UserRole;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author M
 */
public class UserRoleTest {
    
    public UserRoleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class UserRole.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        UserRole[] expResult = UserRole.values();
        UserRole[] result = UserRole.values();
        assertArrayEquals(expResult, result);
        assertNotNull(result);
    }

    /**
     * Test of valueOf method, of class UserRole.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "ADMIN";
        UserRole expResult = UserRole.ADMIN;
        UserRole result = UserRole.valueOf(name);
        assertEquals(expResult, result);
    }
    
}
