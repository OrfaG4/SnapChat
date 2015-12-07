/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class HomeRouter extends HttpServlet{
    protected void home(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        PrintWriter pw = response.getWriter();
        if(s.getAttribute("username") == null){
            pw.println("Δέν έχετε δικαίωμα πρόσβασης");
            pw.println("<a href='index'>Συνδεθείτε</a>");
        }else{
            ArrayList<User> friends = new ArrayList<User>();
            User user = new User();
            user.setUsername(s.getAttribute("username").toString());
            user.getUser();
            friends = user.getAllMyFriends();
            request.setAttribute("friends", friends);
            RequestDispatcher rd1 = request.getRequestDispatcher("views/home.jsp");
            rd1.forward(request,response);
        }
    }
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            home(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeRouter.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            home(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeRouter.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}


