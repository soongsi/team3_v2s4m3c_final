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


/**********************************/
/* Table Name: �̺� */
/**********************************/
CREATE TABLE ebook(
		eb_no                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		cateno                        		NUMBER(10)		 NULL ,
		eb_title                      		VARCHAR2(200)		 NOT NULL,
		eb_author                     		VARCHAR2(100)		 NOT NULL,
		eb_publisher                  		VARCHAR2(20)		 NOT NULL,
		eb_pdate                      		DATE		 NULL ,
		eb_useinfo                    		VARCHAR2(500)		 NULL ,
		eb_device                     		VARCHAR2(500)		 NOT NULL,
		eb_infor                      		LONG		 NULL ,
		eb_price                      		NUMBER(10)		 NOT NULL,
		eb_saletot                    		NUMBER(10)		 DEFAULT 0		 NULL ,
		eb_page                       		NUMBER(10)		 NULL ,
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
COMMENT ON COLUMN ebook.rdate is '�����';


