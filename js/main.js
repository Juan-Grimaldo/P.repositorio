var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {

        $("#mi-perfil-btn").attr("href", "profile.html?username=" + username);

        $("#user-cant-donada").html(user.cant_donada.toFixed(2) + "KG");

        getProductos(false, "ASC");

        $("#ordenar-categoria").click(ordenarproductos);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}


function getProductos(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarProductos(parsedResult);
            } else {
                console.log("Error recuperando los datos de los productos");
            }
        }
    });
}

function mostrarProductos(productos) {

    let contenido = "";

    $.each(productos, function (index, producto) {

        producto = JSON.parse(producto);
        console.log(producto);
        let cant_prod;

        if (producto.cant > 0) {
            
            cant_prod = 5;

            contenido += '<tr><th scope="row">' + producto.id_producto + '</th>' +
                    '<td>' + producto.nombre + '</td>' +
                    '<td>' + producto.categoria + '</td>' +
                    '<td align="center">' + producto.cant +'</td>' +
                    '<td><input type="checkbox" name="perecedero" id="perecedero' + producto.id_producto + '" disabled ';
            if (producto.perecedero) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + cant_prod + ' Kilos'+  '</td>' +
                    '<td><button onclick="donarProducto(' + producto.id_producto + ',' + cant_prod + ');" class="btn btn-success" ';
            contenido += '>Donar</button></td></tr>'

        }
    });
    $("#productos-tbody").html(contenido);
}


function ordenarproductos() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getProductos(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getProductos(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getProductos(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function donarProducto(id_producto, cant_prod) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoDonar",
        data: $.param({
            id_producto: id_producto,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                sumarKGDonados(cant_prod).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la donación del producto");
            }
        }
    });
}


async function sumarKGDonados(cant_prod) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioSumarKGDonados",
        data: $.param({
            username: username,
            cant_donada: parseFloat(user.cant_donada + cant_prod)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
//            console.log('por aca' + parsedResult);

            if (parsedResult != false) {
                console.log("Cantidad donada actualizada");
            } else {
                console.log("Error en el proceso de donación");
            }
        }
    });
}