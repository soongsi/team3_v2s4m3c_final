-- 테이블 삭제

DROP TABLE member;
DROP TABLE notice;

-- 시퀀스 삭제
DROP SEQUENCE member_seq;
DROP SEQUENCE notice_seq;

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
  
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA관리자', 'abc1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '고객관리자', 'def1234@naver.com', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate);

COMMIT;


/**********************************/
/* Table Name: 공지사항분류 */
/**********************************/
CREATE TABLE ncate(
		ncate_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ncate_name                    		VARCHAR2(100)		 NOT NULL,
		nseqno                        		NUMBER(10)		 NOT NULL,
		nvisible                      		CHAR(1)		 NOT NULL,
		ncate_rdate                   		DATE		 NOT NULL,
		ncate_cnt                     		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE ncate is '공지사항분류';
COMMENT ON COLUMN ncate.ncate_no is '카테고리 번호';
COMMENT ON COLUMN ncate.ncate_name is '카테고리 이름';
COMMENT ON COLUMN ncate.nseqno is '출력 순서';
COMMENT ON COLUMN ncate.nvisible is '출력 모드';
COMMENT ON COLUMN ncate.ncate_rdate is '등록일';
COMMENT ON COLUMN ncate.ncate_cnt is '등록된 글 수';

DROP SEQUENCE ncate_seq;
CREATE SEQUENCE ncate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 ?> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

/**********************************/
/* Table Name: 공지사항 */
/**********************************/
CREATE TABLE notice(
		noticeno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		m_no                      		NUMBER(10)		 NULL ,
        ncate_no                      		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(100)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
        ip                                         VARCHAR2(15)         NOT NULL,
        notice_pw                               VARCHAR2(15)         NOT NULL,
        nfile1                               VARCHAR(100)          NULL,
        nthumb1                              VARCHAR(100)          NULL,
        nsize1                               NUMBER(10)      DEFAULT 0 NULL,  
		writer                        		VARCHAR2(20)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		visible                       		CHAR(1)		 DEFAULT 'Y'		 NOT NULL,
  FOREIGN KEY (m_no) REFERENCES member (m_no),
  FOREIGN KEY (ncate_no) REFERENCES ncate (ncate_no)
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '공지사항번호';
COMMENT ON COLUMN notice.ncate_no is '공지사항 카테고리 번호';
COMMENT ON COLUMN notice.m_no is '회원번호';
COMMENT ON COLUMN notice.title is '제목';
COMMENT ON COLUMN notice.content is '내용';
COMMENT ON COLUMN notice.ip is 'IP';
COMMENT ON COLUMN notice.notice_pw is '비밀번호';
COMMENT ON COLUMN notice.nfile1 is '첨부이미지';
COMMENT ON COLUMN notice.nthumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN notice.nsize1 is ' 메인 이미지 크기';
COMMENT ON COLUMN notice.writer is '작성자';
COMMENT ON COLUMN notice.rdate is '작성날짜';
COMMENT ON COLUMN notice.visible is '출력모드';

CREATE SEQUENCE notice_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지


SELECT * FROM member;

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, '첫번째 공지사항입니다', '공지사항 내용입니다.','127.0.0.1', 1234, 'aa.jpg', 'aa_t.jpg', 23657, 'notice1', sysdate, 'Y');

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, 'TEST', 'TEST','127.0.0.1', 1234,  'bb.jpg', 'bb.jpg', 23657, 'notice1', sysdate, 'Y' );

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, 'TEST', 'TEST','127.0.0.1', 1234,  'notice1.jpg', 'notice1_t.jpg', 23657, 'notice1', sysdate, 'Y' );


-- 목록
SELECT noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible
FROM notice
ORDER BY noticeno ASC;

-- 조회
SELECT noticeno, m_no, title, content, ip,  notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible
FROM notice
WHERE noticeno=1;

-- 수정
UPDATE notice
SET title='두번째', content='두번째 내용', nfile1='notice2.png', nthumb1='notice2_t.jpg', nsize1=5000, visible='N'
WHERE noticeno = 2;

-- 패스워드 검사
SELECT COUNT(*) as passwd_cnt
FROM notice
WHERE noticeno=1 AND notice_pw='1234';

SELECT noticeno, notice_pw
FROM notice 
ORDER BY noticeno DESC;   

-- 삭제
DELETE notice
WHERE noticeno = 4;

UPDATE notice
SET visible='Y'
WHERE noticeno=1;

UPDATE notice
SET visible='N'
WHERE noticeno=1;

COMMIT;

SELECT noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible
FROM notice
ORDER BY noticeno ASC;

