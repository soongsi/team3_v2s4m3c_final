-- 테이블 삭제 (자식 테이블 선삭제 후 작업할 것)
DROP TABLE categrp;

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

DROP SEQUENCE categrp_seq;
CREATE SEQUENCE categrp_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

-- 등록
INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '에세이/시', 1, 'Y', sysdate);

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '인문', 1, 'Y', sysdate);

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '자기 계발', 1, 'Y', sysdate);

-- 목록
SELECT cg_no, cg_name, cg_seqno, cg_visible, cg_rdate
FROM categrp;

-- 조회
SELECT cg_no, cg_name, cg_seqno, cg_visible, cg_rdate
FROM categrp
WHERE cg_no = 1;

-- 수정
UPDATE categrp
SET cg_name = '역사'
WHERE cg_no = 1;

-- 삭제
DELETE
FROM categrp
WHERE cg_no = 3;
