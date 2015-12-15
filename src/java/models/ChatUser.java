/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SnapChat TEAM
 */
public class ChatUser {
    
    public static int OFFLINE = 0;
    public static int ONLINE = 1;
    public static int USER = 0;
    public static int ADMIN = 1;
    public static int SUPPORT = 2;
    
    private String id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String email;
    private boolean online;
    private int status = 0;
    
    /**
     *  Constructor
     */
    public ChatUser(){
        id="";
        fname="";
        lname="";
        username="";
        password="";
        email="";
        online=false;
    }
    
    public ChatUser(String id, String fname, String lname, String username, String password, String email){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.username=username;
        this.password=password;
        this.email=email;
    }
    /**
     *   Getters
     */
    public String getId() {
        return id;
    }
    
    public String getLname() {
        return lname;
    }
    
    public String getFname() {
        return fname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getEmail(){
        return email;
    }

    public boolean isOnline() {
        return online;
    }
    
    public int getStatus() {
        return status;
    }
    
    
    /**
     *   Setters
     */
    public void setId(String id){
        this.id = id;
    }
    
    public void setLname(String lname){
        this.lname =lname;
    }
    
    public void setFname(String fname){
        this.fname =fname;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }
    
    public void setEmail(String email) {
        this.email=email;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *  Login
     */
    public static boolean LoginUser(String username,String password) {
        boolean check =false;
        try {      
            DB db=new DB();
            Connection con= db.connect();
            PreparedStatement ps =con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs1 =ps.executeQuery();
            check = rs1.next();
            con.close();        
            }catch(Exception e){
                e.printStackTrace();
            }
        return check;    
    }
    /**
     *  getUser
     *  Γεμίζει δεδομένα 
     *  στο αντικείμενο της κλάσης User
     */
    public void getUser(){
        try {      
            DB db=new DB();
            Connection con= db.connect();
            String sqlString = "SELECT * FROM users WHERE username = '"+username+"'";
            Statement s = con.createStatement();
            ResultSet rs=s.executeQuery(sqlString);
            while(rs.next()){
                id=rs.getString("id");
                lname= rs.getString("lname");
                fname = rs.getString("fname");
                username= rs.getString("username");
                password = rs.getString("password");
                email = rs.getString("email");
                online = rs.getBoolean("online");
            }
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public ArrayList<ChatUser> getAllUsers() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DB db=new DB();
        Connection con= db.connect();
        String sql = "SELECT * FROM users";
            pst = con.prepareStatement("");
            pst.executeQuery(sql);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setId(rs.getString("id"));
                user.setLname(rs.getString("lname"));
                user.setFname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                user.setOnline(rs.getBoolean("online"));
                
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();
        return users;
    }
    
    public void register(){
        try{    
            DB db=new DB();
            Connection con= db.connect();
            String sqlString="INSERT INTO users (lname,fname,username,password,email,online) VALUES ('"+fname+"','"+lname+"','"+username+"','"+password+"','"+email+"','"+0+"')";
            Statement s = con.createStatement();
            try{    
                s.executeUpdate(sqlString);
                s.close();
                con.close();
            } catch (SQLException ex) {Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (SQLException ex) {Logger.getLogger(ChatUser.class.getName()).log(Level.SEVERE, null, ex);}  
    }
    
    /**
     *  select all users
     */
    public ArrayList<ChatUser> getNotMyFriends() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DB db=new DB();
        
        Connection con= db.connect();
        String sqlString = "SELECT * FROM users, friends WHERE users.id NOT IN (SELECT friends.friendId from friends WHERE friends.myId = "+"'"+id+"'"+") AND users.id <> "+"'"+id+"'"+"GROUP BY users.username";
            pst = con.prepareStatement("");
            pst.executeQuery(sqlString);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setId(rs.getString("id"));
                user.setLname(rs.getString("lname"));
                user.setFname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();

        return users;
    }
    
    public ArrayList<ChatUser> getAllMyFriends() throws SQLException{
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<ChatUser> users = new ArrayList<ChatUser>();
        DB db=new DB();
        
        Connection con= db.connect();
        String sql = "SELECT users.id, users.lname, users.fname, users.username, users.online FROM users,friends WHERE friends.friendId  = users.id AND friends.myId = "+"'"+id+"'";
            pst = con.prepareStatement("");
            pst.executeQuery(sql);
            rs = pst.getResultSet();
            while(rs.next()){
                ChatUser user = new ChatUser();
                user.setId(rs.getString("id"));
                user.setLname(rs.getString("lname"));
                user.setFname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                user.setOnline(rs.getBoolean("online"));
                users.add(user);
            }
            rs.close();
            pst.close();
            con.close();

        return users;
    }
}
