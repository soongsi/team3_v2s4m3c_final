/**********************************/
/* Table Name: ÷������ */
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

COMMENT ON TABLE ebook_attachfile is '÷������';
COMMENT ON COLUMN ebook_attachfile.attachfileno is '÷�����Ϲ�ȣ';
COMMENT ON COLUMN ebook_attachfile.eb_no is '�̺� ��ȣ';
COMMENT ON COLUMN ebook_attachfile.fname is '���� ���ϸ�';
COMMENT ON COLUMN ebook_attachfile.fupname is '���ε� ���ϸ�';
COMMENT ON COLUMN ebook_attachfile.thumb is 'Thumb ���ϸ�';
COMMENT ON COLUMN ebook_attachfile.fsize is '���� ������';
COMMENT ON COLUMN ebook_attachfile.rdate is '�����';

DROP SEQUENCE ebook_attachfile_seq;
CREATE SEQUENCE ebook_attachfile_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- 1) ���
INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 1, '������_����.jpg', '������_����_1.jpg', '������_����_t.jpg', 1000, sysdate);

INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 2, '�׷�����_�ұ��ϰ�.jpg', '�׷�����_�ұ��ϰ�_1.jpg', '�׷�����_�ұ��ϰ�_t.jpg', 2000, sysdate);
             
INSERT INTO ebook_attachfile(attachfileno, eb_no, fname, fupname, thumb, fsize, rdate)
VALUES(ebook_attachfile_seq.nextval, 3, '�ȳ�,_������_���.jpg', '�ȳ�,_������_���_1.jpg', '�ȳ�,_������_���_t.jpg', 3000, sysdate);        
             
COMMIT;

-- 2) ���( eb_no ���� ���� ����, attachfileno ���� ��������)
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
ORDER BY eb_no DESC,  attachfileno ASC;


-- 3) PK ���� �ϳ��� ���ڵ� ��ȸ
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE attachfileno = 1;

-- 4) FK ���� eb_no�� ������ ���ڵ� ��ȸ, fname ��������
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE eb_no = 1
ORDER BY fname ASC;


-- 5) �ϳ��� ���� ����
DELETE FROM ebook_attachfile
WHERE attachfileno = 1;

COMMIT;

-- 6) FK eb_no �θ�Ű �� ��ȸ
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE eb_no=1;

-- �θ�Ű�� ���� ����
SELECT COUNT(*) as cnt
FROM ebook_attachfile
WHERE contentsno=1;


-- 7) FK �θ� ���̺� ���ڵ� ����
DELETE FROM ebook_attachfile
WHERE eb_no=1;

   
-- 8) Contents, Attachfile join
    SELECT c.title, 
               a.attachfileno, a.eb_no, a.fname, a.fupname, a.thumb, a.fsize, a.rdate
    FROM contents c, ebook_attachfile a
    WHERE c.eb_no = a.eb_no
    ORDER BY c.eb_no DESC,  a.eb_no ASC;

-- 9) ��ȸ
SELECT attachfileno, eb_no, fname, fupname, thumb, fsize, rdate
FROM ebook_attachfile
WHERE attachfileno=1;

-- ���� ���� ���� ����
DELETE FROM ebook_attachfile
WHERE attachfileno IN ( 10, 11, 12, 13 );
