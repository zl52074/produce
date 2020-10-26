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
    <title>更新用户信息</title>
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
                    <li><a href="${pageContext.request.contextPath}/user/toLoginPage.do"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li  class="dropdown active">
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
        <li class="active">修改信息</li>
    </ul>
    <c:if test="${sessionScope.user == null}">
        <div style="text-align: center">当前已退出登陆，请重新<a href="${pageContext.request.contextPath}/user/toLoginPage.do">登陆</a>后再操作此页面</div>
    </c:if>
    <c:if test="${sessionScope.user != null}">
    <div class="row" style="width:100%;margin:0 auto;">
        <div class="col-md-2 col-sm-0 col-xs-0"></div>
        <div class="col-md-8 col-sm-12 col-xs-12">
            <div class="col-md-12" style="padding-top: 30px;">

                <div class="page-header">
                    <font color="#00bfff" size="4">信息修改</font>
                    <font size="2">USER UPDATE</font>
                </div>

                <form id="defaultForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/user/update.do" >
                    <input type="hidden" name="id" value="${u.id}">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">用户名</label>
                        <div class="col-lg-5">
                            <input type="text" disabled="disabled" value="${u.username}" class="form-control" name="username" placeholder="用户名" />
                        </div>
                    </div>

                    <%--密码--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">原始密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="oldPassword" placeholder="原始密码"/>
                        </div>
                    </div>
                    <%--密码--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="password" placeholder="新密码"/>
                        </div>
                    </div>
                    <%--确认密码--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">确认密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="confirmPassword" placeholder="确认密码"/>
                        </div>
                    </div>
                    <%--真实姓名--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">姓名</label>
                        <div class="col-lg-5">
                            <input type="text" value="${u.name}" class="form-control" name="name" placeholder="姓名" />
                        </div>
                    </div>
                    <%--性别--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">性别</label>
                        <div class="col-lg-5">
                            <div >
                                <c:if test="${u.gender == '1'}">
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="1" checked /> 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="0"  /> 女
                                    </label>
                                </c:if>
                                <c:if test="${u.gender == '0'}">
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="1"  /> 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" value="0" checked /> 女
                                    </label>
                                </c:if>

                            </div>
                        </div>
                    </div>
                    <%--邮箱--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">邮箱</label>
                        <div class="col-lg-5">
                            <input type="text" value="${u.email}" class="form-control" name="email" placeholder="邮箱"/>
                        </div>
                    </div>
                    <%--验证码--%>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">验证码</label>
                        <div></div>
                        <div class="col-lg-2 col-xs-6" style="float: left">
                            <input type="text" class="form-control" name="code" />
                        </div>
                        <div class="col-lg-2" style="float: left">
                            <img id="codeimg" src="${pageContext.request.contextPath}/user/code.do" alt="" style="height: 32px" >
                        </div>
                    </div>
                    <%--提交按钮--%>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button id="submitBtn" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="col-md-2 col-sm-0 col-xs-0"></div>
    </div>
    </c:if>
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

        //表单提交
        $("#submitBtn").on("click",function(){
            $('#defaultForm').bootstrapValidator('validate');
            if(!$('#defaultForm').data('bootstrapValidator').isValid()){
                //没有通过校验
            } else {
                //通过校验，可进行提交等操作
                var form = $('#defaultForm');
                $.post(
                    form.attr('action'),
                    form.serialize(),
                    function(result) {
                        if(result){
                            alert("修改成功，请重新登陆");
                            window.location.href = contextPath+"/user/toLoginPage.do";
                        }else{

                        }
                    },
                    'json'
                );
            }

        });
        $('#defaultForm')
            .bootstrapValidator({
                excluded:[":disabled"],//表示只对于禁用域不进行验证，其他的表单元素都要验证
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
                            },
                            stringLength: {
                                min: 6,
                                max: 30,
                                message: '用户名长度必须为6至30位'
                            },
                            remote: {
                                url: '${pageContext.request.contextPath}/user/usernameValidate.do',
                                message: '用户名被注册',
                                dalay:2000,
                                type:"post"
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9]+$/,
                                message: '用户名只能由字母或数字组成'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空'
                            },
                            regexp: {
                                regexp: /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/,
                                message: '格式不正确'
                            }
                        }
                    },
                    oldPassword: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            remote: {
                                url: '${pageContext.request.contextPath}/user/passwordValidate.do',
                                message: '原始密码不正确',
                                dalay:2000,
                                type:"post"
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            regexp : {
                                regexp : /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{8,16}$/,
                                message : '用户新密码长度为8至16位,并且需要包含数字、小写字母、大写字母、符号(至少三种)'
                            },
                            stringLength: {
                                min: 8,
                                max: 50,
                                message: '用户新密码长度不能少于8位'
                            },
                            identical: {
                                field: 'confirmPassword',
                                message: '两次密码必须一致'
                            }
                        }
                    },
                    confirmPassword:{
                        validators: {
                            notEmpty: {
                                message: '确认密码不能为空'
                            },
                            identical: {
                                field: 'password',
                                message: '两次密码必须一致！'
                            }
                        }
                    },
                    name: {
                        validators: {
                            notEmpty: {
                                message: '姓名不能为空'
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
</script>
</body>
</html>
