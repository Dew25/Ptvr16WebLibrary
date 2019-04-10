
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="col-md-5 offset-md-3 align-self-center">
            <h5 class="text-center">Введите данные пользователя</h5>
            <form id="form">
                <div class="form-group">
                  <label for="name">Имя:</label>
                  <input type="text" class="form-control" id="name" name="name" placeholder="Имя">
                </div>
                <div class="form-group">
                  <label for="surname">Фамилия:</label>
                  <input type="text" class="form-control" id="surname" name="surname" placeholder="Фамилия">
                </div>
                <div class="form-group">
                  <label for="email">Электронная почта</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="Электронная почта">
                </div>
                <div class="form-group">
                  <label for="login">Логин:</label>
                  <input type="text" class="form-control" id="login" name="login" placeholder="Login">
                </div>
                <div class="form-group">
                  <label for="password1">Пароль:</label>
                  <input type="password" class="form-control" id="password1" name="password1" placeholder="Пароль">
                </div>
                <div class="form-group">
                  <label for="password2">Повторите пароль:</label>
                  <input type="password" class="form-control" id="password2" name="password2" placeholder="Повторите пароль">
                </div>
                <button type="button" id="registration" class="btn btn-primary">Зарегистрироваться</button>
            </form>
