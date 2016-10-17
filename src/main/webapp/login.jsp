<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        <%@include file="header.jsp"%>

    
    

        <form method="POST"  action="Login">
            <div class="container padding-top-10">
                <div class="panel panel-default">
                    <div class="panel-heading">Login</div>
                    <div class="panel-body">
                        <form method="POST" action="Login">
                            <label for="username" class="control-label padding-top-10">Username: </label>
                            <div class="row padding-top-10">
                                <div class="col-md-12  padding-top-10">
                                    
                                    <input type="text" class="form-control" name="username" id="username" placeholder="Username" required="required" />
                                </div>
                            </div>
                            <div class="row padding-top-10">
                                <div class="col-md-12 padding-top-10">
                                    <label for="password" class="control-label">Password:</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required="required"/>
                                </div>


                            </div>
                            <div class="row">
                                <div class="col-md-2 col-md-offset-5 padding-top-10">
                                    <input type="submit" value="Login" class="btn btn-success">

                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </form>
        
       
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
