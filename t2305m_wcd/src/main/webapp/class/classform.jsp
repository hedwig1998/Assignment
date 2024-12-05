<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.t2305m_wcd.entity.Class" %>
<html>
<head>
    <title><%= request.getAttribute("class") != null ? "Edit Class" : "Create a new Class" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form action="class" method="post">
        <h1><%= request.getAttribute("class") != null ? "Edit Class" : "Create a new Class" %></h1>
        <%
            Class cls = (Class) request.getAttribute("class"); // Check if we're editing or creating
            boolean isEditing = cls != null;
        %>
        <!-- Hidden field for class ID (only for editing) -->
        <% if (isEditing) { %>
        <input type="hidden" name="id" value="<%= cls.getId() %>">
        <% } %>
        <!-- Hidden field for action -->
        <input type="hidden" name="action" value="<%= isEditing ? "update" : "create" %>">

        <div class="mb-3">
            <label class="form-label">Name</label>
            <!-- Pre-fill the name field if editing -->
            <input type="text" name="name" class="form-control" value="<%= isEditing ? cls.getName() : "" %>" required>
        </div>
        <button type="submit" class="btn btn-primary"><%= isEditing ? "Update" : "Submit" %></button>
    </form>
</div>
</body>
</html>
