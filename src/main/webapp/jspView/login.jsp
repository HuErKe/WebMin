<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="global.css" >
<link rel="stylesheet" href="plugin.css" >
<link rel="stylesheet" href="header.css" >
<link rel="stylesheet" href="footer.css" >
<link rel="stylesheet" href="main.css" > 
<style>  
</style>
<title>HuiYu-Login</title>
<!-- <script src="jquery.js"></script> -->
<!-- <script src="bootstrap.js"></script> -->
<script src="static/js/jquery-2.2.1.min.js"></script>
<script src="static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="static/js/md5.js"></script>
<script src="static/js/mydef.js"></script>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"></jsp:include>
<div class="main">
    	<div class="main-bd">
        	<div class="login-form">
            	<div class="input-area">
                <div class="input-info">
            	<form class="form-horizontal" id="login-form">
                	<div class="vl-tip" id="vl-tip"></div>
                	<div class="form-group">
                        <label for="username" class="col-md-2 control-label">用户名</label>
                        <div class="col-md-10">
                          <input type="text" class="form-control" id="username" placeholder="输入用户名">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="passwd" class="col-md-2 control-label">密码</label>
                        <div class="col-md-10">
                          <input type="password" class="form-control" id="passwd" placeholder="输入密码">
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox"> 记住我
                            </label>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
<!--                           <button type="submit" class="btn btn-default pull-right" id="btnLogin">登录</button> -->
                          <button type="button" class="btn btn-default pull-right" id="btnLogin">登录</button>
                        </div>
                      </div>
                </form>
                </div>
                </div>
            </div>
        </div>
   </div>
<jsp:include page="/footer.jsp" flush="true"></jsp:include>
</body>
</html>