<%-- 
    Document   : showBook
    Created on : Mar 7, 2019, 10:14:49 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выбранная для просмотра книга</title>
    </head>
    <body>
        <h1>Просмотр книги!</h1>
        Обложка: <br>
        <img src="insertFile/${selectedCover.path}"><br>
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
        Заголовок: ${book.name}<br>
        Автор: ${book.author}<br>
        Доступно книг: ${book.count}<br>
        <a href="showEditBook?id=${book.id}">Редактировать запись</a>
        
    </body>
</html>
