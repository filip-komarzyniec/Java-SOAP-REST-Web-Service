<%-- 
    Document   : registerImg
    Created on : 23 paź 2019, 14:35:38
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
Server Version: <%= application.getServerInfo() %><br>
Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>
JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> <br>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <!--<link rel="stylesheet" href="registerImg.css">-->
    </head>
    <body>
        <header class="header-register">
            <h1>PAM Second Lab Session</h1>
            <h4>Please, register images of your likings</h4>
        </header>
        <form name="second_lab_session" action="RegisterImg" method="post" >
            <div>
                <label for="Title">Title:</label>
                <input name="title" type="text" value="obrazek" autocomplete="off"required >
            </div>
            <div>
                <label for="description">Description:</label>
                <input name="description" type="text" value="opisik" autocomplete="off" required>
            </div>
            <div>
                <label for="keyword">Keywords:</label>
                <input name="keywords" type="text" value="zabawa,lato" autocomplete="off" required>
            </div>
            <div>
                <label for="author">Author:</label>
                <input name="author" type="text" value="filip" autocomplete="off" required>
            </div>
            <div>
                <label for="creation_date">Creation Date:</label>
                <input name="creation_date" type="date" placeholder="YYYY/MM/DD" autocomplete="off" required>
            </div>
            <div>
                <label for="storage_date">Storage Date:</label>
                <input name="storage_date" type="text" placeholder="YYYY/MM/DD" autocomplete="off" required>
            </div>
            <div>
                <label for="filename">Filename:</label>    
                <input name="filename" type="text" value="sweeties" autocomplete="off" required>
            </div>
            <input class="done" type="submit">
        </form> 
        
    </body>
</html>
