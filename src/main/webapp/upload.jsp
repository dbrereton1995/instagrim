<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.djb.instagrim.lib.Convertors"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <%@include file="header.jsp"%>
    <body>



        <article>
            <div class="container padding-top-10">
                <div class="panel panel-default">
                    <% 
                        
                        
                        String args[] = (String[]) request.getAttribute("url");
                        if(args != null){
                        if(args.length == 3){
                            if(args[2].equals("ProfilePicture")){
                          %>
                            <div class="panel-heading"> Upload a Profile Picture </div>
                    <div class ="panel-body">
                        <form method="POST" enctype="multipart/form-data" action="Image">
                            <div class="row-padding-top-10">
                                <div class="col-md-3">
                                    <input type="file" name="upfile">
                                    <p></p>
                                </div>
                            </div>
                            <div class="col-md-3 padding-top-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" checked required="required" name="profilepicture"> Confirm Profile Picture submission
                                        </label>
                                    </div>
                                </div>
                            </br>
                            <div class="col-md-3 col-md-offset-4">
                                <input type="submit" value="Press"> to upload the file!
                            </div>
                        </form>
                    </div>
                         <%   }
     }                       
    }else{ %>
                    <div class="panel-heading"> Upload a File </div>
                    <div class ="panel-body">
                        <form method="POST" enctype="multipart/form-data" action="Image">
                            <div class="row-padding-top-10">
                                <div class="col-md-3">
                                    <input type="file" name="upfile">
                                    <p></p>
                                </div>
                            </div>
                            <div class="row padding-top-10" >
                                <div class="col-md-3">
                                    <p>Enter a description: </p>
                                    <input type="text" name="description"> 
                                </div>
                            </div>
                            </br>
                            <div class="col-md-3 col-md-offset-4">
                                <input type="submit" value="Press"> to upload the file!
                            </div>
                        </form>
                    </div>
                    <% } %>
                </div>
            </div>
        </article>

    </body>
</html>
