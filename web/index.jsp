<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.ChatUser"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login</title>
        <%@ include file="template/head.html" %>
        
        <script src="js/jquery.js"></script>
</head>
    <body>
        <div class="container">
            <jsp:useBean id="user" scope="request" class="models.ChatUser"></jsp:useBean>
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center login-title">Συνδέσου στο SnapChat τώρα!</h1>
                    <div class="account-wall">
                        <img class="profile-img img-responsive" src="img/logo.jpg" alt="snapchat logo" >
                        <form class="form-signin" action="login" method="POST">
                            <input type="text" name="username" value="<jsp:getProperty name="user" property="username"/>" class="form-control" placeholder="Όνομα χρήστη" required autofocus >
                            <input type="password" name="password" value="<jsp:getProperty name="user" property="password"/>" class="form-control" placeholder="Κωδικός πρόσβασης" required>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Σύνδεση</button>
                        </form>
                    </div>
                    <a href="views/register.jsp" class="text-center new-account">Δημιούργησε λογαριασμό</a>
                </div>
            </div>
        </div>  
    </body>
</html>
