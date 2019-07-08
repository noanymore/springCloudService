<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container" style="margin-bottom: 30px;">
	开始时间<input class="jcDate" id="begin"
		style="margin-left: 30px;width: 220px; height: 30px; line-height: 20px; padding: 4px;" />
	结束时间<input class="jcDate" id="end"
		style="margin-left: 30px;margin-right: 30px;width: 220px; height: 30px; line-height: 20px; padding: 4px;" />
	选择工作室<select class="form-control" id="studio" name="studio" style="margin-left: 10px;display: inline-block; width: 170px;">
					<option  value="-1">全部
			</select>
		
	<button id="search" class="search btn btn-primary">搜索</button>
	<button id=export class="export btn btn-primary">导出</button>
</div>
<table class="table table-bordered" style="width: 75%; margin: auto; margin-bottom: 50px" >
	<thead>
		<tr>
			<th style="width: 30%">任务</th>
			<th>工作室</th>
			<th>完成时间</th>
			<th>结算时间</th>
			<th>题目数</th>
			<th>字数</th>
			<th>金额</th>
		</tr>
	</thead>
	<tbody id="tbody">
		
	</tbody>
</table>

<div class="col-md-8"></div>

<div class="col-md-4" id="paging">
	第${page }页/<span id="allPage"></span>
	<input type="text" id="toPageCount" style="width: 100px"> 
	<a id="toPage" href="#">跳页</a>
	<button class="btn btn-default" id="lastPage">上一页</button>
	<button class="btn btn-default" id="nextPage">下一页</button>
</div>
<style>
.search{
	margin-left: 40px;
}
.export{
	margin-left: 30px;
}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/xiaobei/date/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/xiaobei/date/js/jQuery-jcDate.js"></script>
<script type="text/javascript">
$(function (){
	$("input.jcDate").jcDate({					       
		IcoClass : "jcDateIco",
		Event : "click",
		Speed : 100,
		Left : 0,
		Top : 28,
		format : "-",
		Timeout : 100
	});
	
	loadTable();
	findAllPage();
	loadStudio();
});

function findAllPage(){
	$.ajax({
		type : "post",
		data : {},
		url : "${pageContext.request.contextPath }/admins/findAllPage",
		success : function(data){
			$("#allPage").text(data);
		},
		dataType : "json"
	});
}



function loadTable(){
	var page = ${page};
	$.ajax({
		type : "post",
		data : {"page":page},
		url : "${pageContext.request.contextPath }/admins/findBill",
		success : function(data){
			createTable(data);
		},
		dataType : "json"
	});
}


function createTable(data){
	$.each(data,function(i,item){
		var tr = $("<tr></tr>");
		var td1 = $("<td></td>");
		td1.text(item.taskName);
		var td2 = $("<td></td>");
		td2.text(item.finishTime);
		var td3 = $("<td></td>");
		td3.text(item.settlementTime);
		var td4 = $("<td></td>");
		td4.text(item.questionNum);
		var td5 = $("<td></td>");
		td5.text(item.wordNumber);
		var td6 = $("<td></td>");
		td6.text(item.account);
		var td7 = $("<td></td>");
		td7.text(item.studioName);
		tr.append(td1);
		tr.append(td7)
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		tr.append(td5);
		tr.append(td6);
		$("#tbody").append(tr);
	});
}


$("#lastPage").click(function(){
	var nowPage = ${page};
	if(nowPage==1){
		alert("已经是第一页");
		return false;
	}
	var page =  nowPage - 1;
	window.location.href = "${pageContext.request.contextPath }/admins/toPage?page="+page;
});
$("#nextPage").click(function(){
	var nowPage = ${page};
	var allPage = $("#allPage").text();
	if(nowPage==allPage){
		alert("已经是最后一页");
		return false;
	}
	var page =  nowPage + 1;
	window.location.href = "${pageContext.request.contextPath }/admins/toPage?page="+page;
});

$("#toPage").click(function(){
	var page = $("#toPageCount").val();
	var allPage = $("#allPage").text();
	if(page<1||page>allPage){
		alert("输入页码错误");
		return false;
	}
	window.location.href = "${pageContext.request.contextPath }/admins/toPage?page="+page;
});

$("#search").click(function(){
	var begin = $("#begin").val();
	var end = $("#end").val();
	var studio = $("#studio").val();
	$("#tbody").children().remove();
	$("#paging").remove();
	$.ajax({
		type : "post",
		data : {"begin":begin,"end":end,"studioId":studio},
		url : "${pageContext.request.contextPath }/admins/findBillByTime",
		success : function(data){
			createTable(data);
		},
		dataType : "json"
	});
});

//导出
$("#export").click(function(){
	var trs = $("#tbody").children("tr");
	var accounts = [];
	$.each(trs,function(i,tr){
		console.info(tr);
		var tds = $(tr).children();
		var account = {};
		account.taskName= tds.eq(0).text();
		account.finishTime= tds.eq(2).text();
		account.settlementTime= tds.eq(3).text();
		account.questionNum= tds.eq(4).text();
		account.wordNumber= tds.eq(5).text();
		account.account= tds.eq(6).text();
		account.studioName= tds.eq(1).text();
		accounts.push(account);
	});
	var json = JSON.stringify(accounts);
	$.ajax({
		type : "post",
		data : {"json":json},
		url : "${pageContext.request.contextPath }/admins/export",
		success : function(data){
			window.location.href="${pageContext.request.contextPath }/admins/download?json="+data;
		},
		error : function(data){
			alert("导出失败");
		},
		//dataType : "json"
	});
	
	
});

function loadStudio(){
	$.ajax({
		type : "post",
		data : {},
		url : "${pageContext.request.contextPath }/admins/findAllStudio",
		success : function(data){
			var select = $("#studio");
			$.each(data,function(i,studio){
				var option = $("<option></option>");
				option.text(studio.studioName);
				option.val(studio.id);
				select.append(option);
			});
		},
		dataType : "json"
	});
}


</script>

<style type="text/css">
*{margin:0;padding:0;font-size:16px;color:#555555;list-style:none;} 
.clear{clear:both;height:0;visibility:hidden;}
a{text-decoration:none;cursor:pointer;}
a:hover{color:red;text-decoration:underline;}
a img{border:0;vertical-align:middle;}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/xiaobei/date/css/jcDate.css" media="all" />
