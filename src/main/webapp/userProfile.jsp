<%@page import="uk.ac.dundee.computing.djb.instagrim.models.PicModel"%>
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
            //grab username, firstname, lastname and country attributes from the user's record in userprofiles table
            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");
            String firstname = (String) session.getAttribute("firstname");
            String lastname = (String) session.getAttribute("lastname");
            String country = (String) session.getAttribute("country");
            Pic profilePic = (Pic) request.getAttribute("profilePicture");
            int numOfPics = 0;
        %>

        <div class="col-sm-4">
            <% if (profilePic == null) {%>
            <img src="/Instagrim/resources/blankProfilePic.png" alt="<%=username%>'s Profile Picture" style="max-width:100%; height:auto; width:auto\9; display:block; margin:auto;">
            <%} else {%>
            <img src="/Instagrim/Thumb/<%=profilePic.getSUUID()%>" alt="<%=username%>'s Profile Picture" style="max-width:100%; height:auto; width:auto\9; display:block; margin:auto;">
            <%}%>
            <p style="text-align: center;"><a href="/Instagrim/Upload/ProfilePicture">Change Profile Picture</a></p>
        </div>

        <h1> <%=username%>'s Profile </h1> <a href="/Instagrim/profile/editProfile"> Edit Profile </a>
        <h4> <b>Name:</b> <%=firstname%> <%=lastname%> </h4>
        <h4> <b>Location:</b> <%=country%> </h4>
        <h4> <b>Email:</b> <%=email%> </h4>
        <h4> <b>Pictures Uploaded:</b> <%=numOfPics%> </h4>
        
        <div class="col-sm-12">
            <br></br>
            <article>
                <%
                    //get a list of all the user's pictures
                    java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                    //but if the list is empty, display "No Pictures found"
                    if (lsPics == null) {
                %>

                <p style="text-align: center;">No Pictures found</p>

                <%
                } else {
                    //else Iterate through the Pic list
                    Iterator<Pic> iterator;
                    iterator = lsPics.iterator();
                    while (iterator.hasNext()) {
                        Pic p = (Pic) iterator.next();
                        numOfPics++;
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
