<%-- 
    Document   : userProfile
    Created on : 17-Oct-2016, 18:19:47
    Author     : dbrer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <%
            LoggedIn loggedIn = (LoggedIn) session.getAttribute("LoggedIn"); 
            %>
        <%@include file="header.jsp"%>
        <p>The username is: <%%></p>


    </body>
</html>
