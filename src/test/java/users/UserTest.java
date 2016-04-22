/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import entity.DebateUser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author D062572
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setName method, of class DebateUser.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        String name = "newName";
        DebateUser instance = new DebateUser();
        instance.setUsername(name);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(name, instance.getUsername());
    }

    /**
     * Test of getName method, of class DebateUser.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "NAME";
        DebateUser instance = new DebateUser();
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
