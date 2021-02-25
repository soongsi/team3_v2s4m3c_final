
DROP TABLE member;
DROP TABLE memberph;
DROP TABLE bookshelf;

commit;

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

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����


/**********************************/
/* Table Name: �ֹ� ���� */
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

COMMENT ON TABLE memberph is '�ֹ� ����';
COMMENT ON COLUMN memberph.orderno is '�ֹ� ��ȣ';
COMMENT ON COLUMN memberph.m_no is 'ȸ�� ��ȣ';
COMMENT ON COLUMN memberph.p_number is '��ǰ ��ȣ';
COMMENT ON COLUMN memberph.p_infor is '��ǰ ����';
COMMENT ON COLUMN memberph.p_amount is '���� ';
COMMENT ON COLUMN memberph.p_state is '�ֹ����� ';
COMMENT ON COLUMN memberph.file1 is '���� �̹���';
COMMENT ON COLUMN memberph.thumb1 is '���� �̹��� Preview';
COMMENT ON COLUMN memberph.size1 is ' ���� �̹��� ũ��';

DROP SEQUENCE memberph_seq;
CREATE SEQUENCE memberph_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����


/**********************************/
/* Table Name: �� ���� */
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

COMMENT ON TABLE bookshelf is '�� ����';
COMMENT ON COLUMN bookshelf.bookshelfno is '���� ��ȣ';
COMMENT ON COLUMN bookshelf.m_no is 'ȸ�� ��ȣ';
COMMENT ON COLUMN bookshelf.p_name is '��ǰ �̸� ';
COMMENT ON COLUMN bookshelf.p_infor is '��ǰ ���� ';
COMMENT ON COLUMN memberph.file1 is '���� �̹���';
COMMENT ON COLUMN memberph.thumb1 is '���� �̹��� Preview';
COMMENT ON COLUMN memberph.size1 is ' ���� �̹��� ũ��';

DROP SEQUENCE bookshelf_seq;
CREATE SEQUENCE bookshelf_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

/**********************************/
/*               ���                   */
/**********************************/
-- ȸ�� ����
-- ȸ�� ������ ����, Q/A �� ����
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA������', 'abc1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '��������', 'def1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

COMMIT;

-- �ֹ� ����
INSERT INTO memberph(orderno, m_no, p_number, p_infor, p_amount, p_state, file1, thumb1, size1)
VALUES(memberph_seq.nextval, 1, 1, '��ǰ ����', 2, '��� �غ���', 'summer.jpg', 'summer_t.jpg', 23657);

-- �� ����
INSERT INTO bookshelf(bookshelfno, m_no, p_name, p_infor, file1, thumb1, size1)
VALUES(bookshelf_seq.nextval, 1, '�̸�', '��ǰ ����', 'summer.jpg', 'summer_t.jpg', 23657 );

/**********************************/
/*               ���                   */
/**********************************/

-- ȸ�� ����
SELECT m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate
FROM member
ORDER BY m_no ASC;

         
-- �ֹ� ����
SELECT orderno, m_no, p_number, p_infor, p_amount, p_state, file1, thumb1, size1
FROM memberph      
ORDER BY orderno DESC;

   
-- �� ����
SELECT bookshelfno, m_no, p_name, p_infor, file1, thumb1, size1
FROM bookshelf
ORDER BY bookshelfno ASC;

  
/**********************************/
/*               ����                   */
/**********************************/


-- ȸ�� ����
UPDATE memberin
SET id='yyj0113', password = '1234', email='yyj0113@naver.com', address = '��⵵ �����ֽ� ������'
WHERE m_no = 1;

-- �ֹ� ����
UPDATE memberph
SET p_number=2, p_infor='å ����' , p_amount =3, p_state='�����'
WHERE orderno = 1;


-- �� ����
UPDATE bookshelf
SET p_name='å�̸�', p_infor = 'å ����'
WHERE bookshelfno = 1;

/**********************************/
/*               ����                   */
/**********************************/

-- ȸ�� ����
DELETE
FROM memberin
WHERE m_no = 1;

-- �ֹ� ����
DELETE 
FROM memberph
WHERE orderno = 1;


-- �� ����
DELETE 
FROM bookshelf
WHERE bookshelfno = 1;



/**�н����� �˻�**/
SELECT COUNT(*) as password_cnt
FROM memberin
WHERE m_no=2 AND password='asd';



5. ����
1) ��� ����
DELETE FROM member;
 
2) Ư�� ȸ�� ����
DELETE FROM member
WHERE m_no=15;

COMMIT;

 
6. �н����� ����
1) �н����� �˻�
SELECT COUNT(m_no) as cnt
FROM member
WHERE m_no=1 AND passwd='1234';
 
2) �н����� ����
UPDATE member
SET passwd='0000'
WHERE m_no=1;

COMMIT;
 
 
7. �α���
SELECT COUNT(m_no) as cnt
FROM member
WHERE id='user1' AND passwd='1234';
 cnt
 ---
   0


COMMIT;




