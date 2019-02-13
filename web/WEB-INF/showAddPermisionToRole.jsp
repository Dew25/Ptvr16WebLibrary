<%-- 
    Document   : addPermisionToRole
    Created on : Feb 13, 2019, 3:05:53 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Добавить разрешение для роли</h1>
        <form action="addPermisionToRole" method="POST">
            <select name="roleId">
                <c:forEach var="role" items="${listRoles}">
                    <option value="${role.id}">${role.name}</option>
                </c:forEach>
            </select>
            <select name="permisionId">
                <c:forEach var="permision" items="${listPermisions}">
                    <option value="${permision.id}">${permision.permisionName.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>
