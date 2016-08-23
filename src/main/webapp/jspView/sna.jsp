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
<link type="text/css" href="static/css/layout.css" rel="stylesheet">
<style type="text/css">
</style>
<title>Huiyu.SNA</title>
<script type="text/javascript" src="static/js/jquery-2.2.1.min.js"></script>  
<script type="text/javascript" src="static/js/md5.js"></script>   
<script type="text/javascript" src="static/js/mydef.js"></script>   
<script type="text/javascript" src="static/js/HY_data_vis.js"></script>  
<script> 

// var width = 1900;
// var height = 1060;
var width = 2048;
var height = 1152;
var svg = null;
function  doRefresh(){  
	
	if(null == svg){
		 svg = d3.select("body")
				.append("svg")
				.attr("width",width)
				.attr("height",height)
				.call(d3.behavior.zoom()
				.on("zoom", function () {
							svg.attr("transform",  " scale(" + d3.event.scale + ")")
							} 
				))
				.append("g");
	}
	else{ 
		d3.select('svg').remove();
		 svg = d3.select("body")
			.append("svg")
			.attr("width",width)
			.attr("height",height)
			.call(d3.behavior
					.zoom()
					.on("zoom", function () {
								svg.attr("transform",  " scale(" + d3.event.scale + ")")
								}
			))
			.append("g");		
	}
		
// 	var svg = d3.select("body")
// 				.append("svg")
// 				.attr("width",width)
// 				.attr("height",height);
				
	
	var force = d3.layout.force()
			.nodes(nodes)
			.links(edges)
			.size([width,height])
			.linkDistance(function(d) { return  d.weight; })	
			.charge(-400);
            
	force.start();
	
	console.log('refresh,nodes.size='+nodes.length);
// 	console.log(JSON.stringify(nodes));
// 	console.log(JSON.stringify(edges));
	
	
	var svg_edges = svg.selectAll("line")
						.data(edges)
						.enter()
						.append("line")
						.style("stroke","#ccc")
						.style("stroke-width",0.5);
	
	var color = d3.scale.category20c();
			
	
	var svg_nodes = svg.selectAll("circle")
						.data(nodes)
						.enter()
						.append("circle")
						
                        .attr("r", function(d) { return d.radius })
						.style("fill",function(d) { return color(d.group); })
						.call(force.drag);

	
	var svg_texts = svg.selectAll("text")
						.data(nodes)
						.enter()
						.append("text")
						.style("fill", "white")
                        .style("font-weight", "bold")
                        .style("font-size",function(d) { return d.radius-30 })
						.attr("dx", -15)
						.attr("dy", 5)
						.text(function(d){
							return d.name;
						});
				

	force.on("tick", function(){
	
		 svg_edges.attr("x1",function(d){ return d.source.x; })
		 		.attr("y1",function(d){ return d.source.y; })
		 		.attr("x2",function(d){ return d.target.x; })
		 		.attr("y2",function(d){ return d.target.y; });
		 
		
		 svg_nodes.attr("cx",function(d){ return d.x; })
		 		.attr("cy",function(d){ return d.y; });

		 
		 svg_texts.attr("x", function(d){ return d.x; })
		 	.attr("y", function(d){ return d.y; });
	});
}
</script>
</head>
<body>
	<div class="container">	 
		<div class="inputCondition">
			<input type="button" value="查询" name="search" id="search">
			<input type="text" maxlength="15" name="qQq" id="qQq" placeholder="输入QQ号" >
		</div>
	</div>  
</body>
</html>