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


public class ChatMessageTest {
    
    public ChatMessageTest() {
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
     * Test of getMessage method, of class ChatMessage.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        ChatMessage instance = new ChatMessage();
        instance.setMessage("ahaha");
        String expResult = "ahaha";
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }



    /**
     * Test of getSender method, of class ChatMessage.
     */
    @Test
    public void testGetSender() {
        System.out.println("getSender");
        ChatMessage instance = new ChatMessage();
        instance.setSender("Paul");
        String expResult = "Paul";
        String result = instance.getSender();
        assertEquals(expResult, result);
    }


}
