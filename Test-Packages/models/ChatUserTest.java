/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author TROUP
 */
public class ChatUserTest {
    
    ChatUser mockObj = new ChatUser("1","First","Last","user","pass","a@b.com");
    
    public ChatUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockObj = mock(ChatUser.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class ChatUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        assertNotNull(instance);
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLname method, of class ChatUser.
     */
    @Test
    public void testGetLname() {
        System.out.println("getLname");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        String expResult = "Last";
        String result = instance.getLname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFname method, of class ChatUser.
     */
    @Test
    public void testGetFname() {
        System.out.println("getFname");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        String expResult = "First";
        String result = instance.getFname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getUsername method, of class ChatUser.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        String expResult = "user";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class ChatUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        String expResult = "pass";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class ChatUser.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        ChatUser instance = new ChatUser("1","First","Last","user","pass","a@b.com");
        String expResult = "a@b.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class ChatUser.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        ChatUser instance = new ChatUser();
        int expResult = 0;
        int result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ChatUser.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "5";
        ChatUser instance = new ChatUser();
        instance.setId(id);
        assertEquals(id,instance.getId());
    }

    /**
     * Test of setLname method, of class ChatUser.
     */
    @Test
    public void testSetLname() {
        System.out.println("setLname");
        String lname = "Last";
        ChatUser instance = new ChatUser();
        instance.setLname(lname);
        assertEquals(lname,instance.getLname());
    }

    /**
     * Test of setFname method, of class ChatUser.
     */
    @Test
    public void testSetFname() {
        System.out.println("setFname");
        String fname = "First";
        ChatUser instance = new ChatUser();
        instance.setFname(fname);
        assertEquals(fname,instance.getFname());
    }

    /**
     * Test of setUsername method, of class ChatUser.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "user";
        ChatUser instance = new ChatUser();
        instance.setUsername(username);
        assertEquals(username,instance.getUsername());
    }

    /**
     * Test of setPassword method, of class ChatUser.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "pass";
        ChatUser instance = new ChatUser();
        instance.setPassword(password);
        assertEquals(password,instance.getPassword());
    }

    /**
     * Test of setEmail method, of class ChatUser.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "a@b.com";
        ChatUser instance = new ChatUser();
        instance.setEmail(email);
        assertEquals(email,instance.getEmail());
    }

    /**
     * Test of setStatus method, of class ChatUser.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int status = 1;
        ChatUser instance = new ChatUser();
        instance.setStatus(status);
        assertEquals(status,instance.getStatus());
    }

    /**
     * Test of LoginUser method, of class ChatUser.
     */
    @Test
    public void testLoginUser() {
        System.out.println("LoginUser");
        String username = "admin";
        String password = "1111";
        boolean expResult = true;
        boolean result = ChatUser.LoginUser(username, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLoginUserWrongInput() {
        System.out.println("LoginUser");
        String username = "wrong";
        String password = "wrong";
        boolean result = ChatUser.LoginUser(username, password);
        assertFalse(result);
    }

    /**
     * Test of getUser method, of class ChatUser.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        ChatUser instance = new ChatUser();
        instance.setUsername("admin");
        instance.getUser();
        assertNotNull(instance.getId());
    }

    /**
     * Test of getAllUsers method, of class ChatUser.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        ChatUser instance = new ChatUser("1","John","Doe","admin","1111","a@b.com");
        ArrayList<ChatUser> result = instance.getAllUsers();
        assertTrue(result.size()>0);
    }

    /**
     * Test of register method, of class ChatUser.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        mockObj.register();
    }

    /**
     * Test of getNotMyFriends method, of class ChatUser.
     */
    @Test
    public void testGetNotMyFriends() throws Exception {
        System.out.println("getNotMyFriends");
        ChatUser instance = new ChatUser("1","John","Doe","admin","1111","a@b.com");
        ArrayList<ChatUser> result = instance.getNotMyFriends();
        assertTrue(result.size()>0);
    }

    /**
     * Test of getAllMyFriends method, of class ChatUser.
     */
    @Test
    public void testGetAllMyFriends() throws Exception {
        System.out.println("getAllMyFriends");
        ChatUser instance = new ChatUser("1","John","Doe","admin","1111","a@b.com");
        ArrayList<ChatUser> result = instance.getAllMyFriends();
        assertTrue(result.size()>0);
    }
    
}
