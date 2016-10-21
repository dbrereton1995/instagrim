<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.djb.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="mainIndex">
            <% if (lg != null) {%>

            <h1 ><%=lg.getUsername()%>'s Pics</h1> <br></br>
            <% } %>
            <article>
                <%
                    java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                    if (lsPics == null) {
                %>
                <p>No Pictures found</p>
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
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
