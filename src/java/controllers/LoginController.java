package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import models.ChatUser;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException  {
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<ChatUser> friends = new ArrayList<ChatUser>();
            PrintWriter pw = response.getWriter();
            try {
                if(ChatUser.LoginUser(request.getParameter("username"),request.getParameter("password"))){
                    ChatUser user = new ChatUser();
                    user.setUsername(String.valueOf(request.getParameter("username")));
                    user.getUser();

                    HttpSession sessionUser = request.getSession();
                    pw.println("success");
                    if(sessionUser != null){
                        sessionUser.setAttribute("username",user.getUsername());
                        sessionUser.setAttribute("id", user.getId());
                        friends = user.getAllMyFriends();
                        sessionUser.setAttribute("friendsList", friends);
                        request.setAttribute("friends", friends);
                        RequestDispatcher rd1 = request.getRequestDispatcher("views/home.jsp");
                        rd1.forward(request,response);
                    }
                }  
                else{
                    pw.println("Λανθασμένος συνδυασμός ονόματος χρήστη/κωδικού πρόσβασης");
                    pw.println("<br><a href=\"index.jsp\"><h3>Δοκιμάστε ξανά...</h3></a>");
                }    
            } finally {
                pw.close();
               }
        }
        
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

}   
