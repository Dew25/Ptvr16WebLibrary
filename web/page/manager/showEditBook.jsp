<%-- 
    Document   : page3
    Created on : Dec 10, 2018, 11:03:45 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Библиотека</title>
    </head>
    <body>
        <h1>Редактировать книгу</h1>
        ${info}<br>
        <a href="index">Главная страница</a><br>
        <a href="showUploadFile">Загрузить изображение обложки книги</a>
        <form action="editBook" method="POST">
            <input type="hidden" name="bookId" value="${book.id}"
            Название:<br>
            <input type="text" name="name" value="${book.name}"><br>
            Автор:<br>
            <input type="text" name="author" value="${book.author}"><br>
            ISBN:<br>
            <input type="text" name="isbn" value="${book.isbn}"><br>
            Количество экземпляров:<br>
            <input type="text" name="quantity" value="${book.quantity}"><br>
            <br>
            Обложка книги:<br>
            <select name="coverId">
                <c:forEach var="cover" items="${listCovers}">
                    <c:if test="${cover eq selectedCover}">
                        <option selected value="${cover.id}">${cover.name}</option>
                    </c:if>
                    <c:if test="${cover ne selectedCover}">
                        <option value="${cover.id}">${cover.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Изменить книгу">
        </form>
    </body>
</html>
