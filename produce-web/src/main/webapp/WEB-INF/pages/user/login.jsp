<%--
  Created by IntelliJ IDEA.
  User: zl52074
  Date: 2020/3/30
  Time: 20:00
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


<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-validator/bootstrapValidator.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-validator/bootstrapValidator.js"></script>

<html>
<head>
    <title>用户登陆</title>
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
                <li><a href="${pageContext.request.contextPath}/marketTable.do">批发市场</a></li>
                <li><a href="${pageContext.request.contextPath}/showListTable.do">展示列表</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user == null}">
                    <li ><a href="${pageContext.request.contextPath}/user/toRegisterPage.do"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/user/toLoginPage.do"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
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
        <li class="active">用户登陆</li>
    </ul>

    <div class="row" style="width:100%;margin:0 auto;">
        <div class="col-md-2 col-sm-0 col-xs-0"></div>
        <div class="col-md-8 col-sm-12 col-xs-12">
            <div class="col-md-12" style="padding-top: 30px;">

                <div class="page-header">
                    <font color="#00bfff" size="4">用户登陆</font>
                    <font size="2">USER LOGIN</font>
                </div>

                <form id="loginForm" method="post"  class="form-horizontal" action="${pageContext.request.contextPath}/user/login.do">
                    <div class="form-group">
                        <label class="col-md-3  control-label"></label>
                        <div class="col-md-5 " id="warnning" style="display: inline-block">
                            <font color="#a94442" size="2px">${msg}</font>
                        </div>
                    </div>
                    <%--用户名--%>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户名</label>
                        <div class="col-md-5 ">
                            <input type="text" id = "username" class="form-control" name="username" placeholder="用户名" />
                        </div>
                    </div>
                    <%--密码--%>
                    <div class="form-group">
                        <label class="col-md-3  control-label">密码</label>
                        <div class="col-md-5 ">
                            <input type="password" id="password" class="form-control" name="password" placeholder="密码"/>
                        </div>
                    </div>
                    <%--验证码--%>
                    <div class="form-group">
                        <label class="col-md-3  control-label">验证码</label>
                        <div></div>
                        <div class="col-md-2 col-xs-6" style="float: left">
                            <input type="text" class="form-control" name="code" />
                        </div>
                        <div class="col-md-2 " style="float: left">
                            <img id="codeimg" src="${pageContext.request.contextPath}/user/code.do" alt="" style="height: 32px" >
                        </div>
                    </div>
                    <%--提交按钮--%>
                    <div class="form-group">
                        <div class="col-md-9  col-md-offset-3">
                            <button id="btn1" type="submit"  class="btn btn-primary"style="float:left ">登陆</button>
                            <div style="float: left; margin-top: 8px;margin-left: 15px;"><a href="${pageContext.request.contextPath}/user/toRegisterPage.do"><font color="#00bfff"size="3">注册</font></a></div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="col-md-2 col-sm-0 col-xs-0"></div>
    </div>
</div>
<script type="text/javascript">
    function logout() {
        $.ajax({
            //请求方式
            type:"GET",
            //发送请求的地址以及传输的数据
            async : false,
            url:contextPath+"/user/logout.do",
            dataType:'json',
            success:function(result){
                location.reload(true);
            },
            error:function(){
            }
        });
    }
    $(document).ready(function() {
        $("#codeimg").click(function(){
            this.src="${pageContext.request.contextPath}/user/code.do?date="+new Date().getTime();
        });
        $('#loginForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    username: {
                        message: 'The username is not valid',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },regexp: {
                                regexp: /^[^\u4e00-\u9fa5]+$/,
                                message: '用户名不能含有中文'
                            }

                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
                    },
                    code: {
                        validators: {
                            notEmpty: {
                                message: '验证码不能为空'
                            },
                            remote: {
                                url: '${pageContext.request.contextPath}/user/codeValidate.do',
                                message: '验证码输入有误',
                                dalay:2000,
                                type:"post"
                            }
                        }
                    }
                }
            })

    });
    $("#warnning").show().delay(3000).fadeOut();
</script>
</body>
</html>
