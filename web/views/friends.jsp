<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="models.ChatUser"%>
<%@page import="models.Friend"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Κάνε Φίλους</title>
        <%@ include file="../template/head.html" %>
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="models.ChatUser"></jsp:useBean>
        <jsp:useBean id="friend" scope="request" class="models.Friend"></jsp:useBean>
        
              <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Welcome <% out.print(s.getAttribute("username")); %></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="home">Προφίλ</a></li>
              <li><a href="friends">Κάνε Φίλους</a></li>
              <li><a href="/SnapChat3/ChatServlet?step=5&userId=<%= activeUser.getId()%>">Logout</a></li>
              
            </ul>
            <div class="col-sm-3 col-md-3">
                <form class="navbar-form" role="search" method="post" action="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="username" value="<jsp:getProperty name="user" property="username"/>">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
                </form>
            </div>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
        <div class="container-fluid">
            <div class="col-md-4 col-md-offset-2">
                <div class = "table-responsive">
                    <table class = "table table-condensed noBorders">
                        <caption style="color: white;">Λίστα με άτομα που μπορείτε να κάνετε φίλους.</caption>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>
                                        <li class="list-group-item" style="padding:20px;"> 
                                            <span class="h3">${user.lname} ${user.fname} - <span class="text-danger">${user.username}</span></span>
                                            <form action="add" method="post" style="display:inline" class="pull-right">
                                                <jsp:setProperty name="friend" property="friendId" value="${user.id}"/>
                                                <input type="hidden" name="friendId" value="<jsp:getProperty name="friend" property="friendId"/>">
                                                <input class="btn btn-primary" type="submit" value="+ Add">
                                            </form>
                                        </li>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>  	
            </div>
        </div>
    </body>
</html>
