<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
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
        <%@include file="header.jsp"%>

        <%
            String errorMsg = (String) request.getAttribute("errorMsg");

            if (errorMsg != null) {


        %>
        <div>
            <%=errorMsg%>
        </div>
        <%
            }

            //grab username, firstname, lastname and country attributes from the user's record in userprofiles table
            String username = (String) session.getAttribute("username");
            String StoredFirstName = (String) session.getAttribute("firstname");
            String StoredLastName = (String) session.getAttribute("lastname");
            String StoredCountry = (String) session.getAttribute("country");
            String StoredEmail = (String) session.getAttribute("email");
        %>

        <div class="container padding-top-10">
            <div class="panel panel-default">
                <div class="panel-heading">Edit User Profile</div>
                <div class="panel-body">
                    <form method="POST" action="profile">
                        
                        <div class="row">
                            <div class="col-md-6 padding-top-10">
                                <label for="email" class="control-label">Email Address: 
                                </label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" required="required" title="Valid email address required" value="<%=StoredEmail%>"/>
                            </div>

                        </div>
                        <label for="first_name" class="control-label padding-top-10">Name:</label>
                        <div class="row">

                            <div class="col-md-6 padding-top-10">
                                <input type="text" class="form-control" name="first_name" id="first_name" placeholder="First Name" value="<%=StoredFirstName%>"/>
                            </div>
                            <div class="col-md-6 padding-top-10">
                                <input type="text" class="form-control" name="last_name" id="last_name" placeholder="Last Name" value="<%=StoredLastName%>"/>
                            </div>
                        </div>

                            <div class="col-md-4 padding-top-10">
                                <label for="region" class="control-label">Country:</label>
                                <input type="text" class="form-control" name="country" id="region" placeholder="Country" required="required" value="<%=StoredCountry%>"/>
                            </div>
                         
                        </div>

                            <div class="row">
                                <div class="col-md-2 col-md-offset-5">
                                    <input type="submit" value="Update" class="btn btn-success">

                                </div>
                            </div>
                    </form>
                </div>
            </div>
        




    </body>
</html>
