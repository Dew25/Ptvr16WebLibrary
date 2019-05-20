
        <h1>Выдать книгу читателю</h1>
        ${info}<br>
        <a href="index">Главная страница</a><br>
        <form action="giveBook" method="POST">
            <select name="bookId">
                <c:forEach var="bookItem" items="${listBooks}">
                    <option value="${bookItem.id}">${bookItem.name}</option>
                </c:forEach>
            </select>
            <select name="readerId">
                <c:forEach var="readerItem" items="${listReaders}">
                    <option value="${readerItem.id}">${readerItem.name} ${readerItem.surname}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Выдать книгу">
        </form>
