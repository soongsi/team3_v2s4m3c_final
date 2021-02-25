<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/menu/top.jsp" flush='false' />

<link href="./css/ebook.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript" src="./js/ebook.js"></script>

<section class="page-section cta">
  <c:import url="/ebook/menu.do" />
  <div id="eb_cont" class="container">
    <div class="row">
      <div class="col-md-6">
        <div id="slider" class="owl-carousel product-slider">
          <div class="item">
            <c:set var="main_img" value="${ebookVO.eb_file2 }"/>
            <c:choose>
                <c:when test="${main_img.endsWith('jpg') || main_img.endsWith('png') || main_img.endsWith('gif')}">
                  <img class="card-img-top" src="./storage/main_images/${main_img }" alt="">
                </c:when>
                <c:otherwise>
                  <img class="card-img-top" src="http://placehold.it/300x400" alt="">            
                </c:otherwise>
            </c:choose>
          </div>
          
          <!-- 다중 파일 -->
          <c:forEach var="attachfileVO" items="${attachlist }">
            <c:set var="fname" value="${attachfileVO.fname.toLowerCase() }" />
            <c:choose>
              <c:when test="${fname.endsWith('jpg') || fname.endsWith('png') || fname.endsWith('gif')}">
                <div class="item">
                  <img src="${pageContext.request.contextPath}/ebook/attachfile/storage/${attachfileVO.fname }" />
                </div>
              </c:when>
            </c:choose>
          </c:forEach>
          
        </div>
        <!-- 썸네일 -->
        <div id="thumb" class="owl-carousel product-thumb">
          <div class="item">
            <c:set var="thumb" value="${ebookVO.eb_thumb }"/>
            <c:choose>
                <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                  <img class="card-img-top" src="./storage/main_images/${thumb }" alt="">
                </c:when>
                <c:otherwise>
                  <img class="card-img-top" src="http://placehold.it/300x400" alt="">            
                </c:otherwise>
            </c:choose>
          </div>
          
          <!-- 다중 파일 -->
          <c:forEach var="attachfileVO" items="${attachlist }">
            <c:set var="thumb" value="${attachfileVO.thumb.toLowerCase() }" />
            <c:choose>
              <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                <div class="item">
                  <img src="${pageContext.request.contextPath}/ebook/attachfile/storage/${attachfileVO.thumb }" />
                </div>
              </c:when>
            </c:choose>
          </c:forEach>
        </div>
      </div>
      <div class="col-md-6">
        <div class="product-dtl">
          <div class="product-info">
            <h2 id="eb_title">${ebookVO.eb_title }</h2>
            <span class="eb_info">${ebookVO.eb_author } 저</span>
            <span class="info_div">|</span>
            <span class="eb_info">${ebookVO.eb_publisher }</span>
            <span class="info_div">|</span>
            <span class="eb_info">${ebookVO.eb_pdate }</span>
            <div class="reviews-counter">
              <div class="rate">
                <input type="radio" id="star5" name="rate" value="5"
                  checked /> <label for="star5" title="text">5
                  stars</label> <input type="radio" id="star4" name="rate"
                  value="4" checked /> <label for="star4"
                  title="text">4 stars</label> <input type="radio"
                  id="star3" name="rate" value="3" checked /> <label
                  for="star3" title="text">3 stars</label> <input
                  type="radio" id="star2" name="rate" value="2" /> <label
                  for="star2" title="text">2 stars</label> <input
                  type="radio" id="star1" name="rate" value="1" /> <label
                  for="star1" title="text">1 star</label>
              </div>
              <span>리뷰 0 개</span>
            </div>
            <div class="product-price-discount">
              <span class="price"><fmt:formatNumber
                  value="${ebookVO.eb_price}" pattern="#,###" /></span> <span
                class="won"> 원</span> <span class="line-through"></span>
            </div>
          </div>
          
          <!-- DA : frm_cart -->
          <form name="frm_cart" id='frm_cart' >
            <input type="hidden" name="user_id" id="user_id" value="${sessionScope.id}">
            <input type="hidden" name="eb_no" id="eb_no" value="${ebookVO.eb_no}">
              <div class="product-count">
                <a class="round-black-btn" id="btn_addCart">장바구니에 담기</a>
                <a href="#" class="round-black-btn">바로 구매</a>
              </div>
          </form>

        </div>
      </div>
    </div>
    <div class="product-info-tabs">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item"><a class="nav-link active"
          id="description-tab" data-toggle="tab" href="#description"
          role="tab" aria-controls="description" aria-selected="true">도서
            정보</a></li>
        <li class="nav-item"><a class="nav-link" id="review-tab"
          data-toggle="tab" href="#review" role="tab"
          aria-controls="review" aria-selected="false">리뷰 (0)</a></li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description"
          role="tabpanel" aria-labelledby="description-tab">
          <div id="useinfo">
            <h2 class="useinfo_head">이용안내</h2>
            <div class="useinfo_cont">
              ${ebookVO.eb_useinfo }
            </div>
            <h2 class="useinfo_head">지원 기기</h2>
            <div class="useinfo_cont">
              ${ebookVO.eb_device }
            </div>
          </div>
          ${ebookVO.eb_infor }
        </div>
        <div class="tab-pane fade" id="review" role="tabpanel"
          aria-labelledby="review-tab">
          <div class="review-heading">REVIEWS</div>
          <p class="mb-20">There are no reviews yet.</p>
          <form class="review-form">
            <div class="form-group">
              <label>Your rating</label>
              <div class="reviews-counter">
                <div class="rate">
                  <input type="radio" id="star5" name="rate" value="5" />
                  <label for="star5" title="text">5 stars</label> <input
                    type="radio" id="star4" name="rate" value="4" />
                  <label for="star4" title="text">4 stars</label> <input
                    type="radio" id="star3" name="rate" value="3" />
                  <label for="star3" title="text">3 stars</label> <input
                    type="radio" id="star2" name="rate" value="2" />
                  <label for="star2" title="text">2 stars</label> <input
                    type="radio" id="star1" name="rate" value="1" />
                  <label for="star1" title="text">1 star</label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label>Your message</label>
              <textarea class="form-control" rows="10"></textarea>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <input type="text" name="" class="form-control"
                    placeholder="Name*">
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <input type="text" name="" class="form-control"
                    placeholder="Email Id*">
                </div>
              </div>
            </div>
            <button class="round-black-btn">Submit Review</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="/menu/bottom.jsp" flush='false' />