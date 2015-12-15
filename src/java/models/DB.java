/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orfa
 */
public class DB {
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbString = "jdbc:mysql://localhost:3306/snapchat?user=root";
            Connection con = DriverManager.getConnection(dbString);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
