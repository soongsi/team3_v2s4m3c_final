DROP TABLE SALEWEEK CASCADE CONSTRAINTS;
DROP TABLE EVENT CASCADE CONSTRAINTS;
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP TABLE PLIST CASCADE CONSTRAINTS;
DROP TABLE PUBLISHER CASCADE CONSTRAINTS;
DROP TABLE REVIEW CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE NCATE CASCADE CONSTRAINTS;
DROP TABLE BOOKSHELF CASCADE CONSTRAINTS;
DROP TABLE SURVEY_CHART CASCADE CONSTRAINTS;
DROP TABLE SURVEY_ITEM CASCADE CONSTRAINTS;
DROP TABLE SURVEY CASCADE CONSTRAINTS;
DROP TABLE ORDER_REQ CASCADE CONSTRAINTS;
DROP TABLE CART CASCADE CONSTRAINTS;
DROP TABLE CS_ATTACHFILE CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE EBOOK_ATTACHFILE CASCADE CONSTRAINTS;
DROP TABLE EBOOK CASCADE CONSTRAINTS;
DROP TABLE CATE CASCADE CONSTRAINTS;
DROP TABLE CATEGRP CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 카테고리 그룹 */
/**********************************/
CREATE TABLE CATEGRP(
		CG_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CG_NAME                       		VARCHAR2(100)		 NOT NULL,
		CG_SEQNO                      		NUMBER(10)		 NOT NULL,
		CG_VISIBLE                    		CHAR(1)		 NOT NULL,
		CG_RDATE                      		DATE		 NOT NULL
);

COMMENT ON TABLE CATEGRP is '카테고리 그룹';
COMMENT ON COLUMN CATEGRP.CG_NO is '카테고리 그룹 번호';
COMMENT ON COLUMN CATEGRP.CG_NAME is '카테고리 그룹 이름';
COMMENT ON COLUMN CATEGRP.CG_SEQNO is '출력 순서';
COMMENT ON COLUMN CATEGRP.CG_VISIBLE is '출력 모드';
COMMENT ON COLUMN CATEGRP.CG_RDATE is '그룹 생성일';


/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE CATE(
		CATENO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CG_NO                         		NUMBER(10)		 NULL ,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (CG_NO) REFERENCES CATEGRP (CG_NO)
);

COMMENT ON TABLE CATE is '카테고리';
COMMENT ON COLUMN CATE.CATENO is '카테고리 번호';
COMMENT ON COLUMN CATE.CG_NO is '카테고리 그룹 번호';
COMMENT ON COLUMN CATE.NAME is '카테고리 이름';
COMMENT ON COLUMN CATE.SEQNO is '출력 순서';
COMMENT ON COLUMN CATE.VISIBLE is '출력 모드';
COMMENT ON COLUMN CATE.RDATE is '등록일';


/**********************************/
/* Table Name: 이북 */
/**********************************/
CREATE TABLE EBOOK(
		EB_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CATENO                        		NUMBER(10)		 NULL ,
		EB_TITLE                      		VARCHAR2(200)		 NOT NULL,
		EB_AUTHOR                     		VARCHAR2(100)		 NOT NULL,
		EB_PUBLISHER                  		VARCHAR2(50)		 NOT NULL,
		EB_PDATE                      		VARCHAR2(20)		 NULL ,
		EB_USEINFO                    		VARCHAR2(500)		 NULL ,
		EB_DEVICE                     		VARCHAR2(500)		 NOT NULL,
		EB_INFOR                      		CLOB(4000)		 NULL ,
		EB_PRICE                      		NUMBER(10)		 NOT NULL,
		EB_SALETOT                    		NUMBER(10)		 NULL ,
		EB_PAGE                       		NUMBER(10)		 NULL ,
		EB_FILE1                      		VARCHAR2(100)		 NULL ,
		EB_SIZE1                      		NUMBER(10)		 NULL ,
		EB_FILE2                      		VARCHAR2(100)		 NULL ,
		EB_SIZE2                      		NUMBER(10)		 NULL ,
		EB_THUMB                      		VARCHAR2(100)		 NULL ,
		WORD                          		VARCHAR2(300)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);

COMMENT ON TABLE EBOOK is '이북';
COMMENT ON COLUMN EBOOK.EB_NO is '이북 번호';
COMMENT ON COLUMN EBOOK.CATENO is '카테고리 번호';
COMMENT ON COLUMN EBOOK.EB_TITLE is '제목';
COMMENT ON COLUMN EBOOK.EB_AUTHOR is '저자';
COMMENT ON COLUMN EBOOK.EB_PUBLISHER is '출판사';
COMMENT ON COLUMN EBOOK.EB_PDATE is '출간일';
COMMENT ON COLUMN EBOOK.EB_USEINFO is '이용안내';
COMMENT ON COLUMN EBOOK.EB_DEVICE is '지원기기';
COMMENT ON COLUMN EBOOK.EB_INFOR is '도서 정보';
COMMENT ON COLUMN EBOOK.EB_PRICE is '판매가';
COMMENT ON COLUMN EBOOK.EB_SALETOT is '판매량';
COMMENT ON COLUMN EBOOK.EB_PAGE is '페이지 수';
COMMENT ON COLUMN EBOOK.EB_FILE1 is '이북 파일';
COMMENT ON COLUMN EBOOK.EB_SIZE1 is '이북 파일 크기';
COMMENT ON COLUMN EBOOK.EB_FILE2 is '메인 이미지';
COMMENT ON COLUMN EBOOK.EB_SIZE2 is '메인 이미지 파일 크기';
COMMENT ON COLUMN EBOOK.EB_THUMB is '썸네일';
COMMENT ON COLUMN EBOOK.WORD is '검색어';
COMMENT ON COLUMN EBOOK.RDATE is '담긴 날짜';


/**********************************/
/* Table Name: 첨부파일 */
/**********************************/
CREATE TABLE EBOOK_ATTACHFILE(
		ATTACHFILENO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		EB_NO                         		NUMBER(10)		 NULL ,
		FNAME                         		VARCHAR2(200)		 NOT NULL,
		FUPNAME                       		VARCHAR2(200)		 NOT NULL,
		THUMB                         		VARCHAR2(200)		 NULL ,
		FSIZE                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO)
);

COMMENT ON TABLE EBOOK_ATTACHFILE is '첨부파일';
COMMENT ON COLUMN EBOOK_ATTACHFILE.ATTACHFILENO is '첨부파일번호';
COMMENT ON COLUMN EBOOK_ATTACHFILE.EB_NO is '이북 번호';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FNAME is '원본 파일명';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FUPNAME is '업로드 파일명';
COMMENT ON COLUMN EBOOK_ATTACHFILE.THUMB is 'Thumb 파일명';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FSIZE is '파일 사이즈';
COMMENT ON COLUMN EBOOK_ATTACHFILE.RDATE is '등록일';


/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE MEMBER(
		M_NO                          		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(20)		 NOT NULL,
		PASSWD                        		VARCHAR2(60)		 NOT NULL,
		MNAME                         		VARCHAR2(20)		 NOT NULL,
		EMAIL                         		VARCHAR2(60)		 NOT NULL,
		TEL                           		VARCHAR2(14)		 NOT NULL,
		ZIPCODE                       		VARCHAR2(5)		 NULL ,
		ADDRESS1                      		VARCHAR2(80)		 NULL ,
		ADDRESS2                      		VARCHAR2(50)		 NULL ,
		MDATE                         		DATE		 NOT NULL,
  CONSTRAINT SYS_C008370 UNIQUE (ID)
);

COMMENT ON TABLE MEMBER is '회원';
COMMENT ON COLUMN MEMBER.M_NO is '회원 번호';
COMMENT ON COLUMN MEMBER.ID is '아이디';
COMMENT ON COLUMN MEMBER.PASSWD is '패스워드';
COMMENT ON COLUMN MEMBER.MNAME is '성명';
COMMENT ON COLUMN MEMBER.EMAIL is '이메일';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.ZIPCODE is '우편번호';
COMMENT ON COLUMN MEMBER.ADDRESS1 is '주소1';
COMMENT ON COLUMN MEMBER.ADDRESS2 is '주소2';
COMMENT ON COLUMN MEMBER.MDATE is '가입일';


/**********************************/
/* Table Name: 고객센터 */
/**********************************/
CREATE TABLE CUSTOMER(
		CS_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CS_TYPE                       		VARCHAR2(20)		 NOT NULL,
		CS_TITLE                      		VARCHAR2(30)		 NOT NULL,
		CS_CONTENTS                   		CLOB(4000)		 NOT NULL,
		CS_FILE1                      		VARCHAR2(100)		 NULL ,
		CS_THUMB1                     		VARCHAR2(100)		 NULL ,
		CS_SIZE1                      		NUMBER(10)		 NULL ,
		CS_RDATE                      		DATE		 NOT NULL,
		CS_CNT                        		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(6)		 NULL ,
		CS_PASSWD                     		VARCHAR2(60)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE CUSTOMER is '고객센터';
COMMENT ON COLUMN CUSTOMER.CS_NO is '질문 번호';
COMMENT ON COLUMN CUSTOMER.CS_TYPE is '질문 유형';
COMMENT ON COLUMN CUSTOMER.CS_TITLE is '제목';
COMMENT ON COLUMN CUSTOMER.CS_CONTENTS is '내용';
COMMENT ON COLUMN CUSTOMER.CS_FILE1 is '메인 이미지';
COMMENT ON COLUMN CUSTOMER.CS_THUMB1 is '메인 이미지 미리보기';
COMMENT ON COLUMN CUSTOMER.CS_SIZE1 is '메인 이미지 크기';
COMMENT ON COLUMN CUSTOMER.CS_RDATE is '등록일';
COMMENT ON COLUMN CUSTOMER.CS_CNT is '질문 조회수';
COMMENT ON COLUMN CUSTOMER.M_NO is '회원 번호';
COMMENT ON COLUMN CUSTOMER.CS_PASSWD is '관리자 비밀번호';


/**********************************/
/* Table Name: 고객센터 첨부파일 */
/**********************************/
CREATE TABLE CS_ATTACHFILE(
		ATTACH_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CS_NO                         		NUMBER(10)		 NULL ,
		FNAME                         		VARCHAR2(200)		 NOT NULL,
		FUPNAME                       		VARCHAR2(200)		 NOT NULL,
		THUMB                         		VARCHAR2(200)		 NULL ,
		FSIZE                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
  FOREIGN KEY (CS_NO) REFERENCES CUSTOMER (CS_NO)
);

COMMENT ON TABLE CS_ATTACHFILE is '고객센터 첨부파일';
COMMENT ON COLUMN CS_ATTACHFILE.ATTACH_NO is '첨부파일 번호';
COMMENT ON COLUMN CS_ATTACHFILE.CS_NO is '문의 번호';
COMMENT ON COLUMN CS_ATTACHFILE.FNAME is '원본 파일명';
COMMENT ON COLUMN CS_ATTACHFILE.FUPNAME is '업로드 파일명';
COMMENT ON COLUMN CS_ATTACHFILE.THUMB is 'Thumb 파일명';
COMMENT ON COLUMN CS_ATTACHFILE.FSIZE is '파일 사이즈';
COMMENT ON COLUMN CS_ATTACHFILE.RDATE is '등록일';


/**********************************/
/* Table Name: 장바구니 */
/**********************************/
CREATE TABLE CART(
		CART_NO                       		NUMBER(10)		 NOT NULL,
		USER_ID                       		VARCHAR2(20)		 NOT NULL,
		EB_NO                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
		M_NO                          		NUMBER(6)		 NULL ,
  PRIMARY KEY (CART_NO, USER_ID),
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE CART is '장바구니';
COMMENT ON COLUMN CART.CART_NO is '카트 번호';
COMMENT ON COLUMN CART.USER_ID is '아이디';
COMMENT ON COLUMN CART.EB_NO is '상품 번호';
COMMENT ON COLUMN CART.RDATE is '담긴 날짜';
COMMENT ON COLUMN CART.M_NO is '회원 번호';


/**********************************/
/* Table Name: 주문요청 */
/**********************************/
CREATE TABLE ORDER_REQ(
		ORDER_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		PAYWAY                        		VARCHAR2(30)		 NOT NULL,
		PRICE                         		NUMBER(10)		 NOT NULL,
		EB_NO                         		NUMBER(10)		 NULL ,
		USER_ID                       		VARCHAR2(20)		 NULL ,
		RDATE                         		DATE		 NULL ,
		PAYID                         		VARCHAR2(30)		 NULL ,
		M_NO                          		NUMBER(6)		 NULL ,
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE ORDER_REQ is '주문요청';
COMMENT ON COLUMN ORDER_REQ.ORDER_NO is '주문번호';
COMMENT ON COLUMN ORDER_REQ.PAYWAY is '결제방법';
COMMENT ON COLUMN ORDER_REQ.PRICE is '결제금액';
COMMENT ON COLUMN ORDER_REQ.EB_NO is '이북 번호';
COMMENT ON COLUMN ORDER_REQ.USER_ID is '회원 아이디';
COMMENT ON COLUMN ORDER_REQ.RDATE is '결제일시';
COMMENT ON COLUMN ORDER_REQ.PAYID is '결제번호';
COMMENT ON COLUMN ORDER_REQ.M_NO is '회원 번호';


/**********************************/
/* Table Name: 설문조사 */
/**********************************/
CREATE TABLE SURVEY(
		SURVEY_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		STARTDATE                     		VARCHAR2(30)		 NOT NULL,
		ENDDATE                       		VARCHAR2(30)		 NOT NULL,
		CNT                           		NUMBER(10)		 NOT NULL,
		CONTINUEYN                    		VARCHAR2(5)		 NOT NULL,
		SURVEY_RESULT                 		VARCHAR2(100)		 NULL ,
		SEQNO                         		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE SURVEY is '설문조사';
COMMENT ON COLUMN SURVEY.SURVEY_NO is '설문 번호';
COMMENT ON COLUMN SURVEY.TITLE is '제목';
COMMENT ON COLUMN SURVEY.STARTDATE is '시작일';
COMMENT ON COLUMN SURVEY.ENDDATE is '종료일';
COMMENT ON COLUMN SURVEY.CNT is '참여 인원수';
COMMENT ON COLUMN SURVEY.CONTINUEYN is '상태';
COMMENT ON COLUMN SURVEY.SURVEY_RESULT is '설문결과';
COMMENT ON COLUMN SURVEY.SEQNO is '순서';


/**********************************/
/* Table Name: 설문 항목 */
/**********************************/
CREATE TABLE SURVEY_ITEM(
		SURVEY_NO                     		NUMBER(10)		 NOT NULL,
		ITEM_NO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ITEM_TITLE                    		VARCHAR2(200)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		RCNT                          		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (SURVEY_NO) REFERENCES SURVEY (SURVEY_NO)
);

COMMENT ON TABLE SURVEY_ITEM is '설문 항목';
COMMENT ON COLUMN SURVEY_ITEM.SURVEY_NO is '설문 번호';
COMMENT ON COLUMN SURVEY_ITEM.ITEM_NO is '설문 항목 번호';
COMMENT ON COLUMN SURVEY_ITEM.ITEM_TITLE is '설문 항목 제목';
COMMENT ON COLUMN SURVEY_ITEM.SEQNO is '순서';
COMMENT ON COLUMN SURVEY_ITEM.RCNT is '집계';


/**********************************/
/* Table Name: 설문 집계  */
/**********************************/
CREATE TABLE SURVEY_CHART(
		ITEM_NO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		SURVEY_NO                     		NUMBER(10)		 NOT NULL,
		ITEM                          		VARCHAR2(200)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		CNT                           		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (SURVEY_NO) REFERENCES SURVEY (SURVEY_NO)
);

COMMENT ON TABLE SURVEY_CHART is '설문 집계 ';
COMMENT ON COLUMN SURVEY_CHART.ITEM_NO is '설문 항목 번호';
COMMENT ON COLUMN SURVEY_CHART.SURVEY_NO is '설문 번호';
COMMENT ON COLUMN SURVEY_CHART.ITEM is '항목';
COMMENT ON COLUMN SURVEY_CHART.SEQNO is '순서';
COMMENT ON COLUMN SURVEY_CHART.CNT is '집계';


/**********************************/
/* Table Name: 내 서재 */
/**********************************/
CREATE TABLE BOOKSHELF(
		BOOKSHELFNO                   		NUMBER(20)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NOT NULL,
		P_NAME                        		VARCHAR2(20)		 NOT NULL,
		P_INFOR                       		VARCHAR2(50)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE BOOKSHELF is '내 서재';
COMMENT ON COLUMN BOOKSHELF.BOOKSHELFNO is '서재 번호';
COMMENT ON COLUMN BOOKSHELF.M_NO is '회원 번호';
COMMENT ON COLUMN BOOKSHELF.P_NAME is '상품 이름 ';
COMMENT ON COLUMN BOOKSHELF.P_INFOR is '상품 정보 ';
COMMENT ON COLUMN BOOKSHELF.FILE1 is 'FILE1';
COMMENT ON COLUMN BOOKSHELF.THUMB1 is 'THUMB1';
COMMENT ON COLUMN BOOKSHELF.SIZE1 is 'SIZE1';


/**********************************/
/* Table Name: 공지사항분류 */
/**********************************/
CREATE TABLE NCATE(
		NCATE_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NCATE_NAME                    		VARCHAR2(100)		 NOT NULL,
		NSEQNO                        		NUMBER(10)		 NOT NULL,
		NVISIBLE                      		CHAR(1)		 NOT NULL,
		NCATE_RDATE                   		DATE		 NOT NULL,
		NCATE_CNT                     		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE NCATE is '공지사항분류';
COMMENT ON COLUMN NCATE.NCATE_NO is '카테고리 번호';
COMMENT ON COLUMN NCATE.NCATE_NAME is '카테고리 이름';
COMMENT ON COLUMN NCATE.NSEQNO is '출력 순서';
COMMENT ON COLUMN NCATE.NVISIBLE is '출력 모드';
COMMENT ON COLUMN NCATE.NCATE_RDATE is '등록일';
COMMENT ON COLUMN NCATE.NCATE_CNT is '등록된 글 수';


/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NULL ,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		CONTENT                       		CLOB(4000)		 NOT NULL,
		IP                            		VARCHAR2(15)		 NOT NULL,
		NOTICE_PW                     		VARCHAR2(15)		 NOT NULL,
		NFILE1                        		VARCHAR2(100)		 NULL ,
		NTHUMB1                       		VARCHAR2(100)		 NULL ,
		NSIZE1                        		NUMBER(10)		 NULL ,
		WRITER                        		VARCHAR2(20)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
		NCATE_NO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (NCATE_NO) REFERENCES NCATE (NCATE_NO)
);

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항번호';
COMMENT ON COLUMN NOTICE.M_NO is '회원번호';
COMMENT ON COLUMN NOTICE.TITLE is '제목';
COMMENT ON COLUMN NOTICE.CONTENT is '내용';
COMMENT ON COLUMN NOTICE.IP is 'IP';
COMMENT ON COLUMN NOTICE.NOTICE_PW is '비밀번호';
COMMENT ON COLUMN NOTICE.NFILE1 is '첨부이미지';
COMMENT ON COLUMN NOTICE.NTHUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN NOTICE.NSIZE1 is ' 메인 이미지 크기';
COMMENT ON COLUMN NOTICE.WRITER is '작성자';
COMMENT ON COLUMN NOTICE.RDATE is '작성날짜';
COMMENT ON COLUMN NOTICE.VISIBLE is '출력모드';
COMMENT ON COLUMN NOTICE.NCATE_NO is '카테고리 번호';


/**********************************/
/* Table Name: 리뷰 */
/**********************************/
CREATE TABLE REVIEW(
		REVIEW_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		EB_NO                         		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(10)		 NOT NULL,
		REVIEW_CONTENT                		CLOB(4000)		 NOT NULL,
		REVIEW_PW                     		VARCHAR2(15)		 NOT NULL,
		REVIEW_GRADE                  		NUMBER(5)		 NOT NULL,
		REVIEW_RDATE                  		DATE		 NOT NULL,
		REVIEW_GOOD                   		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO)
);

COMMENT ON TABLE REVIEW is '리뷰';
COMMENT ON COLUMN REVIEW.REVIEW_NO is '리뷰번호';
COMMENT ON COLUMN REVIEW.EB_NO is '이북 번호';
COMMENT ON COLUMN REVIEW.M_NO is '회원번호';
COMMENT ON COLUMN REVIEW.REVIEW_CONTENT is '리뷰 내용';
COMMENT ON COLUMN REVIEW.REVIEW_PW is '리뷰 패스워드';
COMMENT ON COLUMN REVIEW.REVIEW_GRADE is '리뷰 평점';
COMMENT ON COLUMN REVIEW.REVIEW_RDATE is '리뷰 작성일';
COMMENT ON COLUMN REVIEW.REVIEW_GOOD is '추천수';


/**********************************/
/* Table Name: 출판사 홈페이지 바로가기  */
/**********************************/
CREATE TABLE PUBLISHER(
		PUB_NO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		HTTP                          		VARCHAR2(100)		 NOT NULL,
		ETC                           		VARCHAR2(1000)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE PUBLISHER is '출판사 홈페이지 바로가기 ';
COMMENT ON COLUMN PUBLISHER.PUB_NO is '출판사 고유 번호';
COMMENT ON COLUMN PUBLISHER.NAME is '출판사 이름';
COMMENT ON COLUMN PUBLISHER.HTTP is '홈페이지 주소';
COMMENT ON COLUMN PUBLISHER.ETC is '기타 설명';
COMMENT ON COLUMN PUBLISHER.FILE1 is '기타 설명';
COMMENT ON COLUMN PUBLISHER.THUMB1 is '기타 설명';
COMMENT ON COLUMN PUBLISHER.SIZE1 is '기타 설명';
COMMENT ON COLUMN PUBLISHER.RDATE is '등록 날짜';


/**********************************/
/* Table Name: 주문내역 */
/**********************************/
CREATE TABLE PLIST(
		PLISTNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NOT NULL,
		P_DATE                        		DATE		 NOT NULL,
		P_INFOR                       		VARCHAR2(50)		 NOT NULL,
		P_PRICE                       		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE PLIST is '주문내역';
COMMENT ON COLUMN PLIST.PLISTNO is '주문내역 번호';
COMMENT ON COLUMN PLIST.M_NO is '회원 번호';
COMMENT ON COLUMN PLIST.P_DATE is '주문일자';
COMMENT ON COLUMN PLIST.P_INFOR is '주문정보';
COMMENT ON COLUMN PLIST.P_PRICE is '주문 가격';


/**********************************/
/* Table Name: 댓글 */
/**********************************/
CREATE TABLE REPLY(
		REPLY_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		REVIEW_NO                     		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(6)		 NOT NULL,
		REPLY_CONT                    		VARCHAR2(3000)		 NOT NULL,
		REPLY_PW                      		VARCHAR2(20)		 NOT NULL,
		REPLY_DATE                    		DATE		 NOT NULL,
		REPLY_VISIBLE                 		CHAR(1)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (REVIEW_NO) REFERENCES REVIEW (REVIEW_NO)
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLY_NO is '댓글번호';
COMMENT ON COLUMN REPLY.REVIEW_NO is '리뷰번호';
COMMENT ON COLUMN REPLY.M_NO is '회원 번호';
COMMENT ON COLUMN REPLY.REPLY_CONT is '댓글내용';
COMMENT ON COLUMN REPLY.REPLY_PW is '비밀번호';
COMMENT ON COLUMN REPLY.REPLY_DATE is '등록일';
COMMENT ON COLUMN REPLY.REPLY_VISIBLE is '출력모드';


/**********************************/
/* Table Name: 이벤트 메뉴 */
/**********************************/
CREATE TABLE EVENT(
		EVENTNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(300)		 NOT NULL,
		WHATEVENT                     		VARCHAR2(2000)		 NOT NULL,
		SALENO                        		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE EVENT is '이벤트 메뉴';
COMMENT ON COLUMN EVENT.EVENTNO is '이벤트 번호';
COMMENT ON COLUMN EVENT.NAME is '이벤트 이름';
COMMENT ON COLUMN EVENT.WHATEVENT is '이벤트 설명페이지';
COMMENT ON COLUMN EVENT.SALENO is '할인 가격 넘버';
COMMENT ON COLUMN EVENT.RDATE is '등록일';


/**********************************/
/* Table Name: 이번주 책 할인 */
/**********************************/
CREATE TABLE SALEWEEK(
		SALENO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(300)		 NOT NULL,
		SALESBOOK                     		VARCHAR2(300)		 NOT NULL,
		SALE                          		VARCHAR2(300)		 NOT NULL,
		SALEPERIOD                    		VARCHAR2(300)		 NOT NULL,
		EVENTNO                       		NUMBER(10)		 NOT NULL,
		CONTENTS                      		VARCHAR2(50)		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (EVENTNO) REFERENCES EVENT (EVENTNO)
);

COMMENT ON TABLE SALEWEEK is '이번주 책 할인';
COMMENT ON COLUMN SALEWEEK.SALENO is '할인가격넘버';
COMMENT ON COLUMN SALEWEEK.NAME is '이벤트 이름';
COMMENT ON COLUMN SALEWEEK.SALESBOOK is '이벤트 책';
COMMENT ON COLUMN SALEWEEK.SALE is '할인가';
COMMENT ON COLUMN SALEWEEK.SALEPERIOD is '할인 기간';
COMMENT ON COLUMN SALEWEEK.EVENTNO is '이벤트 번호';
COMMENT ON COLUMN SALEWEEK.CONTENTS is '상품 목록';
COMMENT ON COLUMN SALEWEEK.PASSWD is '패스워드';
COMMENT ON COLUMN SALEWEEK.FILE1 is 'FILE1';
COMMENT ON COLUMN SALEWEEK.THUMB1 is 'THUMB1';
COMMENT ON COLUMN SALEWEEK.SIZE1 is 'SIZE1';
COMMENT ON COLUMN SALEWEEK.RDATE is '시간';


