<%--
  Created by IntelliJ IDEA.
  User: zl52074
  Date: 2020/4/4
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/pages/common/common.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<script>
    var contextPath = "${contextPath}";
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datetimepicker/bootstrap-datetimepicker.min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/produce/marketTable.js"></script>

<html>
<head>
    <title>农产品市场</title>
</head>
<body>

<div style="width: 1400px; margin: 0 auto;padding-top: 15px">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header" style="padding-right: 10px">
                <a class="navbar-brand" href="#">农产品价格系统</a>
            </div>
            <ul class="nav navbar-nav">
                <li ><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user == null}">
                    <li ><a href="${pageContext.request.contextPath}/user/toRegisterPage.do"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/toLoginPage.do"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span> &nbsp;${sessionScope.user.username}
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/favoriteListTable.do">收藏列表</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/toUpdatePage.do">修改信息</a></li>
                            <li><a href="javascript:void(0);" onclick="logout()" >退出登陆</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
    <ul class="breadcrumb">
        <li class="active">批发市场</li>
    </ul>
    <div style="display: inline-block;width: 1110px;">

        <div style="display: inline-block;width: 32px;"><strong>省份</strong></div>
        <div style="display: inline-block;width: 220px;">
            <select id = "provinceId" class="form-control" name="categoryId">
                <option>-选择省份-</option>
                <c:forEach items="${provinces}" var="province">
                    <option value=${province.id}>${province.name}</option>
                </c:forEach>
            </select>
        </div>
        <div style="display: inline-block;width: 10px;"></div>
        <div style="display: inline-block;width: 70px;"><strong>&nbsp;&nbsp;销售类型</strong></div>
        <div style="display: inline-block;width: 220px;">
            <select id = "marketType" class="form-control" name="marketId" >
                <option>-选择类型-</option>
                <option value="0">销地</option>
                <option value="1">产地</option>
            </select>
        </div>
        &nbsp;&nbsp;
        <button class="btn btn-primary search" type="button" style="width: 60px">筛选</button>
        <button id = "reset" class="btn btn-default" type="button" style="width: 60px">重置</button>
    </div>
    <div class="form-inline" style="display: inline-block;width: 280px;text-align: right">
        <input id = "keyWord" type="text" class="form-control" placeholder="请输入市场关键字" style="width: 70%">
        &nbsp;&nbsp;
        <button  class="btn btn-primary search" type="button" style="width: 60px">搜索</button>
    </div>

    <div style="padding-top: 10px">
        <table class="table  table-bordered table-hover" id="marketTable" style="font-size: 15px">
            <thead>
            <tr>
                <th>序号</th>
                <th>marketId</th>
                <th>市场</th>
                <th>类型</th>
                <th>省份</th>
                <th>地址</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>

