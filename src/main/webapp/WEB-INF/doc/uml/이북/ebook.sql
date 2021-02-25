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


/**********************************/
/* Table Name: 이북 */
/**********************************/
CREATE TABLE ebook(
		eb_no                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		eb_title                      		VARCHAR2(200)		 NOT NULL,
		eb_author                     		VARCHAR2(100)		 NOT NULL,
		eb_publisher                  		VARCHAR2(20)		 NOT NULL,
		eb_pdate                      		DATE		 NULL ,
		eb_useinfo                    		VARCHAR2(500)		 NULL ,
		eb_device                     		VARCHAR2(500)		 NOT NULL,
		eb_infor                      		LONG		 NULL ,
		eb_price                      		NUMBER(10)		 NOT NULL,
		eb_saletot                    		NUMBER(10)		 DEFAULT 0		 NULL ,
		eb_page                       		NUMBER(10)		 NULL ,
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
COMMENT ON COLUMN ebook.rdate is '등록일';


