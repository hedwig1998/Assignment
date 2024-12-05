package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.database.Database;
import com.example.t2305m_wcd.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student,Long> {
    @Override
    public List<Student> all() {
        List<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students";
            Database db = Database.createInstance();
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                list.add(new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("telephone")
                ));
            }
        }catch (Exception e){

        }
        return list;
    }

    @Override
    public void create(Student student) {
        try {
            String sql = "INSERT INTO students(name,email,address,telephone) values(?,?,?,?)";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setString(1,student.getName());
            pr.setString(2,student.getEmail());
            pr.setString(3,student.getAddress());
            pr.setString(4,student.getTelephone());
            pr.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Student find(Long id) {
        return null;
    }
}