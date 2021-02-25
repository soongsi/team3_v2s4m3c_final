<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html lang="ko">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>ebook Store</title>

  <!-- jQuery -->
  <script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  
  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/menu/css/business-casual.min.css" rel="stylesheet">
  
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">

  <!-- owl carousel -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
  
</head>

<body>

  <div id="account" class="text-center text-white">
    <c:choose>
      <c:when test="${sessionScope.id == null}">
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/member/login.do">Login</a>
        </span>
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/member/create.do">Register</a>
        </span>
      </c:when>
      <c:otherwise>
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/cart/list.do">Cart</a>
        </span>    
        <span class="mx-2">
          <a class="text-white" href="#">My Page</a>
        </span>
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/member/logout.do">Logout</a>
        </span>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${sessionScope.id_admin == null}">
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/admin/login.do">Admin Login</a>
        </span>
      </c:when>
      <c:otherwise>
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/adm/">Admin</a>
        </span>
        <span class="mx-2">
          <a class="text-white" href="${pageContext.request.contextPath}/admin/logout.do">Admin Logout</a>
        </span>               
      </c:otherwise>
    </c:choose>    
  </div>

  <h1 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-upper text-primary mb-3">TEAM 3 PROJECT</span>
    <span class="site-heading-lower">E-Book STORE</span>
  </h1>

  <!-- Navigation -->
  
  <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
      <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="/">E-BOOK STORE</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/">Home</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/notice/list.do">Notice</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/ebook/list.do">E-Book</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Event
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/">Q&A</a>
          </li>
        </ul>
        
        <ul id="account_ul" class="navbar-nav mx-auto my-3">
        <c:choose>
          <c:when test="${sessionScope.id == null}">
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/member/login.do">Login</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/member/create.do">Register</a>
          </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/cart/list.do">Cart</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="#">My Page</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="${pageContext.request.contextPath}/member/logout.do">Logout</a>
            </li>            
          </c:otherwise>
        </c:choose>
        </ul>
      </div>
    </div>
  </nav>