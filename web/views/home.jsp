<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="controllers.ChatConfig;" %>
<%@page import="models.*;" %>
<%  
    HttpSession s=request.getSession(false);  
    String username=(String)s.getAttribute("username");
    ChatUser activeUser = new ChatUser();
    activeUser.setUsername(username);
    activeUser.getUser();            
%>
<!DOCTYPE html>
<html>
    <head>
        <title><% out.print(s.getAttribute("username")); %></title>
        <script src="js/jquery.js"></script>
        <script src="js/swf.js"></script>
        <script src="js/script.js"></script>
        <script src="views/scriptcam.js"></script>
        <script src="js/ajax_chat.js"></script>

	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/custom.css" />
	<link rel="stylesheet" href="css/chat.css" />
        
        <script charset="UTF-8">
            maxChatWindows=5;
            refreshRate=1000;
            refreshRateForContacts=5;
            currentChatWindows=0;
            refresh=0;
            mute=0;

            var nsstyle='display:""'
            if (document.layers){
                var scrolldoc=document.scroll1.document.scroll2
            }
            function up(){
                if (!document.layers) return
                if (scrolldoc.top<0){
                    scrolldoc.top+=10
                }
                temp2=setTimeout("up()",50)
            }
            function down(){
                if (!document.layers) return
                if (scrolldoc.top-150>=scrolldoc.document.height*-1)
                    scrolldoc.top-=10
                temp=setTimeout("down()",50)
            }
            function clearup(){
                if (window.temp2)
                    clearInterval(temp2)
            }
            function cleardown(){
                if (window.temp)
                    clearInterval(temp)
            }
            function scroll_down(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.scrollTop = objDiv.scrollHeight;
            }
            function clear_text(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.innerHTML='';
            }
            function close_chat(user_no){
                var objDiv = document.getElementById('global_div');
                var oldDiv = document.getElementById(user_no);
                objDiv.removeChild(oldDiv);
                currentChatWindows--;
            }
            
            function base64_url_encode(str) {
                return str.replace(/\+/g, '-').replace(/\//g, '_').replace(/\=+$/, '');
            }

            function base64_url_decode(str) {
                str = (str + '===').slice(0, str.length + (str.length % 4));
                return str.replace(/-/g, '+').replace(/_/g, '/');
            }
            
            function add_message(user_no){
                var b64 = $('#formfield').val();
                var textValue=document.getElementById('input_'+user_no).value;
                if(textValue=='') return;
                sendMessage(<%= activeUser.getId()%>,'<%= activeUser.getUsername()%>',user_no,textValue,b64);
                var objDiv = document.getElementById('scroll3_'+user_no);
                var nameValue='<%= activeUser.getUsername()%>';
                objDiv.innerHTML=objDiv.innerHTML+'<div class="col-md-12 chat-message1"><br><font color=black>'+nameValue+' says : '+textValue+'</font>'+'<br><img src="data:image/jpg; base64,'+b64+' " /></div>';
                document.getElementById('input_'+user_no).value='';
                scroll_down(user_no);
                document.getElementById('state_'+user_no).src='/WebChatApp/images/empty.png';
                document.getElementById('state_'+user_no).width=1;
                document.getElementById('state_'+user_no).hight=1;
            }
            function receive_message(user_no,name,message,time,b64){
                var objDiv = document.getElementById('scroll3_'+user_no);
                if(!document.getElementById('scroll3_'+user_no)) {
                    openNewChat(user_no,name);
                    objDiv = document.getElementById('scroll3_'+user_no);
                }
                objDiv.innerHTML=objDiv.innerHTML+'<div class="col-md-12 chat-message2"><br><font color=blue>'+name+' says : '+message+'</font>'+'<br><img src="data:image/png; base64,'+base64_url_decode(b64)+' " /></div>';
                scroll_down(user_no);
                document.getElementById('state_'+user_no).src='/WebChatApp/images/report.gif';
                document.getElementById('state_'+user_no).width=15;
                document.getElementById('state_'+user_no).hight=15;
                document.getElementById('display_'+user_no).value=' Τελευταίο μήνυμα στις '+time;
            }
            function enableEnterKey(e,user_no){
                var key;
                if(window.event)
                    key = window.event.keyCode;
                else
                    key = e.which;
                if(key == 13){
                    return add_message(user_no);
                } else{
                    return true;
                }
            }
            function openNewChat(user_no,name){
                var oldDiv = document.getElementById(user_no);
                if(oldDiv!=null) return;
                if(currentChatWindows>=maxChatWindows){
                    alert('Sorry, You have exceed the max number of open chat dialogs per user');
                    return;
                }
                currentChatWindows++;
                var objDiv = document.getElementById('global_div');
                var mytext='\
<div id='+user_no+' class="container-fluid chat-wrapper">\n\
    <div id="chat_'+user_no+'">\n\
        <div class="chat-header bg-primary">\n\
            <span class="h3"><font color=white><b> Συνομιλία με: '+name+'</b></font></span>\n\
            <span class="pull-right"><button onclick="close_chat('+user_no+');" class="btn btn-danger btn-sm">X</button></span>\n\
        </div>\n\
        <div>\n\
<table style="display:none" width=400><tr><td width=360><font color=white><b> Chat to '+name+'</b></font></td><td align=right width=20><img id=state_'+user_no+' src=/WebChatApp/images/empty.png border=0 width=1 hight=1></td><td align=right width=20><img src=/WebChatApp/images/close.jpg border=0 title="Close Chat" onclick="close_chat('+user_no+');" style="cursor:pointer"></a></td></tr></table>\n\
            <div id=scroll3_'+user_no+' class="chat-body col-md-12 bg-success"></div>\n\
            <input id=display_'+user_no+' name=display_'+user_no+' disabled class="col-md-12 chat-time-arrived col-xs-12 col-sm-12"><br/>\n\
            <input id=input_'+user_no+' name=input_'+user_no+' onkeydown="return enableEnterKey(event,'+user_no+')" class="col-md-8 col-xs-12 col-sm-12 h4 chat-input" >\n\
            <button onclick="add_message('+user_no+')" class="btn btn-sm btn-success col-md-1 col-md-offset-1 chat-btn-send col-xs-12 col-sm-12">Send</button>\n\
            <button onclick="clear_text('+user_no+');" class="btn btn-sm btn-default col-md-1 chat-btn-clear col-xs-12 col-sm-12">Clear</button>\n\
        </div>\n\
    </div>\n\
</div>';
                objDiv.innerHTML=objDiv.innerHTML+mytext;
            }
            function update(){
                getMessages(<%= activeUser.getId()%>);
                refresh++;
                //each 4 = 1 minute (15 seconds * 4 =60)
                if(refresh>=refreshRateForContacts) {
                    refresh=0;
                    refreshContactList();
                }
                setTimeout("update()",refreshRate);
            }
            function updateUserStatus(user_no,user_name,newStatus){
                //document.getElementById('user_'+user_no).src='/SnapChat1/images/'+type+newStatus+'.png';
                if(newStatus==<%=ChatUser.ONLINE%>){
                    document.getElementById('user_'+user_no).onclick=function(){ openNewChat(user_no,user_name)}
                    $('#user_'+user_no).prop('disabled', false);
                }else{
                    document.getElementById('user_'+user_no).onclick="";
                    $('#user_'+user_no).prop('disabled', true);
                }
            }
        </script>             
    </head>
    <body  onload='setTimeout("update()",refreshRate);'>
        <jsp:useBean id="user" scope="request" class="models.ChatUser"></jsp:useBean>
	<%@ include file="../template/menu.html" %>
        <div class="container-fluid">
            <div class="col-md-12">
                <!-- Create Message Area -->
                <div class="col-md-3">
                    <div id="webcam"></div>
                    <h1>Κάνε Snap</h1>
                    <button class="btn btn-default takeSnap" id="btn2" onclick="base64_tofield_and_image()">Snap!</button><br/><br/>
                    <img id="image"/>
                    <textarea style="display:none;" id="formfield" style="width:190px;height:70px;"></textarea>
                    <a href="/SnapChat1/ChatServlet?step=5&userId=<%= activeUser.getId()%>" id="showHideBtn" class="btn btn-danger col-md-6">Αποσύνδεση</a>
                </div>
                 <div class="col-md-3">
                    <div class=" col-md-12">
                        <button id="showHideBtn" class="btn btn-primary  col-md-12">Φίλοι</button>
                    </div>
                    <div id="showHide" class=" col-md-12">
                        <table class = "table table-condensed noBorders">
                            <caption><b>Λίστα φίλων</b></caption>
                            <tbody>
                                <c:forEach var="friend" items="${friends}">
                                    <tr>
                                        <td>
                                            <li class="list-group-item" style="padding:20px;">
                                                <input type="hidden" id="friendId" type="text" value="${friend.id}">
                                                <span style="margin-right: 15px;">
                                                     <button id="user_${friend.id}" class="btn btn-success startChat " onclick="openNewChat(${friend.id},'${friend.username}')">Chat</button> 
                                                </span>
                                                <span class="h3">${friend.username}</span>
                                            </li>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>                  
                    </div>
                </div>
                <!-- Chat area -->
                <div class="col-md-6">
                    <div id="global_div" class="col-md-12">
                        
                    </div>
                </div>
                <!-- End Chat area -->
            </div>
        </div>   
    </body>
</html>
