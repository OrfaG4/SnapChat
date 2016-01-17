
package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import models.ChatMessage;
import models.ChatUser;


public class ChatHelper implements Runnable{
    private static ChatHelper chatHelper=new ChatHelper();
    private static Hashtable<Integer,Date> onlineUsers=new Hashtable<Integer, Date>();
    private static Hashtable<Integer,Queue<ChatMessage>> chatMessages=new Hashtable<Integer,Queue<ChatMessage>>();
    private Thread cleanThread;
    private static ArrayList<ChatUser> usersList;

    ChatHelper(){
        cleanThread=new Thread(this);
        cleanThread.start();
    }
    public static ChatHelper getChatHelper(){
        return chatHelper;
    }
    public void run() {
        try {
            //wait 10 minutes before start removing users
            cleanThread.sleep(1 * 30 * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while(true){
            try{
                if(onlineUsers==null){
                    onlineUsers=new Hashtable<Integer, Date>();
                }
                if(onlineUsers.size()>0){
                    for(int i=0;i<onlineUsers.size();i++){
                        Enumeration<Integer> keys = onlineUsers.keys() ;
                        while(keys.hasMoreElements()) {
                            Integer current=keys.nextElement();
                            if(onlineUsers.get(current).getTime()+ChatConfig.expireLoginAfter<new Date().getTime()){
                                logOffUser(current);
                            }
                        }
                    }
                }
                //wait 3 minutes
                cleanThread.sleep(1*30*1000);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
    public void logOffUser(int userId) throws SQLException{
        ChatUser cu = new ChatUser();
        usersList = cu.getAllUsers();
        onlineUsers.remove(userId);
        System.out.println("User removed");
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getId().equals(String.valueOf(userId))){
                usersList.get(i).setStatus(ChatUser.OFFLINE);
                break;
            }
        }
        returnMessagesOfUser(userId);
    }

    public void loginUser(int user_id) throws SQLException{
        ChatUser cu = new ChatUser();
        usersList = cu.getAllUsers();
        onlineUsers.put(user_id, new Date());
        System.out.println("User logged in");
        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getId().equals(String.valueOf(user_id))){
                usersList.get(i).setStatus(ChatUser.ONLINE);
                break;
            }
        }
    }

    public boolean isUserOnline(int userId){
        if(onlineUsers.get(userId)!=null){
            return true;
        }else{
            return false;
        }
    }

    public int getUserStatus(int userId){
        if(isUserOnline(userId)) return ChatUser.ONLINE;
        else return ChatUser.OFFLINE;
    }
    
    public void recievedMessage(ChatMessage chatMessage){
        if(onlineUsers.get(chatMessage.getTo_user())!=null){
            //user online
            if(chatMessages.get(chatMessage.getTo_user())!=null){
                chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
            }else{
                Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                newQueue.add(chatMessage);
                chatMessages.put(chatMessage.getTo_user(), newQueue);
            }
            //System.out.println("Message Recieved!");
        }else{
            //user is not online, return the message
            chatMessage.returnMessage();
            if(onlineUsers.get(chatMessage.getTo_user())!=null){
                //user online
                if(chatMessages.get(chatMessage.getTo_user())!=null){
                    //queue is already exist
                    chatMessages.get(chatMessage.getTo_user()).add(chatMessage);
                }else{
                    Queue<ChatMessage> newQueue=new ConcurrentLinkedQueue<ChatMessage>();
                    newQueue.add(chatMessage);
                    chatMessages.put(chatMessage.getTo_user(), newQueue);
                }
            }
        }        
    }

    private void returnMessagesOfUser(Integer current) {
        Queue messages=chatMessages.get(current);
        //queue is already exist
        if(chatMessages.get(current)!=null){
            //if message >0
            if(messages.size()>0){
                //loop over the queue
                while(messages.size()>0){
                    //get the oldest message
                    ChatMessage message=(ChatMessage)messages.poll();
                    //return the message
                    message.returnMessage();
                    //resend it to the sender as returned message if only it is online
                    if(onlineUsers.get(message.getTo_user())!=null) {
                        recievedMessage(message);
                    }
                }
            }
            //remove the queue from the hashtable
            chatMessages.remove(current);
        }
    }

    public ChatMessage[] getUserMessages(int user_id,int count) {
        //if there is a queue for this user
        Queue messages=chatMessages.get(user_id);
        //queue is already exist
        if(chatMessages.get(user_id)!=null){
            ArrayList<ChatMessage> messagesList=new ArrayList<ChatMessage>();
            //if message >0
            if(messages.size()>0){
                //loop over the queue
                while(count>0){
                    //get the oldest message
                    ChatMessage message=(ChatMessage)messages.poll();
                    if(message==null){
                        break;
                    }
                    //put it in the queue
                    messagesList.add(message);
                    //decrease retrieved count
                    count--;
                }
            }
            return messagesList.toArray(new ChatMessage[0]);
        }
        return null;
    }
}
