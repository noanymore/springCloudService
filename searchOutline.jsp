<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">

	<div style="padding: 10px 00px 10px;">
			<div class="col-lg-6"></div>
			<div class="col-lg-6">
				<div class="input-group">
					<input type="text" class="form-control"> 
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="findOutline">搜索大纲</button>
					</span>
				</div>
			</div>

	</div>
		<!-- 大纲 -->
	<div class="panel panel-primary" style="margin-top: 50px;">
		<div class="panel-heading">
			<h3 class="panel-title">大纲</h3>
		</div>
		<div class="panel-body">
			<div class="col-md-1"></div>
			<div id="outlineContent" class="col-md-11"></div>
		</div>
	</div>
</div>

<style type="text/css">
.span_style{
	font-size: 20px;
}
.div_style{
	clear: both;
	padding-bottom: 50px;
}

.son_div_style{
	clear: both;
	padding-top: 30px;
	padding-left: 80px;
}
.add_color{
	color: black;
	font-size: 25px;
	padding-right: 50px;
}
</style>

<script type="text/javascript">
$(function(){
	load();
});

function load(){
	loadOutline();
}

function loadOutline(){
	$.ajax({
		type : "post",
		data : {},
		url : "${pageContext.request.contextPath}/smallAdmin/findAllOutline1",
		success : function(data){
			createOutline(data);
		},
		dataType : "json"
	});
}

function createOutline(outlines){
	$.each(outlines,function(i,outline){
		var div = $("<div></div>");
		div.addClass("div_style");
		var div1 = $("<div></div>");
		var div2 = $("<div></div>");
		var div3 = $("<div></div>");
		div1.addClass("col-md-6");
		div2.addClass("col-md-3");
		div3.addClass("col-md-3");
		div.append(div1);
		div.append(div2);
		div.append(div3);
		var outlineName = $("<span></span>");
		outlineName.text(outline.outlineName);
		outlineName.addClass("span_style");
		var span1 = $("<span>----------------------</span>");
		var span2 = $("<a>查看</a>");
		//添加事件
		span2.click(function(){
			var id = $(this).attr("id");
			var divs = $(this).parent().nextAll();
			if(divs.length==0){
				look(id,div);
			}else{
				divs.remove();
			}
		});
		
		span2.attr("id",outline.outlineId);
		outlineName.addClass("span_style");
		span1.addClass("span_style");
		span2.addClass("span_style");
		div1.append(outlineName);
		div2.append(span1);
		div3.append(span2);
		$("#outlineContent").append(div);
	});
}

//查找子大纲
function look(id,div){
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/smallAdmin/findSectionById1",
		data : {"id":id},
		success : function(outlineTrees){
			createOutlineTree(outlineTrees,div);
		},
		dataType : "json"
	});
}

function createOutlineTree(outlineTrees,fatherDiv){
	$.each(outlineTrees,function(i,outlineTree){
		var div = $("<div></div>");
		var div1 = $("<div></div>");
		var div2 = $("<div></div>");
		div1.addClass("col-md-8");
		div2.addClass("col-md-4");
		div.append(div1);
		div.append(div2);
		div.addClass("son_div_style");
		var outlineName = $("<span></span>");
		var add = $("<span></span>");
		add.attr("id",outlineTree.id);
		add.addClass("add_color");
		add.addClass("glyphicon");
		add.addClass("glyphicon-plus-sign");
		var del = $("<span></span>");
		del.attr("id",outlineTree.id);
		del.addClass("add_color");
		del.addClass("glyphicon");
		del.addClass("glyphicon-minus-sign");
		//添加事件
		add.click(function(){
			var id = $(this).attr("id");
			addBrother($(this),id);
		});
		//添加事件
		del.click(function(){
			var id = $(this).attr("id");
			if(confirm("确认删除？")){
				removeSection($(this),id);
			}
		});
		outlineName.text(outlineTree.name);
		outlineName.addClass("span_style");
		div1.append(outlineName);
		div2.append(add);
		div2.append(del);
		fatherDiv.append(div);
	});
};

function addBrother(brother,id){
	var div = $("<div></div>");
	div.addClass("son_div_style");
	var div1 = $("<div></div>");
	var div2 = $("<div></div>");
	div1.addClass("col-md-8");
	div2.addClass("col-md-4");
	div.append(div1);
	div.append(div2);
	var outlineName = $("<input></input>");
	var add = $("<button>"+"确定"+"</button>");
	//添加事件
	add.click(function(){
		addOutlineTree($(this),id);
	});
	outlineName.addClass("span_style");
	div1.append(outlineName);
	div2.append(add);
	brother.parent("div").parent("div").after(div);
}

//添加
function addOutlineTree(add,id){
	var name = add.parent().prev().children().val();
	$.ajax({
		type : "post",
		data : {"name":name,"id":id},
		url : "${pageContext.request.contextPath}/smallAdmin/addSection",
		success : function(data){
			add.parent().prev().children().remove();
			var outlineName = $("<span></span>");
			outlineName.text(name);
			outlineName.addClass("span_style");
			add.parent().prev().append(outlineName);
			add.remove();
		},
		dataType : "json"
	});
}

//移除
function removeSection(span,id){
	$.ajax({
		type : "post",
		data : {"id":id},
		url : "${pageContext.request.contextPath}/smallAdmin/deleteSection",
		success : function(data){
			if(data=="删除成功"){
				span.parent().parent().remove();
			}
			alert(data);
		},
		dataType : "json"
	});
}

//搜索大纲
$("#findOutline").click(function(){
	$("#outlineContent").children().remove();
	var name = $("#findOutline").parent().prev().val();
	$.ajax({
		type : "post",
		data : {"name":name},
		url : "${pageContext.request.contextPath}/smallAdmin/findOutlineByName",
		success : function(data){
			if(data!=null&&data!=""){
				createOutline1(data);
			}
		},
		dataType : "json"
	});
});


function createOutline1(outline){
	var div = $("<div></div>");
	div.addClass("div_style");
	var div1 = $("<div></div>");
	var div2 = $("<div></div>");
	var div3 = $("<div></div>");
	div1.addClass("col-md-6");
	div2.addClass("col-md-3");
	div3.addClass("col-md-3");
	div.append(div1);
	div.append(div2);
	div.append(div3);
	var outlineName = $("<span></span>");
	outlineName.text(outline.outlineName);
	outlineName.addClass("span_style");
	var span1 = $("<span>----------------------</span>");
	var span2 = $("<a>查看</a>");
	//添加事件
	span2.click(function(){
		var id = $(this).attr("id");
		var divs = $(this).parent().nextAll();
		if(divs.length==0){
			look(id,div);
		}else{
			divs.remove();
		}
	});
	
	span2.attr("id",outline.outlineId);
	outlineName.addClass("span_style");
	span1.addClass("span_style");
	span2.addClass("span_style");
	div1.append(outlineName);
	div2.append(span1);
	div3.append(span2);
	$("#outlineContent").append(div);
}

</script>