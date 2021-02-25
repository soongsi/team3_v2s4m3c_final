-- ���̺� ���� (�ڽ� ���̺� ������ �� �۾��� ��)
DROP TABLE categrp;

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

DROP SEQUENCE categrp_seq;
CREATE SEQUENCE categrp_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

-- ���
INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '������/��', 1, 'Y', sysdate);

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '�ι�', 1, 'Y', sysdate);

INSERT INTO categrp(cg_no, cg_name, cg_seqno, cg_visible, cg_rdate)
VALUES(categrp_seq.nextval, '�ڱ� ���', 1, 'Y', sysdate);

-- ���
SELECT cg_no, cg_name, cg_seqno, cg_visible, cg_rdate
FROM categrp;

-- ��ȸ
SELECT cg_no, cg_name, cg_seqno, cg_visible, cg_rdate
FROM categrp
WHERE cg_no = 1;

-- ����
UPDATE categrp
SET cg_name = '����'
WHERE cg_no = 1;

-- ����
DELETE
FROM categrp
WHERE cg_no = 3;
