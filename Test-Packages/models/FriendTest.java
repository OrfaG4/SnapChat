/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FriendTest {
    
    public FriendTest() {
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
     * Test of setMyId method, of class Friend.
     */
    @Test
    public void testSetMyId() {
        System.out.println("setMyId");
        String myId = "12";
        Friend instance = new Friend();
        instance.setMyId(myId);
        String exp=instance.getMyId();
        assertEquals(myId,exp);
    }

    
    
    


    /**
     * Test of setStatus method, of class Friend.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "0";
        Friend instance = new Friend();
        instance.setStatus(status);
        String exp = instance.getStatus();
        assertEquals(status,exp);
        
     
    }

}
