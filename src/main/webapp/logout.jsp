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
            //sets lg object value to null
            lg = (null);
            //cancel session
            session.invalidate();
            //redirect to the homepage
            response.sendRedirect("/Instagrim");
        %>

    </body>
</html>
