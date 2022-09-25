<%@ page import="com.example.libraryproject.Domain.Lib" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.libraryproject.Service.LibService" %><%--
  Created by IntelliJ IDEA.
  User: chandle
  Date: 2022/09/25
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LibService lib_service = new LibService();
    String name = request.getParameter("name");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> 도서관 이름 검색</h1>
    <nav class = "link_menu">
        <a href = "/"> 홈</a>
    </nav>
    <form class = "search_form" action="./search.jsp">
        <label for="name">NAME:</label>
        <input type = "text" id = "name" name="name"
                <%if(name !=null){%>
               value = <%=name%>
                   <%}else{%>
                       value = 입력해주세요.
        <%}%>
        />
        <button type="submit" id ="searchbutton">검색하기</button>
    </form>
<table class="lib_info_table">
    <thread>
        <tr class = "header">
            <th>시/도</th>
            <th>시/군/구</th>
            <th>도서관 이름</th>
            <th>도서관 분류</th>
        </tr>
    </thread>
    <tbody>
        <%
            if(name == null){
        %>
        <tr id="wait">
            <td colspan="17">검색 결과가 이 곳에 출력됩니다.</td>
        </tr>
    <%
        }else{
                List<Lib> list = lib_service.search(name);
            for (int i = 0; i < list.size(); i++) {
                Lib lib = list.get(i);
    %>
        <TR ID = "search_Library_list">
            <td><%=lib.getCtprvnNm()%></td>
            <Td><%=lib.getSignguNm()%></Td>
            <td><%=lib.getLbrryNm()%></td>
            <td><%=lib.getLbrrySe()%></td>
        </TR>
    <%
            }
        }
    %>
    </tbody>
</table>

</body>
</html>