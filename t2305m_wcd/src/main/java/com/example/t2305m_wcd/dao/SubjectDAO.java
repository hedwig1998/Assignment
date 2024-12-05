package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.database.Database;

import com.example.t2305m_wcd.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAOInterface<Subject,Long>{
    @Override
    public List<Subject> all() {
        List<Subject> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM subjects";
            Database db = Database.createInstance();
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                list.add(new Subject(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getLong("hour")
                ));
            }
        }catch (Exception e){

        }
        return list;
    }

    @Override
    public void create(Subject subject) {
        try {
            String sql = "INSERT INTO subjects (name, code, hour) VALUES (?, ?, ?)";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setString(1, subject.getName());
            pr.setString(2, subject.getCode());
            pr.setLong(3, subject.getHour());
            pr.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void update(Subject subject) {
        try {
            String sql = "UPDATE subjects SET name = ?, code = ?, hour = ? WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setString(1, subject.getName());
            pr.setString(2, subject.getCode());
            pr.setLong(3, subject.getHour());
            pr.setLong(4, subject.getId());
            pr.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM subjects WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setLong(1, id);
            pr.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Subject find(Long id) {
        Subject sbj = null;
        try {
            String sql = "SELECT * FROM subjects WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                sbj = new Subject(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getLong("hour")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sbj;
    }

}
