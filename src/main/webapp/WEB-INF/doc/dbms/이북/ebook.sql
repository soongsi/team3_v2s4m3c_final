-- 테이블 삭제 (자식 테이블 선삭제 후 작업할 것)
DROP TABLE ebook;
COMMIT;
/**********************************/
/* Table Name: 이북 */
/**********************************/
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

DROP SEQUENCE ebook_seq;
CREATE SEQUENCE ebook_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
ALTER TABLE ebook DROP CONSTRAINT 'SYS_C007445';

 
  
-- 등록
INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 1, '여행의 이유', '김영하', '문학동네', '2019-06-03', '', '아이폰,아이패드', '“나는 그 무엇보다 우선 작가였고, 그다음으로는 역시 여행자였다.”
여행-일상-여행의 고리를 잇는,
아홉 개의 매혹적인 이야기', 9500, 0, 150, '', '', '', '', '', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 2, '군주론', '니콜로 마키아벨리/이시연 역', '더클래식', '2018-11-06', '', '안드로이드폰,안드로이드 패드', '피렌체의 군주 메디치 가문에 바쳐진 마키아벨리의 《군주론》', 2200, 0, 64, '', '', '', '', '', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 3, '기분이 태도가 되지 않게', '레몬심리 저/박영란 역', '갤리온', '2020-07-01', '', '전자책 단말기', '집에서, 회사에서, 인간관계에서
후회가 사라지고 오해가 줄어드는 기분 사용법', 9800, 0, 100, '', '', '', '', '', sysdate);


-- 카테고리 그룹 & 카테고리 조회
SELECT cg.cg_no, cg.cg_name, c.cateno, c.name
FROM cate c
LEFT JOIN categrp cg
ON c.cg_no = cg.cg_no;


SELECT eb_title, eb_file1, eb_size1
FROM ebook;

-- 목록
SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, rdate
FROM ebook;

-- 조회
SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, rdate
FROM ebook
WHERE eb_no = 1;

-- 수정
UPDATE ebook
SET eb_saletot = 28104, eb_page = 200
WHERE eb_no = 1;

-- 삭제
DELETE
FROM ebook
WHERE eb_no = 3;

SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate, r
    FROM (
              SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate, rownum as r
              FROM (
                        SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate
                        FROM ebook
                        WHERE (eb_title LIKE '%애쓰지%' 
                            OR eb_author LIKE '%애쓰지%' )
                        ORDER BY eb_no DESC
              )
    )
    WHERE r >= 1 AND r <= 2;


SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, rdate
FROM (
    SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, rdate
    FROM ebook
    ORDER BY rdate DESC
)
WHERE ROWNUM <= 3;