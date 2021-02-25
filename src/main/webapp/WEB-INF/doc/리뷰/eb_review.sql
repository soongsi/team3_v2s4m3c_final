-- ���̺� ����
DROP TABLE categrp;
DROP TABLE cate;
DROP TABLE ebook;
DROP TABLE member;
DROP TABLE review;


-- ������ ����
DROP SEQUENCE categrp_seq;
DROP SEQUENCE cate_seq;
DROP SEQUENCE ebook_seq;
DROP SEQUENCE member_seq;
DROP SEQUENCE review_seq;


/**********************************/
/* Table Name: ī�װ� �׷� */
/**********************************/
CREATE TABLE categrp(
		cg_no                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cg_name                       		VARCHAR2(100)		 NOT NULL,
		cg_seqno                      		NUMBER(10)		 NOT NULL,
		cg_visible                    		CHAR(1)		 DEFAULT 'Y'		 NOT NULL,
		cg_rdate                      		DATE		 NOT NULL
);

COMMENT ON TABLE categrp is 'ī�װ� �׷�';
COMMENT ON COLUMN categrp.cg_no is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN categrp.cg_name is 'ī�װ� �׷� �̸�';
COMMENT ON COLUMN categrp.cg_seqno is '��� ����';
COMMENT ON COLUMN categrp.cg_visible is '��� ���';
COMMENT ON COLUMN categrp.cg_rdate is '�׷� ������';

CREATE SEQUENCE categrp_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����


/**********************************/
/* Table Name: ī�װ� */
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

COMMENT ON TABLE cate is 'ī�װ�';
COMMENT ON COLUMN cate.cateno is 'ī�װ� ��ȣ';
COMMENT ON COLUMN cate.cg_no is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN cate.name is 'ī�װ� �̸�';
COMMENT ON COLUMN cate.seqno is '��� ����';
COMMENT ON COLUMN cate.visible is '��� ���';
COMMENT ON COLUMN cate.rdate is '�����';

CREATE SEQUENCE cate_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

/**********************************/
/* Table Name: �̺� */
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

CREATE SEQUENCE ebook_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

/**********************************/
/* Table Name: ȸ�� ���� */
/**********************************/
CREATE TABLE member(
      m_no NUMBER(6) NOT NULL, -- ȸ�� ��ȣ, ���ڵ带 �����ϴ� �÷� 
      id             VARCHAR(20)   NOT NULL UNIQUE, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 ���� 
      passwd      VARCHAR(60)   NOT NULL, -- �н�����, ������ ����
      mname      VARCHAR(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
      email      VARCHAR(60)   NOT NULL, -- �н�����, ������ ����
      tel            VARCHAR(14)   NOT NULL, -- ��ȭ��ȣ
      zipcode     VARCHAR(5)        NULL, -- �����ȣ, 12345
      address1    VARCHAR(80)       NULL, -- �ּ� 1
      address2    VARCHAR(50)       NULL, -- �ּ� 2
      mdate       DATE             NOT NULL, -- ������    
      PRIMARY KEY (m_no)                     -- �ѹ� ��ϵ� ���� �ߺ� �ȵ�
);

COMMENT ON TABLE MEMBER is 'ȸ��';
COMMENT ON COLUMN MEMBER.M_NO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN MEMBER.ID is '���̵�';
COMMENT ON COLUMN MEMBER.PASSWD is '�н�����';
COMMENT ON COLUMN MEMBER.MNAME is '����';
COMMENT ON COLUMN MEMBER.EMAIL is '�̸���';
COMMENT ON COLUMN MEMBER.TEL is '��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.ZIPCODE is '�����ȣ';
COMMENT ON COLUMN MEMBER.ADDRESS1 is '�ּ�1';
COMMENT ON COLUMN MEMBER.ADDRESS2 is '�ּ�2';
COMMENT ON COLUMN MEMBER.MDATE is '������';

CREATE SEQUENCE member_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '������/��', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 1, '������', 1, 'Y', sysdate);

INSERT INTO ebook(eb_no, cateno, eb_title, eb_author, eb_publisher, eb_pdate, eb_useinfo, 
eb_device, eb_infor, eb_price, eb_saletot, eb_page, eb_file1, eb_size1, eb_file2, eb_size2, eb_thumb, rdate)
VALUES(ebook_seq.nextval, 1, '����� �µ��� ���� �ʰ�', '����ɸ� ��/�ڿ��� ��', '������', '2020-07-01', '', '����å �ܸ���', '������, ȸ�翡��, �ΰ����迡��
��ȸ�� ������� ���ذ� �پ��� ��� ����', 9800, 0, 100, '', '', '', '', '', sysdate);

INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA������', 'abc1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '��������', 'def1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

select * from categrp
SELECT * FROM cate
SELECT * FROM ebook
SELECT * FROM member

/**********************************/
/* Table Name: ���� */
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

COMMENT ON TABLE review is '����';
COMMENT ON COLUMN review.review_no is '�����ȣ';
COMMENT ON COLUMN review.eb_no is '�̺� ��ȣ';
COMMENT ON COLUMN review.m_no is 'ȸ����ȣ';
COMMENT ON COLUMN review.review_content is '���� ����';
COMMENT ON COLUMN review.review_pw is '���� �н�����';
COMMENT ON COLUMN review.review_grade is '���� ����';
COMMENT ON COLUMN review.review_rdate is '���� �ۼ���';
COMMENT ON COLUMN review.review_good is '��õ��';

DROP SEQUENCE review_seq;
CREATE SEQUENCE review_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- ���
INSERT INTO review(review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good)
VALUES(review_seq.nextval,2,1,'å ������ ������ �� �����ؼ� �о�þ��.���뵵 �����ϰ� ��ȭ�� �־ �ݹ� ���� �� �־����ϴ�.',
    '1234','10', sysdate, 5);
    
INSERT INTO review(review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good)
VALUES(review_seq.nextval,2,1,'���� �ð��� �鿩 �Ʋ� �а� �ֽ��ϴ�.',
    '1234','8', sysdate,12);

-- ���
SELECT review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good
FROM review
ORDER BY review_no ASC;

-- ��ȸ
SELECT review_no, eb_no, m_no, review_content, review_pw, review_grade, review_rdate, review_good
FROM review
WHERE review_no=1;

-- ����
UPDATE review
SET review_content='���䳻��', review_grade=7, review_good=32
WHERE review_no = 3;

-- ����
DELETE review
WHERE review_no = 3;

COMMIT;



