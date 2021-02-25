-- ���̺� ���� (�ڽ� ���̺� ������ �� �۾��� ��)
DROP TABLE ebook;
COMMIT;
/**********************************/
/* Table Name: �̺� */
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

COMMENT ON TABLE ebook is '�̺�';
COMMENT ON COLUMN ebook.eb_no is '�̺� ��ȣ';
COMMENT ON COLUMN ebook.cateno is 'ī�װ� ��ȣ';
COMMENT ON COLUMN ebook.eb_title is '����';
COMMENT ON COLUMN ebook.eb_author is '����';
COMMENT ON COLUMN ebook.eb_publisher is '���ǻ�';
COMMENT ON COLUMN ebook.eb_pdate is '�Ⱓ��';
COMMENT ON COLUMN ebook.eb_useinfo is '�̿�ȳ�';
COMMENT ON COLUMN ebook.eb_device is '�������';
COMMENT ON COLUMN ebook.eb_infor is '���� ����';
COMMENT ON COLUMN ebook.eb_price is '�ǸŰ�';
COMMENT ON COLUMN ebook.eb_saletot is '�Ǹŷ�';
COMMENT ON COLUMN ebook.eb_page is '������ ��';
COMMENT ON COLUMN ebook.eb_file1 is '�̺� ����';
COMMENT ON COLUMN ebook.eb_size1 is '�̺� ���� ũ��';
COMMENT ON COLUMN ebook.eb_file2 is '���� �̹���';
COMMENT ON COLUMN ebook.eb_size2 is '���� �̹��� ���� ũ��';
COMMENT ON COLUMN ebook.eb_thumb is '�����';
COMMENT ON COLUMN ebook.eb_word is '�˻���';
COMMENT ON COLUMN ebook.rdate is '�����';

COMMIT;

DROP SEQUENCE ebook_seq;
CREATE SEQUENCE ebook_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
ALTER TABLE ebook DROP CONSTRAINT 'SYS_C007445';

 
  
-- ���
INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 1, '������ ����', '�迵��', '���е���', '2019-06-03', '', '������,�����е�', '������ �� �������� �켱 �۰�����, �״������δ� ���� �����ڿ���.��
����-�ϻ�-������ ���� �մ�,
��ȩ ���� ��Ȥ���� �̾߱�', 9500, 0, 150, '', '', '', '', '', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 2, '���ַ�', '���ݷ� ��Ű�ƺ���/�̽ÿ� ��', '��Ŭ����', '2018-11-06', '', '�ȵ���̵���,�ȵ���̵� �е�', '�Ƿ�ü�� ���� �޵�ġ ������ ������ ��Ű�ƺ����� �����ַС�', 2200, 0, 64, '', '', '', '', '', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 3, '����� �µ��� ���� �ʰ�', '����ɸ� ��/�ڿ��� ��', '������', '2020-07-01', '', '����å �ܸ���', '������, ȸ�翡��, �ΰ����迡��
��ȸ�� ������� ���ذ� �پ��� ��� ����', 9800, 0, 100, '', '', '', '', '', sysdate);


-- ī�װ� �׷� & ī�װ� ��ȸ
SELECT cg.cg_no, cg.cg_name, c.cateno, c.name
FROM cate c
LEFT JOIN categrp cg
ON c.cg_no = cg.cg_no;


SELECT eb_title, eb_file1, eb_size1
FROM ebook;

-- ���
SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, rdate
FROM ebook;

-- ��ȸ
SELECT eb_no, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, eb_device, eb_infor, eb_price, eb_saletot, eb_page, rdate
FROM ebook
WHERE eb_no = 1;

-- ����
UPDATE ebook
SET eb_saletot = 28104, eb_page = 200
WHERE eb_no = 1;

-- ����
DELETE
FROM ebook
WHERE eb_no = 3;

SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate, r
    FROM (
              SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate, rownum as r
              FROM (
                        SELECT eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_price, eb_saletot, eb_page, eb_thumb, word, rdate
                        FROM ebook
                        WHERE (eb_title LIKE '%�־���%' 
                            OR eb_author LIKE '%�־���%' )
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