package com.example.t2305m_wcd.controller;

import com.example.t2305m_wcd.dao.StudentDAO;
import com.example.t2305m_wcd.database.Database;
import com.example.t2305m_wcd.entity.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet(value = "/student")
public class StudentController extends HttpServlet {
    private StudentDAO studentDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action")!=null?req.getParameter("action"):"";
        if(action.equals("create")){
            renderForm(req,resp);
            return;
        }
        List<Student> list = studentDAO.all();
        req.setAttribute("students",list);
        RequestDispatcher rd = req.getRequestDispatcher("student/list.jsp");
        rd.forward(req,resp);
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("student/form.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentDAO.create(new Student(
                null,
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("address"),
                req.getParameter("telephone")
        ));
        resp.sendRedirect("student");
    }
}