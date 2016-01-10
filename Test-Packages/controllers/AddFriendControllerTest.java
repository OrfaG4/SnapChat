/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.xml.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.ws.dump.LoggingDumpTube.Position.Before;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AddFriendControllerTest {
    
    public AddFriendControllerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testProcessRequest() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("friendId")).thenReturn("foo");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new AddFriendController().doGet(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("Something went wrong"));
    }
    @Test
    public void testProcessRequest2() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("friendId")).thenReturn("1");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new AddFriendController().doPost(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("Something went wrong"));
    }
    
}
