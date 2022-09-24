package com.example.libraryproject.Service;

import com.example.libraryproject.Domain.Lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibService {
    public List<Lib> NearByWifi(double x, double y){
        String url = "jdbc:mariadb://127.0.0.1:3306/libinfo";
        String dbuserId = "admin";
        String dbPassword = "lib";
        List<Lib> nearby = new ArrayList<>();
        // 1. 드라이버로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparestatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);
            String sql = " SELECT (6371*acos(cos(radians(?))*cos(radians(`Y좌표`))*cos(radians(`X좌표`) " +
                    " -radians(?))+sin(radians(?))*sin(radians(`Y좌표`)))) AS distance " +
                    " ,wf.* " +
                    " FROM wifi wf " +
                    " ORDER BY distance " +
                    " limit 20 ";
            preparestatement = connection.prepareStatement(sql);
            preparestatement.setDouble(1,x);
            preparestatement.setDouble(2,y);
            preparestatement.setDouble(3,x);
            rs = preparestatement.executeQuery();
            while(rs.next()) {
                Lib lib = new Lib();

                nearby.add(lib);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if(preparestatement != null &&!preparestatement.isClosed()) {
                    preparestatement.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return nearby;
    }
}
