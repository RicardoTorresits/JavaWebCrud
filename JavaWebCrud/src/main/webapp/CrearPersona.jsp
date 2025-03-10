<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Persona</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Barra de Navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sistema de Personas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="listaPersonas.jsp">Lista de Personas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="crearPersona.jsp">Crear Persona</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Formulario para Crear Persona -->
    <div class="container mt-5">
        <h1 class="text-center mb-4">Crear Nueva Persona</h1>
        <form action="guardarPersona" method="post" class="needs-validation" novalidate>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
                <div class="invalid-feedback">
                    Por favor, ingresa un nombre.
                </div>
            </div>
            <div class="mb-3">
                <label for="correo" class="form-label">Correo</label>
                <input type="email" class="form-control" id="correo" name="correo" required>
                <div class="invalid-feedback">
                    Por favor, ingresa un correo válido.
                </div>
            </div>
            <div class="mb-3">
                <label for="edad" class="form-label">Edad</label>
                <input type="number" class="form-control" id="edad" name="edad" required>
                <div class="invalid-feedback">
                    Por favor, ingresa una edad válida.
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Validación del Formulario -->
    <script>
        (function () {
            'use strict';
            var forms = document.querySelectorAll('.needs-validation');
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>
</html>