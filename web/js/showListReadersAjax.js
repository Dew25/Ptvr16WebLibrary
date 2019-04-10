   
   document.addEventListener('DOMContentLoaded', function() {
        getListReaders(); //запускаем функцию после загрузки всего скрипта
    }, false);
    // Получает от сервера список читателей и выводит на странице список
    function getListReaders(){
        var client = new getHttpRequest();
        client.get('ajax/getListReaders', function(response){
            var listReaders = JSON.parse(response); // список читателей от сервера
            var ul= document.getElementById("readers");
            ul.innerHTML=''; // очищаем список
            for(var i in listReaders){ //создаем список ul и добавляем li с данными из listReaders
                text=listReaders[i].name +' '+listReaders[i].surname + ' email: '+ listReaders[i].email;
                let li=document.createElement('li');
                li.innerHTML = text;
                ul.appendChild(li);
            };
            var listReaders = document.getElementById("listReaders");
            listReaders.appendChild(ul);// добавляем список на страницу
        });
    };
    // функция, которая осуществляет ajax запрос (get) на сервер 
    var getHttpRequest = function() {
        this.get = function(aUrl, aCallback) {
            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() { 
                if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
                    aCallback(anHttpRequest.responseText);
            };
            anHttpRequest.open( "GET", aUrl, true );            
            anHttpRequest.send( null );
        };
    };
    // функция, которая осуществляет ajax запрос (post) на сервер
    var postHttpRequest = function() {
        this.post = function(aUrl,data, aCallback) {
            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() { 
                if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
                    aCallback(anHttpRequest.responseText);
            };
            anHttpRequest.open( "POST", aUrl, true );            
            anHttpRequest.send(data);
        };
    };
    let btnAddNewReader=document.getElementById('addNewReader');
    btnAddNewReader.onclick = function(){ // назначаем кнопке событие запускающее функцию переключения
        let el = document.getElementById('showRegistration');
        toggleState(el);
    };
    // переключает видимость регистрации назначая диву класс on или off
    function toggleState(item){
       if(item.className === "on") {
          document.getElementById("form").reset(); // очищает форму
          item.className="off"; 
       } else {
          item.className="on";
          let reg = document.getElementById("registration");
          reg.onclick = function(){ // назанчаем кнопке события отправляющего POST запрос на сервер
            var client = new postHttpRequest();
            client.post('ajax/registration',formData(), function(response){
                 document.getElementById("info").innerHTML = JSON.parse(response);
                 getListReaders(); // переписываем список пользователей
                 let el = document.getElementById('showRegistration');
                 toggleState(el); // переключаем видимость слоя регистрации
            });
          }; 
       };
    };
    // создаем массив из значений элементов формы регистрации
    function formData(){
       let data = [
           document.getElementById("name").value,
           document.getElementById("surname").value,
           document.getElementById("email").value,
           document.getElementById("login").value,
           document.getElementById("password1").value,
           document.getElementById("password2").value
       ];
       return JSON.stringify(data);
    }