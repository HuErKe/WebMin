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
<link rel="stylesheet" href="queryResult.css" > 
<style>  
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
        	 <div class="report">
             	<!-- NO Time-->
                <div class="seral">
                	<div class="report-number">报告编号：2016071700003</div>
                	<div class="query-time">查询时间：2016-07-17 09:20:08</div>
                </div>
             	<!-- Personal Info--> 
                <div class="info-box personal-info">
                	<div class="info-theme">概要信息</div>
                    <div class="info-content">
                    	<ul class="double-col-container">
                        	<li class="double-col">姓名:<span id="qName">*试</span></li>
                        	<li class="double-col">身份证号:<span id="qCid">33018*******280534</span></li>
                        	<li class="double-col">手机号:<span id="qTel">138*****491</span></li>
                        	<li class="double-col">邮箱:<span id="qMail">54**5454@qq.com</span></li>
                        	<li class="double-col">QQ号:<span id="qQq">54**5454</span></li>
                        </ul>
                    </div>
                </div>
             	<!-- MainInfo-->
                <div  class="info-box-ex">
                	<ul class="nav nav-tabs" id="webLoanTab">
                      <li><a href="#loanBeh" data-toggle="tab">涉贷行为信息</a></li> 
                      <li class="active"><a href="#blackNms" data-toggle="tab">网贷黑名单信息</a></li>
                    </ul>
                	<div class="tab-content" id="webLoanTabContent">
                         
                         <div class="tab-pane fade" id="loanBeh"> 
                        	<div class="loanBehInfo">
                            	<div class="info-box">
                                	<div class="info-theme">涉网贷行为信息</div>
                                	<div class="info-content">                                    	
                                        <table> 
                                            <tbody>
                                                <tr>
                                                    <th>涉网贷行为风险等级</th>
                                                    <td>低</td>
                                                </tr>
                                                <tr>
                                                    <th>涉网贷行为命中次数</th>
                                                    <td>2</td>
                                                </tr>
                                                <tr>
                                                    <th>涉网贷行为平台名称</th>
                                                    <td>拍拍贷、趣分期等</td>
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
                                                <tr>
                                                    <th>近1个月申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr>
                                                    <th>近3个月申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr>
                                                    <th>近半年申请贷款次数</th>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                </tr> 
                                                <tr>
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
                         
                        <div class="tab-pane fade  in active" id="blackNms">
                            <div class="webloan-blkNms">
                            	<div class="info-box">
                            	<table class="table1">
                                	<thead>
                                    	<tr>
                                        	<th>命中风险类别</th>
                                        	<th>命中次数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
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
                                    <tbody>
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
        </div>
</div>
<jsp:include page="/footer.jsp" flush="true"></jsp:include>
</body>
</html>