<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.djb.instagrim.models.User"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>

<html>

    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name ="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <%@include file="header.jsp"%>

        <div class="col-sm-2 well">
            <h4>Latest News</h4>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ornare pulvinar felis a aliquam. Curabitur eu massa ligula. Maecenas tempus dignissim nisi, sed vulputate diam ullamcorper id. 
        </div>

        <div class="mainIndex col-sm-8 well-lg">
            <h1>Recent Uploads</h1>
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
}
}
                %>
            
        </div>
        
        <div class="col-sm-2 well">
            <div class="input-group col-xs-12">
                        <input type="text" class="search-query form-control" name="searchBox" placeholder="Search"/>
                        <span class="input-group-btn">
                            <input type="submit" class="btn btn-success" type="button">       
                        </span>
                    </div>
        </div>
        <div class="col-sm-2 well">
            <h4 style="text-align: center;">New Users</h4>
            <%
            //User user = new User();
            //user.selectNewUsers();
            %>
        </div>
    





    </body>
</html>
