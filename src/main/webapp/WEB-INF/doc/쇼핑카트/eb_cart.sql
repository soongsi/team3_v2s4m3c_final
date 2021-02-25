-- 테이블 삭제 
DROP TABLE cart CASCADE CONSTRAINTS;
COMMIT;

/**********************************/
/* Table Name: 장바구니 */
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

COMMENT ON TABLE cart is '장바구니';
COMMENT ON COLUMN cart.cart_no is '카트 번호';
COMMENT ON COLUMN cart.user_id is '아이디';
COMMENT ON COLUMN cart.eb_no is '상품 번호';
COMMENT ON COLUMN cart.rdate is '담긴 날짜';

-- 이북 테이블과 맴버 테이블을 참조
   

COMMIT;

DROP SEQUENCE cart_seq;
-- 카트 번호를 생성할 시퀀스
CREATE SEQUENCE cart_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지    
    
-- 장바구니 상품 레코드 중복 체크
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

-- 카트에 담기
INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',1);

INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',2);

INSERT INTO cart(cart_no, user_id, eb_no)
VALUES (cart_seq.nextval,'crm',3);

SELECT * FROM cart;

-- 카트 리스트
SELECT
     row_number() over(order by c.cart_no desc) as r,
     c.cart_no, c.user_id, c.eb_no, c.rdate,
     e.eb_no, e.eb_title,e.eb_price, e.eb_thumb
FROM cart c
     INNER JOIN ebook e
     ON c.eb_no = e.eb_no   
     WHERE c.user_id = 'crm';

-- 담긴 갯수 수정
UPDATE cart
SET amount = 3 
WHERE user_id='crm' AND eb_no=3;


DELETE cart
WHERE  cart_no = 4 and user_id = 'crm';






    
    
    
    
    