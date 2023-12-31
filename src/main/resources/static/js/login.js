$(document).ready(function() {
    // on ready
});
  
async function iniciarSesion() {
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    const request = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();
    if(respuesta != 'FAIL') {
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.replace("http://localhost:8080/usuarios.html");
    } else {
        alert("Las credenciales son incorrectas.")
    }
    
};
  