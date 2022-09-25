package com.example.libraryproject.DB;

import com.example.libraryproject.Domain.Lib;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Register {
    public long WifiMain() throws Exception{
        JSONParser jsonParser = new JSONParser();
        long allcnt = 3485;
        List<Lib> list = new ArrayList<>();


        int count = (int) (allcnt/100);
        if(allcnt/100 != 0){
            count++;
        }
        for (int i = 0; i < count; i++) {
            JSONObject json1 = (JSONObject) jsonParser.parse(readUrl(i));
            JSONObject json = (JSONObject) json1.get("response");
            JSONObject TbPublicWifiInfo = (JSONObject) json.get("body");
            JSONArray jsonlist = (JSONArray) TbPublicWifiInfo.get("items");
            for (int j = 0; j < jsonlist.size(); j++) {
                JSONObject a = (JSONObject) jsonlist.get(j);

                Lib lib = new Lib();
                lib.setLbrryNm(String.valueOf(a.get("lbrryNm")));
                lib.setCtprvnNm(String.valueOf(a.get("ctprvnNm")));
                lib.setSignguNm(String.valueOf(a.get("signguNm")));
                lib.setLbrrySe(String.valueOf(a.get("lbrrySe")));
                lib.setCloseDay(String.valueOf(a.get("closeDay")));
                lib.setWeekdayOpenHour(String.valueOf(a.get("weekdayOperOpenHhmm")));
                lib.setWeekdayCloseHour(String.valueOf(a.get("weekdayOperColseHhmm")));
                lib.setSaterdayOpenHour(String.valueOf(a.get("satOperOperOpenHhmm")));
                lib.setSaterdayCloseHour(String.valueOf(a.get("satOperCloseHhmm")));
                lib.setHolidayOpenHour(String.valueOf(a.get("holidayOperOpenHhmm")));
                lib.setHolidayCloseHour(String.valueOf(a.get("holidayCloseOpenHhmm")));
                lib.setSeatcnt((String.valueOf(a.get("seatCo"))));
                lib.setBookcnt((String.valueOf(a.get("bookCo"))));
                lib.setPblictnCo((String.valueOf(a.get("pblictnCo"))));
                lib.setNoneBookCo((String.valueOf(a.get("noneBookCo"))));
                lib.setLonCo((String.valueOf(a.get("lonCo"))));
                lib.setLonDaycnt((String.valueOf(a.get("lonDaycnt"))));
                lib.setRdnmadr(String.valueOf(a.get("rdnmadr")));
                lib.setOperInstitutionNm(String.valueOf(a.get("operInstitutionNm")));
                lib.setPhoneNumber(String.valueOf(a.get("phoneNumber")));
                lib.setPlotAr((String.valueOf(a.get("plotAr"))));
                lib.setBuldAr((String.valueOf(a.get("buldAr"))));
                lib.setHomepageUrl(String.valueOf(a.get("homepageUrl")));
                if(String.valueOf(a.get("latitude")).equals("")){
                    lib.setLatitude(0.000000);
                }else{
                    lib.setLatitude(Double.parseDouble(String.valueOf(a.get("latitude"))));
                }
                if(String.valueOf(a.get("longitude")).equals("")){
                    lib.setLongitude(0.000000);
                }else{
                    lib.setLongitude(Double.parseDouble(String.valueOf(a.get("longitude"))));
                }
                lib.setReferenceDate(String.valueOf(a.get("referenceDate")));
                lib.setInsttCode(String.valueOf(a.get("insttCode")));
                list.add(lib);
            }
        }
        register(list);
        return allcnt;
    }

    public static String readUrl(int k) throws Exception {
        int start_idx = k;
        String inputUrl;
        if(k == 34){
            inputUrl = "http://api.data.go.kr/openapi/tn_pubr_public_lbrry_api?serviceKey=I1m%2FtyHkSf911tyeNIAXl%2BcZEqKhcNOPDVGzi%2BGuhQQqXEmyz65vMgPqe7TY5eiSQwNoUnI%2B2qOuFhXZ1v7K3g%3D%3D&pageNo="+start_idx+"&numOfRows=85&type=json";

        }else{
            inputUrl = "http://api.data.go.kr/openapi/tn_pubr_public_lbrry_api?serviceKey=I1m%2FtyHkSf911tyeNIAXl%2BcZEqKhcNOPDVGzi%2BGuhQQqXEmyz65vMgPqe7TY5eiSQwNoUnI%2B2qOuFhXZ1v7K3g%3D%3D&pageNo="+start_idx+"&numOfRows=100&type=json";
        }
        URL url = new URL(inputUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void register(List<Lib> libList) {
        String url = "jdbc:mariadb://127.0.0.1:3306/libinfo";
        String dbuserId = "admin";
        String dbPassword = "lib";
        // 1. 드라이버로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparestatement = null;

        //2. 커넥션 객체 생성
        try {
            connection = DriverManager.getConnection(url, dbuserId, dbPassword);
            connection.setAutoCommit(false);
            String sql = " insert into info(`lbrryNm`,`ctprvnNm`,`signguNm`,`lbrrySe`,`closeDay`,  `weekdayOpenHour`,`weekdayCloseHour`, `saterdayOpenHour`,`saterdayCloseHour`, " +
                    "                 `holidayOpenHour`,`holidayCloseHour`,`seatcnt`,`bookcnt`,`pblictnCo`,`noneBookCo`,`lonCo`,`lonDaycnt`,`rdnmadr`,`operInstitutionNm`, " +
                    "                 `phoneNumber`,`plotAr`,`buldAr`,`homepageUrl`,`latitude`,`longitude`,`referenceDate`,`insttCode`) " +
                    " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            preparestatement = connection.prepareStatement(sql);
            int cnt = 0;
            for(Lib lib : libList) {
                preparestatement.setString(1, lib.getLbrryNm());
                preparestatement.setString(2, lib.getCtprvnNm());
                preparestatement.setString(3, lib.getSignguNm());
                preparestatement.setString(4, lib.getLbrrySe());
                preparestatement.setString(5, lib.getCloseDay());
                preparestatement.setString(6, lib.getWeekdayOpenHour());
                preparestatement.setString(7, lib.getWeekdayCloseHour());
                preparestatement.setString(8, lib.getSaterdayOpenHour());
                preparestatement.setString(9, lib.getSaterdayCloseHour());
                preparestatement.setString(10, lib.getHolidayOpenHour());
                preparestatement.setString(11, lib.getHolidayCloseHour());
                preparestatement.setString(12, lib.getSeatcnt());
                preparestatement.setString(13, lib.getBookcnt());
                preparestatement.setString(14, lib.getPblictnCo());
                preparestatement.setString(15, lib.getNoneBookCo());
                preparestatement.setString(16, lib.getLonCo());
                preparestatement.setString(17, lib.getLonDaycnt());
                preparestatement.setString(18, lib.getRdnmadr());
                preparestatement.setString(19, lib.getOperInstitutionNm());
                preparestatement.setString(20, lib.getPhoneNumber());
                preparestatement.setString(21, lib.getPlotAr());
                preparestatement.setString(22, lib.getBuldAr());
                preparestatement.setString(23, lib.getHomepageUrl());
                preparestatement.setDouble(24, lib.getLatitude());
                preparestatement.setDouble(25, lib.getLongitude());
                preparestatement.setString(26, lib.getReferenceDate());
                preparestatement.setString(27, lib.getInsttCode());

                cnt++;
                preparestatement.addBatch();
                preparestatement.clearParameters();
                if(cnt % 1000 == 0) {
                    preparestatement.executeBatch();
                    preparestatement.clearBatch();
                    connection.commit();
                }
            }
            preparestatement.executeBatch();
            preparestatement.clearParameters();
            connection.commit();
            System.out.println(" 저장 성공 ");
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
    }
}
