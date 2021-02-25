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

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/menu/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/menu/css/business-casual.min.css" rel="stylesheet">
  
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
  
  <!-- CSS of each menu -->
  <c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}" />
  
  <c:if test="">
  </c:if>

</head>

<body>

  <h1 class="site-heading text-center text-white d-none d-lg-block">
    <span class="site-heading-upper text-primary mb-3">TEAM 3 PROJECT</span>
    <span class="site-heading-lower">E-Book STORE</span>
  </h1>
  
  <section class="page-section cta text-center error-section">
    <div class="container">
      <h1>404 NOT FOUND</h1>
      <h3>페이지를 찾을 수 없습니다.</h3>
      <span><a href="javascript:history.back()">뒤로가기</a></span>
    </div>
  </section>
  
  <footer class="footer text-faded text-center py-5">
    <div class="container">
      <p class="m-0 small">Copyright &copy; team3 2020</p>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/menu/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/menu/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  
  <script type="text/javascript">
  $(window).scroll(function() {
     
    $("nav").addClass("fixed");
    
    if ( $(window).scrollTop() == 0 ) {
      $("nav").removeClass("fixed");
    }
    
  });
  
  
  </script>
</body>
</html>