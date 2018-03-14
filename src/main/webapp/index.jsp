<%-- 
    Document   : index
    Created on : Mar 14, 2018, 1:44:42 PM
    Author     : M
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String url = "index.xhtml";
            response.sendRedirect(url);
        %>
        <h1>Hello World!</h1>
    </body>
</html>
