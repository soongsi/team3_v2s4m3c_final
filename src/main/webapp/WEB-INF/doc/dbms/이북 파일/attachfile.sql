/**********************************/
/* Table Name: 첨부파일 */
/**********************************/
DROP TABLE ebook_attachfile;
CREATE TABLE ebook_attachfile(
        attachfileno                  NUMBER(10)         NOT NULL         PRIMARY KEY,
        eb_no                   NUMBER(10)         NULL ,
        fname                             VARCHAR2(200)         NOT NULL,
        fupname                      VARCHAR2(200)         NOT NULL,
        thumb                         VARCHAR2(200)         NULL ,
        fsize                                 NUMBER(10)         DEFAULT 0         NOT NULL,
        rdate                           DATE     NOT NULL,
  FOREIGN KEY (eb_no) REFERENCES ebook (eb_no)
);

COMMENT ON TABLE ebook_attachfile is '첨부파일';
COMMENT ON COLUMN ebook_attachfile.attachfileno is '첨부파일번호';
COMMENT ON COLUMN ebook_attachfile.eb_no is '이북 번호';
COMMENT ON COLUMN ebook_attachfile.fname is '원본 파일명';
COMMENT ON COLUMN ebook_attachfile.fupname is '업로드 파일명';
COMMENT ON COLUMN ebook_attachfile.thumb is 'Thumb 파일명';
COMMENT ON COLUMN ebook_attachfile.fsize is '파일 사이즈';
COMMENT ON COLUMN ebook_attachfile.rdate is '등록일';

DROP SEQUENCE ebook_attachfile_seq;
CREATE SEQUENCE ebook_attachfile_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- 1) 등록
INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 1, '여행의_이유.jpg', '여행의_이유_1.jpg', '여행의_이유_t.jpg', 1000, sysdate);

INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 2, '그럼에도_불구하고.jpg', '그럼에도_불구하고_1.jpg', '그럼에도_불구하고_t.jpg', 2000, sysdate);
             
INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 3, '안녕,_소중한_사람.jpg', '안녕,_소중한_사람_1.jpg', '안녕,_소중한_사람_t.jpg', 3000, sysdate);        
             
COMMIT;

-- 2) 목록( eb_no 기준 내림 차순, attachfileno 기준 오르차순)
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
ORDER BY eb_no DESC,  attachfileno ASC;


-- 3) PK 기준 하나의 레코드 조회
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE attachfileno = 1;

-- 4) FK 기준 eb_no가 동일한 레코드 조회, fname 오름차순
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE eb_no = 1
ORDER BY fname ASC;


-- 5) 하나의 파일 삭제
DELETE FROM ebook_attachfile
WHERE attachfileno = 1;

COMMIT;

-- 6) FK eb_no 부모키 별 조회
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE eb_no=1;

-- 부모키별 갯수 산출
SELECT COUNT(*) as cnt
FROM ebook_attachfile
WHERE contentsno=1;


-- 7) FK 부모 테이블별 레코드 삭제
DELETE FROM ebook_attachfile
WHERE eb_no=1;

   
-- 8) Contents, Attachfile join
    SELECT c.title, 
               a.attachfileno, a.eb_no, a.fname, a.fupname, a.thumb, a.fsize, a.rdate
    FROM contents c, ebook_attachfile a
    WHERE c.eb_no = a.eb_no
    ORDER BY c.eb_no DESC,  a.eb_no ASC;

-- 9) 조회
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE attachfileno=1;

-- 선택 파일 삭제 쿼리
DELETE FROM ebook_attachfile
WHERE attachfileno IN ( 10, 11, 12, 13 );
