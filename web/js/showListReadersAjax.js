
var listReaders = document.getElementById("listReaders");
listReaders.appendChild(printListReaders());
function printListReaders(){
    var xhr = new XMLHttpRequest();
    xhr.open('GET','http://localhost/8080/Ptvr16WebLibrary/ajax/getListReaders',true);
    xhr.send();
    if(xhr.status != 200){
        return 'Список пуст';
    }
    var listReaders = JSON.parse(xhr.responseText);
    var ul= document.createElement('ul');
    var li = document.createElement('li');
    for(var reader in listReaders){
        text=reader['name']+' '+reader['surname'] + ' email: '+ reader['email'];
        li.innerHTML = text;
        ul.appendChild(li);
    }
    return ul;
}

