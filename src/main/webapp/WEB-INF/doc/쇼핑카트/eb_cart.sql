-- ���̺� ���� 
DROP TABLE cart CASCADE CONSTRAINTS;
COMMIT;

/**********************************/
/* Table Name: ��ٱ��� */
/**********************************/
create table cart (
    cart_no         number(10)          not null,
    user_id         varchar2(20)        not null,
    eb_no           number(10)          not null,
    rdate           date                default sysdate,
    primary key(cart_no, user_id), 
    FOREIGN KEY (user_id) REFERENCES member (id),
    FOREIGN KEY (eb_no) REFERENCES ebook (eb_no)
);

COMMENT ON TABLE cart is '��ٱ���';
COMMENT ON COLUMN cart.cart_no is 'īƮ ��ȣ';
COMMENT ON COLUMN cart.user_id is '���̵�';
COMMENT ON COLUMN cart.eb_no is '��ǰ ��ȣ';
COMMENT ON COLUMN cart.rdate is '��� ��¥';

-- �̺� ���̺�� �ɹ� ���̺��� ����
   

COMMIT;

DROP SEQUENCE cart_seq;
-- īƮ ��ȣ�� ������ ������
CREATE SEQUENCE cart_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����    
    
-- ��ٱ��� ��ǰ ���ڵ� �ߺ� üũ
SELECT COUNT(*)
FROM cart
WHERE  user_id ='crm' AND eb_no=2;

SELECT
     row_number() over(order by c.cart_no desc) as r,
     c.cart_no, c.user_id, c.eb_no, c.rdate,
     e.eb_no, e.eb_title,e.eb_price, e.eb_thumb
FROM cart c
     INNER JOIN ebook e
     ON c.eb_no = e.eb_no   
     WHERE c.user_id = 'crm';

-- īƮ�� ���
INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',1);

INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',2);

INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',3);

SELECT * FROM cart;

-- īƮ ����Ʈ
SELECT
     row_number() over(order by c.cart_no desc) as r,
     c.cart_no, c.user_id, c.eb_no, c.rdate,
     e.eb_no, e.eb_title,e.eb_price, e.eb_thumb
FROM cart c
     INNER JOIN ebook e
     ON c.eb_no = e.eb_no   
     WHERE c.user_id = 'crm';

-- ��� ���� ����
UPDATE cart
SET amount = 3 
WHERE user_id='crm' AND eb_no=3;


DELETE cart
WHERE  cart_no = 4 and user_id = 'crm';






    
    
    
    
    