// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
});

function actualizarEmailDelUsuario() {
  document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios() {
    const request = await fetch('http://localhost:8080/usuarios', {
      method: 'GET',
      headers: getHeaders()
    });
    const usuarios = await request.json();
  
    console.log(usuarios);


    let listadoHtml = '';
    for (let usuario of usuarios) {

      let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'

      let telefono = usuario.telefono == null ? '-' : usuario.telefono;

      let usuarioHtml = `
        <tr>
          <td>${usuario.id}</td>
          <td>${usuario.nombre + " " + usuario.apellido}</td>
          <td>${usuario.email}</td>
          <td>${telefono}</td>
          <td>
              <!-- Delete -->
              ${botonEliminar}
          </td>
        </tr>
      `;

      listadoHtml += usuarioHtml;
    };

  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
};


function getHeaders() {
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  }
}

async function eliminarUsuario(id) {

  if (confirm('¿Desea eliminar este usuario?')) {
    const request = await fetch('http://localhost:8080/usuario/' + id, {
      method: 'DELETE',
      headers: getHeaders()
    })
    .then(() => {
      location.reload();
    })
    .catch(error => {
      throw(error);
    });
    
  } else {
    return;
  }
}