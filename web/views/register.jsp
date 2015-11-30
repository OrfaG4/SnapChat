
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <%@ include file="../template/head.html" %>
        <link rel="stylesheet" href="../css/bootstrap.css" />
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="models.User"></jsp:useBean>
        <div class="container-fluid">
            <form action="../register" method="POST">
                <section class="container">
                    <div class="container-page">				
                        <div class="col-md-6">
                            <h3 class="dark-grey">Εγγραφή</h3>
                            
                            <div class="form-group col-lg-12">
                                <label>Επίθετο</label>
                                <input type="text" name="lname" value="<jsp:getProperty name="user" property="lname"/>"  class="form-control" id="" required>
                            </div>

                            <div class="form-group col-lg-12">
                                <label>Όνομα</label>
                                <input type="text" name="fname" value="<jsp:getProperty name="user" property="fname"/>" class="form-control" id="" required>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>Όνομα χρήστη</label>
                                <input type="text" name="username" min="4" value="<jsp:getProperty name="user" property="username"/>" class="form-control" id="" required>
                            </div>

                            <div class="form-group col-lg-12">
                                <label>Κωδικός πρόσβασης</label>
                                <input type="text" name="password" min="4" value="<jsp:getProperty name="user" property="password"/>"  class="form-control" id="" required>
                            </div>

                            <div class="form-group col-lg-12">
                                <label>Email</label>
                                <input type="email" name="email" value="<jsp:getProperty name="user" property="email"/>" class="form-control" id="" required>
                            </div>					
                        </div>

                        <div class="col-md-6">
                            <h3 class="dark-grey">Terms and Conditions</h3>
                            <p>
                                By clicking on "Register" you agree to The Company's' Terms and Conditions
                            </p>
                            <p>
                                While rare, prices are subject to change based on exchange rate fluctuations - 
                                should such a fluctuation happen, we may request an additional payment. You have the option to request a full refund or to pay the new price. (Paragraph 13.5.8)
                            </p>
                            <p>
                                Should there be an error in the description or pricing of a product, we will provide you with a full refund (Paragraph 13.5.6)
                            </p>
                            <p>
                                Acceptance of an order by us is dependent on our suppliers ability to provide the product. (Paragraph 13.5.6)
                            </p>
                            <button type="submit" class="btn btn-primary">Εγγραφή</button>
                            <a href="../index.jsp" class="btn btn-default">Πίσω</a>
                        </div>
                    </div>
                </section>
            </form>
        </div>
    </body>
</html>
