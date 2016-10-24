<%-- 
    Document   : otherProfile
    Created on : 17-Oct-2016, 18:19:47
    Author     : dbrer
--%>

<%@page import="java.util.Iterator"%>
<%@page import="uk.ac.dundee.computing.djb.instagrim.servlets.profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");
            String firstname = (String) session.getAttribute("firstname");
            String lastname = (String) session.getAttribute("lastname");
            String country = (String) session.getAttribute("country");
             Pic profilePic = (Pic) request.getAttribute("profilePicture");
            int numOfPics = 0;
        %>
        <div class="col-sm-4">
            <% if(profilePic == null){ %>
          
           <img src="/Instagrim/resources/blankProfilePic.png" alt="<%=username%>'s Profile Picture" style="max-width:100%; height:auto; width:auto\9;">
        <%}else{%>
        <img src="/Instagrim/Thumb/<%=profilePic.getSUUID()%>" alt="<%=username%>'s Profile Picture" style="max-width:100%; height:auto; width:auto\9; display:inline;">
        <%}%>
        </div>
            <h1> <%=username%>'s Profile </h1>
            <h4> Name: <%=firstname%> <%=lastname%> </h4>
            <h4> Location: <%=country%> </h4>
            <h4> Pictures Uploaded: </h4>
            <div class="col-sm-12">
  <article>
                <%
                    java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                    if (lsPics == null) {
                %>
                <br></br>
                <p style="text-align: center;">No Pictures found</p>
                <%
                } else {
                    Iterator<Pic> iterator;
                    iterator = lsPics.iterator();
                    while (iterator.hasNext()) {
                        Pic p = (Pic) iterator.next();
                %>
                <div class="col-sm-3" style=" display:inline-block; margin: 0 auto; height: auto; width: auto; border: 2px solid;">
                    <br></br><a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"><br>
                        <p><%=p.getDescription()%> <p>
                        </br></a>
                </div>
                <%
                        }
                    }
                %>
            </article>
            </div>
    </body>
</html>
