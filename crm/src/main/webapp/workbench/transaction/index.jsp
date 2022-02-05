<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>"/>
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

	<link href="jquery\bs_pagination\jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="jquery\bs_pagination\jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery\bs_pagination\en.js"></script>
<script type="text/javascript">

	$(function(){
		//页面加载完毕后刷新交易列表
		pageList(1, 2);
		//给查询按钮绑定鼠标单击事件
		$("#query-Btn").click(function() {
			$("#hid-owner").val($.trim($("#query-owner").val()));
			$("#hid-name").val($.trim($("#query-name").val()));
			$("#hid-customerName").val($.trim($("#query-customerName").val()));
			$("#hid-stage").val($.trim($("#query-stage").val()));
			$("#hid-type").val($.trim($("#query-type").val()));
			$("#hid-source").val($.trim($("#query-source").val()));
			$("#hid-contactsName").val($.trim($("#query-contactsName").val()));
			//pageList(1, 2);
			pageList($("#tranPage").bs_pagination('getOption', 'currentPage')
					,$("#tranPage").bs_pagination('getOption', 'rowsPerPage'));
		});
	});
	//刷新交易操作
	function pageList(pageNo, pageSize) {
		$("#query-owner").val($.trim($("#hid-owner").val()));
		$("#query-name").val($.trim($("#hid-name").val()));
		$("#query-customerName").val($.trim($("#hid-customerName").val()));
		$("#query-stage").val($.trim($("#hid-stage").val()));
		$("#query-type").val($.trim($("#hid-type").val()));
		$("#query-source").val($.trim($("#hid-source").val()));
		$("#query-contactsName").val($.trim($("#hid-contactsName").val()));
		//发送ajax请求，刷新交易列表
		$.ajax({
			url : "workbench/transaction/pageList.do",
			data : {
				"pageNo" : pageNo,
				"pageSize" : pageSize,
				"owner" : $.trim($("#query-owner").val()),
				"name" : $.trim($("#query-name").val()),
				"customerName" : $.trim($("#query-customerName").val()),
				"stage" : $.trim($("#query-stage").val()),
				"type" : $.trim($("#query-type").val()),
				"source" : $.trim($("#query-source").val()),
				"contactsName" : $.trim($("#query-contactsName").val())
			},
			type : "get",
			dataType : "json",
			success : function(data) {
				/**
				 * 此时返回的json格式为{"total":total,"tList" : [{交易1},{}...{n}]}
				 */
				var html = '';
				$.each(data.pageList, function(i, n) {
					html += '<tr class="active">';
					html += '<td><input type="checkbox" id="'+n.id+'"/></td>';
					html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/transaction/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
					html += '<td>'+n.customerId+'</td>';
					html += '<td>'+n.stage+'</td>';
					html += '<td>'+n.type+'</td>';
					html += '<td>'+n.owner+'</td>';
					html += '<td>'+n.source+'</td>';
					html += '<td>'+n.contactsId+'</td>';
					html += '</tr>';
				})
				$("#tranBody").html(html);
				//调用分页插件
				var totalPages = data.total%pageSize == 0?data.total/pageSize:parseInt(data.total/pageSize) + 1;
				$("#tranPage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: data.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,
					//该回调函数在点击分页组件时调用
					onChangePage : function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});

			}
		});
	}
</script>
</head>
<body>

	<!--隐藏域，用来保存搜索框中的值-->
	<input type="hidden" id="hid-owner"/>
	<input type="hidden" id="hid-name"/>
	<input type="hidden" id="hid-customerName"/>
	<input type="hidden" id="hid-stage"/>
	<input type="hidden" id="hid-type"/>
	<input type="hidden" id="hid-source"/>
	<input type="hidden" id="hid-contactsName"/>
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>交易列表</h3>
			</div>
		</div>
	</div>
	
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
	
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="query-owner">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="query-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">客户名称</div>
				      <input class="form-control" type="text" id="query-customerName">
				    </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">阶段</div>
					  <select class="form-control" id="query-stage">
					  	<option></option>
					  	<c:forEach items="${stageList}" var="s">
							<option value="${s.value}">${s.text}</option>
						</c:forEach>
					  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">类型</div>
					  <select class="form-control" id="query-type">
					  	<option></option>
					  	<option>已有业务</option>
					  	<option>新业务</option>
					  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">来源</div>
				      <select class="form-control" id="query-source">
						  <option></option>
						  <c:forEach items="${sourceList}" var="s">
							  <option value="${s.value}">${s.text}</option>
						  </c:forEach>
						</select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">联系人名称</div>
				      <input class="form-control" type="text" id="query-contactsName">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="query-Btn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" onclick="window.location.href='workbench/transaction/add.do';"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" onclick="window.location.href='workbench/transaction/edit.html';"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>名称</td>
							<td>客户名称</td>
							<td>阶段</td>
							<td>类型</td>
							<td>所有者</td>
							<td>来源</td>
							<td>联系人名称</td>
						</tr>
					</thead>
					<tbody id="tranBody">
<%--                        <tr class="active">--%>
<%--                            <td><input type="checkbox" /></td>--%>
<%--                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">动力节点-交易01</a></td>--%>
<%--                            <td>动力节点</td>--%>
<%--                            <td>谈判/复审</td>--%>
<%--                            <td>新业务</td>--%>
<%--                            <td>zhangsan</td>--%>
<%--                            <td>广告</td>--%>
<%--                            <td>李四</td>--%>
<%--                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 20px;">
				<div id="tranPage">

				</div>

			</div>
			
		</div>
		
	</div>
</body>
</html>