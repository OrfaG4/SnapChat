<%@page import="models.User"%>
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
    </head>
    <body>
    
	<%@ include file="../template/menu.html" %>
      <div class="container-fluid">
          
          <div class="col-md-12">
              <!-- Create Message Area -->
              <div class="col-md-3">
                  <div id="webcam"></div>
                  <h1>Κάνε Snap</h1>
                  <button class="btn btn-danger" id="btn2" onclick="base64_toimage()">Snap!</button><br/><br/>
                  <img id="image"/>
                  <h4>Γράψε ένα μήνυμα: </h4>
                  <textarea cols="30" rows="5"></textarea><br/>
                  <button class="btn btn-success">Αποστολή</button>
              </div>
              
              <div class="col-md-offset-6 col-md-3">
                  <button id="showHideBtn" class="btn btn-primary col-md-12">Φίλοι</button>
              </div>
              <div id="showHide" class="col-md-offset-6 col-md-3 bg-danger" style="height: 500px;">
                  
              </div>
          </div>
      </div>
    </body>
</html>

