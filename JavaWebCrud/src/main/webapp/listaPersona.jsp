<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.models.Persona" %>

<html>
<head>
    <title>Lista de Personas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="NavBar.jsp" %>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Personas</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Edad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Persona> personas = (List<Persona>) request.getSession().getAttribute("ListaPersona");
                        if (personas != null && !personas.isEmpty()) {
                            for (Persona persona : personas) {
                    %>
                    <tr>
                        <td><%= persona.getId() %></td>
                        <td><%= persona.getNombre() %></td>
                        <td><%= persona.getCorreo() %></td>
                        <td><%= persona.getEdad() %></td>
                        <td>
                            <a href="SvPersona?id=<%= persona.getId() %>" class="btn btn-primary btn-sm">Ver Detalle</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay personas disponibles</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>