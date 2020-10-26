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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
    var contextPath = "${contextPath}";
    var marketProduceId = "${p.marketProduceId}";
    var produceId = "${p.produceId}";
    var date = "${p.date}";
    var rankIndex = "${index}";
    var showIndex = "${showIndex}";
    var isShow = "${isShow}"
    var favoriteId = "${favoriteId}"
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/produce/produceInfo.js"></script>
<!DOCTYPE HTML>
<html>
<head>
    <title>农产品详情</title>

</head>
<body>

<div style="width: 1400px; margin: 0 auto;padding-top: 15px">
    <c:if test="${from == 'p'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
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
            <li><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>
    <c:if test="${from == 's'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
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
            <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>
    <c:if test="${from == 'f'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                    <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.user == null}">
                        <li ><a href="${pageContext.request.contextPath}/user/toRegisterPage.do"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/toLoginPage.do"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <li class="dropdown active">
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
            <li><a href="${pageContext.request.contextPath}/favoriteListTable.do">收藏列表</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>

    <c:if test="${from == 'pm'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
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
            <li><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
            <li><a href="${pageContext.request.contextPath}/marketProduceTable.do?from=p&marketId=${targetProduce.marketId}">市场详情</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>

    <c:if test="${from == 'mm'}">
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
            <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
            <li><a href="${pageContext.request.contextPath}/marketProduceTable.do?from=m&marketId=${targetProduce.marketId}">市场详情</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>

    <c:if test="${from == 'sm'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
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
            <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
            <li><a href="${pageContext.request.contextPath}/marketProduceTable.do?from=s&marketId=${targetProduce.marketId}">市场详情</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>

    <c:if test="${from == 'fm'}">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header" style="padding-right: 10px">
                    <a class="navbar-brand" href="#">农产品价格系统</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/priceTable.do">价格行情</a></li>
                    <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                    <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.user == null}">
                        <li ><a href="${pageContext.request.contextPath}/user/toRegisterPage.do"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/toLoginPage.do"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <li class="dropdown active">
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
            <li><a href="${pageContext.request.contextPath}/favoriteListTable.do">收藏列表</a></li>
            <li><a href="${pageContext.request.contextPath}/marketProduceTable.do?from=f&marketId=${targetProduce.marketId}">市场详情</a></li>
            <li class="active">农产品详情</li>
        </ul>
    </c:if>
    <div style="width: 1600px;height: 505px;">
        <div style="display: inline-block;width: 718px;margin-top: 10px;padding-right: 10px">
            <div>
                <div style="display: inline-block; width: 590px;height: 10px">
                    <h1>&nbsp;${targetProduce.produceName}
                        <small>${targetProduce.marketName}</small>
                    </h1>
                </div>
                <div style="display: inline-block; width: 110px;text-align: right">
                    <c:if test="${isFavorite == false}">
                        <span ><a id="favorite" class="label label-success" href="javascript:void(0);" style="text-decoration: none">收藏</a></span>
                    </c:if>
                    <c:if test="${isFavorite == true}">
                        <span ><a id="favorite" class="label label-default" href="javascript:void(0);" style="text-decoration: none">已收藏</a></span>
                    </c:if>
                    <c:if test="${isShow == false}">
                        <span ><a id="show" class="label label-success" href="javascript:void(0);" style="text-decoration: none">展示</a></span>
                    </c:if>
                    <c:if test="${isShow == true}">
                        <span ><a id="show" class="label label-default" href="javascript:void(0);" style="text-decoration: none">已展示</a></span>
                    </c:if>

                </div>

                <div style="width: 700px;margin:0 auto;height: 10px"> <hr></div>

            </div>


            <div>
                <div style="display: inline-block;width: 340px; padding-left: 8px">
                    <font size="4" >类型：${targetProduce.categoryName}</font><br>
                    <div style="height: 5px"></div>
                    <font size="4" >更新日期：${targetProduce.date}</font><br>
                </div>
                <div style="display: inline-block; width: 360px;text-align: right;" >
                    <h1>${targetProduce.price}
                        <small>元/公斤</small>
                    </h1>
                </div>
                <div style="width: 700px;margin:0 auto;height: 52px;">
                    <br>
                    <div id = "title" style="width: 300px; display: inline-block;padding-left: 4px;">
                        <font style="font-size: 20px;">当日低价排行</font>
                    </div>
                    <div style="display: inline-block;width: 396px;text-align: right;">
                        <div id="switch" class="btn-group" data-toggle="buttons" style="padding-bottom: 25px">
                            <label class="btn btn-primary active">
                                <input type="radio" checked="checked"  name="options" value="rank" > 排行
                            </label>
                            <label class="btn btn-primary">
                                <input type="radio" name="options" value="show"> 展示
                            </label>
                        </div>
                    </div>
                </div>
                <div style="width: 700px;margin:0 auto;height: 15px"> <hr></div>
                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4">当日均价</font></div>
                <div style="width:200px;text-align: right;display: inline-block"><font size="4"><fmt:formatNumber type="number" value="${avg}" pattern="#.00"/> 元/公斤</font></div>
                <div id="rankDiv">
                    <c:forEach items="${orders}" var="order" varStatus="status">
                        <c:if test="${order.marketProduceId == p.marketProduceId}">
                            <div style="padding-top: 10px">
                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4" color="#c23531">${status.index + 1}. ${order.marketName} </font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4" color="#c23531">${order.price} 元/公斤</font></div>
                            </div>
                        </c:if>
                        <c:if test="${order.marketProduceId != p.marketProduceId}">
                            <div style="padding-top: 10px">
                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4">${status.index + 1}. ${order.marketName}</font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4">${order.price} 元/公斤</font></div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <c:if test="${index > 6}">
                        <div style="padding-left: 30px"><b > .&nbsp; .&nbsp; . </b></div>
                        <div style="padding-top: 10px">
                            <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4" color="#c23531">${index}. ${targetProduce.marketName}</font></div>
                            <div style="width:200px;text-align: right;display: inline-block"><font size="4" color="#c23531">${targetProduce.price} 元/公斤</font></div>
                        </div>
                    </c:if>
                    <c:if test="${(orders.size() < 7)&&(index <= 6)}">
                        <br>
                        <c:forEach var="item"  begin="1" end="${6-orders.size()}">
                            <div style="padding-top: 10px">
                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4">&nbsp;</font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4">&nbsp;</font></div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <br>
                    <br>
                    <br>
                </div>
                <div id="showDiv" hidden="hidden">
                    <c:forEach items="${shows}" var="show" varStatus="status">
                        <c:if test="${show.marketProduceId == p.marketProduceId}">
                            <div style="padding-top: 10px">

                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4" color="#c23531">${status.index + 1}. ${show.marketName} </font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4" color="#c23531">${show.price} 元/公斤</font></div>
                            </div>
                        </c:if>
                        <c:if test="${show.marketProduceId != p.marketProduceId}">
                            <div style="padding-top: 10px">
                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4">${status.index + 1}. ${show.marketName}</font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4">${show.price} 元/公斤</font></div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <c:if test="${(shows.size() < 7)}">
                        <br>
                        <c:forEach var="item"  begin="1" end="${6-shows.size()}">
                            <div style="padding-top: 10px">
                                <div style="padding-left: 8px;display: inline-block;width: 496px;"><font size="4">&nbsp;</font></div>
                                <div style="width:200px;text-align: right;display: inline-block"><font size="4">&nbsp;</font></div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <br>
                    <br>
                    <br>
                </div>
            </div>
        </div>
        <div id="barChart" style="display: inline-block;width: 700px;height:550px;padding-top: 20px">
        </div>

    </div>
    <hr>
    <div style="text-align: center;padding-top: 20px"><b><font size="4">历史价格走势</font></b></div>
    <div id="lineChart" style="display: inline-block;width: 1400px; height: 500px;padding-top: 30px"></div>
</div>
<div style="height: 50px"></div>
</body>
</html>

