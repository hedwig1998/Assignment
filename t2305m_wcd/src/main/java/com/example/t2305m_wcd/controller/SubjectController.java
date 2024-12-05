package com.example.t2305m_wcd.controller;


import com.example.t2305m_wcd.dao.SubjectDAO;
import com.example.t2305m_wcd.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/subject")
public class SubjectController extends HttpServlet {
    private SubjectDAO subjectDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        subjectDAO = new SubjectDAO();
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
                        Subject editSubject = subjectDAO.find(editId);
                        if (editSubject != null) {
                            req.setAttribute("subject", editSubject);
                            renderForm(req, resp);
                        } else {
                            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Subject not found");
                        }
                    } else {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                    break;
                case "delete":
                    String deleteIdStr = req.getParameter("id");
                    if (deleteIdStr != null && deleteIdStr.matches("\\d+")) { // Ensure the ID is numeric
                        Long deleteId = Long.parseLong(deleteIdStr);
                        subjectDAO.delete(deleteId);
                        resp.sendRedirect("subject"); // Redirect to the list view
                    } else {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
                    }
                    break;
                default:
                    List<Subject> subjects = subjectDAO.all();
                    req.setAttribute("subjects", subjects);
                    RequestDispatcher listDispatcher = req.getRequestDispatcher("subject/subjectlist.jsp");
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
        RequestDispatcher formDispatcher = req.getRequestDispatcher("subject/subjectform.jsp");
        formDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        if ("update".equals(action)) {
            subjectDAO.update(new Subject(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("code"),
                    Long.parseLong(req.getParameter("hour"))
            ));
        } else {
            subjectDAO.create(new Subject(
                    null,
                    req.getParameter("name"),
                    req.getParameter("code"),
                    Long.parseLong(req.getParameter("hour"))
            ));
        }
        resp.sendRedirect("subject"); // Redirect to list view after create/update
    }
}
