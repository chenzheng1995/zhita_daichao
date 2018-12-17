<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>page</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<div style="width: 100%;margin-top:20px;">
    <table>
        <tr style="background-color: #F5F5F5;">
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
        </tr>
        <div id = "show_data">
            <c:choose>
                <c:when test="${ulist != null}">
                    <c:forEach items="${ulist}" var="u">
                        <tr>
                            <td>${u.username}</td>
                            <td>${u.password}</td>
                            <td>${u.sex}</td>
                            <td>${u.email}</td>
                            <td><fmt:formatDate value="${u.create_time}" type="date"/></td>
                            <td><fmt:formatDate value="${u.update_time}" type="date"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </div>
    </table>
    <div class="page">
        <div class="page_cell">首页</div>
        <div class="page_cell" ip="up_page">上一页</div>
        <div style="float: left;margin: 2px"><%=session.getAttribute("page")%>/${ulist[0].number}</div>
        <div class="page_cell" onclick="next_page(<%=session.getAttribute("page")%>)">下一页</div>
        <div class="page_cell">末页</div>
    </div>
</div>
</body>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
</html>