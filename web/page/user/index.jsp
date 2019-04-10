
       <H3 class="text-center">Вы вошли как пользователь приложения</H3>
            <c:if test="${info ne null}">
                <div class="alert alert-primary" role="alert">${info}</div>
            </c:if>
        <a href="logout">Выйти</a><br>
        <a href="showListBooks">Показать список книг</a><br>
        <a href="showChangePassword">Изменить свой пароль</a><br>
    