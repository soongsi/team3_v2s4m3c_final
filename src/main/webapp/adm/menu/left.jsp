<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Ebook Store 관리자</title>

  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/adm/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/adm/css/sb-admin-2.min.css" rel="stylesheet">
  
  <!-- 2020-10-28 yskim -->
  <link href="${pageContext.request.contextPath}/adm/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/adm/css/custom.css" rel="stylesheet">
  
  <!-- jquery -->
  <script type="text/JavaScript" 
                 src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/adm">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-grin-alt"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Administrator</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="/">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>
      
      <!-- Nav Item - Dashboard -->
      <%-- <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/categrp/list.do">
          <i class="fas fa-fw fa-cog"></i>
          <span>카테고리 그룹 관리</span></a>
      </li> --%>

      <!-- Divider -->
      <hr class="sidebar-divider" />

      <!-- Heading -->
      <div class="sidebar-heading">
        Member
      </div>
      
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/member/list.do">
          <i class="fas fa-user"></i>
          <span>회원 목록</span>
        </a>
      </li>
      
      <hr class="sidebar-divider" />
      
      <!-- Heading -->
      <div class="sidebar-heading">
        E-book
      </div>
      
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/adm/categrp/list.do" data-toggle="collapse" data-target="#categrp" aria-expanded="true" aria-controls="categrp">
          <i class="far fa-list-alt"></i>
          <span>E-book 카테고리</span>
        </a>
        <div id="categrp" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">카테고리 관리</h6>
            <a class="collapse-item" href="${pageContext.request.contextPath}/adm/categrp/list.do">카테고리 그룹</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/adm/cate/list_all.do">전체 카테고리</a>
          </div>
        </div>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/ebook/list.do">
          <i class="fas fa-book-open"></i>
          <span>E-book 전체 목록</span>
        </a>
      </li>
      
      <hr class="sidebar-divider" />
      
      <!-- Heading -->
      <div class="sidebar-heading">
        Order
      </div>
      
      <!-- DA: 추가 시작-->
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/customer/list_all.do">
          <i class="fas fa-question-circle"></i>
          <span>고객센터</span>
        </a>
      </li>  
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/orderreq/list_all.do">
          <i class="fas fa-shopping-cart"></i>
          <span>주문요청 리스트</span>
        </a>
      </li>  
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#survey" aria-expanded="true" aria-controls="survey">
          <i class="far fa-list-alt"></i>
          <span>설문조사</span>
        </a>
        <div id="survey" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">설문 조사</h6>
            <a class="collapse-item" href="${pageContext.request.contextPath}/adm/survey/list.do">설문조사 리스트</a>
          </div>
        </div>
      </li>
      <!-- DA: 추가 끝-->
      

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        ETC
      </div>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/notice/list.do">
          <i class="fas fa-bell"></i>
          <span>공지사항</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adm/event/list.do">
          <i class="far fa-calendar-check"></i>
          <span>이벤트</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->