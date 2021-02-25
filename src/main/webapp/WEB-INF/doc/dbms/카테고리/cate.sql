-- ���̺� ���� (�ڽ� ���̺� ������ �� �۾��� ��)
DROP TABLE cate;

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

DROP SEQUENCE cate_seq;
CREATE SEQUENCE cate_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

-- ���
INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 1, '������', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 2, '����ö��', 1, 'Y', sysdate);

INSERT INTO cate(cateno, cg_no, name, seqno, visible, rdate)
VALUES(cate_seq.nextval, 3, '������/��°���', 1, 'Y', sysdate);

-- ���
SELECT cateno, cg_no, name, seqno, visible, rdate
FROM cate;

-- ��ȸ
SELECT cateno, cg_no, name, seqno, visible, rdate
FROM cate
WHERE cateno = 1;

-- ����
UPDATE cate
SET name = '��'
WHERE cateno = 1;

-- ����
DELETE
FROM cate
WHERE cateno = 3;

SELECT g.cg_no as g_cg_no, g.cg_name as g_name,
               c.cateno, c.cg_no, c.name, c.seqno, c.visible, c.rdate
    FROM categrp g, cate c
    WHERE g.cg_no = c.cg_no
    ORDER BY g.cg_no ASC, c.seqno ASC