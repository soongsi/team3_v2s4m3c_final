
DROP TABLE member;
DROP TABLE memberph;
DROP TABLE bookshelf;

commit;

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

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


/**********************************/
/* Table Name: 주문 내역 */
/**********************************/
CREATE TABLE memberph(
		orderno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		m_no                      		NUMBER(10)		 NOT NULL,
		p_number                     		NUMBER(20)		 NOT NULL,
		p_infor                        		VARCHAR2(50)		 NOT NULL,
        p_amount                     		NUMBER(20)		 NOT NULL,
        p_state                        		VARCHAR2(30)		 NOT NULL,
        file1                                   VARCHAR(100)          NULL,
        thumb1                              VARCHAR(100)          NULL,
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  
  FOREIGN KEY (m_no) REFERENCES member (m_no)
);

COMMENT ON TABLE memberph is '주문 내역';
COMMENT ON COLUMN memberph.orderno is '주문 번호';
COMMENT ON COLUMN memberph.m_no is '회원 번호';
COMMENT ON COLUMN memberph.p_number is '상품 번호';
COMMENT ON COLUMN memberph.p_infor is '상품 정보';
COMMENT ON COLUMN memberph.p_amount is '수량 ';
COMMENT ON COLUMN memberph.p_state is '주문상태 ';
COMMENT ON COLUMN memberph.file1 is '메인 이미지';
COMMENT ON COLUMN memberph.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN memberph.size1 is ' 메인 이미지 크기';

DROP SEQUENCE memberph_seq;
CREATE SEQUENCE memberph_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


/**********************************/
/* Table Name: 내 서재 */
/**********************************/
CREATE TABLE bookshelf(
		bookshelfno                   		NUMBER(20)		 NOT NULL		 PRIMARY KEY,
		m_no                      		NUMBER(10)		 NOT NULL,
		p_name                     		VARCHAR2(20)		 NOT NULL,
        p_infor                     		VARCHAR2(50)		 NOT NULL,
        file1                                   VARCHAR(100)          NULL,
        thumb1                              VARCHAR(100)          NULL,
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  
  FOREIGN KEY (m_no) REFERENCES member (m_no)
);

COMMENT ON TABLE bookshelf is '내 서재';
COMMENT ON COLUMN bookshelf.bookshelfno is '서재 번호';
COMMENT ON COLUMN bookshelf.m_no is '회원 번호';
COMMENT ON COLUMN bookshelf.p_name is '상품 이름 ';
COMMENT ON COLUMN bookshelf.p_infor is '상품 정보 ';
COMMENT ON COLUMN memberph.file1 is '메인 이미지';
COMMENT ON COLUMN memberph.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN memberph.size1 is ' 메인 이미지 크기';

DROP SEQUENCE bookshelf_seq;
CREATE SEQUENCE bookshelf_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

/**********************************/
/*               등록                   */
/**********************************/
-- 회원 정보
-- 회원 관리용 계정, Q/A 용 계정
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA관리자', 'abc1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '고객관리자', 'def1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);

COMMIT;

-- 주문 내역
INSERT INTO memberph(orderno, m_no, p_number, p_infor, p_amount, p_state, file1, thumb1, size1)
VALUES(memberph_seq.nextval, 1, 1, '상품 정보', 2, '배송 준비중', 'summer.jpg', 'summer_t.jpg', 23657);

-- 내 서재
INSERT INTO bookshelf(bookshelfno, m_no, p_name, p_infor, file1, thumb1, size1)
VALUES(bookshelf_seq.nextval, 1, '이름', '상품 정보', 'summer.jpg', 'summer_t.jpg', 23657 );

/**********************************/
/*               목록                   */
/**********************************/

-- 회원 정보
SELECT m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate
FROM member
ORDER BY m_no ASC;

         
-- 주문 내역
SELECT orderno, m_no, p_number, p_infor, p_amount, p_state, file1, thumb1, size1
FROM memberph      
ORDER BY orderno DESC;

   
-- 내 서재
SELECT bookshelfno, m_no, p_name, p_infor, file1, thumb1, size1
FROM bookshelf
ORDER BY bookshelfno ASC;

  
/**********************************/
/*               수정                   */
/**********************************/


-- 회원 정보
UPDATE memberin
SET id='yyj0113', password = '1234', email='yyj0113@naver.com', address = '경기도 남양주시 오남읍'
WHERE m_no = 1;

-- 주문 내역
UPDATE memberph
SET p_number=2, p_infor='책 정보' , p_amount =3, p_state='배송중'
WHERE orderno = 1;


-- 내 서재
UPDATE bookshelf
SET p_name='책이름', p_infor = '책 정보'
WHERE bookshelfno = 1;

/**********************************/
/*               삭제                   */
/**********************************/

-- 회원 정보
DELETE
FROM memberin
WHERE m_no = 1;

-- 주문 내역
DELETE 
FROM memberph
WHERE orderno = 1;


-- 내 서재
DELETE 
FROM bookshelf
WHERE bookshelfno = 1;



/**패스워드 검사**/
SELECT COUNT(*) as password_cnt
FROM memberin
WHERE m_no=2 AND password='asd';



5. 삭제
1) 모두 삭제
DELETE FROM member;
 
2) 특정 회원 삭제
DELETE FROM member
WHERE m_no=15;

COMMIT;

 
6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(m_no) as cnt
FROM member
WHERE m_no=1 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE m_no=1;

COMMIT;
 
 
7. 로그인
SELECT COUNT(m_no) as cnt
FROM member
WHERE id='user1' AND passwd='1234';
 cnt
 ---
   0


COMMIT;




