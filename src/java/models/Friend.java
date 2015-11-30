
package models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Friend {
    String myId;
    String friendId;
    String status;

    public Friend() {
        myId="";
        friendId="";
        status="";
    }

    
    
    public String getMyId() {
        return myId;
    }

    public String getFriendId() {
        return friendId;
    }

    public String getStatus() {
        return status;
    }
    
    public void setMyId(String myId) {
        this.myId = myId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void addFriend(int myId, int friendId){
        try{    
            DB db=new DB();
            Connection con= db.connect();
            String sqlString="INSERT INTO friends (myId,friendId,status) VALUES ('"+myId+"','"+friendId+"','"+0+"')";
            Statement s = con.createStatement();
            try{    
                s.executeUpdate(sqlString);
                s.close();
                con.close();
            } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (SQLException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}  
    }
}
