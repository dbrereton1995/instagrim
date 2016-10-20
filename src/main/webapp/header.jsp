<%-- 
    Document   : header
    Created on : 28-Sep-2016, 16:12:33
    Author     : dbrer
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <div class="header">
            <div class ="container-fluid">
                <div class="row">

                    <div class="header col-sm-12">
                        <img src="/Instagrim/resources/logo.png" alt="InstaGrim Logo" style="max-width:100%; height:auto; width:auto\9;">  

                    </div>





                </div>


            </div>
        </div>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">


                </div>

                <ul class="nav navbar-nav">
                    <%  //Returns LoggedIn object from current session
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    <li><a href="/Instagrim/">Home</a></li>
                    <li><a href="/Instagrim/Upload">Upload</a></li>
                        <% }
                        } else { %>
                    <li><a href="/Instagrim/">Home</a></li>
                    <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                        <% } %>
                </ul>



                <ul class="nav navbar-nav navbar-right">
                    <%

                        if (lg != null) {

                            if (lg.getlogedin()) {
                    %>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <li><a href="/Instagrim/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log Out (<%=lg.getUsername()%>)</a></li>
                        <% }
                        } else { %>


                    <li><a href="/Instagrim/Register"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                    <li><a href="/Instagrim/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        <% }%>
                </ul>
            </div>
        </nav>
    </body>
</html>
