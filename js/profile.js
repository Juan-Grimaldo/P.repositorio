var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        $("#user-cant-donada").html("KG Donados: " + user.cant_donada.toFixed());

        getDonaciones(user.username);
    });

    $("#donar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-username").val(parsedResult.username);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellidos").val(parsedResult.apellidos);
                $("#input-telefono").val(parsedResult.telefono);
                $("#input-email").val(parsedResult.email);
                $("#input-direccion").val(parsedResult.direccion);
                $("#input-cant").val(parsedResult.cant_donada.toFixed(2));
                $("#input-tipousu").prop("checked", parsedResult.tipo_usuario);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getDonaciones(username) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDonacionListar",
        data: $.param({
            username: username,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las donaciones");
            }
        }
    });
}

function mostrarHistorial(donaciones) {
    console.log('Donacion arrar ->::'+donaciones);
    let contenido = "";
    if (donaciones.length >= 1) {
        $.each(donaciones, function (index, donacion) {
            donacion = JSON.parse(donacion);

            contenido += '<tr><th scope="row">' + donacion.id_producto + '</th>' +
                    '<td>' + donacion.username + '</td>' +
                    '<td>' + donacion.categoria + '</td>' +
                    '<td><input type="checkbox" name="perecedero" id="perecedero' + donacion.id_producto 
                    + '" disabled ';
            if (donacion.perecedero) {
                contenido += 'checked'
            }
            contenido += '></td><td>' + donacion.fecha_donacion + '</td> </tr>';
//                    '<td><button id="devolver-btn" onclick= "devolverproducto(' + donacion.id_producto 
//                    + ');" class="btn btn-danger">Devolver Producto</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function devolverProducto(id_producto) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoDevolver",
        data: $.param({
            username: username,
            id: id,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error devolviendo el Producto");
            }
        }
    });

}

function modificarUsuario() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellidos = $("#input-apellidos").val();
    let telefono = $("#input-telefono").val();
    let email = $("#input-email").val();
    let direccion = $("#input-direccion").val();
    let cant_donada = $("#input-cant").val();
    let tipo_usuario = $("#input-tipousu").prop('checked');
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            username: username,
            contrasena: contrasena,
            nombre: nombre,
            apellidos: apellidos,
            telefono: telefono,
            email: email,
            direccion: direccion,
            cant_donada: cant_donada,
            tipo_usuario: tipo_usuario,
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            username: username
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}
