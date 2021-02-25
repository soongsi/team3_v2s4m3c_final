-- 테이블 삭제
DROP TABLE categrp;
DROP TABLE cate;
DROP TABLE ebook;
DROP TABLE member;
DROP TABLE review;


-- 시퀀스 삭제
DROP SEQUENCE categrp_seq;
DROP SEQUENCE cate_seq;
DROP SEQUENCE ebook_seq;
DROP SEQUENCE member_seq;
DROP SEQUENCE review_seq;


/**********************************/
/* Table Name: 카테고리 그룹 */
/**********************************/
CREATE TABLE categrp(
		cg_no                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cg_name                       		VARCHAR2(100)		 NOT NULL,
		cg_seqno                      		NUMBER(10)		 NOT NULL,
		cg_visible                    		CHAR(1)		 DEFAULT 'Y'		 NOT NULL,
		cg_rdate                      		DATE		 NOT NULL
);

COMMENT ON TABLE categrp is '카테고리 그룹';
COMMENT ON COLUMN categrp.cg_no is '카테고리 그룹 번호';
COMMENT ON COLUMN categrp.cg_name is '카테고리 그룹 이름';
COMMENT ON COLUMN categrp.cg_seqno is '출력 순서';
COMMENT ON COLUMN categrp.cg_visible is '출력 모드';
COMMENT ON COLUMN categrp.cg_rdate is '그룹 생성일';

CREATE SEQUENCE categrp_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE cate(
		cateno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cg_no                         		NUMBER(10)		 NULL ,
		name                          		VARCHAR2(100)		 NOT NULL,
		seqno                         		NUMBER(10)		 NOT NULL,
		visible                       		CHAR(1)		 DEFAULT 'Y'		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY (cg_no) REFERENCES categrp (cg_no)
);

COMMENT ON TABLE cate is '카테고리';
COMMENT ON COLUMN cate.cateno is '카테고리 번호';
COMMENT ON COLUMN cate.cg_no is '카테고리 그룹 번호';
COMMENT ON COLUMN cate.name is '카테고리 이름';
COMMENT ON COLUMN cate.seqno is '출력 순서';
COMMENT ON COLUMN cate.visible is '출력 모드';
COMMENT ON COLUMN cate.rdate is '등록일';

CREATE SEQUENCE cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

/**********************************/
/* Table Name: 이북 */
/**********************************/
DROP TABLE ebook;

CREATE TABLE ebook(
		eb_no                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		eb_title                      		VARCHAR2(200)		 NOT NULL,
		eb_author                     		VARCHAR2(100)		 NOT NULL,
		eb_publisher                  		VARCHAR2(50)		 NOT NULL,
		eb_pdate                      		VARCHAR2(20)		 NULL ,
        eb_useinfo                          VARCHAR2(500)   NULL,
        eb_device                           VARCHAR2(500)   NOT NULL,
		eb_infor                      		CLOB		 NULL ,
		eb_price                      		NUMBER(10)		 NOT NULL,
		eb_saletot                    		NUMBER(10)		 DEFAULT 0		 NULL ,
		eb_page                       		NUMBER(10)		 DEFAULT 0	 NULL ,
        eb_file1                                   VARCHAR(100)          NULL,
        eb_size1                                 NUMBER(10)      DEFAULT 0 NULL, 
        eb_file2                                   VARCHAR(100)          NULL,
        eb_size2                                 NUMBER(10)      DEFAULT 0 NULL, 
        eb_thumb                                VARCHAR(100)          NULL,
        word                                 VARCHAR(300)          NULL,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY (cateno) REFERENCES cate (cateno)
);

COMMENT ON TABLE ebook is '이북';
COMMENT ON COLUMN ebook.eb_no is '이북 번호';
COMMENT ON COLUMN ebook.cateno is '카테고리 번호';
COMMENT ON COLUMN ebook.eb_title is '제목';
COMMENT ON COLUMN ebook.eb_author is '저자';
COMMENT ON COLUMN ebook.eb_publisher is '출판사';
COMMENT ON COLUMN ebook.eb_pdate is '출간일';
COMMENT ON COLUMN ebook.eb_useinfo is '이용안내';
COMMENT ON COLUMN ebook.eb_device is '지원기기';
COMMENT ON COLUMN ebook.eb_infor is '도서 정보';
COMMENT ON COLUMN ebook.eb_price is '판매가';
COMMENT ON COLUMN ebook.eb_saletot is '판매량';
COMMENT ON COLUMN ebook.eb_page is '페이지 수';
COMMENT ON COLUMN ebook.eb_file1 is '이북 파일';
COMMENT ON COLUMN ebook.eb_size1 is '이북 파일 크기';
COMMENT ON COLUMN ebook.eb_file2 is '메인 이미지';
COMMENT ON COLUMN ebook.eb_size2 is '메인 이미지 파일 크기';
COMMENT ON COLUMN ebook.eb_thumb is '썸네일';
COMMENT ON COLUMN ebook.eb_word is '검색어';
COMMENT ON COLUMN ebook.rdate is '등록일';

COMMIT;

CREATE SEQUENCE ebook_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

/**********************************/
/* Table Name: 회원 정보 */
/**********************************/
CREATE TABLE member(
      m_no NUMBER(6) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
      id             VARCHAR(20)   NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
      passwd      VARCHAR(60)   NOT NULL, -- 패스워드, 영숫자 조합
      mname      VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
      email      VARCHAR(60)   NOT NULL, -- 패스워드, 영숫자 조합
      tel            VARCHAR(14)   NOT NULL, -- 전화번호
      zipcode     VARCHAR(5)        NULL, -- 우편번호, 12345
      address1    VARCHAR(80)       NULL, -- 주소 1
      address2    VARCHAR(50)       NULL, -- 주소 2
      mdate       DATE             NOT NULL, -- 가입일    
      PRIMARY KEY (m_no)                     -- 한번 등록된 값은 중복 안됨
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

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '에세이/시', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 1, '에세이', 1, 'Y', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, 
eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 1, '기분이 태도가 되지 않게', '레몬심리 저/박영란 역', '갤리온', '2020-07-01', '', '전자책 단말기', '집에서, 회사에서, 인간관계에서
후회가 사라지고 오해가 줄어드는 기분 사용법', 9800, 0, 100, '', '', '', '', '', sysdate);

INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA관리자', 'abc1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '고객관리자', 'def1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);

select * from categrp
SELECT * FROM cate
SELECT * FROM ebook
SELECT * FROM member

/**********************************/
/* Table Name: 리뷰 */
/**********************************/
CREATE TABLE review(
		review_no                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		eb_no                         		NUMBER(10)		 NOT NULL,
		m_no                      		NUMBER(10)		 NOT NULL ,
		review_content                		CLOB		 NOT NULL,
        review_pw                           VARCHAR2(15)         NOT NULL,
		review_grade                  		NUMBER(5)		 NOT NULL,
		review_rdate                  		DATE		 NOT NULL,
		review_good                   		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (m_no) REFERENCES member (m_no),
  FOREIGN KEY (eb_no) REFERENCES ebook (eb_no)
);

COMMENT ON TABLE review is '리뷰';
COMMENT ON COLUMN review.review_no is '리뷰번호';
COMMENT ON COLUMN review.eb_no is '이북 번호';
COMMENT ON COLUMN review.m_no is '회원번호';
COMMENT ON COLUMN review.review_content is '리뷰 내용';
COMMENT ON COLUMN review.review_pw is '리뷰 패스워드';
COMMENT ON COLUMN review.review_grade is '리뷰 평점';
COMMENT ON COLUMN review.review_rdate is '리뷰 작성일';
COMMENT ON COLUMN review.review_good is '추천수';

DROP SEQUENCE review_seq;
CREATE SEQUENCE review_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- 등록
INSERT INTO review(review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good)
VALUES(review_seq.nextval,2,1,'책 제목이 마음에 들어서 구입해서 읽어봤어요.내용도 간단하고 삽화도 있어서 금방 읽을 수 있었습니다.',
    '1234','10', sysdate, 5);
    
INSERT INTO review(review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good)
VALUES(review_seq.nextval,2,1,'매일 시간을 들여 아껴 읽고 있습니다.',
    '1234','8', sysdate,12);

-- 목록
SELECT review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good
FROM review
ORDER BY review_no ASC;

-- 조회
SELECT review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good
FROM review
WHERE review_no=1;

-- 수정
UPDATE review
SET review_content='리뷰내용', review_grade=7, review_good=32
WHERE review_no = 3;

-- 삭제
DELETE review
WHERE review_no = 3;

COMMIT;



