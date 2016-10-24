<%@page import="uk.ac.dundee.computing.djb.instagrim.models.PicModel"%>
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
            
            <article>
                <%
                    //get a list of all the user's pictures
                    java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                    //but if the list is empty, display "No Pictures found"
                    if (lsPics == null) {
                %>
                <p>No Pictures found</p>
                <%
                } else {
                    //else Iterate through the Pic list
                    Iterator<Pic> iterator;
                    iterator = lsPics.iterator();
                    while (iterator.hasNext()) {
                        Pic p = (Pic) iterator.next();
                %>
                
                <!-- displays picture and corresponding description -->
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
