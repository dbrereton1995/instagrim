<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>
<!-- Bootstrap Content -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>

<html>
    
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name ="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="header">
        <div class ="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    
                    <!-- put jumbotron here if dont want it extended to edges -->
                    <header>
                        <h1 class="titleText"> InstaGrim </h1>
                        <h4 class="sloganText">Your world in Black and White</h2>
                    </header>
                    
                </div>
            </div>
        </div>
        </div>
        
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Something</a>
                </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="upload.jsp">Upload</a></li>
                        <%
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                        %>
                        <li><a href="#">Other Thing</a></li>
                    </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <%}
                            }else{
                                %>
                    <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <% }
                    %>
                </ul>
            </div>
        </nav>

            
            <div class="row">
                <div class="col-sm-4">.col-sm-4</div>
                <div class="col-sm-8">.col-sm-8</div>
            </div>
            
        <footer>
            <div class="footer">
            <ul>
                      &COPY; Daniel Brereton - Applied Computing, Year 3
            </ul>
            </div>
        </footer>
    </body>
</html>
