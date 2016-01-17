
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Friend;

public class AddFriendController extends HttpServlet{
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        HttpSession s = request.getSession();

        pw.println(request.getParameter("friendId"));
        Friend f = new Friend();
        if(s != null){
            f.setMyId(s.getAttribute("id").toString());
            f.setFriendId(request.getParameter("friendId"));
            f.addFriend(Integer.valueOf(f.getMyId()), Integer.valueOf(f.getFriendId()));
            RequestDispatcher rd1 = request.getRequestDispatcher("home");
            rd1.forward(request,response);
        }else{
            pw.println("Something went wrong");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        add(request, response);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        add(request, response);
    }
}
