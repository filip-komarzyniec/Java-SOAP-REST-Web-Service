<%-- 
    Document   : menu
    Created on : 23 paź 2019, 14:35:20
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>App Menu</title>
        <!-- <link rel="stylesheet" href="registerImg.css"/> -->
    </head>
    <body>
        <div class="container">
            <header>
                <h1>What do you want to do?</h1>
            </header>
            <ul>
                <li class="register">
                    <a class="menu_anch" href="registerImg.jsp">Upload an image</a>
                </li>
                <li class="search">
                    <a class="menu_anch" href="SearchBy">Search for an image</a>
                </li>
                <li class="view">
                    <a class="menu_anch" href="ListImage">View all of the images</a>
                </li>
            </ul>
            <footer>
                <p>Created by Filip Komarzyniec and Milan Ściślewski<br>all rights reserved &#xa9;</p>
            </footer>
        </div>
    </body>
</html>
