DROP TABLE SALEWEEK CASCADE CONSTRAINTS;
DROP TABLE EVENT CASCADE CONSTRAINTS;
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP TABLE PLIST CASCADE CONSTRAINTS;
DROP TABLE PUBLISHER CASCADE CONSTRAINTS;
DROP TABLE REVIEW CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE NCATE CASCADE CONSTRAINTS;
DROP TABLE BOOKSHELF CASCADE CONSTRAINTS;
DROP TABLE SURVEY_CHART CASCADE CONSTRAINTS;
DROP TABLE SURVEY_ITEM CASCADE CONSTRAINTS;
DROP TABLE SURVEY CASCADE CONSTRAINTS;
DROP TABLE ORDER_REQ CASCADE CONSTRAINTS;
DROP TABLE CART CASCADE CONSTRAINTS;
DROP TABLE CS_ATTACHFILE CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE EBOOK_ATTACHFILE CASCADE CONSTRAINTS;
DROP TABLE EBOOK CASCADE CONSTRAINTS;
DROP TABLE CATE CASCADE CONSTRAINTS;
DROP TABLE CATEGRP CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ī�װ� �׷� */
/**********************************/
CREATE TABLE CATEGRP(
		CG_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CG_NAME                       		VARCHAR2(100)		 NOT NULL,
		CG_SEQNO                      		NUMBER(10)		 NOT NULL,
		CG_VISIBLE                    		CHAR(1)		 NOT NULL,
		CG_RDATE                      		DATE		 NOT NULL
);

COMMENT ON TABLE CATEGRP is 'ī�װ� �׷�';
COMMENT ON COLUMN CATEGRP.CG_NO is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN CATEGRP.CG_NAME is 'ī�װ� �׷� �̸�';
COMMENT ON COLUMN CATEGRP.CG_SEQNO is '��� ����';
COMMENT ON COLUMN CATEGRP.CG_VISIBLE is '��� ���';
COMMENT ON COLUMN CATEGRP.CG_RDATE is '�׷� ������';


/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE CATE(
		CATENO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CG_NO                         		NUMBER(10)		 NULL ,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (CG_NO) REFERENCES CATEGRP (CG_NO)
);

COMMENT ON TABLE CATE is 'ī�װ�';
COMMENT ON COLUMN CATE.CATENO is 'ī�װ� ��ȣ';
COMMENT ON COLUMN CATE.CG_NO is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN CATE.NAME is 'ī�װ� �̸�';
COMMENT ON COLUMN CATE.SEQNO is '��� ����';
COMMENT ON COLUMN CATE.VISIBLE is '��� ���';
COMMENT ON COLUMN CATE.RDATE is '�����';


/**********************************/
/* Table Name: �̺� */
/**********************************/
CREATE TABLE EBOOK(
		EB_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CATENO                        		NUMBER(10)		 NULL ,
		EB_TITLE                      		VARCHAR2(200)		 NOT NULL,
		EB_AUTHOR                     		VARCHAR2(100)		 NOT NULL,
		EB_PUBLISHER                  		VARCHAR2(50)		 NOT NULL,
		EB_PDATE                      		VARCHAR2(20)		 NULL ,
		EB_USEINFO                    		VARCHAR2(500)		 NULL ,
		EB_DEVICE                     		VARCHAR2(500)		 NOT NULL,
		EB_INFOR                      		CLOB(4000)		 NULL ,
		EB_PRICE                      		NUMBER(10)		 NOT NULL,
		EB_SALETOT                    		NUMBER(10)		 NULL ,
		EB_PAGE                       		NUMBER(10)		 NULL ,
		EB_FILE1                      		VARCHAR2(100)		 NULL ,
		EB_SIZE1                      		NUMBER(10)		 NULL ,
		EB_FILE2                      		VARCHAR2(100)		 NULL ,
		EB_SIZE2                      		NUMBER(10)		 NULL ,
		EB_THUMB                      		VARCHAR2(100)		 NULL ,
		WORD                          		VARCHAR2(300)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);

COMMENT ON TABLE EBOOK is '�̺�';
COMMENT ON COLUMN EBOOK.EB_NO is '�̺� ��ȣ';
COMMENT ON COLUMN EBOOK.CATENO is 'ī�װ� ��ȣ';
COMMENT ON COLUMN EBOOK.EB_TITLE is '����';
COMMENT ON COLUMN EBOOK.EB_AUTHOR is '����';
COMMENT ON COLUMN EBOOK.EB_PUBLISHER is '���ǻ�';
COMMENT ON COLUMN EBOOK.EB_PDATE is '�Ⱓ��';
COMMENT ON COLUMN EBOOK.EB_USEINFO is '�̿�ȳ�';
COMMENT ON COLUMN EBOOK.EB_DEVICE is '�������';
COMMENT ON COLUMN EBOOK.EB_INFOR is '���� ����';
COMMENT ON COLUMN EBOOK.EB_PRICE is '�ǸŰ�';
COMMENT ON COLUMN EBOOK.EB_SALETOT is '�Ǹŷ�';
COMMENT ON COLUMN EBOOK.EB_PAGE is '������ ��';
COMMENT ON COLUMN EBOOK.EB_FILE1 is '�̺� ����';
COMMENT ON COLUMN EBOOK.EB_SIZE1 is '�̺� ���� ũ��';
COMMENT ON COLUMN EBOOK.EB_FILE2 is '���� �̹���';
COMMENT ON COLUMN EBOOK.EB_SIZE2 is '���� �̹��� ���� ũ��';
COMMENT ON COLUMN EBOOK.EB_THUMB is '�����';
COMMENT ON COLUMN EBOOK.WORD is '�˻���';
COMMENT ON COLUMN EBOOK.RDATE is '��� ��¥';


/**********************************/
/* Table Name: ÷������ */
/**********************************/
CREATE TABLE EBOOK_ATTACHFILE(
		ATTACHFILENO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		EB_NO                         		NUMBER(10)		 NULL ,
		FNAME                         		VARCHAR2(200)		 NOT NULL,
		FUPNAME                       		VARCHAR2(200)		 NOT NULL,
		THUMB                         		VARCHAR2(200)		 NULL ,
		FSIZE                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO)
);

COMMENT ON TABLE EBOOK_ATTACHFILE is '÷������';
COMMENT ON COLUMN EBOOK_ATTACHFILE.ATTACHFILENO is '÷�����Ϲ�ȣ';
COMMENT ON COLUMN EBOOK_ATTACHFILE.EB_NO is '�̺� ��ȣ';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FNAME is '���� ���ϸ�';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FUPNAME is '���ε� ���ϸ�';
COMMENT ON COLUMN EBOOK_ATTACHFILE.THUMB is 'Thumb ���ϸ�';
COMMENT ON COLUMN EBOOK_ATTACHFILE.FSIZE is '���� ������';
COMMENT ON COLUMN EBOOK_ATTACHFILE.RDATE is '�����';


/**********************************/
/* Table Name: ȸ�� */
/**********************************/
CREATE TABLE MEMBER(
		M_NO                          		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		ID                            		VARCHAR2(20)		 NOT NULL,
		PASSWD                        		VARCHAR2(60)		 NOT NULL,
		MNAME                         		VARCHAR2(20)		 NOT NULL,
		EMAIL                         		VARCHAR2(60)		 NOT NULL,
		TEL                           		VARCHAR2(14)		 NOT NULL,
		ZIPCODE                       		VARCHAR2(5)		 NULL ,
		ADDRESS1                      		VARCHAR2(80)		 NULL ,
		ADDRESS2                      		VARCHAR2(50)		 NULL ,
		MDATE                         		DATE		 NOT NULL,
  CONSTRAINT SYS_C008370 UNIQUE (ID)
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


/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE CUSTOMER(
		CS_NO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CS_TYPE                       		VARCHAR2(20)		 NOT NULL,
		CS_TITLE                      		VARCHAR2(30)		 NOT NULL,
		CS_CONTENTS                   		CLOB(4000)		 NOT NULL,
		CS_FILE1                      		VARCHAR2(100)		 NULL ,
		CS_THUMB1                     		VARCHAR2(100)		 NULL ,
		CS_SIZE1                      		NUMBER(10)		 NULL ,
		CS_RDATE                      		DATE		 NOT NULL,
		CS_CNT                        		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(6)		 NULL ,
		CS_PASSWD                     		VARCHAR2(60)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE CUSTOMER is '������';
COMMENT ON COLUMN CUSTOMER.CS_NO is '���� ��ȣ';
COMMENT ON COLUMN CUSTOMER.CS_TYPE is '���� ����';
COMMENT ON COLUMN CUSTOMER.CS_TITLE is '����';
COMMENT ON COLUMN CUSTOMER.CS_CONTENTS is '����';
COMMENT ON COLUMN CUSTOMER.CS_FILE1 is '���� �̹���';
COMMENT ON COLUMN CUSTOMER.CS_THUMB1 is '���� �̹��� �̸�����';
COMMENT ON COLUMN CUSTOMER.CS_SIZE1 is '���� �̹��� ũ��';
COMMENT ON COLUMN CUSTOMER.CS_RDATE is '�����';
COMMENT ON COLUMN CUSTOMER.CS_CNT is '���� ��ȸ��';
COMMENT ON COLUMN CUSTOMER.M_NO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN CUSTOMER.CS_PASSWD is '������ ��й�ȣ';


/**********************************/
/* Table Name: ������ ÷������ */
/**********************************/
CREATE TABLE CS_ATTACHFILE(
		ATTACH_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CS_NO                         		NUMBER(10)		 NULL ,
		FNAME                         		VARCHAR2(200)		 NOT NULL,
		FUPNAME                       		VARCHAR2(200)		 NOT NULL,
		THUMB                         		VARCHAR2(200)		 NULL ,
		FSIZE                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
  FOREIGN KEY (CS_NO) REFERENCES CUSTOMER (CS_NO)
);

COMMENT ON TABLE CS_ATTACHFILE is '������ ÷������';
COMMENT ON COLUMN CS_ATTACHFILE.ATTACH_NO is '÷������ ��ȣ';
COMMENT ON COLUMN CS_ATTACHFILE.CS_NO is '���� ��ȣ';
COMMENT ON COLUMN CS_ATTACHFILE.FNAME is '���� ���ϸ�';
COMMENT ON COLUMN CS_ATTACHFILE.FUPNAME is '���ε� ���ϸ�';
COMMENT ON COLUMN CS_ATTACHFILE.THUMB is 'Thumb ���ϸ�';
COMMENT ON COLUMN CS_ATTACHFILE.FSIZE is '���� ������';
COMMENT ON COLUMN CS_ATTACHFILE.RDATE is '�����';


/**********************************/
/* Table Name: ��ٱ��� */
/**********************************/
CREATE TABLE CART(
		CART_NO                       		NUMBER(10)		 NOT NULL,
		USER_ID                       		VARCHAR2(20)		 NOT NULL,
		EB_NO                         		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NULL ,
		M_NO                          		NUMBER(6)		 NULL ,
  PRIMARY KEY (CART_NO, USER_ID),
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE CART is '��ٱ���';
COMMENT ON COLUMN CART.CART_NO is 'īƮ ��ȣ';
COMMENT ON COLUMN CART.USER_ID is '���̵�';
COMMENT ON COLUMN CART.EB_NO is '��ǰ ��ȣ';
COMMENT ON COLUMN CART.RDATE is '��� ��¥';
COMMENT ON COLUMN CART.M_NO is 'ȸ�� ��ȣ';


/**********************************/
/* Table Name: �ֹ���û */
/**********************************/
CREATE TABLE ORDER_REQ(
		ORDER_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		PAYWAY                        		VARCHAR2(30)		 NOT NULL,
		PRICE                         		NUMBER(10)		 NOT NULL,
		EB_NO                         		NUMBER(10)		 NULL ,
		USER_ID                       		VARCHAR2(20)		 NULL ,
		RDATE                         		DATE		 NULL ,
		PAYID                         		VARCHAR2(30)		 NULL ,
		M_NO                          		NUMBER(6)		 NULL ,
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO),
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE ORDER_REQ is '�ֹ���û';
COMMENT ON COLUMN ORDER_REQ.ORDER_NO is '�ֹ���ȣ';
COMMENT ON COLUMN ORDER_REQ.PAYWAY is '�������';
COMMENT ON COLUMN ORDER_REQ.PRICE is '�����ݾ�';
COMMENT ON COLUMN ORDER_REQ.EB_NO is '�̺� ��ȣ';
COMMENT ON COLUMN ORDER_REQ.USER_ID is 'ȸ�� ���̵�';
COMMENT ON COLUMN ORDER_REQ.RDATE is '�����Ͻ�';
COMMENT ON COLUMN ORDER_REQ.PAYID is '������ȣ';
COMMENT ON COLUMN ORDER_REQ.M_NO is 'ȸ�� ��ȣ';


/**********************************/
/* Table Name: �������� */
/**********************************/
CREATE TABLE SURVEY(
		SURVEY_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		STARTDATE                     		VARCHAR2(30)		 NOT NULL,
		ENDDATE                       		VARCHAR2(30)		 NOT NULL,
		CNT                           		NUMBER(10)		 NOT NULL,
		CONTINUEYN                    		VARCHAR2(5)		 NOT NULL,
		SURVEY_RESULT                 		VARCHAR2(100)		 NULL ,
		SEQNO                         		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE SURVEY is '��������';
COMMENT ON COLUMN SURVEY.SURVEY_NO is '���� ��ȣ';
COMMENT ON COLUMN SURVEY.TITLE is '����';
COMMENT ON COLUMN SURVEY.STARTDATE is '������';
COMMENT ON COLUMN SURVEY.ENDDATE is '������';
COMMENT ON COLUMN SURVEY.CNT is '���� �ο���';
COMMENT ON COLUMN SURVEY.CONTINUEYN is '����';
COMMENT ON COLUMN SURVEY.SURVEY_RESULT is '�������';
COMMENT ON COLUMN SURVEY.SEQNO is '����';


/**********************************/
/* Table Name: ���� �׸� */
/**********************************/
CREATE TABLE SURVEY_ITEM(
		SURVEY_NO                     		NUMBER(10)		 NOT NULL,
		ITEM_NO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ITEM_TITLE                    		VARCHAR2(200)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		RCNT                          		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (SURVEY_NO) REFERENCES SURVEY (SURVEY_NO)
);

COMMENT ON TABLE SURVEY_ITEM is '���� �׸�';
COMMENT ON COLUMN SURVEY_ITEM.SURVEY_NO is '���� ��ȣ';
COMMENT ON COLUMN SURVEY_ITEM.ITEM_NO is '���� �׸� ��ȣ';
COMMENT ON COLUMN SURVEY_ITEM.ITEM_TITLE is '���� �׸� ����';
COMMENT ON COLUMN SURVEY_ITEM.SEQNO is '����';
COMMENT ON COLUMN SURVEY_ITEM.RCNT is '����';


/**********************************/
/* Table Name: ���� ����  */
/**********************************/
CREATE TABLE SURVEY_CHART(
		ITEM_NO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		SURVEY_NO                     		NUMBER(10)		 NOT NULL,
		ITEM                          		VARCHAR2(200)		 NOT NULL,
		SEQNO                         		NUMBER(10)		 NOT NULL,
		CNT                           		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (SURVEY_NO) REFERENCES SURVEY (SURVEY_NO)
);

COMMENT ON TABLE SURVEY_CHART is '���� ���� ';
COMMENT ON COLUMN SURVEY_CHART.ITEM_NO is '���� �׸� ��ȣ';
COMMENT ON COLUMN SURVEY_CHART.SURVEY_NO is '���� ��ȣ';
COMMENT ON COLUMN SURVEY_CHART.ITEM is '�׸�';
COMMENT ON COLUMN SURVEY_CHART.SEQNO is '����';
COMMENT ON COLUMN SURVEY_CHART.CNT is '����';


/**********************************/
/* Table Name: �� ���� */
/**********************************/
CREATE TABLE BOOKSHELF(
		BOOKSHELFNO                   		NUMBER(20)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NOT NULL,
		P_NAME                        		VARCHAR2(20)		 NOT NULL,
		P_INFOR                       		VARCHAR2(50)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE BOOKSHELF is '�� ����';
COMMENT ON COLUMN BOOKSHELF.BOOKSHELFNO is '���� ��ȣ';
COMMENT ON COLUMN BOOKSHELF.M_NO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN BOOKSHELF.P_NAME is '��ǰ �̸� ';
COMMENT ON COLUMN BOOKSHELF.P_INFOR is '��ǰ ���� ';
COMMENT ON COLUMN BOOKSHELF.FILE1 is 'FILE1';
COMMENT ON COLUMN BOOKSHELF.THUMB1 is 'THUMB1';
COMMENT ON COLUMN BOOKSHELF.SIZE1 is 'SIZE1';


/**********************************/
/* Table Name: �������׺з� */
/**********************************/
CREATE TABLE NCATE(
		NCATE_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NCATE_NAME                    		VARCHAR2(100)		 NOT NULL,
		NSEQNO                        		NUMBER(10)		 NOT NULL,
		NVISIBLE                      		CHAR(1)		 NOT NULL,
		NCATE_RDATE                   		DATE		 NOT NULL,
		NCATE_CNT                     		NUMBER(10)		 NOT NULL
);

COMMENT ON TABLE NCATE is '�������׺з�';
COMMENT ON COLUMN NCATE.NCATE_NO is 'ī�װ� ��ȣ';
COMMENT ON COLUMN NCATE.NCATE_NAME is 'ī�װ� �̸�';
COMMENT ON COLUMN NCATE.NSEQNO is '��� ����';
COMMENT ON COLUMN NCATE.NVISIBLE is '��� ���';
COMMENT ON COLUMN NCATE.NCATE_RDATE is '�����';
COMMENT ON COLUMN NCATE.NCATE_CNT is '��ϵ� �� ��';


/**********************************/
/* Table Name: �������� */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NULL ,
		TITLE                         		VARCHAR2(100)		 NOT NULL,
		CONTENT                       		CLOB(4000)		 NOT NULL,
		IP                            		VARCHAR2(15)		 NOT NULL,
		NOTICE_PW                     		VARCHAR2(15)		 NOT NULL,
		NFILE1                        		VARCHAR2(100)		 NULL ,
		NTHUMB1                       		VARCHAR2(100)		 NULL ,
		NSIZE1                        		NUMBER(10)		 NULL ,
		WRITER                        		VARCHAR2(20)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		VISIBLE                       		CHAR(1)		 NOT NULL,
		NCATE_NO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (NCATE_NO) REFERENCES NCATE (NCATE_NO)
);

COMMENT ON TABLE NOTICE is '��������';
COMMENT ON COLUMN NOTICE.NOTICENO is '�������׹�ȣ';
COMMENT ON COLUMN NOTICE.M_NO is 'ȸ����ȣ';
COMMENT ON COLUMN NOTICE.TITLE is '����';
COMMENT ON COLUMN NOTICE.CONTENT is '����';
COMMENT ON COLUMN NOTICE.IP is 'IP';
COMMENT ON COLUMN NOTICE.NOTICE_PW is '��й�ȣ';
COMMENT ON COLUMN NOTICE.NFILE1 is '÷���̹���';
COMMENT ON COLUMN NOTICE.NTHUMB1 is '���� �̹��� Preview';
COMMENT ON COLUMN NOTICE.NSIZE1 is ' ���� �̹��� ũ��';
COMMENT ON COLUMN NOTICE.WRITER is '�ۼ���';
COMMENT ON COLUMN NOTICE.RDATE is '�ۼ���¥';
COMMENT ON COLUMN NOTICE.VISIBLE is '��¸��';
COMMENT ON COLUMN NOTICE.NCATE_NO is 'ī�װ� ��ȣ';


/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE REVIEW(
		REVIEW_NO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		EB_NO                         		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(10)		 NOT NULL,
		REVIEW_CONTENT                		CLOB(4000)		 NOT NULL,
		REVIEW_PW                     		VARCHAR2(15)		 NOT NULL,
		REVIEW_GRADE                  		NUMBER(5)		 NOT NULL,
		REVIEW_RDATE                  		DATE		 NOT NULL,
		REVIEW_GOOD                   		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (EB_NO) REFERENCES EBOOK (EB_NO)
);

COMMENT ON TABLE REVIEW is '����';
COMMENT ON COLUMN REVIEW.REVIEW_NO is '�����ȣ';
COMMENT ON COLUMN REVIEW.EB_NO is '�̺� ��ȣ';
COMMENT ON COLUMN REVIEW.M_NO is 'ȸ����ȣ';
COMMENT ON COLUMN REVIEW.REVIEW_CONTENT is '���� ����';
COMMENT ON COLUMN REVIEW.REVIEW_PW is '���� �н�����';
COMMENT ON COLUMN REVIEW.REVIEW_GRADE is '���� ����';
COMMENT ON COLUMN REVIEW.REVIEW_RDATE is '���� �ۼ���';
COMMENT ON COLUMN REVIEW.REVIEW_GOOD is '��õ��';


/**********************************/
/* Table Name: ���ǻ� Ȩ������ �ٷΰ���  */
/**********************************/
CREATE TABLE PUBLISHER(
		PUB_NO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		HTTP                          		VARCHAR2(100)		 NOT NULL,
		ETC                           		VARCHAR2(1000)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE PUBLISHER is '���ǻ� Ȩ������ �ٷΰ��� ';
COMMENT ON COLUMN PUBLISHER.PUB_NO is '���ǻ� ���� ��ȣ';
COMMENT ON COLUMN PUBLISHER.NAME is '���ǻ� �̸�';
COMMENT ON COLUMN PUBLISHER.HTTP is 'Ȩ������ �ּ�';
COMMENT ON COLUMN PUBLISHER.ETC is '��Ÿ ����';
COMMENT ON COLUMN PUBLISHER.FILE1 is '��Ÿ ����';
COMMENT ON COLUMN PUBLISHER.THUMB1 is '��Ÿ ����';
COMMENT ON COLUMN PUBLISHER.SIZE1 is '��Ÿ ����';
COMMENT ON COLUMN PUBLISHER.RDATE is '��� ��¥';


/**********************************/
/* Table Name: �ֹ����� */
/**********************************/
CREATE TABLE PLIST(
		PLISTNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		M_NO                          		NUMBER(10)		 NOT NULL,
		P_DATE                        		DATE		 NOT NULL,
		P_INFOR                       		VARCHAR2(50)		 NOT NULL,
		P_PRICE                       		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO)
);

COMMENT ON TABLE PLIST is '�ֹ�����';
COMMENT ON COLUMN PLIST.PLISTNO is '�ֹ����� ��ȣ';
COMMENT ON COLUMN PLIST.M_NO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN PLIST.P_DATE is '�ֹ�����';
COMMENT ON COLUMN PLIST.P_INFOR is '�ֹ�����';
COMMENT ON COLUMN PLIST.P_PRICE is '�ֹ� ����';


/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE REPLY(
		REPLY_NO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		REVIEW_NO                     		NUMBER(10)		 NOT NULL,
		M_NO                          		NUMBER(6)		 NOT NULL,
		REPLY_CONT                    		VARCHAR2(3000)		 NOT NULL,
		REPLY_PW                      		VARCHAR2(20)		 NOT NULL,
		REPLY_DATE                    		DATE		 NOT NULL,
		REPLY_VISIBLE                 		CHAR(1)		 NOT NULL,
  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
  FOREIGN KEY (REVIEW_NO) REFERENCES REVIEW (REVIEW_NO)
);

COMMENT ON TABLE REPLY is '���';
COMMENT ON COLUMN REPLY.REPLY_NO is '��۹�ȣ';
COMMENT ON COLUMN REPLY.REVIEW_NO is '�����ȣ';
COMMENT ON COLUMN REPLY.M_NO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN REPLY.REPLY_CONT is '��۳���';
COMMENT ON COLUMN REPLY.REPLY_PW is '��й�ȣ';
COMMENT ON COLUMN REPLY.REPLY_DATE is '�����';
COMMENT ON COLUMN REPLY.REPLY_VISIBLE is '��¸��';


/**********************************/
/* Table Name: �̺�Ʈ �޴� */
/**********************************/
CREATE TABLE EVENT(
		EVENTNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(300)		 NOT NULL,
		WHATEVENT                     		VARCHAR2(2000)		 NOT NULL,
		SALENO                        		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE EVENT is '�̺�Ʈ �޴�';
COMMENT ON COLUMN EVENT.EVENTNO is '�̺�Ʈ ��ȣ';
COMMENT ON COLUMN EVENT.NAME is '�̺�Ʈ �̸�';
COMMENT ON COLUMN EVENT.WHATEVENT is '�̺�Ʈ ����������';
COMMENT ON COLUMN EVENT.SALENO is '���� ���� �ѹ�';
COMMENT ON COLUMN EVENT.RDATE is '�����';


/**********************************/
/* Table Name: �̹��� å ���� */
/**********************************/
CREATE TABLE SALEWEEK(
		SALENO                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(300)		 NOT NULL,
		SALESBOOK                     		VARCHAR2(300)		 NOT NULL,
		SALE                          		VARCHAR2(300)		 NOT NULL,
		SALEPERIOD                    		VARCHAR2(300)		 NOT NULL,
		EVENTNO                       		NUMBER(10)		 NOT NULL,
		CONTENTS                      		VARCHAR2(50)		 NOT NULL,
		PASSWD                        		VARCHAR2(15)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (EVENTNO) REFERENCES EVENT (EVENTNO)
);

COMMENT ON TABLE SALEWEEK is '�̹��� å ����';
COMMENT ON COLUMN SALEWEEK.SALENO is '���ΰ��ݳѹ�';
COMMENT ON COLUMN SALEWEEK.NAME is '�̺�Ʈ �̸�';
COMMENT ON COLUMN SALEWEEK.SALESBOOK is '�̺�Ʈ å';
COMMENT ON COLUMN SALEWEEK.SALE is '���ΰ�';
COMMENT ON COLUMN SALEWEEK.SALEPERIOD is '���� �Ⱓ';
COMMENT ON COLUMN SALEWEEK.EVENTNO is '�̺�Ʈ ��ȣ';
COMMENT ON COLUMN SALEWEEK.CONTENTS is '��ǰ ���';
COMMENT ON COLUMN SALEWEEK.PASSWD is '�н�����';
COMMENT ON COLUMN SALEWEEK.FILE1 is 'FILE1';
COMMENT ON COLUMN SALEWEEK.THUMB1 is 'THUMB1';
COMMENT ON COLUMN SALEWEEK.SIZE1 is 'SIZE1';
COMMENT ON COLUMN SALEWEEK.RDATE is '�ð�';


