<%@ page import="com.example.libraryproject.Domain.Lib" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.libraryproject.Service.LibService" %><%--
  Created by IntelliJ IDEA.
  User: chandle
  Date: 2022/09/26
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LibService service = new LibService();
    String name = request.getParameter("name");
    Lib lib = service.LibInfo(name);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1><%=name%> 세부 정보</h1>
    <nav class = "link_menu">
        <a href = "/search.jsp?name=<%=name%>"></a>
    </nav>
<table class="lib_info_table">
    <thread>
        <tr>
            <p><td><%=name%>정보</td></p>
        </tr>
        <tr>
            <td>도서관명</td>
            <td><%=lib.getLbrryNm()%></td>
        </tr>
        <tr>
            <td>시도명</td>
            <td><%=lib.getCtprvnNm()%></td>
        </tr>
        <tr>
            <td>시군구명</td>
            <Td><%=lib.getSignguNm()%></Td>
        </tr>
        <tr>
            <td>도서관유형</td>
            <td><%=lib.getLbrrySe()%></td>
        </tr>
        <tr>
            <td>휴관일</td>
            <td><%=lib.getCloseDay()%></td>
        </tr>
        <tr>
            <td>평일운영시작시각</td>
            <td><%=lib.getWeekdayOpenHour()%></td>
        </tr>
        <tr>
            <td>평일운영종료시각</td>
            <td><%=lib.getWeekdayCloseHour()%></td>
        </tr>
        <tr>
            <td>토요일운영시작시각</td>
            <td><%=lib.getSaterdayOpenHour()%></td>
        </tr>
        <tr>
            <td>토요일운영종료시각</td>
            <td><%=lib.getSaterdayCloseHour()%></td>
        </tr>
        <tr>
            <td>공휴일운영시작시각</td>
            <td><%=lib.getHolidayOpenHour()%></td>
        </tr>
        <tr>
            <td>공휴일운영종료시각</td>
            <td><%=lib.getHolidayCloseHour()%></td>
        </tr>
        <tr>
            <td>열람좌석수</td>
            <td><%=lib.getSeatcnt()%></td>
        </tr>
        <tr>
            <td>자료수(도서)</td>
            <td><%=lib.getBookcnt()%></td>
        </tr>
        <tr>
            <td>자료수(연속간행물)</td>
            <Td><%=lib.getPblictnCo()%></Td>
        </tr>
        <tr>
            <td>자료수(비도서)</td>
            <td><%=lib.getNoneBookCo()%></td>
        </tr>
        <tr>
            <td>대출가능권수</td>
            <TD><%=lib.getLonCo()%></TD>
        </tr>
        <tr>
            <td>대출가능일수</td>
            <TD><%=lib.getLonDaycnt()%></TD>
        </tr>
        <tr>
            <td>소재지도로명주소</td>
            <td><%=lib.getRdnmadr()%></td>
        </tr>
        <tr>
            <td>운영기관명</td>
            <td><%=lib.getOperInstitutionNm()%></td>
        </tr>
        <tr>
            <td>도서관전화번호</td>
            <td><%=lib.getPhoneNumber()%></td>
        </tr>
        <tr>
            <td>부지면적</td>
            <TD><%=lib.getPlotAr()%></TD>
        </tr>
        <tr>
            <td>건물면적</td>
            <td><%=lib.getBuldAr()%></td>
        </tr>
        <tr>
            <td>홈페이지 주소</td>
            <Td><%=lib.getHomepageUrl()%></Td>
        </tr>
        <tr>
            <td>위도</td>
            <td><%=lib.getLatitude()%></td>
        </tr>
        <tr>
            <td>경도</td>
            <td><%=lib.getLongitude()%></td>
        </tr>
        <tr>
            <td>데이터기준일자</td>
            <td><%=lib.getReferenceDate()%></td>
        </tr>
        <tr>
            <td>제공기관코드</td>
            <td><%=lib.getInsttCode()%></td>
        </tr>
    </thread>
</table>
</body>
</html>
