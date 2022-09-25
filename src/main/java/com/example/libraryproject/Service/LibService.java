package com.example.libraryproject.Service;

import com.example.libraryproject.Domain.Lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibService {
    public List<Lib> search(String name){
        String url = "jdbc:mariadb://127.0.0.1:3306/libinfo";
        String dbuserId = "admin";
        String dbPassword = "lib";
        List<Lib> result = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparestatement = null;
        ResultSet rs = null;
        name = name+"%";
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);
            String sql = " select * " +
                    " from info " +
                    " where lbrryNm like ? ";
            preparestatement = connection.prepareStatement(sql);
            preparestatement.setString(1, name);

            rs = preparestatement.executeQuery();
            while(rs.next()) {
                Lib lib = new Lib();
                lib.setLbrryNm(rs.getString("lbrryNm"));
                lib.setCtprvnNm(rs.getString("ctprvnNm"));
                lib.setSignguNm(rs.getString("signguNm"));
                lib.setLbrrySe(rs.getString("lbrrySe"));
                lib.setCloseDay(rs.getString("closeDay"));
                lib.setWeekdayOpenHour(rs.getString("weekdayOpenHour"));
                lib.setWeekdayCloseHour(rs.getString("weekdayCloseHour"));
                lib.setSaterdayOpenHour(rs.getString("saterdayOpenHour"));
                lib.setSaterdayCloseHour(rs.getString("saterdayCloseHour"));
                lib.setHolidayOpenHour(rs.getString("holidayOpenHour"));
                lib.setHolidayCloseHour(rs.getString("holidayCloseHour"));
                lib.setSeatcnt(rs.getString("seatcnt"));
                lib.setBookcnt(rs.getString("bookcnt"));
                lib.setPblictnCo(rs.getString("pblictnCo"));
                lib.setNoneBookCo(rs.getString("noneBookCo"));
                lib.setLonCo(rs.getString("lonCo"));
                lib.setLonDaycnt(rs.getString("lonDaycnt"));
                lib.setRdnmadr(rs.getString("rdnmadr"));
                lib.setOperInstitutionNm(rs.getString("operInstitutionNm"));
                lib.setPhoneNumber(rs.getString("phoneNumber"));
                lib.setPlotAr(rs.getString("plotAr"));
                lib.setBuldAr(rs.getString("buldAr"));
                lib.setHomepageUrl(rs.getString("homepageUrl"));
                lib.setLatitude(rs.getDouble("latitude"));
                lib.setLongitude(rs.getDouble("longitude"));
                lib.setReferenceDate(rs.getString("referenceDate"));
                lib.setInsttCode(rs.getString("insttCode"));
                result.add(lib);
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
        return result;
    }

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
