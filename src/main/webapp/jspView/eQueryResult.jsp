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
<link rel="stylesheet" href="queryResult.css" > 
<style>  
#query-container{ position:relative;}
#query-container .container-switch{ position:absolute; top:-70px; right:150px; height:30px;}
#query-container .container-switch a{ display:inline-block; margin-left:10px;width:90px; height:30px; line-height:30px; text-align:center; border-radius:5px; color:#fff; background-color:#327AB7; text-decoration:none;}
#query-input{height:400px;}
#query-result{}
</style>
<title>HuiYu-QueryReport</title>
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
    		<div id="query-container">
    		<div class="container-switch"> 
    			<a href="javascript:;" id="switchToQueryInput">新建查询</a>
    		</div>
    		<div id="query-input">
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
	                      <div class="form-group has-feedback" id="parent-queryMail">
	                        <label for="queryMail" class="col-md-3 control-label">借款人邮箱</label>
	                        <div class="col-md-9">
	                          <input type="text" class="form-control" id="queryMail" placeholder="邮箱">
	  						  <span class="glyphicon form-control-feedback" aria-hidden="true" id="son-queryMail"></span>
	                        </div>
	                      </div>
<!-- 	                      <div class="form-group"> -->
<!-- 	                        <div class="col-md-offset-3 col-md-3"> -->
<!-- 	                          <div class="checkbox"> -->
<!-- 	                            <label> -->
<!-- 	                              <input type="checkbox" id="webloanCk"> 网贷行为 -->
<!-- 	                            </label> -->
<!-- 	                          </div> -->
<!-- 	                        </div>                         -->
<!-- 	                        <div class="col-md-offset-1 col-md-5"> -->
<!-- 	                          <div class="checkbox"> -->
<!-- 	                            <label> -->
<!-- 	                              <input type="checkbox" id="qqQueryCk"> QQ查询 -->
<!-- 	                            </label> -->
<!-- 	                          </div> -->
<!-- 	                          </div> -->
<!-- 	                      </div> -->
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
    			<div style="clear:both; minHeight:1px;"></div>
    		</div>
    		<div id="query-result" style="display:none;">
        	 <div class="report">
             	<!-- NO Time-->
                <div class="seral">
                	<div class="report-number">报告编号：<span id="qr-queryId">2016071700003</span></div>
                	<div class="query-time">查询时间：<span id="qr-queryTime">2016-07-17 09:20:08</span></div>
                </div>
             	<!-- Personal Info--> 
                <div class="info-box personal-info">
                	<div class="info-theme">概要信息</div>
                    <div class="info-content">
                    	<ul class="double-col-container">
                        	<li class="double-col">姓名:<span id="qr-qName">*试</span></li>
                        	<li class="double-col">身份证号:<span id="qr-qCid">33018*******280534</span></li>
                        	<li class="double-col">手机号:<span id="qr-qTel">138*****491</span></li>
                        	<li class="double-col">邮箱:<span id="qr-qMail">54**5454@qq.com</span></li>
                        	<li class="double-col">QQ号:<span id="qr-qQq">54**5454</span></li>
                        </ul>
                    </div>
                </div>
             	<!-- MainInfo-->
                <div  class="info-box-ex">
                	<ul class="nav nav-tabs" id="webLoanTab">
                      <li class="active"><a href="#loanBeh" data-toggle="tab">涉贷行为信息</a></li> 
                      <li><a href="#blackNms" data-toggle="tab">网贷黑名单信息</a></li>
                    </ul>
                	<div class="tab-content" id="webLoanTabContent">
                         
                         <div class="tab-pane fade  in active" id="loanBeh"> 
                        	<div class="loanBehInfo">
                            	<div class="info-box">
                                	<div class="info-theme">涉网贷行为信息</div>
                                	<div class="info-content">                                    	
                                        <table id="table-webloan-rough"> 
                                            <tbody>
                                                <tr>
                                                    <th >涉网贷行为风险等级</th>
                                                    <td><span id="qr-riskLevel">低</span></td>
                                                </tr>
                                                <tr>
                                                    <th>涉网贷行为命中次数</th>
                                                    <td><span id="qr-webloanTimes">2</span></td>
                                                </tr>
                                                <tr>
                                                    <th>涉网贷行为平台名称</th>
                                                    <td><span id="qr-webloanStages">拍拍贷、趣分期等</span></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            	<div class="info-box">
                                	<div class="info-theme">网贷行为详情</div>
                                	<div class="info-content">
                                    	                                  	
                                        <table id="table-webloan-detail"> 
                                        	<thead>
                                            	 <tr>
                                                    <th>类型</th>
                                                    <th>P2P网贷</th>
                                                    <th>小贷公司</th>
                                                    <th>消费/分期平台</th>
                                                    <th>汽车金融平台</th>
                                                    <th>其他</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr id="qr-oneMonth">
                                                    <th>近1个月申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr id="qr-oneSeason">
                                                    <th>近3个月申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr id="qr-halfYear">
                                                    <th>近半年申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr  id="qr-oneYear">
                                                    <th>近一年申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div> 
                         
                        <div class="tab-pane fade" id="blackNms">
                            <div class="webloan-blkNms">
                            	<div class="info-box">
                            	<table class="table1">
                                	<thead>
                                    	<tr>
                                        	<th>命中风险类别</th>
                                        	<th>命中次数</th>
                                        </tr>
                                    </thead>
                                    <tbody id="qr-blkInfo">
                                    	<tr>
                                        	<td>贷款逾期</td>
                                        	<td>3</td>
                                        </tr>
                                    	<tr>
                                        	<td>手机涉嫌欺诈</td>
                                        	<td>1</td>
                                        </tr>
                                    </tbody>
                                </table>
                                
                            	<table class="table2">
                                	<thead>
                                    	<tr>
                                        	<th>风险类型</th>
                                        	<th>逾期笔数</th>
                                        	<th>最高逾期金额</th>
                                        	<th>最长逾期天数</th>
                                        	<th>最近逾期时间</th>
                                        </tr>
                                    </thead>
                                    <tbody id="qr-blkInfoDetail">
                                    	<tr>
                                        	<td>贷款逾期</td>
                                        	<td>3</td>
                                        	<td>35,000</td>
                                        	<td>23</td>
                                        	<td>2016-06-21</td>
                                        </tr>
                                    	<tr>
                                        	<td>失信被执行人</td>
                                        	<td>1</td>
                                        	<td>5,600</td>
                                        	<td>12</td>
                                        	<td>2015-02-12</td>
                                        </tr>
                                    </tbody>
                                </table>
                                </div>
                            </div>
                        </div>
                	</div>                    
					<script>
                      $('#webLoanTab a').click(function (e) {
                          e.preventDefault()
                          $(this).tab('show')
                      })
                    </script>
                </div>
             </div>
             <div class="info-box notice">
             	<p>注:</p>
             	<p>1、本报告须获得信息主体的授权，并仅限于约定的范围内使用</p>
             	<p>2、本报告查询机构应妥善保管及处理，避免造成信息泄露，不得将查询结果信息提交给信息主体本人及其他任何第三方使用。</p>
             	<p>3、本报告分析结果仅供参考使用。</p>
             </div>
             <div style="clear:both; minHeight:1px;"></div>
            </div>
            </div>
        </div>
</div>
<jsp:include page="/footer.jsp" flush="true"></jsp:include>
</body>
</html>