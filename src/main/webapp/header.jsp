<%-- 
    Document   : header
    Created on : 28-Sep-2016, 16:12:33
    Author     : dbrer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
             <div class="header">
        <div class ="container-fluid">
            <div class="row">
                
                <div class="header col-sm-12">
                    <img src="logo.png" alt="InstaGrim Logo" style="max-width:100%; height:auto; width:auto\9;">  
                    
                </div>
      
           
                    
                        
                        
                </div>
                
            
        </div>
        </div>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    
                    <!--<div class = "searchbar">
                        <form>
                            <input class = "searchInput" placeholder="Enter Search Term..." type="search" value="" name="search">
                            <input class = "searchSubmit" type="submit" value="">
                            <span class="searchIcon"></span>
                        </form>
                    </div>-->
                </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="upload.jsp">Upload</a></li>
                        
                        <li><a href="#">Other Thing</a></li>
                    </ul>
                
                
                
                <ul class="nav navbar-nav navbar-right">
                    <%  //Returns LoggedIn object from current session
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    
                    if(lg!=null){
                        String UserName = lg.getUsername();
                        if(lg.getlogedin()){
                    %>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <% }
}else{
%>
}
                   
                    <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <% }
%>
                </ul>
            </div>
        </nav>
    </body>
</html>
