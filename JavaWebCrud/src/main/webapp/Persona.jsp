<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.models.Persona" %>

<html>
<head>
    <title>Detalle de Persona</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Detalle de Persona</h1>
        <%
            Persona persona = (Persona) request.getSession().getAttribute("persona");
            if (persona != null) {
        %>
        <div class="card mx-auto" style="max-width: 400px;">
            <div class="card-header">
                Información de la Persona
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><strong>ID:</strong> <%= persona.getId() %></li>
                    <li class="list-group-item"><strong>Nombre:</strong> <%= persona.getNombre() %></li>
                    <li class="list-group-item"><strong>Correo:</strong> <%= persona.getCorreo() %></li>
                    <li class="list-group-item"><strong>Edad:</strong> <%= persona.getEdad() %></li>
                </ul>
            </div>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-warning text-center" role="alert">
            No se encontró información de la persona.
        </div>
        <%
            }
        %>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>