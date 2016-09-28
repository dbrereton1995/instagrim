<%-- 
    Document   : header
    Created on : 28-Sep-2016, 16:12:33
    Author     : dbrer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="col-sm-10">
                    
                    
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
                        
                        <li><a href="#">Other Thing</a></li>
                    </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Your Images</a></li>
                   
                    <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    
                </ul>
            </div>
        </nav>
    </body>
</html>
