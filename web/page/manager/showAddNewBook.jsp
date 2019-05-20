
        <h1>Добавить книгу</h1>
        ${info}<br>
        <a href="index">Главная страница</a><br>
        <a href="showUploadFile">Загрузить изображение обложки книги</a>
        <form action="addNewBook" method="POST">
            Название:<br>
            <input type="text" name="name"><br>
            Автор:<br>
            <input type="text" name="author"><br>
            ISBN:<br>
            <input type="text" name="isbn"><br>
            Количество экземпляров:<br>
            <input type="text" name="count"><br>
            <br>
            Обложка книги:<br>
            <select name="coverId">
                <c:forEach var="cover" items="${listCovers}">
                    <option value="${cover.id}">${cover.name}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Добавить книгу">
        </form>
    
