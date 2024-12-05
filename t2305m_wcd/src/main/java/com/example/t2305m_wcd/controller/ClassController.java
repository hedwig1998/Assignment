package com.example.t2305m_wcd.controller;

import com.example.t2305m_wcd.dao.ClassDAO;
import com.example.t2305m_wcd.entity.Class;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/class")
public class ClassController extends HttpServlet {
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        try {
            switch (action) {
                case "create":
                    renderForm(req, resp);
                    break;
                case "edit":
                    String editIdStr = req.getParameter("id");
                    if (editIdStr != null && editIdStr.matches("\\d+")) { // Ensure the ID is numeric
                        Long editId = Long.parseLong(editIdStr);
                        Class editClass = classDAO.find(editId);
                        if (editClass != null) {
                            req.setAttribute("class", editClass);
                            renderForm(req, resp);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Class not found");
                        }
                    } else {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                    break;
                case "delete":
                    String deleteIdStr = req.getParameter("id");
                    if (deleteIdStr != null && deleteIdStr.matches("\\d+")) { // Ensure the ID is numeric
                        Long deleteId = Long.parseLong(deleteIdStr);
                        classDAO.delete(deleteId);
                        resp.sendRedirect("class"); // Redirect to the list view
                    } else {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                    break;
                default:
                    List<Class> classes = classDAO.all();
                    req.setAttribute("classes", classes);
                    RequestDispatcher listDispatcher = req.getRequestDispatcher("class/classlist.jsp");
                    listDispatcher.forward(req, resp);
                    break;
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }


    private void renderForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher formDispatcher = req.getRequestDispatcher("class/classform.jsp");
        formDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        if ("update".equals(action)) {
            classDAO.update(new Class(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("name")
            ));
        } else {
            classDAO.create(new Class(
                    null,
                    req.getParameter("name")
            ));
        }
        resp.sendRedirect("class"); // Redirect to list view after create/update
    }
}
