<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admPril.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("page", "ticket");
%>

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/HTML/BackEnd/assets/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>ChuMeet-Manage</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--  Material Dashboard CSS    -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/material-dashboard.css" rel="stylesheet" />
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/demo.css" rel="stylesheet" />
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>
    <style>
    .table,
    .table th,
    .table td,
    .table select {
        text-align: center;
        vertical-align: middle;
        line-height: 1em;
    }

    .table>tbody>tr>td {
        line-height: 1em;
        padding: .3em;
    }

    .table>thead>tr>th {
        padding: .5em;
        font-weight: bold;
        line-height: 1.5em;
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
    .cclabel:active:after {
        width: 20px;
    }
    </style>
</head>

<body>
   <c:if test="${adminVO!=null}">
		<div class="wrapper">

			<!-- Sidebar -->
			<c:import url="/back-end/backEndSidebar.jsp">
			</c:import>

			<div class="main-panel">
				<!-- Navbar -->
				<c:import url="/back-end/backEndNavbar.jsp">
				</c:import>
            <!--/////////////CONTENT///////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <!--/////////////////////////////////////////////////////////////////////////////-->
            <div class="container">

             <h2><strong>檢舉管理</strong></h2>
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#home">會員檢舉管理</a></li>
                    <li><a data-toggle="tab" href="#menu1">留言/相片檢舉管理</a></li>
                    <li><a data-toggle="tab" href="#menu2">相簿檢舉管理</a></li>
                    <li><a data-toggle="tab" href="#menu2">活動/社團檢舉管理</a></li>
                                      
                </ul>


                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group  is-empty">
                        <input type="text" class="form-control" placeholder="搜索">
                        <span class="material-input"></span> </div>
                    <button type="submit" class="btn btn-white btn-round btn-just-icon"> <i class="material-icons">search</i>
                        <div class="ripple-container"></div>
                    </button>
                </form>
                <div class="tab-content">
                    <div class="tab-pane fade in active">
                        <h2>相簿檢舉管理</h2>
                        <table class="table  table-hover">
                            <thead>
                            <tr class="bg-danger">
                                <th class="col-md-1">流水號</th>
                                <th class="col-md-1">檢舉人</th>
                                <th class="col-md-1">被檢舉人</th>
                                <th class="col-md-1">相簿</th>
                                <th class="col-md-1">說明</th>
                                <th class="col-md-1">日期</th>
                                <th class="col-md-1">狀態</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>NO.1</td>
                                    <td>徐敏道</td>
                                    <td>123</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <span class="btn-group">
                        <button type="button" class="btn btn-danger btn-sm statbtn" name="actStatID" value="5">刪除</button>                     
                               </span>
                                        <button class="btn btn-sm btn-warning">恢復</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                </div>
                <!--/////////////////////////////////////////////////////////////////////////////-->
                <!--/////////////////////////////////////////////////////////////////////////////-->
                <!--/////////////////////////////////////////////////////////////////////////////-->
                <!--/////////////////////////////////////////////////////////////////////////////-->
            </div>
        </div>
        </c:if>
</body>
<!--   Core JS Files   -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
<!-- Material Dashboard javascript methods -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/material-dashboard.js"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="<%=request.getContextPath()%>/HTML/BackEnd/assets/js/demo.js"></script>
<script type="text/javascript">
$(document).ready(function() {

    // Javascript method's body can be found in assets/js/demos.js
    demo.initDashboardPageCharts();

});
</script>

</html>