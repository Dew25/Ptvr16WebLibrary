
       <H3 class="text-center">Вы вошли как менеджер приложения</H3>
            <c:if test="${info ne null}">
                <div class="alert alert-primary" role="alert">${info}</div>
            </c:if>
        ${info}<br>
        <a href="logout">Выйти</a><br>
        <a href="showRegistration">Зарегистрировать нового пользователя</a><br>
        <a href="showListBooks">Показать список книг</a><br>
        <a href="showListReadersAjax">Показать список пользователей (ajax)</a><br>
        <a href="showChangePassword">Изменить свой пароль</a><br>
        <a href="showListReaders">Показать список читателей</a><br>
        <a href="showPageForGiveBook">Выдать книгу пользователю</a><br>
        <a href="showPageForReturnBook">Вернуть книгу</a><br>
        <a href="showAddNewBook">Добавить новую книгу</a><br>
        