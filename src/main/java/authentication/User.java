/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

/**
 *
 * @author D062572
 */
public class User {
    private String name;
    private String password;

    public static final String DUMMY_NAME1 = "DUMMY1";
    public static final String DUMMY_PW1 = "DUMMY1";
    public static final String DUMMY_NAME2 = "DUMMY2";
    public static final String DUMMY_PW2 = "DUMMY2";
    
    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
   
   
   
}
