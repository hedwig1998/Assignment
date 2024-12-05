package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.database.Database;
import com.example.t2305m_wcd.entity.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements DAOInterface<Class,Long>{
    @Override
    public List<Class> all() {
        List<Class> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM classes";
            Database db = Database.createInstance();
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                list.add(new Class(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        }catch (Exception e){

        }
        return list;
    }

    @Override
    public void create(Class classes) {
        try {
            String sql = "INSERT INTO classes(name) values(?)";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setString(1,classes.getName());
            pr.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Class classes) {
        try {
            String sql = "UPDATE classes SET name = ? WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setString(1, classes.getName());
            pr.setLong(2, classes.getId());
            pr.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM classes WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setLong(1, id);
            pr.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Class find(Long id) {
        Class cls = null;
        try {
            String sql = "SELECT * FROM classes WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement pr = db.getPreparedStatement(sql);
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                cls = new Class(
                        rs.getLong("id"),
                        rs.getString("name")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cls;
    }

}
