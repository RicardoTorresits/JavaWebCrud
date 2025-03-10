<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.models.Persona" %>

<html>
<head>
    <title>Detalle de Persona</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome para íconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <!-- Estilos personalizados -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            border-radius: 15px 15px 0 0;
            font-size: 1.25rem;
            font-weight: bold;
        }
        .list-group-item {
            border: none;
            padding: 1.25rem;
            background-color: #ffffff;
        }
        .list-group-item strong {
            color: #007bff;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 10px;
            padding: 10px 20px;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
            border-radius: 10px;
            padding: 10px 20px;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }
        .btn-danger:hover {
            background-color: #a71d2a;
        }
        .alert-warning {
            background-color: #fff3cd;
            border: none;
            border-radius: 10px;
            color: #856404;
            padding: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4" style="color: #007bff; font-weight: bold;">Detalle de Persona</h1>
        <%
            Persona persona = (Persona) request.getSession().getAttribute("persona");
            if (persona != null) {
        %>
        <div class="card mx-auto" style="max-width: 500px;">
            <div class="card-header text-center">
                <i class="fas fa-user-circle me-2"></i>Información de la Persona
            </div>
            <div class="card-body">
                <form action="SvPersona" method="put">
                    <input type="hidden" name="id" value="<%= persona.getId() %>">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <strong>ID:</strong> <%= persona.getId() %>
                        </li>
                        <li class="list-group-item">
                            <strong>Nombre:</strong>
                            <input type="text" class="form-control mt-2" name="nombre" value="<%= persona.getNombre() %>" required>
                        </li>
                        <li class="list-group-item">
                            <strong>Correo:</strong>
                            <input type="email" class="form-control mt-2" name="correo" value="<%= persona.getCorreo() %>" required>
                        </li>
                        <li class="list-group-item">
                            <strong>Edad:</strong>
                            <input type="number" class="form-control mt-2" name="edad" value="<%= persona.getEdad() %>" required>
                        </li>
                    </ul>
                    <div class="mt-4 d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-sync-alt me-2"></i>Actualizar
                        </button>
                        <button type="button" class="btn btn-danger" onclick="eliminarPersona(<%= persona.getId() %>)">
                            <i class="fas fa-trash-alt me-2"></i>Eliminar
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-warning mx-auto" style="max-width: 500px;">
            <i class="fas fa-exclamation-triangle me-2"></i>No se encontró información de la persona.
        </div>
        <%
            }
        %>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Script para eliminar persona -->
    <script>
        function eliminarPersona(id) {
            if (confirm("¿Estás seguro de que deseas eliminar esta persona?")) {
                fetch(`SvPersona?id=${persona.getId() }`, {
                    method: 'DELETE',
                })
                .then(response => {
                    if (response.ok) {
                        alert("Persona eliminada correctamente.");
                        window.location.href = "listaPersonas.jsp"; // Redirigir a la lista de personas
                    } else {
                        alert("Error al eliminar la persona.");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("Error al eliminar la persona.");
                });
            }
        }
    </script>
</body>
</html>