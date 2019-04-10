<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Библиотека</title>
    </head>
    <body>
        <link rel="stylesheet" href="css/showListReadersAjax.css"/>
        <h1>Список читателей</h1>
        <p id="info"></p>
        <a href="index">Главная страница</a><br>
        <div id="listReaders">
            <ul id="readers"></ul>
        </div>
        <input type="button" id="addNewReader" value="Добавить нового пользователя">
        <div class="off" id="showRegistration">
            <c:import url="addNewUserAjax.jsp"></c:import>
        </div>
        
        <script src="js/showListReadersAjax.js"></script>
    </body>
    
</html>
