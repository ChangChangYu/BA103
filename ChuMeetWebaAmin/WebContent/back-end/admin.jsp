<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	AdminService adminSvc = new AdminService();
	List<AdminVO> list = adminSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<link rel="apple-touch-icon" sizes="76x76"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" />
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>ChuMeet-Manage</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<!-- Bootstrap core CSS     -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<!--  Material Dashboard CSS    -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css"
	rel="stylesheet" />
<!--  CSS for Demo Purpose, don't include it in your project     -->
<link
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css"
	rel="stylesheet" />
<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons'
	rel='stylesheet' type='text/css'>
<style>
.table, .table th, .table td, .table select {
	text-align: center;
	vertical-align: middle;
	line-height: 1em;
}

.table>tbody>tr>td {
	line-height: 1em;
	padding: .3em;
}

.cclabel:after {
	content: '';
	position: absolute;
	top: 0px;
	left: 2px;
	width: 20px;
	height: 20px;
	background: #fff;
	border-radius: 20px;
	transition: 0.3s;
}

.table>thead>tr>th {
	padding: .5em;
	font-weight: bold;
	line-height: 1.5em;
}

.cclabel:active:after {
	width: 20px;
}
</style>
</head>

<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>



	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/sidebar-1.jpg">
			<!--
            Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

            Tip 2: you can also add an image using data-image tag
        -->
			<div class="logo text-center">
				<img
					src="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/ChuMeet_logo_2.png">
				<strong>管理平台</strong>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li><a href="index.html"><i class="material-icons">home</i>
							<p>
								<strong>首頁</strong>
							</p></a></li>
					<li class="active"><a href="admin_admin.html"><i
							class="material-icons">person</i>
							<p>
								<strong>管理員管理</strong>
							</p></a>
					<li><a href="admin_info.html"><i class="material-icons">person</i>
							<p>
								<strong>網站管理</strong>
							</p></a></li>
					<li><a href="admin_appAnn.html"><i class="material-icons">phonelink_ring</i>
							<p>
								<strong>APP推播管理</strong>
							</p></a></li>
					<li><a href="admin_member.html"><i
							class="material-icons text-gray">face</i>
							<p>
								<strong>會員管理</strong>
							</p></a></li>
					<li><a href="admin_reward.html"><i
							class="material-icons text-gray">face</i>
							<p>
								<strong>成就與獎賞管理</strong>
							</p></a></li>
					<li><a href="admin_memTicket.html"><i
							class="material-icons">library_books</i>
							<p>
								<strong>檢舉管理</strong>
							</p></a></li>
					<li><a href="admin_POI.html"><i class="material-icons">location_on</i>
							<p>
								<strong>活動社團分類管理</strong>
							</p></a></li>
					<li><a href="admin_act.html"><i
							class="material-icons text-gray">directions_bike</i>
							<p>
								<strong>活動管理</strong>
							</p></a></li>
					<li><a href="admin_club.html"><i
							class="material-icons text-gray">wc</i>
							<p>
								<strong>社團資料管理</strong>
							</p></a></li>
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<nav class="navbar navbar-transparent ">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Material Dashboard</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#pablo" class="dropdown-toggle"
							data-toggle="dropdown">
								<p class="hidden-lg hidden-md">Dashboard</p>
						</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="material-icons">notifications</i>
								<span class="notification">5</span>
								<p class="hidden-lg hidden-md">Notifications</p>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#">Mike John responded to your email</a></li>
								<li><a href="#">You have 5 new tasks</a></li>
								<li><a href="#">You're now friend with Andrew</a></li>
								<li><a href="#">Another Notification</a></li>
								<li><a href="#">Another One</a></li>
							</ul></li>
						<li><a href="#pablo" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="material-icons">person</i>
								<p class="hidden-lg hidden-md">Profile</p>
						</a></li>
					</ul>
				</div>
			</div>
			</nav>
			<!--/////////////CONTENT///////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<div class="container">
				<h2>
					<strong>管理員列表</strong>
				</h2>
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#actMain">管理員資料</a>
					</li>
					<li><a data-toggle="tab" href="#actTemp">管理員權限</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group  is-empty">
						<input type="text" class="form-control" placeholder="搜索">
						<span class="material-input"></span>
					</div>
					<button type="submit" class="btn btn-white btn-round btn-just-icon">
						<i class="material-icons">search</i>
						<div class="ripple-container"></div>
					</button>
				</form>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="actMain">
						<h2>管理員資料管理</h2>
						<table class="table table-hover">
							<thead>
								<tr class="bg-danger">

									<th class="col-md-1">編號</th>
									<th class="col-md-1">姓名</th>
									<th class="col-md-1">帳號</th>
									<th class="col-md-1">信箱</th>
									<th class="col-md-1">時間</th>
									<th class="col-md-1">狀態</th>
									<th class="col-md-1">修改</th>
									<th class="col-md-1">暫停</th>
								</tr>
							</thead>
							<tbody>


			
	<c:forEach var="adminVO" items="${list}">
								<tr>
									<td>${adminVO.adminID}</td>
									<td>${adminVO.adminName}</td>
									<td>${adminVO.adminMail}</td>
									<td>${adminVO.adminEmail}</td>
									<td>${adminVO.adminDate}</td>
									<td>${adminVO.adminStatus}</td>
									
									<td>
											<FORM METHOD="post"ACTION="<%=request.getContextPath()%>/back-end/admin.do">											
												<input type="submit" value="修改" class="btn btn-sm btn-warning"> 
												
												<input type="hidden" name="action" value="getOne_For_Update">
												<input type="hidden" name="adminID" value="${adminVO.adminID}">
											 </FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/admin.do">
											     <input type="submit" value="刪除" class="btn btn-danger btn-sm statbtn">							 
												
												 <input type="hidden" name="action" value="delete">
												 <input type="hidden" name="adminID" value="${adminVO.adminID}">
										</FORM>
									</td>
								</tr>
								
						</c:forEach>

							</tbody>
						</table>					
								<ul>
 									<a href='addadmin.jsp' class="btn btn-success">新增</a>
								</ul>
						
						
					
					</div>
					<!--====================2nd-->
					<div class="tab-pane fade" id="actTemp">
						<div class="tt">
							<div class="tab-content">
								<div class="tab-pane fade in active">
									<h2>管理員權限管理</h2>
									<table class="table table-hover">
										<thead>
											<tr class="bg-danger">
												<th class="col-md-1">編號</th>
												<th class="col-md-1">網頁管理</th>
												<th class="col-md-1">姓名</th>
												<th class="col-md-1">帳號</th>											
												<th class="col-md-1">權限</th>
											</tr>
										</thead>
										<tbody>
										
										
															
											 <tr>
                                                <td>NO.1</td>
                                                <td>管理員管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.2</td>
                                                <td>網站管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.3</td>
                                                <td>App推播管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.4</td>
                                                <td>會員管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.5</td>
                                                <td>成就與獎賞管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.6</td>
                                                <td>檢舉管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.7</td>
                                                <td>活動社團分類管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.8</td>
                                                <td>活動管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>NO.9</td>
                                                <td>社團管理</td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="optionsCheckboxes">
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
										</tbody>
									
									</table>
									<button class="btn btn-success">新增</button>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
			<!--/////////////////////////////////////////////////////////////////////////////-->
		</div>
	</div>
</body>
<!--   Core JS Files   -->
<script src="../assets/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="../assets/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js"></script>
<!-- Material Dashboard javascript methods -->
<script src="../assets/js/material-dashboard.js"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="../assets/js/demo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		// Javascript method's body can be found in assets/js/demos.js
		demo.initDashboardPageCharts();

	});
</script>

</html>