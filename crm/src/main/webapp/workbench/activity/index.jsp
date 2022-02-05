<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>"/>
	<title>市场活动</title>
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
	//页面初始化
	$(function(){
        pageList(1,2);
		//为创建按钮添加鼠标单击事件
		$("#addBtn").click(function() {
			//alert("123");
			//引入日期插件
			$(".time").datetimepicker({
				minView: "month",
				language:  'zh-CN',
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left"
			});
			//发送ajax请求，查询所有的市场活动所有人
			$.ajax({
				url : "workbench/activity/getUserList.do",
				type : "get",
				dataType : "json",
				success : function(data) {

					var html = "<option></option>";
					$.each(data,function(i, n) {
						html += "<option value='" + n.id + "'>" + n.name + "</option>";
					})
					$("#create-owner").html(html);
					//打开模态窗口
					//默认选中所有者是当前登录用户
					$("#create-owner").val("${user.id}");
					//打开模态窗口
					$("#createActivityModal").modal("show");
				}
			});
		});
		//为保存按钮添加鼠标单击事件
		$("#saveBtn").click(function() {
			//发送ajax请求，将前端获取到的数据发送到后端处理
			$.ajax({
				url : "workbench/activity/save.do",
				data : {
					"owner" : $.trim($("#create-owner").val()),
					"startDate" : $.trim($("#create-startDate").val()),
					"endDate" :  $.trim($("#create-endDate").val()),
					"cost" :  $.trim($("#create-cost").val()),
					"name" :  $.trim($("#create-name").val()),
					"description" :  $.trim($("#create-description").val())
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					/**
					 * data	{"success" : true/false}
					 */
					if (data.success) {
						//刷新市场活动列表
                        pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						//通过id获取表单对象
						//将模态窗口内容清空
						$("#addActivityForm")[0].reset();
						//关闭模态窗口
						$("#createActivityModal").modal("hide");
					} else {
						alert("市场活动添加失败");
					}
				}
			});

        });
		//pageList(1,2);
		//为查询按钮添加鼠标单击事件
		$("#search-submitBtn").click(function() {
			//在查询操作时，将搜索框中的信息保存起来，保存到隐藏域中
			$("#hidden-name").val($.trim($("#search-name").val()));
			$("#hidden-owner").val($.trim($("#search-owner").val()));
			$("#hidden-starDate").val($.trim($("#search-startDate").val()));
			$("#hidden-endDate").val($.trim($("#search-endDate").val()));
			//刷新市场活动列表
			//pageList(1,2);
            pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

        });

		//为全选框绑定一个鼠标单击事件
		$("#select-all").click(function() {
			$(".online").prop("checked",this.checked);
		});
		//为单选框绑定鼠标单击事件
        $("#activityBody").on("click", $(".online"), function() {
            //alert(123);
            $("#select-all").prop("checked", $("input[class = online]").length == $("input[class = online]:checked").length);
        });
        //给删除按钮绑定鼠标单击事件
        $("#deleteBtn").click(function() {
            //通过id删除相关信息，获取用户所选删除项的id，此值可能为零、有一个或者多个
            var $onlines = $("input[class = online]:checked");
            //遍历数组
            if ($onlines.length == 0) {
                alert("请选择你要删除的项");
            } else {
                if (confirm("你确定要删除选中项吗？")) {
                    //用来封装用户选择删除框的id
                    var param = '';
                    for (var i = 0; i < $onlines.length; i ++) {
                        param += 'id=' + $($onlines[i]).val();
                        if (i < $onlines.length - 1) {
                            param += '&';
                        }
                    }
                    //alert(param);
                    //发送ajax请求，通过id删除市场活动条数
                    $.ajax({
                        url : "workbench/activity/delete.do",
                        data : param,
                        type : "post",
                        dataType : "json",
                        success : function(data) {
                            /**
                             *此时返回的json串应该是一个标志，操作成功或者失败
                             * {"success" : true/false}
                             */
                            if (data.success) {
                                //删除操作完成后，刷新市场活动列表
                                //pageList(1, 2);
                                pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
                                    ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                            } else {
                                alert("市场活动记录删除失败。");
                            }
                        }
                    });
                }
            }

        });
        //给修改按钮添加鼠标单击事件
        $("#editBtn").click(function() {
            //获取用户要修改的记录条数
            var $xz = $("input[class = online]:checked");
            //选择要修改的条数不能为零
            if ($xz.length == 0) {
                alert("请选择你要修改的记录");
            } else if ($xz.length > 1) {
                alert("一次只能修改一条记录");
            } else {
            	var id = $xz.val();
                //发送ajax请求，获取用户列表信息和市场活动列表
				$.ajax({
					url : "workbench/activity/edit.do",
					data : {
						"id" : id
					},
					type :"get",
					dataType : "json",
					success : function(data) {
						/**
						 * 此处返回的json串为{"userList" : [{用户1},{用户2},...{}], "a" : Activity}
						 */
						//打开修改模态窗口
						var html = "<option></option>";
						$.each(data.userList, function(i, n) {
							html += "<option value='"+ n.id +"'>"+ n.name +"</option>";
						});
						//给下拉列表赋值
						$("#edit-owner").html(html);
						//处理单条Activity
						$("#edit-id").val(data.a.id);
						$("#edit-owner").val(data.a.owner);
						$("#edit-name").val(data.a.name);
						$("#edit-startDate").val(data.a.startDate);
						$("#edit-endDate").val(data.a.endDate);
						$("#edit-cost").val(data.a.cost);
						$("#edit-description").val(data.a.description);
					}
				});
				//打开修改模态窗口
				$("#editActivityModal").modal("show");
            }
        });
        //给更新按钮添加鼠标单击事件
		$("#updateBtn").click(function() {
			//发送ajax请求，将前端获取到的数据发送到后端处理
			$.ajax({
				url: "workbench/activity/update.do",
				data: {
					"id": $.trim($("#edit-id").val()),
					"owner": $.trim($("#edit-owner").val()),
					"startDate": $.trim($("#edit-startDate").val()),
					"endDate": $.trim($("#edit-endDate").val()),
					"cost": $.trim($("#edit-cost").val()),
					"name": $.trim($("#edit-name").val()),
					"description": $.trim($("#edit-description").val())
				},
				type: "post",
				dataType: "json",
				success: function (data) {
					/**
					 * data    {"success" : true/false}
					 */
					if (data.success) {
						//刷新市场活动列表
						//pageList(1, 2);
                        pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
                            ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                        //关闭模态窗口
						$("#editActivityModal").modal("hide");
					} else {
						alert("市场活动修改失败");
					}
				}
			});
		});
	});
	//提供一个局部刷新市场活动信息的方法
	function pageList(pageNo, pageSize) {
	    //每次刷新页面时，将全选框中的√去掉
        $("#select-all").prop("checked", false);
		//查询前，将隐藏域中的信息取出来，重新赋予到搜索框中
		$("#search-name").val($.trim($("#hidden-name").val()));
		$("#search-owner").val($.trim($("#hidden-owner").val()));
		$("#search-starDate").val($.trim($("#hidden-startDate").val()));
		$("#search-endDate").val($.trim($("#hidden-endDate").val()));
		 //alert(123);
		//发送ajax请求，局部刷新市场活动信息
		$.ajax({
			"url" : "workbench/activity/pageList.do",
			data : {
				"pageNo" : pageNo,
				"pageSize" : pageSize,
				"name" : $.trim($("#search-name").val()),
				"owner" : $.trim($("#search-owner").val()),
				"startDate" : $.trim($("#search-startDate").val()),
				"endDate" : $.trim($("#search-endDate").val())
			},
			type : "get",
			dataType : "json",
			success : function(data) {
				//alert(data);
				/**
				 * data	: {"total" : ?, "pageList" : [{市场活动对象1}, {}， {}，.......]}
				 * total : 总的记录条数
				 * pageList	: 市场活动对象集合
				 */
				var html = '';
				$.each(data.pageList,function(i, n) {
					html += '<tr class="active">';
					html += '<td><input type="checkbox" class="online" value="'+ n.id +'" /></td>';
					html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/activity/detail.do?id='+ n.id +'\';">'+ n.name +'</a></td>';
					html += '<td>'+ n.owner +'</td>';
					html += '<td>'+ n.startDate +'</td>';
					html += '<td>'+ n.endDate +'</td>';
					html += '</tr>';
				});
				$("#activityBody").html(html);
				//调用分页插件
				var totalPages = data.total%pageSize == 0?data.total/pageSize:parseInt(data.total/pageSize) + 1;
				$("#activityPage").bs_pagination({
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
	<!--加入隐藏域，解决分页查询bug()-->
	<input type="hidden" id="hidden-name"/>
	<input type="hidden" id="hidden-owner"/>
	<input type="hidden" id="hidden-starDate"/>
	<input type="hidden" id="hidden-endDate"/>
	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="addActivityForm">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly>
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					    <input type="hidden" id="edit-id"/>
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" >
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startDate" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endDate" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="search-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="search-submitBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus" ></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="select-all"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">

					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage">

				</div>
			</div>
		</div>
		
	</div>
</body>
</html>