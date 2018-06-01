/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkRegistro(){
    var galletaReg = document.cookie;
    var textToast1 = "<span>Registrado con éxito</span>";
    var textToast2 ="<span>No se pudo iniciar sesión, verifica tus datos</span>";
    if(getCookie("registro") !== ""){
        M.toast({html: textToast1,displayLength : 8000,inDuration: 4000,outDuration:3000, classes: 'orange darken-2' });
    }
    if(getCookie("login")==="false"){
         M.toast({html: textToast2,displayLength : 8000,inDuration: 4000,outDuration:3000, classes: 'orange darken-2' });
    }
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkUsuario(){
    var elemento = document.getElementById("usuario").value;
    
    $.ajax({
        // En data puedes utilizar un objeto JSON, un array o un query string
        data: {"nombre" : elemento},
        //Cambiar a type: POST si necesario
        type: "GET",
        // Formato de datos que se espera en la respuesta
        dataType: "text",
        // URL a la que se enviará la solicitud Ajax
        url: "GetUsuario"
    })
     .done(function( data, textStatus, jqXHR ) {
         if ( console && console.log ) {
            console.log( "La solicitud se ha completado correctamente." );
            document.getElementById("nick").innerHTML=data;
            console.log(data.id_ocupada);
         }
     })
     .fail(function( jqXHR, textStatus, errorThrown ) {
         if ( console && console.log ) {
             console.log( "La solicitud a fallado: " +  textStatus);
         }
    });
}

function checkUsuarioJSON(){
     var elemento = document.getElementById("usuario").value;
    
    $.ajax({
        // En data puedes utilizar un objeto JSON, un array o un query string
        data: {"nombre" : elemento},
        //Cambiar a type: POST si necesario
        type: "GET",
        // Formato de datos que se espera en la respuesta
        dataType: "text",
        // URL a la que se enviará la solicitud Ajax
        url: "GetUsuario"
    })
     .done(function( data, textStatus, jqXHR ) {
         if ( console && console.log ) {
            console.log( "La solicitud se ha completado correctamente." );
            var obj = JSON.parse(data);
            console.log(obj.id_cliente);
            editHTMLJson(obj);
         }
     })
     .fail(function( jqXHR, textStatus, errorThrown ) {
         if ( console && console.log ) {
             console.log( "La solicitud a fallado: " +  textStatus);
         }
    });
}

function editHTMLJson(objJson){
    var tamElem = document.getElementById("usuario").value.length;
    if(tamElem<6){
         document.getElementById("nick").innerHTML="<span class=\"red-text text-darken-2\">Ingresa más de 6 caracteres</span>";
         document.getElementById("usuario").className="invalid";
    }
    if(objJson.id_cliente ==="ocupado" && tamElem>=6){
        console.log(tamElem);
        document.getElementById("nick").innerHTML="<span class=\"red-text text-darken-2\">Nombre de usuario ocupado</span>";
        document.getElementById("usuario").className="invalid";
    }else if(objJson.id_cliente ==="libre" && tamElem>=6){
        document.getElementById("nick").innerHTML="<span class=\"green-text \">Nombre de usuario disponible</span>";
        console.log(tamElem);
        document.getElementById("usuario").className="valid";
    }
    
}

function agregarCarrito(id_elemento){
    $.ajax({
        // En data puedes utilizar un objeto JSON, un array o un query string
        data: {"elemento" : id_elemento},
        //Cambiar a type: POST si necesario
        type: "GET",
        // Formato de datos que se espera en la respuesta
        dataType: "text",
        // URL a la que se enviará la solicitud Ajax
        url: "Carrito"
    })
     .done(function( data, textStatus, jqXHR ) {
         if ( console && console.log ) {
            console.log( "La solicitud se ha completado correctamente." );
            var obj = JSON.parse(data);
            console.log(obj.addSucces);
         }
     })
     .fail(function( jqXHR, textStatus, errorThrown ) {
         if ( console && console.log ) {
             console.log( "La solicitud a fallado: " +  textStatus);
         }
    });
}