/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import authentication.User;
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
     * Test of setName method, of class User.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        String name = "newName";
        User instance = new User("name");
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getName method, of class User.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "NAME";
        User instance = new User(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
