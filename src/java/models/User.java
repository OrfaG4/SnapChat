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
public class User {
    
    private String id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String email;
    
    /**
     *  Constructor
     */
    public User(){
        id="";
        fname="";
        lname="";
        username="";
        password="";
        email="";
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
            }
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void register(){
        try{    
            DB db=new DB();
            Connection con= db.connect();
            String sqlString="INSERT INTO users (lname,fname,username,password,email) VALUES ('"+fname+"','"+lname+"','"+username+"','"+password+"','"+email+"')";
            Statement s = con.createStatement();
            try{    
                s.executeUpdate(sqlString);
                s.close();
                con.close();
            } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}  
    }
    
    /**
     *  select all users
     */
    public ArrayList<User> getAllUsers() throws SQLException{
        
        PreparedStatement pst = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        DB db=new DB();
        
        Connection con= db.connect();
        String sqlString = "SELECT * FROM users WHERE users.username <> "+"'"+username+"'";
            pst = con.prepareStatement("");
            pst.executeQuery(sqlString);
            rs = pst.getResultSet();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));
                user.setLname(rs.getString("lname"));
                user.setFname(rs.getString("fname"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
            rs.close();
            pst.close();

        return users;
    }
}
