/**
 * 
 */ 
// all in use
var ctrol={
		"login":{
			"$username":null,
			"$passwd":null,
			"$btnLogin":null,
			"$vlTip":null
		},
		"query":{
			"$loanUserName":null,			
			"$queryCid":null,
			"$queryTel":null,
			"$queryQQ":null,
			"$queryMail":null,
			"$webloanCk":null,
			"$qqQueryCk":null,
			"$btnQuery":null,
			"$btnQReset":null,
			"$switchToQueryInput":null,
			
			"$parenloanUserName":null,			
			"$parenqueryCid":null,
			"$parenqueryTel":null,
			"$parenqueryQQ":null,
			"$sonloanUserName":null,			
			"$sonqueryCid":null,
			"$sonqueryTel":null,
			"$sonqueryQQ":null,
			"$sonqueryMail":null
		},
		"queryResult":{
			"$qrqueryId" : null,
			"$qrqueryTime" : null,
			"$qrqName" : null,
			"$qrqCid" : null,
			"$qrqTel" : null,
			"$qrqMail" : null,
			"$qrqQq" : null,
			"$qrriskLevel" : null,
			"$qrwebloanTimes" : null,
			"$qrwebloanStages" : null,
			"$qroneMonth" : null,
			"$qroneSeason" : null,
			"$qrhalfYear" : null,
			"$qroneYear" : null,
			
			"$qroneMonthCell" : null,
			"$qroneSeasonCell" : null,
			"$qrhalfYearCell" : null,
			"$qroneYearCell" : null,
			
			"$blkInfo" : null,
			"$blkInfoDetail" : null 
		} 
};
// all in use
var retInfo={
		"login":{
			"result":null 
		},
		"query":{
			"result":null
		},
		"queryResult":{
			"result":null
		}
}
var loginedUserName = null;
var reqInfo={
		"login":{
			"result":null 
		},
		"query":{
			"user_name":null,				// 用户名(String)  
			"query_condition":{
				"query_name":null,					// 姓名(String)
				"query_cid":null,	// 身份证号(String)
				"query_mobile":null,		// 手机号(String) g)
				"query_qq":null, 
			},
			"business_set":"A;B;",			// 业务集(String) 
			"product_name":""			// 产品名字(String)
		}				
} 
function isStrEmpty(str){
	return null == str || ""==str || ""==str.trim();
}

var lastReqTime = null;
function shouldRequest(){
	var timestamp = Date.parse(new Date()); 
	if(null == lastReqTime){
		lastReqTime = timestamp;
		return true;
	}
	
	if(timestamp - lastReqTime < 6000){
		return false;
	}
	
	lastReqTime = timestamp;
	
	return true;
}
const PAREN_clsNormal = "form-group has-feedback";
const PAREN_clsWarning = "form-group has-warning has-feedback";
const PAREN_clsError = "form-group has-error has-feedback";
const sonclsNormal = "glyphicon form-control-feedback";
const sonclsWarning = "glyphicon glyphicon-warning-sign form-control-feedback";
const sonclsError = "glyphicon glyphicon-remove form-control-feedback";
const CID_REG = /^(\d{15}$)|(^\d{17}([0-9]|X)$)/;
const TEL_REG = /^(((13[0-9])|(15([0-3]|[5-9]))|(17[0-9])|(18[0-9]))\d{8})|(0\d{2}-\d{8})|(0\d{3}-\d{7})$/;
const QQ_REG = /^\d{5,10}$/;
function  doQuery(){  	 
	
	if(!shouldRequest()){
		alert('为避免重复计费,请耐心等待结果返回!');
		return ;
	}; 
	
	var qname = ctrol.query.$loanUserName.prop('value');
	var cid = ctrol.query.$queryCid.prop('value');
	var tel = ctrol.query.$queryTel.prop('value');
	var qQQ = ctrol.query.$queryQQ.prop('value');   
	var qMail = ctrol.query.$queryMail.prop('value'); 
	
	if(!isStrEmpty(qname)){
		ctrol.query.$parenloanUserName.prop('class',PAREN_clsNormal);
		ctrol.query.$sonloanUserName.prop('class',sonclsNormal);
	}
	else{
		ctrol.query.$parenloanUserName.prop('class',PAREN_clsError);
		ctrol.query.$sonloanUserName.prop('class',sonclsError);	
		return ;
	}
	if(CID_REG.test(cid)){
		ctrol.query.$parenqueryCid.prop('class',PAREN_clsNormal);
		ctrol.query.$sonqueryCid.prop('class',sonclsNormal);
	}
	else{
		ctrol.query.$parenqueryCid.prop('class',PAREN_clsError);
		ctrol.query.$sonqueryCid.prop('class',sonclsError);	
		return ;
	}
	if(TEL_REG.test(tel)){
		ctrol.query.$parenqueryTel.prop('class',PAREN_clsNormal);
		ctrol.query.$sonqueryTel.prop('class',sonclsNormal);
	}
	else{
		ctrol.query.$parenqueryTel.prop('class',PAREN_clsError);
		ctrol.query.$sonqueryTel.prop('class',sonclsError);	
		return ;
	}
	if(!isStrEmpty(qQQ) && !QQ_REG.test(qQQ)){ // 有具体输入才校验 
		ctrol.query.$parenqueryQQ.prop('class',PAREN_clsError);
		ctrol.query.$sonqueryQQ.prop('class',sonclsError);	
		return ; 
	}
	else{
		ctrol.query.$parenqueryQQ.prop('class',PAREN_clsNormal);
		ctrol.query.$sonqueryQQ.prop('class',sonclsNormal);
	}
	if(!isStrEmpty(qMail) && !QQ_REG.test(qMail)){ // 有具体输入才校验 
		ctrol.query.$parenqueryMail.prop('class',PAREN_clsError);
		ctrol.query.$sonqueryMail.prop('class',sonclsError);	
		return ; 
	}
	else{
		ctrol.query.$parenqueryMail.prop('class',PAREN_clsNormal);
		ctrol.query.$sonqueryMail.prop('class',sonclsNormal);
	}
	
	reqInfo.query.user_name=loginedUserName;
	reqInfo.query.query_condition.query_name=qname; 
	reqInfo.query.query_condition.query_cid=cid; 
	reqInfo.query.query_condition.query_mobile=tel; 
	reqInfo.query.query_condition.query_qq=qQQ; 
	reqInfo.query.query_condition.query_email=qMail; 
	reqInfo.query.business_set="A1:A6;C1;C3"; 

//	requestFromServer("http://localhost:8080/CommonWebClient/v1/cwc/queries",
	requestFromServer("v1/cwc/queries",
			reqInfo.query,
			function(result){  
//				console.log(JSON.stringify(result)); 
				doParseResponse(result); 
				retInfo.query.result =  result.result; 
//				alert(JSON.stringify(retInfo.query.result)); // 有数据
				if((typeof(retInfo.query.result) == "object") &&  (null != retInfo.query.result)){
//					console.log('返回正确');
//					console.log(JSON.stringify(retInfo.query.result));
//					alert(JSON.stringify(retInfo.query.result));
//					location.href = "http://localhost:8080/CommonWebClient/jspView/queryResult.jsp"; 
					$queryPage.hide();
					$queryResultPage.show();
					updateQueryResultUI();
				} 
				else{
//					console.log('返回不正确');					   				
				}
			});
}	 
function updateCells($ctl,data){ 
	$ctl.each(function(index,e){
		if(0 == index){
//			alert('e='+e);
			e.innerHTML = data.p2pNum;
		}
		else if(1 == index){
			e.innerHTML = data.xiaodai;			
		}
		else if(2 == index){
			e.innerHTML = data.xiaofeifenqi;			
		}
		else if(3 == index){
			e.innerHTML = data.qichejinrong;			
		}
		else if(4 == index){
			e.innerHTML = data.qita;			
		}
	}); 
}
function updateBlkInfo(blkInfo){
	ctrol.queryResult.$qrblkInfo.children().remove();
	$.each(blkInfo,function(i,e){
		ctrol.queryResult.$qrblkInfo.append("<tr>"+
				"<td>"+e.black_risk_type+"</td>"+
				"<td>"+e.black_hit_count+"</td>"+
				"</tr>"); 
	});
}
function updateBlkInfoDetail(blkInfoDetail){
	ctrol.queryResult.$qrblkInfoDetail.children().remove();
	$.each(blkInfoDetail,function(i,e){
		ctrol.queryResult.$qrblkInfoDetail.append("<tr>"+
				"<td>"+e.black_risk_type+"</td>"+
				"<td>"+e.overdue_count+"</td>"+
				"<td>"+e.overdue_amount_most+"</td>"+
				"<td>"+e.overdue_days_most+"</td>"+
				"<td>"+e.overdue_time_latest+"</td>"+
				"</tr>"); 
	});	
}
function updateQueryResultUI(){
	if((typeof(retInfo.query.result) != "object") ||  (null == retInfo.query.result)){
		return ;
	}
	// part 1
	ctrol.queryResult.$qrqueryId.text(retInfo.query.result.report.serial.query_id);
	ctrol.queryResult.$qrqueryTime.text(retInfo.query.result.report.serial.query_time);
	ctrol.queryResult.$qrqName.text(retInfo.query.result.summaryInfo.qName);
	ctrol.queryResult.$qrqCid.text(retInfo.query.result.summaryInfo.qCid);
	ctrol.queryResult.$qrqTel.text(retInfo.query.result.summaryInfo.qTel);
	ctrol.queryResult.$qrqMail.text(retInfo.query.result.summaryInfo.qMail);
	ctrol.queryResult.$qrqQq.text(retInfo.query.result.summaryInfo.qQq);
	ctrol.queryResult.$qrriskLevel.text(retInfo.query.result.report.webloan.wiskLevel);
	ctrol.queryResult.$qrwebloanTimes.text(retInfo.query.result.report.webloan.times);
	ctrol.queryResult.$qrwebloanStages.text(retInfo.query.result.report.webloan.stage);
//	alert('cells='+ctrol.queryResult.$qroneMonth.length);

	// part 2
	updateCells(ctrol.queryResult.$qroneMonthCell,retInfo.query.result.report.webloanDetail.oneMonth);
	updateCells(ctrol.queryResult.$qroneSeasonCell,retInfo.query.result.report.webloanDetail.oneSeason);
	updateCells(ctrol.queryResult.$qrhalfYearCell,retInfo.query.result.report.webloanDetail.halfYear);
	updateCells(ctrol.queryResult.$qroneYearCell,retInfo.query.result.report.webloanDetail.oneYear);

	// part 3
	updateBlkInfo(retInfo.query.result.report.blkInfo);
	updateBlkInfoDetail(retInfo.query.result.report.blkInfoDetail);
}
function  clearInput(){
	alert('clear input');
}	  	 
function doParseResponse(result){
	if(1003 == result.result_code){
//		location.href = "http://localhost:8080/CommonWebClient/jspView/login.jsp"; 	
//		location.href = "jspView/login.jsp"; 	
		location.href = getLocationRealPath("jspView/login.jsp");		
	}
}
function getJsonFromRequestSearchUrl(strSearch){
	var result={};
	var itemsParam = strSearch.split('&');
	var itemsKV = null;
	for(var i=0;i<itemsParam.length;i++){
		itemsKV = itemsParam[i].split('=');
		result.itemsKV[0] = isStrEmpty(itemsKV[1]) ? "": itemsKV[1];
	}
	alert('result='+JSON.stringify(result));
	return result;
}
const USER_NAME_REG = /unm=(\w+)/;
function getUserNameFromUrl(url){
	var group = url.match(USER_NAME_REG);
	return null == group ? "" : group[1];
//	var index = url.indexOf('=');
//	return index >= 0 && index < url.length ? url.substring(index+1) : "";
}
function login(){   
	
	var uname = ctrol.login.$username.prop('value').trim();  
	if(isStrEmpty(uname)){
		alert('请输入用户名');
		return;
	}
	var pwd = ctrol.login.$passwd.prop('value').trim();  
	if(isStrEmpty(pwd)){
		alert('请输密码');
		return;
	}
	var reqData={
		"user_login_name":uname,
		"user_password":hex_md5(pwd)
	}; 
//	requestFromServer("http://localhost:8080/CommonWebClient/v1/cwc/logins",
	requestFromServer("v1/cwc/logins",
			reqData,
			function(result){  
//				console.log(JSON.stringify(result));
				if(200 != result.result_code){
					ctrol.login.$vlTip.text(result.result_desc); // eg:用户名错误
					return ;
				}				
				retInfo.login.result =  result.result; 
				if((typeof(retInfo.login.result) == "object") &&  (null != retInfo.login.result)){
					var reqUrl = retInfo.login.result.reqUrl;
					loginedUserName = uname;
//					if(!isStrEmpty(reqUrl)){
//						console.log('已有url请求'); 
//						location.href = reqUrl+"?username="+uname;  	
//					} 
//					else{ 
//						location.href = "jspView/eQueryResult.jsp?username="+uname;  					
//					}
//					location.href = "jspView/eQueryResult.jsp?username="+uname; 
//					console.log('username='+uname);
//					location.href = "jspView/eQueryResult.jsp?unm="+uname;  
					location.href = getLocationRealPath("jspView/eQueryResult.jsp?unm="+uname);
				} 
				else{
					loginedUserName = "";
//					location.href = "http://localhost:8080/CommonWebClient/jspView/login.jsp";  
//					location.href = "jspView/login.jsp"; 	
				}
			});
}
 
var $btnLogin = null;
var $queryPage = null;
var $queryResultPage = null; 
/**
 * 20160721 
 * @param relativeUrl
 * @returns
 */
function getLocationRealPath(relativeUrl){
	var baseUrl = $('base').prop('href'); 
	return baseUrl + relativeUrl;
}
window.onload=function(){   // 20160721
	if(location.href.lastIndexOf("login.jsp") >= 0){
		console.log("init login");
		ctrol.login.$username=$('#username');
		ctrol.login.$passwd=$('#passwd');
		ctrol.login.$btnLogin=$('#btnLogin'); 
		ctrol.login.$vlTip = $('#vl-tip');
		ctrol.login.$vlTip.text('请重新登录!');
		ctrol.login.$btnLogin.click(function(){  
			login();	 
		}); 
	}
	else if(location.href.lastIndexOf("query.jsp") >= 0){
		console.log("init query");
		ctrol.query.$loanUserName = $('#loanUserName');
		ctrol.query.$queryCid = $('#queryCid');
		ctrol.query.$queryTel = $('#queryTel');
		ctrol.query.$queryQQ = $('#queryQQ');
		ctrol.query.$webloanCk = $('#webloanCk');
		ctrol.query.$qqQueryCk = $('#qqQueryCk');
		ctrol.query.$btnQuery = $('#btnQuery');
		ctrol.query.$btnQReset = $('#btnQReset'); 
		ctrol.query.$parenloanUserName = $('#parent-loanUserName');			
		ctrol.query.$parenqueryCid = $('#parent-queryCid');
		ctrol.query.$parenqueryTel = $('#parent-queryTel');
		ctrol.query.$parenqueryQQ = $('#parent-queryQQ');
		ctrol.query.$sonloanUserName = $('#son-loanUserName');		
		ctrol.query.$sonqueryCid = $('#son-queryCid');
		ctrol.query.$sonqueryTel = $('#son-queryTel');
		ctrol.query.$sonqueryQQ = $('#son-queryQQ');
		ctrol.query.$btnQuery.click(function(){  
			doQuery();	 
		}); 
		ctrol.query.$btnQReset.click(function(){  
			clearInput();	 
		}); 
		$queryPage = $('#query-input');
		$queryResultPage = $('#query-result');
		
		ctrol.queryResult.$qrqueryId = $('#qr-queryId');
		ctrol.queryResult.$qrqueryTime = $('#qr-queryTime');
		ctrol.queryResult.$qrqName = $('#qr-qName');
		ctrol.queryResult.$qrqCid = $('#qr-qCid');
		ctrol.queryResult.$qrqTel = $('#qr-qTel');
		ctrol.queryResult.$qrqMail = $('#qr-qMail');
		ctrol.queryResult.$qrqQq = $('#qr-qQq');
		ctrol.queryResult.$qrriskLevel = $('#qr-riskLevel');
		ctrol.queryResult.$qrwebloanTimes = $('#qr-webloanTimes');
		ctrol.queryResult.$qrwebloanStages = $('#qr-webloanStages');
		ctrol.queryResult.$qroneMonth = $('#qr-oneMonth');
		ctrol.queryResult.$qroneSeason = $('#qr-oneSeason');
		ctrol.queryResult.$qrhalfYear = $('#qr-halfYear');
		ctrol.queryResult.$qroneYear = $('#qr-oneYear');
		
		ctrol.queryResult.$qroneMonthCell = $('#qr-oneMonth td');
		ctrol.queryResult.$qroneSeasonCell = $('#qr-oneSeason td');
		ctrol.queryResult.$qrhalfYearCell = $('#qr-halfYear td');
		ctrol.queryResult.$qroneYearCell = $('#qr-oneYear td');
		
		ctrol.queryResult.$qrblkInfo = $('#qr-blkInfo');
		ctrol.queryResult.$qrblkInfoDetail = $('#qr-blkInfoDetail');
	}
	else if(location.href.lastIndexOf("queryResult.jsp") >= 0){
		console.log("init query result");	
		console.log(JSON.stringify(retInfo.query.result));	
	}
//	else if(location.href.lastIndexOf("eQueryResult.jsp") >= 0){ 	
	else if(location.href.indexOf("eQueryResult.jsp") >= 0){ 	
		loginedUserName = getUserNameFromUrl(location.search.substring(1)); 
		
//		console.log("init query result,unm="+loginedUserName); 
		console.log("init query result"); 
		ctrol.query.$switchToQueryInput = $('#switchToQueryInput');
		ctrol.query.$loanUserName = $('#loanUserName');
		ctrol.query.$queryCid = $('#queryCid');
		ctrol.query.$queryTel = $('#queryTel');
		ctrol.query.$queryQQ = $('#queryQQ');
		ctrol.query.$queryMail = $('#queryMail');
		ctrol.query.$webloanCk = $('#webloanCk');
		ctrol.query.$qqQueryCk = $('#qqQueryCk');
		ctrol.query.$btnQuery = $('#btnQuery');
		ctrol.query.$btnQReset = $('#btnQReset'); 
		ctrol.query.$parenloanUserName = $('#parent-loanUserName');			
		ctrol.query.$parenqueryCid = $('#parent-queryCid');
		ctrol.query.$parenqueryTel = $('#parent-queryTel');
		ctrol.query.$parenqueryQQ = $('#parent-queryQQ');
		ctrol.query.$parenqueryMail = $('#parent-queryMail');
		ctrol.query.$sonloanUserName = $('#son-loanUserName');		
		ctrol.query.$sonqueryCid = $('#son-queryCid');
		ctrol.query.$sonqueryTel = $('#son-queryTel');
		ctrol.query.$sonqueryQQ = $('#son-queryQQ');
		ctrol.query.$sonqueryMail = $('#son-queryMail');
		
		ctrol.query.$btnQuery.click(function(){  
			doQuery();	 
		}); 
		ctrol.query.$btnQReset.click(function(){  
			clearInput();	 
		}); 
		$queryPage = $('#query-input');
		$queryResultPage = $('#query-result');
		ctrol.query.$switchToQueryInput.click(function(){  
			$queryResultPage.hide();	 
			$queryPage.show();
		}); 
		
		ctrol.queryResult.$qrqueryId = $('#qr-queryId');
		ctrol.queryResult.$qrqueryTime = $('#qr-queryTime');
		ctrol.queryResult.$qrqName = $('#qr-qName');
		ctrol.queryResult.$qrqCid = $('#qr-qCid');
		ctrol.queryResult.$qrqTel = $('#qr-qTel');
		ctrol.queryResult.$qrqMail = $('#qr-qMail');
		ctrol.queryResult.$qrqQq = $('#qr-qQq');
		ctrol.queryResult.$qrriskLevel = $('#qr-riskLevel');
		ctrol.queryResult.$qrwebloanTimes = $('#qr-webloanTimes');
		ctrol.queryResult.$qrwebloanStages = $('#qr-webloanStages');
		ctrol.queryResult.$qroneMonth = $('#qr-oneMonth');
		ctrol.queryResult.$qroneSeason = $('#qr-oneSeason');
		ctrol.queryResult.$qrhalfYear = $('#qr-halfYear');
		ctrol.queryResult.$qroneYear = $('#qr-oneYear');
		
		ctrol.queryResult.$qroneMonthCell = $('#qr-oneMonth td');
		ctrol.queryResult.$qroneSeasonCell = $('#qr-oneSeason td');
		ctrol.queryResult.$qrhalfYearCell = $('#qr-halfYear td');
		ctrol.queryResult.$qroneYearCell = $('#qr-oneYear td');
		
		ctrol.queryResult.$qrblkInfo = $('#qr-blkInfo');
		ctrol.queryResult.$qrblkInfoDetail = $('#qr-blkInfoDetail');
		
	}
};


function digitFill(n){
	return (n > 9 ? n : "0" + n );
}
var errorNum=0;
var failNum=0;
var count=0;
function requestFromServer(url,data,callback){ 
//	alert('url='+url+"\r\ndata="+JSON.stringify(data));
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		contentType:"application/json;chaset:utf-8",
		data:JSON.stringify(data),
		success:function(result){
			var code = result.result_code; 
//			if(code != 200){   
//				alert("result.result_code="+result.result_code);
//				document.title = "E["+(errorNum)+"] F"+(++failNum)+"] C["+(count)+"]";
//				return ;
//			}
			callback(result) ;
		},
		error:function(){ 
			alert("A error happens");
			document.title = "E["+(++errorNum)+"] F"+(failNum)+"] C["+(count)+"]";
		}
	});
}

var dataDefault = {
		"nodes":[{"name":"陈琦","radius":60,"index":0},
		         {"name":"None","radius":35,"index":1},
		         {"name":"毕业@风云〓","radius":35,"index":2},
		         {"name":"None","radius":35,"index":3},
		         {"name":"∞②.⑺.㈠∞","radius":35,"index":4},
		         {"name":"迅雷在线交流群(★)","radius":35,"index":5},
		         {"name":"︶ㄣ絕版贵族","radius":35,"index":6},
		         {"name":"﹊⒉7Ⅰ委員會ら","radius":35,"index":7},
		         {"name":"电脑爱好者","radius":35,"index":8},
		         {"name":"家属@群","radius":35,"index":9},
		         {"name":"3中06届初三271","radius":35,"index":10},
		         {"name":"詠逺dè⊙2⑦1","radius":35,"index":11},
		         {"name":"None","radius":35,"index":12},
		         {"name":"[íoadīnɡ..◆6.⒌","radius":35,"index":13},
		         {"name":"王氏家族","radius":35,"index":14},
		         {"name":"291的神话","radius":35,"index":15},
		         {"name":"None","radius":35,"index":16},
		         {"name":"℡闪耀‖42班。","radius":35,"index":17},
		         {"name":"﹊糖粿℡家族﹏","radius":35,"index":18},
		         {"name":"新襘所、⒉㈨1","radius":35,"index":19},
		         {"name":"爱疯一族①iPhone手机","radius":35,"index":20},
		         {"name":"依然和谐永存的群","radius":35,"index":21},
		         {"name":"快乐","radius":35,"index":22},
		         {"name":"朋友、一輩子旳朋友","radius":35,"index":23},
		         {"name":"None","radius":35,"index":24},
		         {"name":"何玉","radius":13,"index":25},
		         {"name":"张亚红","radius":13,"index":26},
		         {"name":"陈永峰","radius":13,"index":27},
		         {"name":"陈志军","radius":13,"index":28},
		         {"name":"None","radius":13,"index":29},
		         {"name":"None","radius":13,"index":30},
		         {"name":"陈朝波","radius":13,"index":31},
		         {"name":"None","radius":13,"index":32},
		         {"name":"刘洁","radius":13,"index":33},
		         {"name":"尚世晨","radius":13,"index":34},
		         {"name":"None","radius":13,"index":35},
		         {"name":"金小璇","radius":13,"index":36},
		         {"name":"徐聪","radius":13,"index":37},
		         {"name":"杨宸一","radius":13,"index":38},
		         {"name":"王月辉","radius":13,"index":39},
		         {"name":"班的","radius":13,"index":40},
		         {"name":"None","radius":13,"index":41},
		         {"name":"吴摩西","radius":13,"index":42},
		         {"name":"None","radius":13,"index":43},
		         {"name":"李军豪","radius":13,"index":44},
		         {"name":"花宝","radius":13,"index":45},
		         {"name":"李佳","radius":13,"index":46},
		         {"name":"None","radius":13,"index":47},
		         {"name":"苏芮","radius":13,"index":48},
		         {"name":"None","radius":13,"index":49},
		         {"name":"姚梦圆","radius":13,"index":50},
		         {"name":"翟泽星","radius":13,"index":51},
		         {"name":"曹爽","radius":13,"index":52},
		         {"name":"柳滋钰","radius":13,"index":53},
		         {"name":"常青","radius":13,"index":54},
		         {"name":"贾汉清","radius":13,"index":55},
		         {"name":"白云龙","radius":13,"index":56},
		         {"name":"None","radius":13,"index":57},
		         {"name":"None","radius":13,"index":58},
		         {"name":"常亮","radius":13,"index":59},
		         {"name":"马健","radius":13,"index":60},
		         {"name":"翟世玉","radius":13,"index":61},
		         {"name":"刘陶琳","radius":13,"index":62},
		         {"name":"None","radius":13,"index":63},
		         {"name":"戴道海","radius":13,"index":64},
		         {"name":"None","radius":13,"index":65},
		         {"name":"武超","radius":13,"index":66},
		         {"name":"张晓国","radius":13,"index":67},
		         {"name":"张明皓","radius":13,"index":68},
		         {"name":"陈娜","radius":13,"index":69},
		         {"name":"王棣","radius":13,"index":70},
		         {"name":"白宇杰","radius":13,"index":71},
		         {"name":"于小雨","radius":13,"index":72},
		         {"name":"None","radius":13,"index":73},
		         {"name":"周爽","radius":13,"index":74},
		         {"name":"None","radius":13,"index":75},
		         {"name":"庞渐潇","radius":13,"index":76},
		         {"name":"张铭序","radius":13,"index":77},
		         {"name":"高琪","radius":13,"index":78},
		         {"name":"霍艳霞","radius":13,"index":79},
		         {"name":"宋子超","radius":13,"index":80},
		         {"name":"王君玮","radius":13,"index":81},
		         {"name":"王斯瑶","radius":13,"index":82},
		         {"name":"张学思","radius":13,"index":83},
		         {"name":"段笑然","radius":13,"index":84},
		         {"name":"赵玮","radius":13,"index":85},
		         {"name":"孔令杨","radius":13,"index":86},
		         {"name":"张琪","radius":13,"index":87},
		         {"name":"冯瑞雪","radius":13,"index":88},
		         {"name":"陈月","radius":13,"index":89},
		         {"name":"赵慕杰","radius":13,"index":90},
		         {"name":"郑凯月","radius":13,"index":91},
		         {"name":"None","radius":13,"index":92},
		         {"name":"桑朵朵","radius":13,"index":93},
		         {"name":"王玮","radius":13,"index":94},
		         {"name":"刘方舟","radius":13,"index":95},
		         {"name":"None","radius":13,"index":96},
		         {"name":"None","radius":13,"index":97},
		         {"name":"金这零","radius":13,"index":98},
		         {"name":"马利","radius":13,"index":99},
		         {"name":"山西","radius":13,"index":100},
		         {"name":"刘昭","radius":13,"index":101},
		         {"name":"郭睦羽","radius":13,"index":102},
		         {"name":"赵思路","radius":13,"index":103},
		         {"name":"白小郁","radius":13,"index":104},
		         {"name":"史泽宇","radius":13,"index":105},
		         {"name":"白诗雨","radius":13,"index":106},
		         {"name":"王耀","radius":13,"index":107},
		         {"name":"武嘉仪","radius":13,"index":108},
		         {"name":"舒米楽","radius":13,"index":109},
		         {"name":"王希","radius":13,"index":110},
		         {"name":"路路","radius":13,"index":111},
		         {"name":"王希","radius":13,"index":112},
		         {"name":"李诗雯","radius":13,"index":113},
		         {"name":"None","radius":13,"index":114},
		         {"name":"林涛","radius":13,"index":115},
		         {"name":"刘先生","radius":13,"index":116},
		         {"name":"王少锋","radius":13,"index":117},
		         {"name":"雷友","radius":13,"index":118},
		         {"name":"尹红梅","radius":13,"index":119},
		         {"name":"唐唐","radius":13,"index":120},
		         {"name":"祝玉婷","radius":13,"index":121},
		         {"name":"左之狼","radius":13,"index":122},
		         {"name":"司令部","radius":13,"index":123},
		         {"name":"茅英卓","radius":13,"index":124},
		         {"name":"姜燕林","radius":13,"index":125},
		         {"name":"孙彝光","radius":13,"index":126},
		         {"name":"韩雷","radius":13,"index":127},
		         {"name":"张艳","radius":13,"index":128},
		         {"name":"薛炜","radius":13,"index":129},
		         {"name":"高锐","radius":13,"index":130},
		         {"name":"薛凡","radius":13,"index":131},
		         {"name":"雷友","radius":13,"index":132},
		         {"name":"雷神","radius":13,"index":133},
		         {"name":"雷友","radius":13,"index":134},
		         {"name":"夏兲","radius":13,"index":135},
		         {"name":"雷友","radius":13,"index":136},
		         {"name":"姬鹏","radius":13,"index":137},
		         {"name":"赵乐","radius":13,"index":138},
		         {"name":"郑涛","radius":13,"index":139},
		         {"name":"张绪营","radius":13,"index":140},
		         {"name":"石慧","radius":13,"index":141},
		         {"name":"雷友","radius":13,"index":142},
		         {"name":"吴睿滨","radius":13,"index":143},
		         {"name":"胡逸翔","radius":13,"index":144},
		         {"name":"雷友","radius":13,"index":145},
		         {"name":"蒙蒙","radius":13,"index":146},
		         {"name":"彭啸","radius":13,"index":147},
		         {"name":"周梦伟","radius":13,"index":148},
		         {"name":"马立群","radius":13,"index":149},
		         {"name":"None","radius":13,"index":150},
		         {"name":"雷友","radius":13,"index":151},
		         {"name":"叶强","radius":13,"index":152},
		         {"name":"房晓亮","radius":13,"index":153},
		         {"name":"王劳敏","radius":13,"index":154},
		         {"name":"雷友","radius":13,"index":155},
		         {"name":"张新阳","radius":13,"index":156},
		         {"name":"王静","radius":13,"index":157},
		         {"name":"雷友","radius":13,"index":158},
		         {"name":"周耀鹏","radius":13,"index":159},
		         {"name":"雷友","radius":13,"index":160},
		         {"name":"陈仕银","radius":13,"index":161},
		         {"name":"None","radius":13,"index":162},
		         {"name":"徐洪涛","radius":13,"index":163},
		         {"name":"龙锋","radius":13,"index":164},
		         {"name":"刘钧尧","radius":13,"index":165},
		         {"name":"雷友","radius":13,"index":166},
		         {"name":"沈路佳","radius":13,"index":167},
		         {"name":"沈云州","radius":13,"index":168},
		         {"name":"卜一名","radius":13,"index":169},
		         {"name":"None","radius":13,"index":170},
		         {"name":"尚昆","radius":13,"index":171},
		         {"name":"雷友","radius":13,"index":172},
		         {"name":"雷友","radius":13,"index":173},
		         {"name":"孙滔","radius":13,"index":174},
		         {"name":"刘轩祎","radius":13,"index":175},
		         {"name":"雷友","radius":13,"index":176},
		         {"name":"胡峰","radius":13,"index":177},
		         {"name":"李璨","radius":13,"index":178},
		         {"name":"雷友","radius":13,"index":179},
		         {"name":"None","radius":13,"index":180},
		         {"name":"花园","radius":13,"index":181},
		         {"name":"张太公","radius":13,"index":182},
		         {"name":"None","radius":13,"index":183},
		         {"name":"雷友","radius":13,"index":184},
		         {"name":"薛争强","radius":13,"index":185},
		         {"name":"徐锋","radius":13,"index":186},
		         {"name":"None","radius":13,"index":187},
		         {"name":"赵鸿","radius":13,"index":188},
		         {"name":"None","radius":13,"index":189},
		         {"name":"None","radius":13,"index":190},
		         {"name":"魏暄","radius":13,"index":191},
		         {"name":"古骨","radius":13,"index":192},
		         {"name":"皮蛋","radius":13,"index":193},
		         {"name":"赵文瑾","radius":13,"index":194},
		         {"name":"杨薛枫","radius":13,"index":195},
		         {"name":"刘国庆","radius":13,"index":196},
		         {"name":"陈强","radius":13,"index":197},
		         {"name":"雷友","radius":13,"index":198},
		         {"name":"叶络","radius":13,"index":199},
		         {"name":"周晓龙","radius":13,"index":200},
		         {"name":"None","radius":13,"index":201},
		         {"name":"雷友","radius":13,"index":202},
		         {"name":"雷友","radius":13,"index":203},
		         {"name":"李杰","radius":13,"index":204},
		         {"name":"孙爱姚","radius":13,"index":205},
		         {"name":"盛志冬","radius":13,"index":206},
		         {"name":"张新鼎","radius":13,"index":207},
		         {"name":"雷友","radius":13,"index":208},
		         {"name":"陈立伟","radius":13,"index":209},
		         {"name":"罗湖","radius":13,"index":210},
		         {"name":"郑一峰","radius":13,"index":211},
		         {"name":"吴广泽","radius":13,"index":212},
		         {"name":"罗凌霄","radius":13,"index":213},
		         {"name":"雷友","radius":13,"index":214},
		         {"name":"雷友","radius":13,"index":215},
		         {"name":"雷友","radius":13,"index":216},
		         {"name":"何素楠","radius":13,"index":217},
		         {"name":"雷友","radius":13,"index":218},
		         {"name":"雷友","radius":13,"index":219},
		         {"name":"叶草","radius":13,"index":220},
		         {"name":"秦皇岛","radius":13,"index":221},
		         {"name":"None","radius":13,"index":222},
		         {"name":"李冰","radius":13,"index":223},
		         {"name":"雷友","radius":13,"index":224},
		         {"name":"甘草","radius":13,"index":225},
		         {"name":"幸運一","radius":13,"index":226},
		         {"name":"邹胜龙","radius":13,"index":227},
		         {"name":"冯越","radius":13,"index":228},
		         {"name":"雷友","radius":13,"index":229},
		         {"name":"刘志强","radius":13,"index":230},
		         {"name":"None","radius":13,"index":231},
		         {"name":"雷友","radius":13,"index":232},
		         {"name":"None","radius":13,"index":233},
		         {"name":"None","radius":13,"index":234},
		         {"name":"王棣","radius":13,"index":235},
		         {"name":"张学思","radius":13,"index":236},
		         {"name":"冯国杉","radius":13,"index":237},
		         {"name":"朱鸿远","radius":13,"index":238},
		         {"name":"刘若晨","radius":13,"index":239},
		         {"name":"史强","radius":13,"index":240},
		         {"name":"秦思远","radius":13,"index":241},
		         {"name":"郭凡","radius":13,"index":242},
		         {"name":"赵振华","radius":13,"index":243},
		         {"name":"秦磊","radius":13,"index":244},
		         {"name":"孔祥源","radius":13,"index":245},
		         {"name":"刘贺","radius":13,"index":246},
		         {"name":"姜河","radius":13,"index":247},
		         {"name":"路广","radius":13,"index":248},
		         {"name":"None","radius":13,"index":249},
		         {"name":"None","radius":13,"index":250},
		         {"name":"None","radius":13,"index":251},
		         {"name":"None","radius":13,"index":252},
		         {"name":"白羽","radius":13,"index":253},
		         {"name":"路璐","radius":13,"index":254},
		         {"name":"None","radius":13,"index":255},
		         {"name":"安琪","radius":13,"index":256},
		         {"name":"王璐慧","radius":13,"index":257},
		         {"name":"None","radius":13,"index":258},
		         {"name":"郭宇翔","radius":13,"index":259},
		         {"name":"赵天","radius":13,"index":260},
		         {"name":"None","radius":13,"index":261},
		         {"name":"None","radius":13,"index":262},
		         {"name":"None","radius":13,"index":263},
		         {"name":"None","radius":13,"index":264},
		         {"name":"None","radius":13,"index":265},
		         {"name":"万力","radius":13,"index":266},
		         {"name":"张斌一","radius":13,"index":267},
		         {"name":"None","radius":13,"index":268},
		         {"name":"王子垚","radius":13,"index":269},
		         {"name":"戴道海","radius":13,"index":270},
		         {"name":"张钰琛","radius":13,"index":271},
		         {"name":"史惠文","radius":13,"index":272},
		         {"name":"马志远","radius":13,"index":273},
		         {"name":"尹申","radius":13,"index":274},
		         {"name":"张烜","radius":13,"index":275},
		         {"name":"None","radius":13,"index":276},
		         {"name":"张卉","radius":13,"index":277},
		         {"name":"李翔","radius":13,"index":278},
		         {"name":"孔维雅","radius":13,"index":279},
		         {"name":"王瑜","radius":13,"index":280},
		         {"name":"郝铭轩","radius":13,"index":281},
		         {"name":"张宁","radius":13,"index":282},
		         {"name":"云云","radius":13,"index":283},
		         {"name":"白冰","radius":13,"index":284},
		         {"name":"郝彤","radius":13,"index":285},
		         {"name":"张雪子","radius":13,"index":286},
		         {"name":"赵琦晟","radius":13,"index":287},
		         {"name":"王骞","radius":13,"index":288},
		         {"name":"赵箭","radius":13,"index":289},
		         {"name":"刘国祥","radius":13,"index":290},
		         {"name":"李洋","radius":13,"index":291},
		         {"name":"穆晓鹏","radius":13,"index":292},
		         {"name":"时学东","radius":13,"index":293},
		         {"name":"石娟","radius":13,"index":294},
		         {"name":"张夏婷","radius":13,"index":295},
		         {"name":"王广君","radius":13,"index":296},
		         {"name":"封饭","radius":13,"index":297},
		         {"name":"石晋阳","radius":13,"index":298},
		         {"name":"冯静","radius":13,"index":299},
		         {"name":"王文浩","radius":13,"index":300},
		         {"name":"王翼豪","radius":13,"index":301},
		         {"name":"杨明月","radius":13,"index":302},
		         {"name":"刘颖","radius":13,"index":303},
		         {"name":"杨宇超","radius":13,"index":304},
		         {"name":"崔强","radius":13,"index":305},
		         {"name":"樊乐","radius":13,"index":306},
		         {"name":"李宝平","radius":13,"index":307},
		         {"name":"王治民","radius":13,"index":308},
		         {"name":"夏哲","radius":13,"index":309},
		         {"name":"王玥","radius":13,"index":310},
		         {"name":"丁海朝","radius":13,"index":311},
		         {"name":"贾月月","radius":13,"index":312},
		         {"name":"尚洁","radius":13,"index":313},
		         {"name":"王玥珺","radius":13,"index":314},
		         {"name":"耿嘉敏","radius":13,"index":315},
		         {"name":"曹佳雯","radius":13,"index":316},
		         {"name":"朱清磊","radius":13,"index":317},
		         {"name":"None","radius":13,"index":318},
		         {"name":"None","radius":13,"index":319},
		         {"name":"冯茜","radius":13,"index":320},
		         {"name":"华杰","radius":13,"index":321},
		         {"name":"景澈","radius":13,"index":322},
		         {"name":"李晶晶","radius":13,"index":323},
		         {"name":"None","radius":13,"index":324},
		         {"name":"None","radius":13,"index":325},
		         {"name":"卢肖怡","radius":13,"index":326},
		         {"name":"None","radius":13,"index":327},
		         {"name":"林桂谋","radius":13,"index":328},
		         {"name":"王鸿翔","radius":13,"index":329},
		         {"name":"王志勇","radius":13,"index":330},
		         {"name":"王大爷","radius":13,"index":331},
		         {"name":"管理","radius":13,"index":332},
		         {"name":"None","radius":13,"index":333},
		         {"name":"陈洲龙","radius":13,"index":334},
		         {"name":"None","radius":13,"index":335},
		         {"name":"孙小美","radius":13,"index":336},
		         {"name":"None","radius":13,"index":337},
		         {"name":"倪维安","radius":13,"index":338},
		         {"name":"罗辉燕","radius":13,"index":339},
		         {"name":"杜朝海","radius":13,"index":340},
		         {"name":"None","radius":13,"index":341},
		         {"name":"None","radius":13,"index":342},
		         {"name":"None","radius":13,"index":343},
		         {"name":"None","radius":13,"index":344},
		         {"name":"张帅鸽","radius":13,"index":345},
		         {"name":"张小霞","radius":13,"index":346},
		         {"name":"None","radius":13,"index":347},
		         {"name":"花无恒","radius":13,"index":348},
		         {"name":"陈鹏","radius":13,"index":349},
		         {"name":"王力红","radius":13,"index":350},
		         {"name":"傅偉瀧","radius":13,"index":351},
		         {"name":"None","radius":13,"index":352},
		         {"name":"路有情","radius":13,"index":353},
		         {"name":"None","radius":13,"index":354},
		         {"name":"孙海军","radius":13,"index":355},
		         {"name":"胡松","radius":13,"index":356},
		         {"name":"陈刚","radius":13,"index":357},
		         {"name":"None","radius":13,"index":358},
		         {"name":"None","radius":13,"index":359},
		         {"name":"平西王","radius":13,"index":360},
		         {"name":"None","radius":13,"index":361},
		         {"name":"李春江","radius":13,"index":362},
		         {"name":"王文浩","radius":13,"index":363},
		         {"name":"None","radius":13,"index":364},
		         {"name":"None","radius":13,"index":365},
		         {"name":"汪庆","radius":13,"index":366},
		         {"name":"巴萨","radius":13,"index":367},
		         {"name":"None","radius":13,"index":368},
		         {"name":"None","radius":13,"index":369},
		         {"name":"None","radius":13,"index":370},
		         {"name":"None","radius":13,"index":371},
		         {"name":"None","radius":13,"index":372},
		         {"name":"李奇翎","radius":13,"index":373},
		         {"name":"闻一杰","radius":13,"index":374},
		         {"name":"陈中蓝","radius":13,"index":375},
		         {"name":"王斌侠","radius":13,"index":376},
		         {"name":"焦思","radius":13,"index":377},
		         {"name":"祁佳晖","radius":13,"index":378},
		         {"name":"None","radius":13,"index":379},
		         {"name":"王智勇","radius":13,"index":380},
		         {"name":"王建宇","radius":13,"index":381},
		         {"name":"张少轩","radius":13,"index":382},
		         {"name":"史新玉","radius":13,"index":383},
		         {"name":"None","radius":13,"index":384},
		         {"name":"李麗波","radius":13,"index":385},
		         {"name":"左岸","radius":13,"index":386},
		         {"name":"尹新尧","radius":13,"index":387},
		         {"name":"张金龙","radius":13,"index":388},
		         {"name":"陈威","radius":13,"index":389},
		         {"name":"None","radius":13,"index":390},
		         {"name":"山西","radius":13,"index":391},
		         {"name":"徐五涛","radius":13,"index":392},
		         {"name":"董其成","radius":13,"index":393},
		         {"name":"张立十","radius":13,"index":394},
		         {"name":"李鑫","radius":13,"index":395},
		         {"name":"杨仁杰","radius":13,"index":396},
		         {"name":"王宏","radius":13,"index":397},
		         {"name":"王泽","radius":13,"index":398},
		         {"name":"李元杰","radius":13,"index":399},
		         {"name":"None","radius":13,"index":400},
		         {"name":"杨机机","radius":13,"index":401},
		         {"name":"李文慧","radius":13,"index":402},
		         {"name":"苗林","radius":13,"index":403},
		         {"name":"刘燕龙","radius":13,"index":404},
		         {"name":"刘鹏","radius":13,"index":405},
		         {"name":"王岳","radius":13,"index":406},
		         {"name":"王涛","radius":13,"index":407},
		         {"name":"张淑涛","radius":13,"index":408},
		         {"name":"郭琰楠","radius":13,"index":409},
		         {"name":"None","radius":13,"index":410},
		         {"name":"郑锐","radius":13,"index":411},
		         {"name":"郭思诚","radius":13,"index":412},
		         {"name":"None","radius":13,"index":413},
		         {"name":"None","radius":13,"index":414},
		         {"name":"赵鑫","radius":13,"index":415},
		         {"name":"武帆","radius":13,"index":416},
		         {"name":"秋千","radius":13,"index":417},
		         {"name":"石佳鑫","radius":13,"index":418},
		         {"name":"任玥","radius":13,"index":419},
		         {"name":"黄帅","radius":13,"index":420},
		         {"name":"毕嘉琪","radius":13,"index":421},
		         {"name":"史梦","radius":13,"index":422},
		         {"name":"孙鹤","radius":13,"index":423},
		         {"name":"刘倩","radius":13,"index":424},
		         {"name":"终极","radius":13,"index":425},
		         {"name":"郗砚璋","radius":13,"index":426},
		         {"name":"贾芸","radius":13,"index":427},
		         {"name":"崔洁荣","radius":13,"index":428},
		         {"name":"申伟","radius":13,"index":429},
		         {"name":"王威","radius":13,"index":430},
		         {"name":"None","radius":13,"index":431},
		         {"name":"None","radius":13,"index":432},
		         {"name":"None","radius":13,"index":433},
		         {"name":"周海健","radius":13,"index":434},
		         {"name":"安易年","radius":13,"index":435},
		         {"name":"王超","radius":13,"index":436},
		         {"name":"赵航","radius":13,"index":437},
		         {"name":"吴风","radius":13,"index":438},
		         {"name":"广东","radius":13,"index":439},
		         {"name":"张亚宗","radius":13,"index":440},
		         {"name":"韶关","radius":13,"index":441},
		         {"name":"徐业兴","radius":13,"index":442},
		         {"name":"None","radius":13,"index":443},
		         {"name":"唐程","radius":13,"index":444},
		         {"name":"邬志杰","radius":13,"index":445},
		         {"name":"劳动局","radius":13,"index":446},
		         {"name":"金陵","radius":13,"index":447},
		         {"name":"洪松涛","radius":13,"index":448},
		         {"name":"麻栗坡","radius":13,"index":449},
		         {"name":"张爱兵","radius":13,"index":450},
		         {"name":"苏州","radius":13,"index":451},
		         {"name":"石器","radius":13,"index":452},
		         {"name":"None","radius":13,"index":453},
		         {"name":"乌云","radius":13,"index":454},
		         {"name":"白鹏飞","radius":13,"index":455},
		         {"name":"苏伟","radius":13,"index":456},
		         {"name":"黄毅","radius":13,"index":457},
		         {"name":"胡英杰","radius":13,"index":458},
		         {"name":"郭鹏","radius":13,"index":459},
		         {"name":"颜春广","radius":13,"index":460},
		         {"name":"王宇","radius":13,"index":461},
		         {"name":"骆驼","radius":13,"index":462},
		         {"name":"王健","radius":13,"index":463},
		         {"name":"None","radius":13,"index":464},
		         {"name":"汪凯","radius":13,"index":465},
		         {"name":"李白勇","radius":13,"index":466},
		         {"name":"None","radius":13,"index":467},
		         {"name":"白斓","radius":13,"index":468},
		         {"name":"None","radius":13,"index":469},
		         {"name":"刘基万","radius":13,"index":470},
		         {"name":"马锋华","radius":13,"index":471},
		         {"name":"华东","radius":13,"index":472},
		         {"name":"毛毛","radius":13,"index":473},
		         {"name":"王总","radius":13,"index":474},
		         {"name":"None","radius":13,"index":475},
		         {"name":"董坤龙","radius":13,"index":476},
		         {"name":"朱纯秀","radius":13,"index":477},
		         {"name":"None","radius":13,"index":478},
		         {"name":"孙兴朋","radius":13,"index":479},
		         {"name":"None","radius":13,"index":480},
		         {"name":"单色调","radius":13,"index":481},
		         {"name":"None","radius":13,"index":482},
		         {"name":"袁江平","radius":13,"index":483},
		         {"name":"陈浩南","radius":13,"index":484},
		         {"name":"吉翔","radius":13,"index":485},
		         {"name":"苏一涛","radius":13,"index":486},
		         {"name":"汤冬冬","radius":13,"index":487},
		         {"name":"金龍","radius":13,"index":488},
		         {"name":"朱凯","radius":13,"index":489},
		         {"name":"骆华军","radius":13,"index":490},
		         {"name":"林丽彬","radius":13,"index":491},
		         {"name":"满明","radius":13,"index":492},
		         {"name":"胡红剑","radius":13,"index":493},
		         {"name":"戴新良","radius":13,"index":494},
		         {"name":"钱生建","radius":13,"index":495},
		         {"name":"史鹏","radius":13,"index":496},
		         {"name":"邹杰","radius":13,"index":497},
		         {"name":"薛远方","radius":13,"index":498},
		         {"name":"None","radius":13,"index":499},
		         {"name":"None","radius":13,"index":500},
		         {"name":"刘超","radius":13,"index":501},
		         {"name":"幸福","radius":13,"index":502},
		         {"name":"武汉","radius":13,"index":503},
		         {"name":"None","radius":13,"index":504},
		         {"name":"刘文杰","radius":13,"index":505},
		         {"name":"None","radius":13,"index":506},
		         {"name":"None","radius":13,"index":507},
		         {"name":"广州","radius":13,"index":508},
		         {"name":"华氏","radius":13,"index":509},
		         {"name":"None","radius":13,"index":510},
		         {"name":"None","radius":13,"index":511},
		         {"name":"None","radius":13,"index":512},
		         {"name":"蓝月亮","radius":13,"index":513},
		         {"name":"安防","radius":13,"index":514},
		         {"name":"陈宜升","radius":13,"index":515},
		         {"name":"印霞","radius":13,"index":516},
		         {"name":"广州","radius":13,"index":517},
		         {"name":"叶哥","radius":13,"index":518},
		         {"name":"欧阳","radius":13,"index":519},
		         {"name":"None","radius":13,"index":520},
		         {"name":"None","radius":13,"index":521},
		         {"name":"None","radius":13,"index":522},
		         {"name":"李美平","radius":13,"index":523},
		         {"name":"路由器","radius":13,"index":524},
		         {"name":"苗苗","radius":13,"index":525},
		         {"name":"赵生","radius":13,"index":526},
		         {"name":"None","radius":13,"index":527},
		         {"name":"None","radius":13,"index":528},
		         {"name":"None","radius":13,"index":529},
		         {"name":"None","radius":13,"index":530},
		         {"name":"None","radius":13,"index":531},
		         {"name":"None","radius":13,"index":532},
		         {"name":"None","radius":13,"index":533},
		         {"name":"None","radius":13,"index":534},
		         {"name":"苏广鶴","radius":13,"index":535},
		         {"name":"祝星海","radius":13,"index":536},
		         {"name":"巫女","radius":13,"index":537},
		         {"name":"金星","radius":13,"index":538},
		         {"name":"桂言葉","radius":13,"index":539},
		         {"name":"林进源","radius":13,"index":540},
		         {"name":"古道","radius":13,"index":541},
		         {"name":"李锡溢","radius":13,"index":542},
		         {"name":"叶子","radius":13,"index":543},
		         {"name":"白狐","radius":13,"index":544},
		         {"name":"韩鹏","radius":13,"index":545},
		         {"name":"韩腾","radius":13,"index":546},
		         {"name":"None","radius":13,"index":547},
		         {"name":"None","radius":13,"index":548},
		         {"name":"马庚辰","radius":13,"index":549},
		         {"name":"徐东东","radius":13,"index":550},
		         {"name":"王小东","radius":13,"index":551},
		         {"name":"None","radius":13,"index":552},
		         {"name":"刘正北","radius":13,"index":553},
		         {"name":"李瑞","radius":13,"index":554},
		         {"name":"范子文","radius":13,"index":555},
		         {"name":"艾超","radius":13,"index":556},
		         {"name":"苏醒","radius":13,"index":557},
		         {"name":"毛新宇","radius":13,"index":558},
		         {"name":"None","radius":13,"index":559},
		         {"name":"雷霆","radius":13,"index":560},
		         {"name":"None","radius":13,"index":561},
		         {"name":"蓝月","radius":13,"index":562},
		         {"name":"None","radius":13,"index":563},
		         {"name":"陈龙","radius":13,"index":564},
		         {"name":"弓虽","radius":13,"index":565},
		         {"name":"None","radius":13,"index":566},
		         {"name":"向庆千","radius":13,"index":567},
		         {"name":"None","radius":13,"index":568},
		         {"name":"袁浩","radius":13,"index":569},
		         {"name":"None","radius":13,"index":570},
		         {"name":"None","radius":13,"index":571},
		         {"name":"陈文超","radius":13,"index":572},
		         {"name":"赵捷","radius":13,"index":573},
		         {"name":"None","radius":13,"index":574},
		         {"name":"None","radius":13,"index":575},
		         {"name":"王龙章","radius":13,"index":576},
		         {"name":"None","radius":13,"index":577},
		         {"name":"云山","radius":13,"index":578},
		         {"name":"胡欢","radius":13,"index":579},
		         {"name":"None","radius":13,"index":580},
		         {"name":"张伟","radius":13,"index":581},
		         {"name":"巫师","radius":13,"index":582},
		         {"name":"None","radius":13,"index":583},
		         {"name":"None","radius":13,"index":584},
		         {"name":"江门","radius":13,"index":585},
		         {"name":"None","radius":13,"index":586},
		         {"name":"None","radius":13,"index":587},
		         {"name":"高峰","radius":13,"index":588},
		         {"name":"马阳","radius":13,"index":589},
		         {"name":"杭州","radius":13,"index":590},
		         {"name":"None","radius":13,"index":591},
		         {"name":"None","radius":13,"index":592},
		         {"name":"路过","radius":13,"index":593},
		         {"name":"胡淼","radius":13,"index":594},
		         {"name":"康一凡","radius":13,"index":595},
		         {"name":"向往","radius":13,"index":596},
		         {"name":"None","radius":13,"index":597},
		         {"name":"None","radius":13,"index":598},
		         {"name":"李汉轩","radius":13,"index":599},
		         {"name":"None","radius":13,"index":600},
		         {"name":"蔡庆辽","radius":13,"index":601},
		         {"name":"None","radius":13,"index":602},
		         {"name":"None","radius":13,"index":603},
		         {"name":"None","radius":13,"index":604},
		         {"name":"None","radius":13,"index":605},
		         {"name":"None","radius":13,"index":606},
		         {"name":"None","radius":13,"index":607},
		         {"name":"None","radius":13,"index":608},
		         {"name":"None","radius":13,"index":609},
		         {"name":"None","radius":13,"index":610}],
		         
		  "edges":[{"source":9,"target":25,"weight":100},
		           {"source":9,"target":26,"weight":100},
		           {"source":9,"target":27,"weight":100},
		           {"source":9,"target":0,"weight":100},
		           {"source":9,"target":28,"weight":100},
		           {"source":6,"target":29,"weight":100},
		           {"source":6,"target":30,"weight":100},
		           {"source":6,"target":31,"weight":100},
		           {"source":6,"target":0,"weight":100},
		           {"source":6,"target":32,"weight":100},
		           {"source":6,"target":33,"weight":100},
		           {"source":6,"target":34,"weight":100},
		           {"source":6,"target":35,"weight":100},
		           {"source":6,"target":36,"weight":100},
		           {"source":6,"target":37,"weight":100},
		           {"source":6,"target":38,"weight":100},
		           {"source":6,"target":39,"weight":100},
		           {"source":6,"target":40,"weight":100},
		           {"source":6,"target":41,"weight":100},
		           {"source":13,"target":42,"weight":100},
		           {"source":13,"target":43,"weight":100},
		           {"source":13,"target":44,"weight":100},
		           {"source":13,"target":45,"weight":100},
		           {"source":13,"target":46,"weight":100},
		           {"source":13,"target":47,"weight":100},
		           {"source":13,"target":48,"weight":100},
		           {"source":13,"target":49,"weight":100},
		           {"source":13,"target":50,"weight":100},
		           {"source":13,"target":0,"weight":100},
		           {"source":13,"target":51,"weight":100},
		           {"source":13,"target":52,"weight":100},
		           {"source":13,"target":53,"weight":100},
		           {"source":13,"target":54,"weight":100},
		           {"source":13,"target":55,"weight":100},
		           {"source":13,"target":56,"weight":100},
		           {"source":13,"target":57,"weight":100},
		           {"source":13,"target":58,"weight":100},
		           {"source":13,"target":59,"weight":100},
		           {"source":13,"target":60,"weight":100},
		           {"source":13,"target":61,"weight":100},
		           {"source":13,"target":62,"weight":100},
		           {"source":13,"target":63,"weight":100},
		           {"source":13,"target":64,"weight":100},
		           {"source":13,"target":65,"weight":100},
		           {"source":13,"target":66,"weight":100},
		           {"source":13,"target":67,"weight":100},
		           {"source":4,"target":68,"weight":100},
		           {"source":4,"target":69,"weight":100},
		           {"source":4,"target":70,"weight":100},
		           {"source":4,"target":71,"weight":100},
		           {"source":4,"target":72,"weight":100},
		           {"source":4,"target":73,"weight":100},
		           {"source":4,"target":0,"weight":100},
		           {"source":4,"target":74,"weight":100},
		           {"source":4,"target":75,"weight":100},
		           {"source":4,"target":76,"weight":100},
		           {"source":4,"target":77,"weight":100},
		           {"source":4,"target":78,"weight":100},
		           {"source":4,"target":79,"weight":100},
		           {"source":4,"target":80,"weight":100},
		           {"source":4,"target":81,"weight":100},
		           {"source":4,"target":82,"weight":100},
		           {"source":4,"target":83,"weight":100},
		           {"source":4,"target":84,"weight":100},
		           {"source":4,"target":85,"weight":100},
		           {"source":4,"target":86,"weight":100},
		           {"source":4,"target":87,"weight":100},
		           {"source":4,"target":88,"weight":100},
		           {"source":4,"target":89,"weight":100},
		           {"source":4,"target":90,"weight":100},
		           {"source":4,"target":91,"weight":100},
		           {"source":4,"target":92,"weight":100},
		           {"source":4,"target":93,"weight":100},
		           {"source":4,"target":94,"weight":100},
		           {"source":4,"target":95,"weight":100},
		           {"source":4,"target":96,"weight":100},
		           {"source":4,"target":97,"weight":100},
		           {"source":4,"target":98,"weight":100},
		           {"source":4,"target":99,"weight":100},
		           {"source":4,"target":61,"weight":100},
		           {"source":4,"target":100,"weight":100},
		           {"source":4,"target":101,"weight":100},
		           {"source":4,"target":102,"weight":100},
		           {"source":4,"target":103,"weight":100},
		           {"source":4,"target":104,"weight":100},
		           {"source":4,"target":105,"weight":100},
		           {"source":4,"target":106,"weight":100},
		           {"source":4,"target":107,"weight":100},
		           {"source":4,"target":108,"weight":100},
		           {"source":4,"target":109,"weight":100},
		           {"source":4,"target":110,"weight":100},
		           {"source":4,"target":111,"weight":100},
		           {"source":4,"target":112,"weight":100},
		           {"source":4,"target":113,"weight":100},
		           {"source":5,"target":114,"weight":100},
		           {"source":5,"target":115,"weight":100},
		           {"source":5,"target":116,"weight":100},
		           {"source":5,"target":117,"weight":100},
		           {"source":5,"target":118,"weight":100},
		           {"source":5,"target":119,"weight":100},
		           {"source":5,"target":120,"weight":100},
		           {"source":5,"target":121,"weight":100},
		           {"source":5,"target":122,"weight":100},
		           {"source":5,"target":123,"weight":100},
		           {"source":5,"target":124,"weight":100},
		           {"source":5,"target":125,"weight":100},
		           {"source":5,"target":126,"weight":100},
		           {"source":5,"target":127,"weight":100},
		           {"source":5,"target":128,"weight":100},
		           {"source":5,"target":129,"weight":100},
		           {"source":5,"target":130,"weight":100},
		           {"source":5,"target":131,"weight":100},
		           {"source":5,"target":132,"weight":100},
		           {"source":5,"target":133,"weight":100},
		           {"source":5,"target":134,"weight":100},
		           {"source":5,"target":135,"weight":100},
		           {"source":5,"target":136,"weight":100},
		           {"source":5,"target":137,"weight":100},
		           {"source":5,"target":138,"weight":100},
		           {"source":5,"target":139,"weight":100},
		           {"source":5,"target":140,"weight":100},
		           {"source":5,"target":141,"weight":100},
		           {"source":5,"target":142,"weight":100},
		           {"source":5,"target":143,"weight":100},
		           {"source":5,"target":144,"weight":100},
		           {"source":5,"target":145,"weight":100},
		           {"source":5,"target":146,"weight":100},
		           {"source":5,"target":147,"weight":100},
		           {"source":5,"target":148,"weight":100},
		           {"source":5,"target":149,"weight":100},
		           {"source":5,"target":150,"weight":100},
		           {"source":5,"target":151,"weight":100},
		           {"source":5,"target":152,"weight":100},
		           {"source":5,"target":153,"weight":100},
		           {"source":5,"target":154,"weight":100},
		           {"source":5,"target":155,"weight":100},
		           {"source":5,"target":156,"weight":100},
		           {"source":5,"target":157,"weight":100},
		           {"source":5,"target":158,"weight":100},
		           {"source":5,"target":159,"weight":100},
		           {"source":5,"target":160,"weight":100},
		           {"source":5,"target":161,"weight":100},
		           {"source":5,"target":162,"weight":100},
		           {"source":5,"target":163,"weight":100},
		           {"source":5,"target":164,"weight":100},
		           {"source":5,"target":165,"weight":100},
		           {"source":5,"target":166,"weight":100},
		           {"source":5,"target":0,"weight":100},
		           {"source":5,"target":167,"weight":100},
		           {"source":5,"target":168,"weight":100},
		           {"source":5,"target":169,"weight":100},
		           {"source":5,"target":170,"weight":100},
		           {"source":5,"target":171,"weight":100},
		           {"source":5,"target":172,"weight":100},
		           {"source":5,"target":173,"weight":100},
		           {"source":5,"target":174,"weight":100},
		           {"source":5,"target":175,"weight":100},
		           {"source":5,"target":176,"weight":100},
		           {"source":5,"target":177,"weight":100},
		           {"source":5,"target":178,"weight":100},
		           {"source":5,"target":179,"weight":100},
		           {"source":5,"target":180,"weight":100},
		           {"source":5,"target":181,"weight":100},
		           {"source":5,"target":182,"weight":100},
		           {"source":5,"target":183,"weight":100},
		           {"source":5,"target":184,"weight":100},
		           {"source":5,"target":185,"weight":100},
		           {"source":5,"target":186,"weight":100},
		           {"source":5,"target":187,"weight":100},
		           {"source":5,"target":188,"weight":100},
		           {"source":5,"target":189,"weight":100},
		           {"source":5,"target":190,"weight":100},
		           {"source":5,"target":191,"weight":100},
		           {"source":5,"target":192,"weight":100},
		           {"source":5,"target":193,"weight":100},
		           {"source":5,"target":194,"weight":100},
		           {"source":5,"target":195,"weight":100},
		           {"source":5,"target":196,"weight":100},
		           {"source":5,"target":197,"weight":100},
		           {"source":5,"target":198,"weight":100},
		           {"source":5,"target":199,"weight":100},
		           {"source":5,"target":200,"weight":100},
		           {"source":5,"target":201,"weight":100},
		           {"source":5,"target":202,"weight":100},
		           {"source":5,"target":203,"weight":100},
		           {"source":5,"target":204,"weight":100},
		           {"source":5,"target":205,"weight":100},
		           {"source":5,"target":206,"weight":100},
		           {"source":5,"target":207,"weight":100},
		           {"source":5,"target":208,"weight":100},
		           {"source":5,"target":209,"weight":100},
		           {"source":5,"target":210,"weight":100},
		           {"source":5,"target":211,"weight":100},
		           {"source":5,"target":212,"weight":100},
		           {"source":5,"target":213,"weight":100},
		           {"source":5,"target":214,"weight":100},
		           {"source":5,"target":215,"weight":100},
		           {"source":5,"target":216,"weight":100},
		           {"source":5,"target":217,"weight":100},
		           {"source":5,"target":218,"weight":100},
		           {"source":5,"target":219,"weight":100},
		           {"source":5,"target":220,"weight":100},
		           {"source":5,"target":221,"weight":100},
		           {"source":5,"target":222,"weight":100},
		           {"source":5,"target":223,"weight":100},
		           {"source":5,"target":224,"weight":100},
		           {"source":5,"target":225,"weight":100},
		           {"source":5,"target":226,"weight":100},
		           {"source":5,"target":227,"weight":100},
		           {"source":5,"target":228,"weight":100},
		           {"source":5,"target":229,"weight":100},
		           {"source":5,"target":230,"weight":100},
		           {"source":5,"target":231,"weight":100},
		           {"source":5,"target":232,"weight":100},
		           {"source":5,"target":233,"weight":100},
		           {"source":5,"target":234,"weight":100},
		           {"source":10,"target":235,"weight":100},
		           {"source":10,"target":68,"weight":100},
		           {"source":10,"target":69,"weight":100},
		           {"source":10,"target":236,"weight":100},
		           {"source":10,"target":237,"weight":100},
		           {"source":10,"target":70,"weight":100},
		           {"source":10,"target":71,"weight":100},
		           {"source":10,"target":238,"weight":100},
		           {"source":10,"target":72,"weight":100},
		           {"source":10,"target":73,"weight":100},
		           {"source":10,"target":239,"weight":100},
		           {"source":10,"target":240,"weight":100},
		           {"source":10,"target":0,"weight":100},
		           {"source":10,"target":74,"weight":100},
		           {"source":10,"target":75,"weight":100},
		           {"source":10,"target":76,"weight":100},
		           {"source":10,"target":79,"weight":100},
		           {"source":10,"target":80,"weight":100},
		           {"source":10,"target":81,"weight":100},
		           {"source":10,"target":83,"weight":100},
		           {"source":10,"target":84,"weight":100},
		           {"source":10,"target":85,"weight":100},
		           {"source":10,"target":241,"weight":100},
		           {"source":10,"target":86,"weight":100},
		           {"source":10,"target":87,"weight":100},
		           {"source":10,"target":88,"weight":100},
		           {"source":10,"target":89,"weight":100},
		           {"source":10,"target":90,"weight":100},
		           {"source":10,"target":91,"weight":100},
		           {"source":10,"target":242,"weight":100},
		           {"source":10,"target":93,"weight":100},
		           {"source":10,"target":94,"weight":100},
		           {"source":10,"target":95,"weight":100},
		           {"source":10,"target":96,"weight":100},
		           {"source":10,"target":97,"weight":100},
		           {"source":10,"target":98,"weight":100},
		           {"source":10,"target":99,"weight":100},
		           {"source":10,"target":61,"weight":100},
		           {"source":10,"target":100,"weight":100},
		           {"source":10,"target":101,"weight":100},
		           {"source":10,"target":243,"weight":100},
		           {"source":10,"target":103,"weight":100},
		           {"source":10,"target":244,"weight":100},
		           {"source":10,"target":245,"weight":100},
		           {"source":10,"target":105,"weight":100},
		           {"source":10,"target":246,"weight":100},
		           {"source":10,"target":247,"weight":100},
		           {"source":10,"target":107,"weight":100},
		           {"source":10,"target":108,"weight":100},
		           {"source":10,"target":248,"weight":100},
		           {"source":10,"target":109,"weight":100},
		           {"source":10,"target":110,"weight":100},
		           {"source":10,"target":111,"weight":100},
		           {"source":10,"target":249,"weight":100},
		           {"source":10,"target":250,"weight":100},
		           {"source":10,"target":251,"weight":100},
		           {"source":7,"target":252,"weight":100},
		           {"source":7,"target":253,"weight":100},
		           {"source":7,"target":254,"weight":100},
		           {"source":7,"target":68,"weight":100},
		           {"source":7,"target":69,"weight":100},
		           {"source":7,"target":236,"weight":100},
		           {"source":7,"target":237,"weight":100},
		           {"source":7,"target":70,"weight":100},
		           {"source":7,"target":71,"weight":100},
		           {"source":7,"target":238,"weight":100},
		           {"source":7,"target":72,"weight":100},
		           {"source":7,"target":255,"weight":100},
		           {"source":7,"target":73,"weight":100},
		           {"source":7,"target":239,"weight":100},
		           {"source":7,"target":256,"weight":100},
		           {"source":7,"target":0,"weight":100},
		           {"source":7,"target":74,"weight":100},
		           {"source":7,"target":75,"weight":100},
		           {"source":7,"target":257,"weight":100},
		           {"source":7,"target":76,"weight":100},
		           {"source":7,"target":77,"weight":100},
		           {"source":7,"target":78,"weight":100},
		           {"source":7,"target":79,"weight":100},
		           {"source":7,"target":80,"weight":100},
		           {"source":7,"target":81,"weight":100},
		           {"source":7,"target":83,"weight":100},
		           {"source":7,"target":258,"weight":100},
		           {"source":7,"target":84,"weight":100},
		           {"source":7,"target":85,"weight":100},
		           {"source":7,"target":259,"weight":100},
		           {"source":7,"target":260,"weight":100},
		           {"source":7,"target":241,"weight":100},
		           {"source":7,"target":86,"weight":100},
		           {"source":7,"target":88,"weight":100},
		           {"source":7,"target":89,"weight":100},
		           {"source":7,"target":261,"weight":100},
		           {"source":7,"target":90,"weight":100},
		           {"source":7,"target":91,"weight":100},
		           {"source":7,"target":93,"weight":100},
		           {"source":7,"target":94,"weight":100},
		           {"source":7,"target":96,"weight":100},
		           {"source":7,"target":97,"weight":100},
		           {"source":7,"target":262,"weight":100},
		           {"source":7,"target":99,"weight":100},
		           {"source":7,"target":61,"weight":100},
		           {"source":7,"target":100,"weight":100},
		           {"source":7,"target":101,"weight":100},
		           {"source":7,"target":102,"weight":100},
		           {"source":7,"target":103,"weight":100},
		           {"source":7,"target":263,"weight":100},
		           {"source":7,"target":264,"weight":100},
		           {"source":7,"target":245,"weight":100},
		           {"source":7,"target":105,"weight":100},
		           {"source":7,"target":246,"weight":100},
		           {"source":7,"target":247,"weight":100},
		           {"source":7,"target":107,"weight":100},
		           {"source":7,"target":108,"weight":100},
		           {"source":7,"target":109,"weight":100},
		           {"source":7,"target":265,"weight":100},
		           {"source":7,"target":112,"weight":100},
		           {"source":7,"target":266,"weight":100},
		           {"source":7,"target":267,"weight":100},
		           {"source":7,"target":249,"weight":100},
		           {"source":7,"target":113,"weight":100},
		           {"source":7,"target":268,"weight":100},
		           {"source":7,"target":250,"weight":100},
		           {"source":7,"target":251,"weight":100},
		           {"source":7,"target":269,"weight":100},
		           {"source":2,"target":44,"weight":100},
		           {"source":2,"target":45,"weight":100},
		           {"source":2,"target":46,"weight":100},
		           {"source":2,"target":48,"weight":100},
		           {"source":2,"target":50,"weight":100},
		           {"source":2,"target":0,"weight":100},
		           {"source":2,"target":51,"weight":100},
		           {"source":2,"target":53,"weight":100},
		           {"source":2,"target":54,"weight":100},
		           {"source":2,"target":55,"weight":100},
		           {"source":2,"target":56,"weight":100},
		           {"source":2,"target":58,"weight":100},
		           {"source":2,"target":270,"weight":100},
		           {"source":2,"target":59,"weight":100},
		           {"source":2,"target":271,"weight":100},
		           {"source":2,"target":61,"weight":100},
		           {"source":2,"target":62,"weight":100},
		           {"source":2,"target":272,"weight":100},
		           {"source":2,"target":63,"weight":100},
		           {"source":2,"target":64,"weight":100},
		           {"source":2,"target":65,"weight":100},
		           {"source":2,"target":66,"weight":100},
		           {"source":2,"target":109,"weight":100},
		           {"source":2,"target":273,"weight":100},
		           {"source":11,"target":69,"weight":100},
		           {"source":11,"target":70,"weight":100},
		           {"source":11,"target":72,"weight":100},
		           {"source":11,"target":0,"weight":100},
		           {"source":11,"target":74,"weight":100},
		           {"source":11,"target":75,"weight":100},
		           {"source":11,"target":76,"weight":100},
		           {"source":11,"target":79,"weight":100},
		           {"source":11,"target":80,"weight":100},
		           {"source":11,"target":81,"weight":100},
		           {"source":11,"target":82,"weight":100},
		           {"source":11,"target":83,"weight":100},
		           {"source":11,"target":84,"weight":100},
		           {"source":11,"target":85,"weight":100},
		           {"source":11,"target":86,"weight":100},
		           {"source":11,"target":87,"weight":100},
		           {"source":11,"target":88,"weight":100},
		           {"source":11,"target":92,"weight":100},
		           {"source":11,"target":93,"weight":100},
		           {"source":11,"target":94,"weight":100},
		           {"source":11,"target":95,"weight":100},
		           {"source":11,"target":96,"weight":100},
		           {"source":11,"target":98,"weight":100},
		           {"source":11,"target":61,"weight":100},
		           {"source":11,"target":101,"weight":100},
		           {"source":11,"target":103,"weight":100},
		           {"source":11,"target":107,"weight":100},
		           {"source":11,"target":109,"weight":100},
		           {"source":11,"target":110,"weight":100},
		           {"source":19,"target":274,"weight":100},
		           {"source":19,"target":275,"weight":100},
		           {"source":19,"target":276,"weight":100},
		           {"source":19,"target":277,"weight":100},
		           {"source":19,"target":278,"weight":100},
		           {"source":19,"target":279,"weight":100},
		           {"source":19,"target":280,"weight":100},
		           {"source":19,"target":72,"weight":100},
		           {"source":19,"target":281,"weight":100},
		           {"source":19,"target":0,"weight":100},
		           {"source":19,"target":282,"weight":100},
		           {"source":19,"target":283,"weight":100},
		           {"source":19,"target":284,"weight":100},
		           {"source":19,"target":285,"weight":100},
		           {"source":19,"target":286,"weight":100},
		           {"source":19,"target":287,"weight":100},
		           {"source":19,"target":288,"weight":100},
		           {"source":19,"target":289,"weight":100},
		           {"source":19,"target":290,"weight":100},
		           {"source":19,"target":291,"weight":100},
		           {"source":19,"target":292,"weight":100},
		           {"source":19,"target":293,"weight":100},
		           {"source":19,"target":294,"weight":100},
		           {"source":19,"target":295,"weight":100},
		           {"source":19,"target":296,"weight":100},
		           {"source":19,"target":297,"weight":100},
		           {"source":19,"target":298,"weight":100},
		           {"source":19,"target":299,"weight":100},
		           {"source":19,"target":300,"weight":100},
		           {"source":19,"target":301,"weight":100},
		           {"source":19,"target":302,"weight":100},
		           {"source":23,"target":303,"weight":100},
		           {"source":23,"target":254,"weight":100},
		           {"source":23,"target":304,"weight":100},
		           {"source":23,"target":69,"weight":100},
		           {"source":23,"target":305,"weight":100},
		           {"source":23,"target":70,"weight":100},
		           {"source":23,"target":71,"weight":100},
		           {"source":23,"target":72,"weight":100},
		           {"source":23,"target":73,"weight":100},
		           {"source":23,"target":306,"weight":100},
		           {"source":23,"target":307,"weight":100},
		           {"source":23,"target":308,"weight":100},
		           {"source":23,"target":0,"weight":100},
		           {"source":23,"target":74,"weight":100},
		           {"source":23,"target":309,"weight":100},
		           {"source":23,"target":75,"weight":100},
		           {"source":23,"target":257,"weight":100},
		           {"source":23,"target":310,"weight":100},
		           {"source":23,"target":76,"weight":100},
		           {"source":23,"target":77,"weight":100},
		           {"source":23,"target":78,"weight":100},
		           {"source":23,"target":79,"weight":100},
		           {"source":23,"target":80,"weight":100},
		           {"source":23,"target":81,"weight":100},
		           {"source":23,"target":82,"weight":100},
		           {"source":23,"target":83,"weight":100},
		           {"source":23,"target":84,"weight":100},
		           {"source":23,"target":241,"weight":100},
		           {"source":23,"target":86,"weight":100},
		           {"source":23,"target":87,"weight":100},
		           {"source":23,"target":88,"weight":100},
		           {"source":23,"target":89,"weight":100},
		           {"source":23,"target":91,"weight":100},
		           {"source":23,"target":93,"weight":100},
		           {"source":23,"target":311,"weight":100},
		           {"source":23,"target":94,"weight":100},
		           {"source":23,"target":95,"weight":100},
		           {"source":23,"target":96,"weight":100},
		           {"source":23,"target":312,"weight":100},
		           {"source":23,"target":262,"weight":100},
		           {"source":23,"target":313,"weight":100},
		           {"source":23,"target":99,"weight":100},
		           {"source":23,"target":61,"weight":100},
		           {"source":23,"target":314,"weight":100},
		           {"source":23,"target":101,"weight":100},
		           {"source":23,"target":102,"weight":100},
		           {"source":23,"target":103,"weight":100},
		           {"source":23,"target":244,"weight":100},
		           {"source":23,"target":245,"weight":100},
		           {"source":23,"target":105,"weight":100},
		           {"source":23,"target":315,"weight":100},
		           {"source":23,"target":247,"weight":100},
		           {"source":23,"target":108,"weight":100},
		           {"source":23,"target":109,"weight":100},
		           {"source":23,"target":112,"weight":100},
		           {"source":23,"target":266,"weight":100},
		           {"source":23,"target":267,"weight":100},
		           {"source":23,"target":113,"weight":100},
		           {"source":22,"target":316,"weight":100},
		           {"source":22,"target":317,"weight":100},
		           {"source":22,"target":318,"weight":100},
		           {"source":22,"target":0,"weight":100},
		           {"source":22,"target":319,"weight":100},
		           {"source":22,"target":320,"weight":100},
		           {"source":22,"target":321,"weight":100},
		           {"source":22,"target":322,"weight":100},
		           {"source":22,"target":323,"weight":100},
		           {"source":22,"target":324,"weight":100},
		           {"source":22,"target":325,"weight":100},
		           {"source":22,"target":326,"weight":100},
		           {"source":8,"target":327,"weight":100},
		           {"source":8,"target":328,"weight":100},
		           {"source":8,"target":329,"weight":100},
		           {"source":8,"target":330,"weight":100},
		           {"source":8,"target":331,"weight":100},
		           {"source":8,"target":332,"weight":100},
		           {"source":8,"target":333,"weight":100},
		           {"source":8,"target":334,"weight":100},
		           {"source":8,"target":335,"weight":100},
		           {"source":8,"target":336,"weight":100},
		           {"source":8,"target":337,"weight":100},
		           {"source":8,"target":338,"weight":100},
		           {"source":8,"target":339,"weight":100},
		           {"source":8,"target":340,"weight":100},
		           {"source":8,"target":341,"weight":100},
		           {"source":8,"target":342,"weight":100},
		           {"source":8,"target":343,"weight":100},
		           {"source":8,"target":344,"weight":100},
		           {"source":8,"target":345,"weight":100},
		           {"source":8,"target":346,"weight":100},
		           {"source":8,"target":347,"weight":100},
		           {"source":8,"target":348,"weight":100},
		           {"source":8,"target":349,"weight":100},
		           {"source":8,"target":350,"weight":100},
		           {"source":8,"target":351,"weight":100},
		           {"source":8,"target":352,"weight":100},
		           {"source":8,"target":0,"weight":100},
		           {"source":8,"target":353,"weight":100},
		           {"source":8,"target":354,"weight":100},
		           {"source":8,"target":355,"weight":100},
		           {"source":8,"target":356,"weight":100},
		           {"source":8,"target":357,"weight":100},
		           {"source":8,"target":358,"weight":100},
		           {"source":8,"target":359,"weight":100},
		           {"source":8,"target":360,"weight":100},
		           {"source":8,"target":361,"weight":100},
		           {"source":8,"target":362,"weight":100},
		           {"source":8,"target":363,"weight":100},
		           {"source":8,"target":364,"weight":100},
		           {"source":8,"target":365,"weight":100},
		           {"source":8,"target":366,"weight":100},
		           {"source":8,"target":367,"weight":100},
		           {"source":8,"target":368,"weight":100},
		           {"source":8,"target":369,"weight":100},
		           {"source":8,"target":370,"weight":100},
		           {"source":8,"target":371,"weight":100},
		           {"source":8,"target":372,"weight":100},
		           {"source":17,"target":254,"weight":100},
		           {"source":17,"target":237,"weight":100},
		           {"source":17,"target":70,"weight":100},
		           {"source":17,"target":71,"weight":100},
		           {"source":17,"target":72,"weight":100},
		           {"source":17,"target":73,"weight":100},
		           {"source":17,"target":0,"weight":100},
		           {"source":17,"target":74,"weight":100},
		           {"source":17,"target":75,"weight":100},
		           {"source":17,"target":76,"weight":100},
		           {"source":17,"target":77,"weight":100},
		           {"source":17,"target":79,"weight":100},
		           {"source":17,"target":80,"weight":100},
		           {"source":17,"target":81,"weight":100},
		           {"source":17,"target":83,"weight":100},
		           {"source":17,"target":258,"weight":100},
		           {"source":17,"target":84,"weight":100},
		           {"source":17,"target":85,"weight":100},
		           {"source":17,"target":260,"weight":100},
		           {"source":17,"target":241,"weight":100},
		           {"source":17,"target":86,"weight":100},
		           {"source":17,"target":88,"weight":100},
		           {"source":17,"target":90,"weight":100},
		           {"source":17,"target":91,"weight":100},
		           {"source":17,"target":93,"weight":100},
		           {"source":17,"target":94,"weight":100},
		           {"source":17,"target":96,"weight":100},
		           {"source":17,"target":97,"weight":100},
		           {"source":17,"target":373,"weight":100},
		           {"source":17,"target":99,"weight":100},
		           {"source":17,"target":61,"weight":100},
		           {"source":17,"target":100,"weight":100},
		           {"source":17,"target":101,"weight":100},
		           {"source":17,"target":102,"weight":100},
		           {"source":17,"target":103,"weight":100},
		           {"source":17,"target":263,"weight":100},
		           {"source":17,"target":245,"weight":100},
		           {"source":17,"target":105,"weight":100},
		           {"source":17,"target":107,"weight":100},
		           {"source":17,"target":108,"weight":100},
		           {"source":17,"target":112,"weight":100},
		           {"source":17,"target":266,"weight":100},
		           {"source":17,"target":267,"weight":100},
		           {"source":18,"target":374,"weight":100},
		           {"source":18,"target":69,"weight":100},
		           {"source":18,"target":70,"weight":100},
		           {"source":18,"target":71,"weight":100},
		           {"source":18,"target":375,"weight":100},
		           {"source":18,"target":72,"weight":100},
		           {"source":18,"target":308,"weight":100},
		           {"source":18,"target":0,"weight":100},
		           {"source":18,"target":75,"weight":100},
		           {"source":18,"target":257,"weight":100},
		           {"source":18,"target":76,"weight":100},
		           {"source":18,"target":78,"weight":100},
		           {"source":18,"target":79,"weight":100},
		           {"source":18,"target":80,"weight":100},
		           {"source":18,"target":81,"weight":100},
		           {"source":18,"target":82,"weight":100},
		           {"source":18,"target":83,"weight":100},
		           {"source":18,"target":84,"weight":100},
		           {"source":18,"target":86,"weight":100},
		           {"source":18,"target":87,"weight":100},
		           {"source":18,"target":88,"weight":100},
		           {"source":18,"target":90,"weight":100},
		           {"source":18,"target":376,"weight":100},
		           {"source":18,"target":93,"weight":100},
		           {"source":18,"target":95,"weight":100},
		           {"source":18,"target":96,"weight":100},
		           {"source":18,"target":97,"weight":100},
		           {"source":18,"target":98,"weight":100},
		           {"source":18,"target":377,"weight":100},
		           {"source":18,"target":99,"weight":100},
		           {"source":18,"target":61,"weight":100},
		           {"source":18,"target":378,"weight":100},
		           {"source":18,"target":101,"weight":100},
		           {"source":18,"target":243,"weight":100},
		           {"source":18,"target":103,"weight":100},
		           {"source":18,"target":105,"weight":100},
		           {"source":18,"target":107,"weight":100},
		           {"source":18,"target":108,"weight":100},
		           {"source":18,"target":109,"weight":100},
		           {"source":18,"target":111,"weight":100},
		           {"source":18,"target":112,"weight":100},
		           {"source":18,"target":379,"weight":100},
		           {"source":15,"target":274,"weight":100},
		           {"source":15,"target":275,"weight":100},
		           {"source":15,"target":276,"weight":100},
		           {"source":15,"target":380,"weight":100},
		           {"source":15,"target":277,"weight":100},
		           {"source":15,"target":278,"weight":100},
		           {"source":15,"target":279,"weight":100},
		           {"source":15,"target":280,"weight":100},
		           {"source":15,"target":72,"weight":100},
		           {"source":15,"target":281,"weight":100},
		           {"source":15,"target":381,"weight":100},
		           {"source":15,"target":0,"weight":100},
		           {"source":15,"target":282,"weight":100},
		           {"source":15,"target":382,"weight":100},
		           {"source":15,"target":283,"weight":100},
		           {"source":15,"target":284,"weight":100},
		           {"source":15,"target":285,"weight":100},
		           {"source":15,"target":286,"weight":100},
		           {"source":15,"target":287,"weight":100},
		           {"source":15,"target":288,"weight":100},
		           {"source":15,"target":289,"weight":100},
		           {"source":15,"target":290,"weight":100},
		           {"source":15,"target":291,"weight":100},
		           {"source":15,"target":292,"weight":100},
		           {"source":15,"target":293,"weight":100},
		           {"source":15,"target":294,"weight":100},
		           {"source":15,"target":295,"weight":100},
		           {"source":15,"target":296,"weight":100},
		           {"source":15,"target":297,"weight":100},
		           {"source":15,"target":298,"weight":100},
		           {"source":15,"target":383,"weight":100},
		           {"source":15,"target":384,"weight":100},
		           {"source":15,"target":300,"weight":100},
		           {"source":15,"target":301,"weight":100},
		           {"source":15,"target":302,"weight":100},
		           {"source":15,"target":385,"weight":100},
		           {"source":15,"target":386,"weight":100},
		           {"source":15,"target":387,"weight":100},
		           {"source":12,"target":235,"weight":100},
		           {"source":12,"target":70,"weight":100},
		           {"source":12,"target":238,"weight":100},
		           {"source":12,"target":0,"weight":100},
		           {"source":12,"target":74,"weight":100},
		           {"source":12,"target":75,"weight":100},
		           {"source":12,"target":76,"weight":100},
		           {"source":12,"target":77,"weight":100},
		           {"source":12,"target":85,"weight":100},
		           {"source":12,"target":241,"weight":100},
		           {"source":12,"target":90,"weight":100},
		           {"source":12,"target":100,"weight":100},
		           {"source":12,"target":63,"weight":100},
		           {"source":12,"target":266,"weight":100},
		           {"source":12,"target":268,"weight":100},
		           {"source":12,"target":388,"weight":100},
		           {"source":24,"target":274,"weight":100},
		           {"source":24,"target":275,"weight":100},
		           {"source":24,"target":276,"weight":100},
		           {"source":24,"target":380,"weight":100},
		           {"source":24,"target":277,"weight":100},
		           {"source":24,"target":278,"weight":100},
		           {"source":24,"target":279,"weight":100},
		           {"source":24,"target":72,"weight":100},
		           {"source":24,"target":281,"weight":100},
		           {"source":24,"target":381,"weight":100},
		           {"source":24,"target":0,"weight":100},
		           {"source":24,"target":282,"weight":100},
		           {"source":24,"target":283,"weight":100},
		           {"source":24,"target":284,"weight":100},
		           {"source":24,"target":285,"weight":100},
		           {"source":24,"target":286,"weight":100},
		           {"source":24,"target":287,"weight":100},
		           {"source":24,"target":288,"weight":100},
		           {"source":24,"target":289,"weight":100},
		           {"source":24,"target":290,"weight":100},
		           {"source":24,"target":291,"weight":100},
		           {"source":24,"target":292,"weight":100},
		           {"source":24,"target":293,"weight":100},
		           {"source":24,"target":295,"weight":100},
		           {"source":24,"target":296,"weight":100},
		           {"source":24,"target":297,"weight":100},
		           {"source":24,"target":298,"weight":100},
		           {"source":24,"target":383,"weight":100},
		           {"source":24,"target":299,"weight":100},
		           {"source":24,"target":389,"weight":100},
		           {"source":24,"target":300,"weight":100},
		           {"source":24,"target":301,"weight":100},
		           {"source":24,"target":390,"weight":100},
		           {"source":3,"target":391,"weight":100},
		           {"source":3,"target":392,"weight":100},
		           {"source":3,"target":393,"weight":100},
		           {"source":3,"target":394,"weight":100},
		           {"source":3,"target":395,"weight":100},
		           {"source":3,"target":396,"weight":100},
		           {"source":3,"target":0,"weight":100},
		           {"source":3,"target":397,"weight":100},
		           {"source":3,"target":398,"weight":100},
		           {"source":3,"target":399,"weight":100},
		           {"source":3,"target":241,"weight":100},
		           {"source":3,"target":400,"weight":100},
		           {"source":3,"target":401,"weight":100},
		           {"source":3,"target":402,"weight":100},
		           {"source":3,"target":403,"weight":100},
		           {"source":3,"target":404,"weight":100},
		           {"source":3,"target":405,"weight":100},
		           {"source":3,"target":406,"weight":100},
		           {"source":3,"target":63,"weight":100},
		           {"source":3,"target":105,"weight":100},
		           {"source":3,"target":407,"weight":100},
		           {"source":3,"target":408,"weight":100},
		           {"source":14,"target":275,"weight":100},
		           {"source":14,"target":276,"weight":100},
		           {"source":14,"target":409,"weight":100},
		           {"source":14,"target":278,"weight":100},
		           {"source":14,"target":410,"weight":100},
		           {"source":14,"target":411,"weight":100},
		           {"source":14,"target":412,"weight":100},
		           {"source":14,"target":0,"weight":100},
		           {"source":14,"target":75,"weight":100},
		           {"source":14,"target":413,"weight":100},
		           {"source":14,"target":284,"weight":100},
		           {"source":14,"target":414,"weight":100},
		           {"source":14,"target":415,"weight":100},
		           {"source":14,"target":416,"weight":100},
		           {"source":14,"target":417,"weight":100},
		           {"source":14,"target":418,"weight":100},
		           {"source":14,"target":286,"weight":100},
		           {"source":14,"target":419,"weight":100},
		           {"source":14,"target":420,"weight":100},
		           {"source":14,"target":421,"weight":100},
		           {"source":14,"target":422,"weight":100},
		           {"source":14,"target":423,"weight":100},
		           {"source":14,"target":290,"weight":100},
		           {"source":14,"target":291,"weight":100},
		           {"source":14,"target":292,"weight":100},
		           {"source":14,"target":424,"weight":100},
		           {"source":14,"target":293,"weight":100},
		           {"source":14,"target":425,"weight":100},
		           {"source":14,"target":426,"weight":100},
		           {"source":14,"target":427,"weight":100},
		           {"source":14,"target":428,"weight":100},
		           {"source":14,"target":297,"weight":100},
		           {"source":14,"target":429,"weight":100},
		           {"source":14,"target":430,"weight":100},
		           {"source":14,"target":431,"weight":100},
		           {"source":14,"target":432,"weight":100},
		           {"source":14,"target":433,"weight":100},
		           {"source":14,"target":434,"weight":100},
		           {"source":14,"target":435,"weight":100},
		           {"source":14,"target":301,"weight":100},
		           {"source":14,"target":387,"weight":100},
		           {"source":14,"target":436,"weight":100},
		           {"source":1,"target":437,"weight":100},
		           {"source":1,"target":438,"weight":100},
		           {"source":1,"target":439,"weight":100},
		           {"source":1,"target":440,"weight":100},
		           {"source":1,"target":441,"weight":100},
		           {"source":1,"target":442,"weight":100},
		           {"source":1,"target":443,"weight":100},
		           {"source":1,"target":444,"weight":100},
		           {"source":1,"target":445,"weight":100},
		           {"source":1,"target":446,"weight":100},
		           {"source":1,"target":447,"weight":100},
		           {"source":1,"target":448,"weight":100},
		           {"source":1,"target":449,"weight":100},
		           {"source":1,"target":450,"weight":100},
		           {"source":1,"target":451,"weight":100},
		           {"source":1,"target":452,"weight":100},
		           {"source":1,"target":453,"weight":100},
		           {"source":1,"target":454,"weight":100},
		           {"source":1,"target":455,"weight":100},
		           {"source":1,"target":456,"weight":100},
		           {"source":1,"target":457,"weight":100},
		           {"source":1,"target":458,"weight":100},
		           {"source":1,"target":459,"weight":100},
		           {"source":1,"target":460,"weight":100},
		           {"source":1,"target":461,"weight":100},
		           {"source":1,"target":462,"weight":100},
		           {"source":1,"target":463,"weight":100},
		           {"source":1,"target":464,"weight":100},
		           {"source":1,"target":465,"weight":100},
		           {"source":1,"target":466,"weight":100},
		           {"source":1,"target":467,"weight":100},
		           {"source":1,"target":468,"weight":100},
		           {"source":1,"target":469,"weight":100},
		           {"source":1,"target":470,"weight":100},
		           {"source":1,"target":471,"weight":100},
		           {"source":1,"target":472,"weight":100},
		           {"source":1,"target":473,"weight":100},
		           {"source":1,"target":474,"weight":100},
		           {"source":1,"target":475,"weight":100},
		           {"source":1,"target":476,"weight":100},
		           {"source":1,"target":477,"weight":100},
		           {"source":1,"target":478,"weight":100},
		           {"source":1,"target":479,"weight":100},
		           {"source":1,"target":480,"weight":100},
		           {"source":1,"target":481,"weight":100},
		           {"source":1,"target":482,"weight":100},
		           {"source":1,"target":483,"weight":100},
		           {"source":1,"target":484,"weight":100},
		           {"source":1,"target":485,"weight":100},
		           {"source":1,"target":486,"weight":100},
		           {"source":1,"target":487,"weight":100},
		           {"source":1,"target":488,"weight":100},
		           {"source":1,"target":489,"weight":100},
		           {"source":1,"target":490,"weight":100},
		           {"source":1,"target":0,"weight":100},
		           {"source":1,"target":491,"weight":100},
		           {"source":1,"target":492,"weight":100},
		           {"source":1,"target":493,"weight":100},
		           {"source":1,"target":494,"weight":100},
		           {"source":1,"target":495,"weight":100},
		           {"source":1,"target":496,"weight":100},
		           {"source":1,"target":497,"weight":100},
		           {"source":1,"target":498,"weight":100},
		           {"source":1,"target":499,"weight":100},
		           {"source":1,"target":500,"weight":100},
		           {"source":1,"target":501,"weight":100},
		           {"source":1,"target":502,"weight":100},
		           {"source":1,"target":503,"weight":100},
		           {"source":1,"target":504,"weight":100},
		           {"source":1,"target":505,"weight":100},
		           {"source":1,"target":506,"weight":100},
		           {"source":1,"target":507,"weight":100},
		           {"source":1,"target":508,"weight":100},
		           {"source":1,"target":509,"weight":100},
		           {"source":1,"target":510,"weight":100},
		           {"source":1,"target":511,"weight":100},
		           {"source":1,"target":512,"weight":100},
		           {"source":1,"target":513,"weight":100},
		           {"source":1,"target":514,"weight":100},
		           {"source":1,"target":515,"weight":100},
		           {"source":1,"target":516,"weight":100},
		           {"source":1,"target":517,"weight":100},
		           {"source":1,"target":518,"weight":100},
		           {"source":1,"target":519,"weight":100},
		           {"source":1,"target":520,"weight":100},
		           {"source":1,"target":521,"weight":100},
		           {"source":1,"target":522,"weight":100},
		           {"source":1,"target":523,"weight":100},
		           {"source":1,"target":524,"weight":100},
		           {"source":1,"target":525,"weight":100},
		           {"source":1,"target":526,"weight":100},
		           {"source":1,"target":527,"weight":100},
		           {"source":1,"target":528,"weight":100},
		           {"source":1,"target":529,"weight":100},
		           {"source":1,"target":530,"weight":100},
		           {"source":1,"target":531,"weight":100},
		           {"source":1,"target":532,"weight":100},
		           {"source":1,"target":533,"weight":100},
		           {"source":1,"target":534,"weight":100},
		           {"source":21,"target":535,"weight":100},
		           {"source":21,"target":536,"weight":100},
		           {"source":21,"target":537,"weight":100},
		           {"source":21,"target":538,"weight":100},
		           {"source":21,"target":539,"weight":100},
		           {"source":21,"target":540,"weight":100},
		           {"source":21,"target":541,"weight":100},
		           {"source":21,"target":542,"weight":100},
		           {"source":21,"target":543,"weight":100},
		           {"source":21,"target":544,"weight":100},
		           {"source":21,"target":545,"weight":100},
		           {"source":21,"target":546,"weight":100},
		           {"source":21,"target":547,"weight":100},
		           {"source":21,"target":548,"weight":100},
		           {"source":21,"target":549,"weight":100},
		           {"source":21,"target":550,"weight":100},
		           {"source":21,"target":551,"weight":100},
		           {"source":21,"target":552,"weight":100},
		           {"source":21,"target":553,"weight":100},
		           {"source":21,"target":554,"weight":100},
		           {"source":21,"target":555,"weight":100},
		           {"source":21,"target":0,"weight":100},
		           {"source":21,"target":556,"weight":100},
		           {"source":21,"target":557,"weight":100},
		           {"source":21,"target":558,"weight":100},
		           {"source":21,"target":559,"weight":100},
		           {"source":21,"target":560,"weight":100},
		           {"source":21,"target":561,"weight":100},
		           {"source":21,"target":562,"weight":100},
		           {"source":21,"target":563,"weight":100},
		           {"source":21,"target":564,"weight":100},
		           {"source":21,"target":565,"weight":100},
		           {"source":21,"target":566,"weight":100},
		           {"source":21,"target":567,"weight":100},
		           {"source":21,"target":568,"weight":100},
		           {"source":21,"target":569,"weight":100},
		           {"source":21,"target":570,"weight":100},
		           {"source":21,"target":571,"weight":100},
		           {"source":21,"target":572,"weight":100},
		           {"source":21,"target":573,"weight":100},
		           {"source":21,"target":574,"weight":100},
		           {"source":21,"target":575,"weight":100},
		           {"source":21,"target":576,"weight":100},
		           {"source":21,"target":577,"weight":100},
		           {"source":21,"target":578,"weight":100},
		           {"source":21,"target":579,"weight":100},
		           {"source":21,"target":580,"weight":100},
		           {"source":21,"target":581,"weight":100},
		           {"source":21,"target":582,"weight":100},
		           {"source":21,"target":583,"weight":100},
		           {"source":21,"target":584,"weight":100},
		           {"source":21,"target":585,"weight":100},
		           {"source":21,"target":586,"weight":100},
		           {"source":21,"target":587,"weight":100},
		           {"source":21,"target":588,"weight":100},
		           {"source":21,"target":589,"weight":100},
		           {"source":21,"target":590,"weight":100},
		           {"source":21,"target":591,"weight":100},
		           {"source":21,"target":592,"weight":100},
		           {"source":21,"target":593,"weight":100},
		           {"source":21,"target":594,"weight":100},
		           {"source":21,"target":595,"weight":100},
		           {"source":21,"target":596,"weight":100},
		           {"source":21,"target":597,"weight":100},
		           {"source":21,"target":598,"weight":100},
		           {"source":21,"target":599,"weight":100},
		           {"source":21,"target":600,"weight":100},
		           {"source":21,"target":601,"weight":100},
		           {"source":21,"target":602,"weight":100},
		           {"source":21,"target":603,"weight":100},
		           {"source":21,"target":604,"weight":100},
		           {"source":21,"target":605,"weight":100},
		           {"source":21,"target":606,"weight":100},
		           {"source":21,"target":607,"weight":100},
		           {"source":21,"target":608,"weight":100},
		           {"source":21,"target":609,"weight":100},
		           {"source":21,"target":610,"weight":100}]
}