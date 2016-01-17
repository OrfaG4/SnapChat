
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

public class SearchController extends HttpServlet{
    
    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        HttpSession s = request.getSession();
        ChatUser cu = new ChatUser();
        cu.setUsername(s.getAttribute("username").toString());
        cu.getUser();
        ChatUser found = null;
        
        ArrayList<ChatUser> c = cu.getNotMyFriends();
        ArrayList<ChatUser> d = new ArrayList<>();
        for(int i=0; i<c.size(); i++){
            if(c.get(i).getUsername().equals(request.getParameter("username"))){
                found = c.get(i);
            }
        }

        if(found != null){
            d.add(found);
            request.setAttribute("users",d);
            RequestDispatcher rd1 = request.getRequestDispatcher("views/friends.jsp");
            rd1.forward(request,response);
        }else{
            pw.println("<h3>Search Failed</h3>");
            pw.println("<h3><a href='home'>Please try again</a></h3>");
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            search(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            search(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
