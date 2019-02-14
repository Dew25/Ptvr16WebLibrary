<%-- 
    Document   : showPermisionName
    Created on : Feb 13, 2019, 2:26:11 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Разрешения</title>
    </head>
    <body>
        <h1>Добавть новое имя разрешения</h1>
        <form action="createPermisionName" method="POST">
            <select name="permisionNameId">
                <c:forEach var="permisionName" items="${listPermisionNames}">
                    <option value="${permisionName.id}">${permisionName.name}</option>
                </c:forEach>
            </select>
            <input type="text" name="newPermisionName">
            <input type="submit" value="Добавить">
        </form>
         <h1>Добавить разрешение!</h1>
        <form action="createPermisions" method="POST">
            <select name="permisionNameId">
                <c:forEach var="permisionName" items="${listPermisionNames}">
                    <option value="${permisionName.id}">${permisionName.name}</option>
                </c:forEach>
            </select>
            <select name="roleId">
                <c:forEach var="role" items="${listRoles}">
                    <option value="${role.id}">${role.name}</option>
                </c:forEach>
            </select>
            
            <input type="submit" value="Добавить">
        </form>
        
        
    </body>
</html>
