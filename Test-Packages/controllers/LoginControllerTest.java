/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    
    public LoginControllerTest() {
    }

    /**
     * Test of processRequest method, of class LoginController.
     */
    @Test
    public void testProcessRequest() throws Exception {
        
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("username")).thenReturn("foo");
        when(request.getParameter("password")).thenReturn("foo");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new LoginController().doPost(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("Λανθασμένος συνδυασμός ονόματος χρήστη/κωδικού πρόσβασης"));
    }
    @Test
    public void testProcessRequestWithProperArguements() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter out = new StringWriter();
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1111");
        PrintWriter writer = new PrintWriter(out);
        when(response.getWriter()).thenReturn(writer);

        new LoginController().doGet(request, response);
        System.out.println(out);
        
        writer.flush();
        assertTrue(out.toString().contains("success"));
    }
}
