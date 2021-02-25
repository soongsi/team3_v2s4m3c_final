-- 테이블 삭제
DROP TABLE customer CASCADE CONSTRAINTS; 

-- 컬럼 크기 변경
ALTER TABLE customer MODIFY m_no NUMBER(6);

/**********************************/
/* Table Name: 고객센터 */
/**********************************/
CREATE TABLE customer(
		cs_no                         		NUMBER(10)		     NOT NULL		 PRIMARY KEY,
		cs_type                       		VARCHAR2(20)		     NOT NULL,
		cs_title                      		    VARCHAR2(30)		     NOT NULL,
		cs_contents                   	 	CLOB 	                 NOT NULL,
		cs_file1                      		    VARCHAR2(100)		 NULL ,
		cs_thumb1                     		VARCHAR2(100)		 NULL ,
		cs_size1                      		NUMBER(10)		     DEFAULT 0		 NULL ,
		cs_rdate                      		DATE		                 NOT NULL,
		cs_cnt                        		    NUMBER(10)		     DEFAULT 0		 NOT NULL,
		m_no                          		NUMBER(6)		     NULL,
        cs_passwd                           VARCHAR2(60)     DEFAULT '123456'    NOT NULL,
        FOREIGN KEY (m_no) REFERENCES member (m_no)
);

COMMENT ON TABLE customer is '고객센터';
COMMENT ON COLUMN customer.cs_no is '질문 번호';
COMMENT ON COLUMN customer.cs_type is '질문 유형';
COMMENT ON COLUMN customer.cs_title is '제목';
COMMENT ON COLUMN customer.cs_contents is '내용';
COMMENT ON COLUMN customer.cs_file1 is '메인 이미지';
COMMENT ON COLUMN customer.cs_thumb1 is '메인 이미지 미리보기';
COMMENT ON COLUMN customer.cs_size1 is '메인 이미지 크기';
COMMENT ON COLUMN customer.cs_rdate is '등록일';
COMMENT ON COLUMN customer.cs_cnt is '질문 조회수';
COMMENT ON COLUMN customer.m_no is '회원 번호';
COMMENT ON COLUMN customer.cs_passwd is '관리자 비밀번호';

DROP SEQUENCE cs_seq;
CREATE SEQUENCE cs_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- 컬럼 삭제
ALTER TABLE customer DROP COLUMN email;

-- 컬럼 추가
ALTER TABLE customer ADD cs_passwd VARCHAR2(60) DEFAULT 'a084' NOT NULL;

-- 컬럼 수정
ALTER TABLE customer
MODIFY (cs_title VARCHAR2(70));

COMMIT;

-- 질문 등록
INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A01', '상품문의', 'OOO 이북 불량', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A02', '결제장애', 'OOO 이북 환불', 'b_img2.jpg', 'b_img2_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A03', '환불', 'OOO 이북 환불', 'b_img4.jpg', 'b_img4_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A04', '개선사항', 'OOO 이북 불량', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A99', '기타', 'OOO 이북 불량', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A01', '상품문의', 'ABCDE 이북 불량', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

-- 목록
SELECT cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, cs_cnt, m_no, cs_passwd
FROM customer
ORDER BY cs_no ASC;

-- 조회
SELECT m_no, cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, cs_cnt
FROM customer
WHERE cs_no=1
ORDER BY cs_no ASC;

-- 삭제
DELETE FROM customer
WHERE cs_no= 3;

-- 수정
UPDATE customer 
SET cs_title='제목', cs_contents='내용', email='email@domain.do'
WHERE cs_no = 7;

COMMIT;

