<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.t2305m_wcd.entity.Subject" %>
<html>
<head>
    <title><%= request.getAttribute("subject") != null ? "Edit Subject" : "Create a new Subject" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form action="subject" method="post">
        <h1><%= request.getAttribute("subject") != null ? "Edit Subject" : "Create a new Subject" %></h1>
        <%
            Subject sbj = (Subject) request.getAttribute("subject");
            boolean isEditing = sbj != null;
        %>
        <% if (isEditing) { %>
        <input type="hidden" name="id" value="<%= sbj.getId() %>">
        <% } %>
        <input type="hidden" name="action" value="<%= isEditing ? "update" : "create" %>">

        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="<%= isEditing ? sbj.getName() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Code</label>
            <input type="text" name="code" class="form-control" value="<%= isEditing ? sbj.getCode() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Hour</label>
            <input type="text" name="hour" class="form-control" value="<%= isEditing ? sbj.getHour() : "" %>" required>
        </div>
        <button type="submit" class="btn btn-primary"><%= isEditing ? "Update" : "Submit" %></button>
    </form>

</div>
</body>
</html>
