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
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <%@include file="header.jsp"%>

        <div class="container padding-top-10">
            <div class="panel panel-default">
                <div class="panel-heading">Registration</div>
                <div class="panel-body">
                    <form method="POST" action="Register">
                        <label for="username" class="control-label padding-top-10">Username: </label>
                        <div class="row padding-top-10">
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="username" id="username" placeholder="Username"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 padding-top-10">
                                <label for="password" class="control-label">Password:</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password"/>
                            </div>
                             <div class="col-md-6 padding-top-10">
                                 <label for="confirmpassword" class="control-label">Confirm Password:</label>
                                 <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="Confirm your password"/>
                                         </div>
                             </div>
                        <label for="first_name" class="control-label padding-top-10">Name:</label>
                        <div class="row">
                            
                            <div class="col-md-6 padding-top-10">
                                <input type="text" class="form-control" name="first_name" id="first_name" placeholder="First Name"/>
                            </div>
                            <div class="col-md-6 padding-top-10">
                                <input type="text" class="form-control" name="last_name" id="last_name" placeholder="Last Name"/>
                            </div>
                        </div>
                        <label for="address1" class="control-label padding-top-10">Address: </label>
                        <div class="row padding-top-10">
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="address1" id="address1" placeholder="Address Line 1"/>
                            </div>
                        </div>
                        <div class="row padding-top-10">
                            <div class="col-md-12">
                                <input type="text" class="form-control" name="address2" id="address2" placeholder="Address Line 2"/>
                            </div>
                        </div>
                     
                        <div class="row">
                            <div class="col-md-6 padding-top-10">
                                <label for="city" class="control-label">City:</label>
                                <input type="text" class="form-control" name="city" id="city" placeholder="City"/>
                            </div>
                            <div class="col-md-2 padding-top-10">
                                <label for="region" class="control-label">Region:</label>
                                 <input type="text" class="form-control" name="region" id="region" placeholder="Region"/>
                            </div>
                            <div class="col-md-4 padding-top-10">
                                <label for="postcode" class="control-label">Postcode:</label>
                                 <input type="text" class="form-control" name="postcode" id="postcode" placeholder="Postcode"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 padding-top-10">
                                <label for="email" class="control-label">Email Address:</label>
                                <input type="text" class="form-control" name="email" id="email" placeholder="Email Address"/>
                            </div>
                      
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6 padding-top-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"/> Click here to agree to our terms of service
                                        
                                    </label>
                            </div
                            <div class="col-md-6 padding-top-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"/> Click here to opt out of our newsletter
                                    </label>
                            </div>
                        </div>
                        </div>
                            <div class="row">
                                <div class="col-md-2 col-md-offset-5">
                                    <input type="submit" value="Register" class="btn btn-success">
                                    
                                </div>
                            </div>
                    </form>
                </div>
            </div>
        </div>




    </body>
</html>
