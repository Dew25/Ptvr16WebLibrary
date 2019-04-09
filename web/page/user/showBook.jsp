<%-- 
    Document   : showBook
    Created on : Mar 7, 2019, 10:14:49 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выбранная для просмотра книга</title>
    </head>
    <body>
        <h1>Просмотр книги!</h1>
        <p>${info}</p>
        <a href="index">На главную</a><br>
        Обложка: <br>
        <img src="insertFile/${cover.path}"><br>
        Заголовок: ${book.name}<br>
        Автор: ${book.author}<br>
        Доступно книг: ${book.quantity}<br>
        <c:if test="${notUser eq true}">
            <a href="showEditBook?bookId=${book.id}">Редактировать запись</a>
        </c:if>
        
        
        
    </body>
</html>
