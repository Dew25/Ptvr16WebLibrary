
        <h1>Список читателей</h1>
        ${info}<br>
        <a href="index">Главная страница</a><br>
        <ul>
            <c:forEach var="reader" items="${listReaders}">
                <li>${reader.name}, ${reader.surname}, ${reader.email}
            </c:forEach>
        </ul>

