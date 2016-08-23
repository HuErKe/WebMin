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
<link rel="stylesheet" href="query.css" > 
<style>  
</style>
<title>HuiYu-Query</title>
<!-- <script src="jquery.js"></script> -->
<!-- <script src="bootstrap.js"></script> -->
<script src="static/js/jquery-2.2.1.min.js"></script>
<script src="static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="static/js/mydef.js"></script>
</head>
<body>
<jsp:include page="/header.jsp" flush="true"></jsp:include>
<div class="main">
    	<div class="main-bd">
        	<div class="login-form">
            	<div class="input-area">
                <div class="input-info">
            	<form class="form-horizontal" id="queryCondition">
                	<div class="vl-tip"> </div>
                	<div class="form-group has-feedback" id="parent-loanUserName">
                        <label for="loanUserName" class="col-md-3 control-label">借款人姓名</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="loanUserName" placeholder="姓名">
  						  <span class="glyphicon form-control-feedback" aria-hidden="true" id="son-loanUserName"></span>
                        </div>
                      </div>
                      <div class="form-group has-feedback" id="parent-queryCid">
                        <label for="queryCid" class="col-md-3 control-label">借款人身份证号</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="queryCid" placeholder="身份证号">
  						  <span class="glyphicon form-control-feedback" aria-hidden="true" id="son-queryCid"></span>
                        </div>
                      </div>
                      <div class="form-group has-feedback" id="parent-queryTel">
                        <label for="queryTel" class="col-md-3 control-label">借款人手机号</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="queryTel" placeholder="手机号">
  							<span class="glyphicon form-control-feedback" aria-hidden="true" id="son-queryTel"></span>
                        </div>
                      </div>
                      <div class="form-group has-feedback" id="parent-queryQQ">
                        <label for="queryQQ" class="col-md-3 control-label">借款人QQ号</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="queryQQ" placeholder="QQ号">
  						  <span class="glyphicon form-control-feedback" aria-hidden="true" id="son-queryQQ"></span>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-offset-3 col-md-3">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="webloanCk"> 网贷行为
                            </label>
                          </div>
                        </div>                        
                        <div class="col-md-offset-1 col-md-5">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="qqQueryCk"> QQ查询
                            </label>
                          </div>
                          </div>
                      </div>
                      <div class="form-group">
                        <div class="col-md-offset-3 col-md-3">
                          <button type="button" class="btn btn-primary pull-left" id="btnQuery">查询</button>
                        </div>
                        <div class="col-md-offset-1 col-md-5">
                        	<label>
                          		<input type="button" class="btn btn-default pull-left" id="btnQReset" value="重置"></button>
                         	</label>
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