<%-- 
    Document   : logout
    Created on : 16-Oct-2016, 20:27:30
    Author     : dbrer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        
        <%
            lg = (null); 
            session.invalidate();
            response.sendRedirect("/Instagrim");
            %>
        
    </body>
</html>
