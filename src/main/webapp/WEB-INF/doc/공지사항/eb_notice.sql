-- ���̺� ����

DROP TABLE member;
DROP TABLE notice;

-- ������ ����
DROP SEQUENCE member_seq;
DROP SEQUENCE notice_seq;

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
  
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'qnaadmin', '1234', 'QNA������', 'abc1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);
 
INSERT INTO member(m_no, id, passwd, mname, email, tel, zipcode, address1, address2, mdate)
VALUES (member_seq.nextval, 'crm', '1234', '��������', 'def1234@naver.com', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

COMMIT;


/**********************************/
/* Table Name: �������׺з� */
/**********************************/
CREATE TABLE ncate(
		ncate_no                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ncate_name                    		VARCHAR2(100)		 NOT NULL,
		nseqno                        		NUMBER(10)		 NOT NULL,
		nvisible                      		CHAR(1)		 NOT NULL,
		ncate_rdate                   		DATE		 NOT NULL,
		ncate_cnt                     		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE ncate is '�������׺з�';
COMMENT ON COLUMN ncate.ncate_no is 'ī�װ� ��ȣ';
COMMENT ON COLUMN ncate.ncate_name is 'ī�װ� �̸�';
COMMENT ON COLUMN ncate.nseqno is '��� ����';
COMMENT ON COLUMN ncate.nvisible is '��� ���';
COMMENT ON COLUMN ncate.ncate_rdate is '�����';
COMMENT ON COLUMN ncate.ncate_cnt is '��ϵ� �� ��';

DROP SEQUENCE ncate_seq;
CREATE SEQUENCE ncate_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 ?> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

/**********************************/
/* Table Name: �������� */
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

COMMENT ON TABLE notice is '��������';
COMMENT ON COLUMN notice.noticeno is '�������׹�ȣ';
COMMENT ON COLUMN notice.ncate_no is '�������� ī�װ� ��ȣ';
COMMENT ON COLUMN notice.m_no is 'ȸ����ȣ';
COMMENT ON COLUMN notice.title is '����';
COMMENT ON COLUMN notice.content is '����';
COMMENT ON COLUMN notice.ip is 'IP';
COMMENT ON COLUMN notice.notice_pw is '��й�ȣ';
COMMENT ON COLUMN notice.nfile1 is '÷���̹���';
COMMENT ON COLUMN notice.nthumb1 is '���� �̹��� Preview';
COMMENT ON COLUMN notice.nsize1 is ' ���� �̹��� ũ��';
COMMENT ON COLUMN notice.writer is '�ۼ���';
COMMENT ON COLUMN notice.rdate is '�ۼ���¥';
COMMENT ON COLUMN notice.visible is '��¸��';

CREATE SEQUENCE notice_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����


SELECT * FROM member;

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, 'ù��° ���������Դϴ�', '�������� �����Դϴ�.','127.0.0.1', 1234, 'aa.jpg', 'aa_t.jpg', 23657, 'notice1', sysdate, 'Y');

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, 'TEST', 'TEST','127.0.0.1', 1234,  'bb.jpg', 'bb.jpg', 23657, 'notice1', sysdate, 'Y' );

INSERT INTO notice(noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible)
VALUES(notice_seq.nextval, 1, 'TEST', 'TEST','127.0.0.1', 1234,  'notice1.jpg', 'notice1_t.jpg', 23657, 'notice1', sysdate, 'Y' );


-- ���
SELECT noticeno, m_no, title, content, ip, notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible
FROM notice
ORDER BY noticeno ASC;

-- ��ȸ
SELECT noticeno, m_no, title, content, ip,  notice_pw, nfile1, nthumb1, nsize1, writer, rdate, visible
FROM notice
WHERE noticeno=1;

-- ����
UPDATE notice
SET title='�ι�°', content='�ι�° ����', nfile1='notice2.png', nthumb1='notice2_t.jpg', nsize1=5000, visible='N'
WHERE noticeno = 2;

-- �н����� �˻�
SELECT COUNT(*) as passwd_cnt
FROM notice
WHERE noticeno=1 AND notice_pw='1234';

SELECT noticeno, notice_pw
FROM notice 
ORDER BY noticeno DESC;   

-- ����
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

