// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});
  
async function registarUsuario() {
    
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    
    let repetirPassword = document.getElementById('txtRepetirPassword').value;

    if (repetirPassword != datos.password) {
        alert('Las contraseñas no coinciden.')
        return;
    }

    const request = await fetch('http://localhost:8080/usuario', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    // window.location.href = "login.html";
    window.location.replace("http://localhost:8080/login.html");
};
  