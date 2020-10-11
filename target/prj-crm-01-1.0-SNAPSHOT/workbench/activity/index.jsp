<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">

	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<%--分页插件--%>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>
	<script type="text/javascript">

		$(function(){

			//加载日历控件
			$(".time").datetimepicker({
				minView: "month",
				language:  'zh-CN',
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left"
			});

			$("#createBtn").click(function () {

				//提交ajax请求，获取用户信息，为所有者下拉框铺值
				$.ajax({
					url: "workbench/activity/getUserList.do",
					type: "get",
					dataType: "json",
					success: function (data) {
						//展示返回的json数组
						var html = "";
						$.each(data, function (i, n) {
							html += "<option value='" + n.id + "'>" + n.name + "</option>";
						})
						$("#create-owner").html(html);
						////将所有者框中默认选中的是“当前登录用户"，从session中取
						// js代码中使用el表达式需要用双引号括起来
						var id = "${sessionScope.user.id}";
						//根据value设置下拉框的值
						$("#create-owner").val(id);
						$("#createActivityModal").modal("show");

					}
				})
			})


			//点击保存按钮，将数据持久化
			$("#saveBtn").click(function () {
				//ajax请求保存表单数据
				$.ajax({
					url: "workbench/activity/save.do",
					data: {
						owner : $("#create-owner").val(),
						name :$("#create-name").val(),
						startDate : $("#create-startDate").val(),
						endDate : $("#create-endDate").val(),
						cost : $("#create-cost").val(),
						description : $("#create-description").val()
					},
					type: "get",
					dataType: "json",
					success: function (data) {
						if(data.success){
							//清空表单内容
							// $("#activityForm").get(0).reset();
							pageList(1,3);
							$("#createActivityModal").modal("hide");
						}
						else {
							alert("保存失败");
						}
					}
				})
			})
			//页面加载完执行
			pageList(1,3)

			//为查询按钮绑定事件
			$("#searchBtn").click(function () {
				//将查询条件保存在隐藏域中，在分页查询时候将隐藏域的值设置到参数框中。
				//点击查询按钮时候，将查询条件保存到隐藏域
				$("#hidden-name").val($("#serach-name").val());
                $("#hidden-owner").val($("#serach-owner").val());
                $("#hidden-startDate").val($("#serach-startDate").val());
                $("#hidden-endDate").val($("#search-endDate").val());
                // alert("隐藏域:" + $("#hidden-name").val());
                // alert("隐藏域:" + $("#hidden-owner").val());
                // alert("隐藏域:" + $("#hidden-startDate").val());
                // alert("隐藏域:" + $("#hidden-endDate").val());
				pageList(1,3);
			})

			//为全选按钮绑定事件
			$("#qx").click(function () {
				//全选框改动时候，
				$("input[name='xz']").prop("checked", this.checked);
			})

			//为单选按钮绑定事件
			$("#activityBody").on("click", $("input[name=xz]"), function () {
				$("#qx").prop("checked", $("input[name='xz']:checked").size() == $("input[name='xz']").size())
			})
			//为修改按钮绑定事件 执行单个删除或批量删除
			$("#deleteBtn").click(function () {
				//获取选中按钮数量
				var num = $("input[name='xz']:checked").size();
				if (num == 0) {
					alert("请选择删除项");
					return ;
				}
				//执行到这里说明删除项已经勾选（>=1）
				//拼接发送数据 id=12312313&id=12132123
				var str = "";
				var $xz = $("input[name='xz']:checked");
				for (var i = 0; i < $xz.size(); i++){
					str += "id=";
					str += $xz.get(i).value;
					if (i < $xz.size() - 1) {
						str += "&";
					}
				}
				if (window.confirm("您确定要删除吗？")){
					$.ajax({
						url: "workbench/activity/delete.do",
						data: str,
						type: "post",
						dataType: "json",
						success: function (data) {
							if (data.success){
								alert("删除成功");
								pageList(1, 3);
							}
							else{
								alert("删除失败")
							}
						}
					})
				}
			})

			//为修改按钮绑定事件
			$("#editBtn").click(function () {
				//1. 进行判断，0条或多条不能修改，只有单条能进行 修改
				var num = $("input[name=xz]:checked").size();
				if (num == 0){
					alert("请选择要修改的活动项");
					return;
				}
				else if (num > 1){
					alert("不允许批量修改");
					return;
				}
				//执行到这里说明选中项只有一条
				//打开修改的模态窗口前需要先从后台取数据
				$.ajax({
					url: "workbench/activity/getUserListAndActivity.do",
					data: {
						id : $("input[name=xz]:checked").val()
					},
					type: "get",
					dataJson: "json",
					success: function (data) {
						//userList和 Activity
						$("#editActivityModal").modal("show");
					}
				})
			})

		});

		function pageList(pageNo, pageSize) {
			//分页后，将全选框干掉
			$("#qx").prop("checked", false);
			//执行分页查询 ajax发送请求
			// alert("发起分页查询123");
			//在执行分页带参查询时候，将隐藏域的值设置到条件框中
			$("#serach-name").val($("#hidden-name").val());
            $("#serach-owner").val($("#hidden-owner").val());
            $("#serach-name").val($("#hidden-name").val());
            $("#serach-name").val($("#hidden-name").val());
			$.ajax({
				url: "workbench/activity/pageList.do",
				data: {
					pageNo : pageNo,
					pageSize: pageSize,
					name: $.trim($("#serach-name").val()),
					owner: $.trim($("#serach-owner").val()),
					startDate: $.trim($("#serach-startDate").val()),
					endDate: $.trim($("#search-endDate").val())
				},
				type: "get",
				dataType: "json",
				success: function (data) {
					//动态展现分页数据
					var html = "";
					$.each(data.dataList, function (i, n) {
						html += '<tr class="active">';
						html += '<td><input type="checkbox" name="xz" value="'+n.id+'"/></td>';
						html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'detail.jsp\';">'+n.name+'</a></td>';
						html += '<td>'+n.owner+'</td>';
						html += '<td>'+n.startDate+'</td>';
						html += '<td>'+n.endDate+'</td>';
						html += '</tr>';
					})
					//将拼接数据展示到tbody中
					$("#activityBody").html(html);
					//加入分页组件
					var totalPages = (data.total % pageSize == 0) ? (data.total / pageSize) : ( (parseInt(data.total / pageSize)+1));
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

						onChangePage : function(event, data){
							pageList(data.currentPage , data.rowsPerPage);
						}
					});

				}
			})

		}
	
	</script>
</head>
<body>

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
				
					<form class="form-horizontal" role="form" id="activityForm">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label ">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" >
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate" >
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
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
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								  <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
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
				<%--隐藏域暂时存储查询参数--%>
				<input type="hidden" id="hidden-name">
				<input type="hidden" id="hidden-owner">
				<input type="hidden" id="hidden-startDate">
				<input type="hidden" id="hidden-endDate">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="serach-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="serach-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" type="text" id="serach-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
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
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>