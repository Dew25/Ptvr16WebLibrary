<%-- 
    Document   : index
    Created on : Jan 14, 2019, 9:28:52 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Библиотека</title>
    </head>
    <body>
        <H1>Добро пожаловать в нашу библиотеку</H1>
        ${info}<br>
        <a href="page1.jsp">Page1</a><br>
        <a href="showListBooks">Список книг</a><br>
        <a href="showListReaders">Список читателей</a><br>
        <a href="showPageForGiveBook">Выдать книгу</a><br>
        <a href="showPageForReturnBook">Вернуть книгу</a><br>
        <a href="showAddNewBook">Добавить книгу</a><br>
        <a href="showAddNewReader">Добавить читателя</a><br>
        <script src="js/index.js"></script>
    </body>
</html>
