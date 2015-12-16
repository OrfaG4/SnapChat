
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ChatMessage;
import models.ChatUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;


public class ChatServlet extends HttpServlet {
    private static String RECIVE_MESSAGE="2";
    private static String REQUEST_MESSAGE="3";
    private static String REFRESH_CONTACT_LIST="4";
    private static String LOGOFF="5";
    private static String STEP="step";
    private static String USERNAME="username";
    private static String PASSWORD="password";
    private static String ERROR="ERROR";
    private static String USER="USER";
    private static String USER_ID="userId";
    private static String FROM="from_user";
    private static String FROM_NAME="from_user_name";
    private static String TO="to_user";
    private static String MESSAGE="message";
    private static String B64="b64";
    private static String MESSAGE_COUNT="message_count";
    private static String CONTACT_COUNT="contact_count";
    private static String CONTACT_LIST="contacts";
    private static ChatHelper chatHelper=ChatHelper.getChatHelper();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        String stepId=(String)request.getParameter(STEP);
        
        if(stepId.equals(RECIVE_MESSAGE)){
            //will retrieve message attributes...
            String from=request.getParameter(FROM);
            String from_name=request.getParameter(FROM_NAME);
            String to=request.getParameter(TO);
            String message=request.getParameter(MESSAGE);
            String b64 = request.getReader().readLine();
            //System.out.println("from_user="+from+" from_user_name="+from_name+" to_user="+to+" message=["+message+"]"+"img: "+b64);
            //System.out.println("Reader: "+request.getReader().readLine());
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setFrom_user(Integer.parseInt(from));
            chatMessage.setTo_user(Integer.parseInt(to));
            chatMessage.setFrom_user_name(from_name);
            chatMessage.setMessage(message);
            chatMessage.setB64(b64);
            Calendar cal=Calendar.getInstance();
            chatMessage.setMessageTime(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
            chatMessage.setMessageType(ChatMessage.NEW_MESSAGE_TYPE);
            chatHelper.loginUser(chatMessage.getFrom_user());
            chatHelper.recievedMessage(chatMessage);
        }else if(stepId.equals(REQUEST_MESSAGE)){
            String userId=request.getParameter(USER_ID);
            //System.out.println("Request messages for the user="+userId);
            chatHelper.loginUser(Integer.parseInt(userId));
            ChatMessage[] messages=chatHelper.getUserMessages(Integer.parseInt(userId), ChatConfig.RECIEVE_MAX_MESSAGE_PER_CALL);
            if(messages!=null && messages.length>0){
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader(MESSAGE_COUNT, ""+messages.length);
                PrintWriter out = response.getWriter();
                try{
                    //System.out.println("Number of existing messages="+messages.length);
                    if(ChatConfig.responseType==ChatConfig.XML){
                        out.println("<chat_messages>");
                        for(int i=0;i<messages.length;i++){
                            String messageAsXML=messages[i].toXML();
                            //System.out.println(messageAsXML);
                            out.println(messageAsXML);
                        }
                        out.println("</chat_messages>");
                    }else{
                        for(int i=0;i<messages.length;i++){
                            String messageAsString=messages[i].toMultipleValues();
                            //System.out.println(messageAsString);
                            out.print(messageAsString);
                        }
                    }
                }finally{
                    out.flush();
                    out.close();
                }
            }else{
                return;
            }
        }else if(stepId.equals(LOGOFF)){
            String userId=request.getParameter(USER_ID);
            //System.out.println("Logoff user="+userId);
            chatHelper.logOffUser(Integer.parseInt(userId));
            LogoutController lc = new LogoutController();
            lc.doLogout(request, response);
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else if(stepId.equals(REFRESH_CONTACT_LIST)){
            String statusList="";
            ArrayList<ChatUser> contactList =null; //= (ArrayList<ChatUser>)request.getSession().getAttribute("friends");
            HttpSession s = request.getSession();
            contactList = (ArrayList<ChatUser>)s.getAttribute("friendsList");
            if(contactList!=null && contactList.size()>0){
                for(int i=0;i<contactList.size();i++){
                    int status=chatHelper.getUserStatus(Integer.parseInt(contactList.get(i).getId()));
                    //System.out.println("Status Before: "+contactList.get(i).getUsername()+" "+contactList.get(i).getStatus());
                    contactList.get(i).setStatus(status);
                    //System.out.println("Status After: "+contactList.get(i).getStatus());
                    statusList+=contactList.get(i).getId()+"ø"+contactList.get(i).getUsername()+"ø"+status+"ø";
                }
                s.setAttribute("friendsList", contactList);
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                response.setHeader(CONTACT_COUNT, ""+contactList.size());
                try{
                    //System.out.println("will send back the following status list="+statusList);
                    out.print(statusList);
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                    out.flush();
                    out.close();
                }
            }
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ChatServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ChatServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "ChatServlet Servlet the handle all interaction with chat backend.";
    }// </editor-fold>

}
