-- ���̺� ����
DROP TABLE customer CASCADE CONSTRAINTS; 

-- �÷� ũ�� ����
ALTER TABLE customer MODIFY m_no NUMBER(6);

/**********************************/
/* Table Name: ������ */
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

COMMENT ON TABLE customer is '������';
COMMENT ON COLUMN customer.cs_no is '���� ��ȣ';
COMMENT ON COLUMN customer.cs_type is '���� ����';
COMMENT ON COLUMN customer.cs_title is '����';
COMMENT ON COLUMN customer.cs_contents is '����';
COMMENT ON COLUMN customer.cs_file1 is '���� �̹���';
COMMENT ON COLUMN customer.cs_thumb1 is '���� �̹��� �̸�����';
COMMENT ON COLUMN customer.cs_size1 is '���� �̹��� ũ��';
COMMENT ON COLUMN customer.cs_rdate is '�����';
COMMENT ON COLUMN customer.cs_cnt is '���� ��ȸ��';
COMMENT ON COLUMN customer.m_no is 'ȸ�� ��ȣ';
COMMENT ON COLUMN customer.cs_passwd is '������ ��й�ȣ';

DROP SEQUENCE cs_seq;
CREATE SEQUENCE cs_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- �÷� ����
ALTER TABLE customer DROP COLUMN email;

-- �÷� �߰�
ALTER TABLE customer ADD cs_passwd VARCHAR2(60) DEFAULT 'a084' NOT NULL;

-- �÷� ����
ALTER TABLE customer
MODIFY (cs_title VARCHAR2(70));

COMMIT;

-- ���� ���
INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A01', '��ǰ����', 'OOO �̺� �ҷ�', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A02', '�������', 'OOO �̺� ȯ��', 'b_img2.jpg', 'b_img2_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A03', 'ȯ��', 'OOO �̺� ȯ��', 'b_img4.jpg', 'b_img4_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A04', '��������', 'OOO �̺� �ҷ�', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A99', '��Ÿ', 'OOO �̺� �ҷ�', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

INSERT INTO customer(cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, m_no)
VALUES(cs_seq.nextval, 'A01', '��ǰ����', 'ABCDE �̺� �ҷ�', 'b_img1.jpg', 'b_img1_t.jpg', 500, sysdate, 2);

-- ���
SELECT cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, cs_cnt, m_no, cs_passwd
FROM customer
ORDER BY cs_no ASC;

-- ��ȸ
SELECT m_no, cs_no, cs_type, cs_title, cs_contents, cs_file1, cs_thumb1, cs_size1, cs_rdate, cs_cnt
FROM customer
WHERE cs_no=1
ORDER BY cs_no ASC;

-- ����
DELETE FROM customer
WHERE cs_no= 3;

-- ����
UPDATE customer 
SET cs_title='����', cs_contents='����', email='email@domain.do'
WHERE cs_no = 7;

COMMIT;

