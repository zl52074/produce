<%--
  Created by IntelliJ IDEA.
  User: zl52074
  Date: 2020/3/29
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/common/common.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>爬虫日志</title>

</head>
<body>
<div style="margin: 0 auto;width: 1300px">
    <h2><center>定时爬虫日志</center></h2>
    <h2>日期：${date}</h2>
    <h3>总 CATEGORY:${categoryCount} | PRODUCE:${produceCount} | MARKET:${marketCount} | MARKET-PRODUCE:${marketProduceCount} | PRICE:${priceCount} </h3>
    <h3>当日日志</h3>
    <hr>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>时间</th>
            <th>日志</th>
            <th>变化</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${msgs}" var="msg">
            <tr>
                <td>${msg.time }</td>
                <td>${msg.msg }</td>
                <td>${msg.change }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3>全部日志</h3>
    <table class="table table-hover">
        <tbody>
        <c:forEach items="${allMsgs}" var="msg">
            <tr>
                <td>${msg.time }</td>
                <td>${msg.msg }</td>
                <td>${msg.change }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
