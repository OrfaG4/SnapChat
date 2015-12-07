<%@page import="models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    HttpSession s=request.getSession(false);  
    String username=(String)s.getAttribute("username");
    User activeUser = new User();
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

		
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/custom.css" />
<script>
	var wsocket;
	var serviceLocation = "ws://localhost:8080/SnapChat/chat/";
	var $nickName;
	var $message;
	var $chatWindow;
	var room = '';
 
	function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data); // native API
		var $messageLine = $('<span class="text-info">'+ 'Στάλθηκε: ' + msg.received
				+ '</span><span class="text-success">'+ ', Από ' + msg.sender
				+ '</span><p class="bg-info">' + msg.message
				+ '</p>');
		$chatWindow.append($messageLine);
                $("#response").animate({ scrollTop: $(document).height() }, "slow");return false;
	}
	function sendMessage() {
		var msg = '{"message":"' + $message.val() + '", "sender":"'
				+ $nickName.text() + '", "received":""}';
		wsocket.send(msg);
		$message.val('').focus();
	}
 
	function connectToChatserver() {
		room = 'global';
		wsocket = new WebSocket(serviceLocation + room);
		wsocket.onmessage = onMessageReceived;
	}
 
	function leaveRoom() {
		wsocket.close();
		$chatWindow.empty();
		$('.chat-wrapper').hide();
		//$('.chat-signin').show();
		//$nickName.focus();
	}
 
	$(document).ready(function() {
		$nickName = $('head title');
		$message = $('#message');
		$chatWindow = $('#response');
		$('.chat-wrapper').hide();
		//$nickName.focus();
 
		$('.startChat').click(function(evt) {
			evt.preventDefault();
			connectToChatserver();
			//$('.chat-wrapper h2').text('Chat # '+$nickName.text() + "@" + room);
			//$('.chat-signin').hide();
			$('.chat-wrapper').show();
			$message.focus();
		});
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage()
		});
 
		$('#leave-room').click(function(){
			leaveRoom();
		});
	});
</script>
        
        
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="models.User"></jsp:useBean>
	<%@ include file="../template/menu.html" %>
        <div class="container-fluid">
            <div class="col-md-12">
                <!-- Create Message Area -->
                <div class="col-md-3">
                    <div id="webcam"></div>
                    <h1>Κάνε Snap</h1>
                    <button class="btn btn-danger" id="btn2" onclick="base64_toimage()">Snap!</button><br/><br/>
                    <img id="image"/>
                </div>
                <!-- Chat area -->
                <div class="col-md-6">
                    <div class="chat-wrapper">
                        <form id="do-chat">
                            <h2 class="alert alert-success"></h2>
                            <div id="response" class="col-md-7" style=""></div>
                            <fieldset class="col-md-6">
                                <div class="controls">
                                    <input type="text" class="input-block-level" placeholder="Your message..." id="message" style="height:60px"/>
                                    <input type="submit" class="btn btn-large btn-block btn-primary" value="Αποστολή" />
                                    <button class="btn btn-large btn-block" type="button" id="leave-room">Τέλος συζήτησης</button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
                <!-- End Chat area -->
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
                                                <span style="margin-right: 15px;"><button class="btn btn-success startChat">Chat</button></span>
                                                <span class="h3">${friend.lname} ${friend.fname}</span>
                                            </li>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>                  
                    </div>
                </div>
            </div>
        </div>   
    </body>
</html>
