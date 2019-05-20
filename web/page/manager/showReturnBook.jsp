
        <h1>Добавить книгу</h1>
        ${info}<br>
        <a href="index">Главная страница</a><br>
        <form action="returnBook" method="POST">
            <ul>
                <c:forEach var="historyItem" items="${listHistories}">
                    <li>${historyItem.id}. ${historyItem.reader.name} ${historyItem.reader.surname} читает книгу ${historyItem.book.name}</li>
                </c:forEach>
            </ul>
            <br>
            Номер позиции возврата:<br>
            <input type="text" name="returnHistoryId">
            <input type="submit" value="Вернуть книгу">
        </form>

