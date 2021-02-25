-- 테이블 삭제 (자식 테이블 선삭제 후 작업할 것)
DROP TABLE cate;

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

DROP SEQUENCE cate_seq;
CREATE SEQUENCE cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

-- 등록
INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 1, '에세이', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 2, '서양철학', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 3, '성공학/경력관리', 1, 'Y', sysdate);

-- 목록
SELECT cateno, cg_no, name, seqno, visible, rdate
FROM cate;

-- 조회
SELECT cateno, cg_no, name, seqno, visible, rdate
FROM cate
WHERE cateno = 1;

-- 수정
UPDATE cate
SET name = '시'
WHERE cateno = 1;

-- 삭제
DELETE
FROM cate
WHERE cateno = 3;

SELECT g.cg_no as g_cg_no, g.cg_name as g_name,
               c.cateno, c.cg_no, c.name, c.seqno, c.visible, c.rdate
    FROM categrp g, cate c
    WHERE g.cg_no = c.cg_no
    ORDER BY g.cg_no ASC, c.seqno ASC