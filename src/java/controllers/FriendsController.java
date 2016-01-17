
package controllers;

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
import models.ChatUser;

public class FriendsController extends HttpServlet{
    protected void friends(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        PrintWriter pw = response.getWriter();
        if(s != null && s.getAttribute("username") != null){
            ChatUser user = new ChatUser();
            user.setUsername(s.getAttribute("username").toString());
            user.getUser();
            users = user.getNotMyFriends();
            request.setAttribute("users",users);
            RequestDispatcher rd1 = request.getRequestDispatcher("views/friends.jsp");
            rd1.forward(request,response);
        }else{
            pw.println("Δέν έχετε δικαίωμα πρόσβασης");
            pw.println("<a href='index'>Συνδεθείτε</a>");
        }
    }
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                friends(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                friends(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
