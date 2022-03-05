drop table users;

--주문테이블
create table users(
  user_tel varchar2(30) primary key, --회원전화번호
  user_name varchar2(30) not null,--이름
  user_point number not null,--적립금
  reg_date date not null, -- 가입일
  user_pwd number not null --비밀번호
);

insert into  users values('010-1111-2222','yuda', 0, sysdate, '1111');
insert into  users values('010-3333-4444','jina', 0, sysdate, '2222');
insert into  users values('010-5555-6666','yong', 0, sysdate, '3333');
insert into  users values('010-7777-8888','hyo', 0, sysdate, '4444');
insert into  users values('010-9999-1111','woo', 0, sysdate, '5555');

select * from users;
-----------------------------------------------------------------
drop table order_state;

--주문테이블
create table order_state(
  state_code number primary key, --주문상태코드
  field varchar2(30) not null--주문상태
);

insert into order_state values(0, 'Ordered');
insert into order_state values(1, 'Shipped');

select * from order_state;
---------------------------------------------------------------------------
drop table orders;

--주문테이블
create table orders(
  order_num NUMBER primary key, -- 주문번호
  user_tel varchar2(30),--전화번호
  state_code NUMBER not null,--주문상태코드
  pay_method varchar2(10) not null, --결제방법
  pay_point number, --적립금사용액수
  total_price number not null, --총결제금액
  order_date date not null, --주문일자
  takeout number not null, --테이크아웃여부
  constraint fk_user_tel foreign key(user_tel) references users(user_tel),
  constraint fk_state_code foreign key(state_code) references order_state(state_code)
);

create sequence orders_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCYCLE
    NOCACHE
    NOORDER;

insert into orders values(orders_seq.nextval, '010-1111-2222', 1, 'cash', 0, 10000, sysdate, 0);
insert into orders values(orders_seq.nextval, '010-3333-4444', 0, 'card', 0, 20000, sysdate, 1);
insert into orders values(orders_seq.nextval, '010-5555-6666', 0, 'cash', 0, 30000, sysdate, 0);
insert into orders values(orders_seq.nextval, '010-7777-8888', 1, 'card', 0, 40000, sysdate, 1);
insert into orders values(orders_seq.nextval, '010-9999-1111', 1, 'cash', 0, 50000, sysdate, 1);
insert into orders values(orders_seq.nextval, null, 1, 'cash', null, 50000, sysdate, 1);

select * from orders;

---------------------------------------------------------------------------
drop table product;

--상품(커피,티, 스무디, 디저트)
create table product( 
  prod_code varchar2(30) primary key, --상품코드
  prod_group varchar2(30), -- 상품분류 -coffee -smoothie -tea -dessert
  prod_name varchar2(30), -- 상품명
  prod_price number, -- 판매가격
  prod_detail varchar2(50), -- 상세정보
  soldout number --품절여부  
);

insert into product values('A01', null, 'coffee', null, null, null);
insert into product values('B01', null, 'smoothie', null, null, null);
insert into product values('C01', null, 'tea', null, null, null);
insert into product values('D01', null, 'dessert', null, null, null);
insert into product values('A02', 'A01', 'americano', 4500, 'bitter', 0);
insert into product values('B02', 'B01', 'strawberry', 5000, 'sweet', 1);
insert into product values('C02', 'C01', 'englishbreakfast', 6000, 'bitter', 1);
insert into product values('A03', 'A01', 'espresso', 4000, 'bitter', 0);
insert into product values('B03', 'B01', 'banana', 5000, 'sweet', 1);
insert into product values('C03', 'C01', 'appletea', 6000, 'bitter', 0);
insert into product values('D03', 'D01', 'redvelvetcake', 4000, 'sweet', 0);
insert into product values('D04', 'D01', 'macaron', 2500, 'sweet', 0);
insert into product values('D02', 'D01', 'cookie', 1500, 'sweet', 0);
insert into product values('B04', 'B01', 'kiwi', 5000, 'sweet', 1);
insert into product values('A04', 'A01', 'cappuccino', 6000, 'bitter', 1);
insert into product values('C04', 'C01', 'appletea', 5000, 'bitter', 1);
insert into product values('D05', 'D01', 'sandwich', 4500, 'sweet', 0);
insert into product values('B05', 'B01', 'kiwi', 5500, 'sour', 1);
insert into product values('A05', 'A01', 'cafemocha', 6000, 'sweet', 1);
insert into product values('C05', 'C01', 'gingertea', 4000, 'sweet', 1);

select * from product;


SELECT level, prod_code, prod_group, prod_name
 FROM product
 START WITH prod_group IS NULL
CONNECT BY PRIOR prod_code = prod_group
ORDER SIBLINGS BY prod_code;

------------------------------------------------------------
drop table order_line;

--주문상세
create table order_line( 
  order_line_code number primary key,--주문상세코드
  order_num number REFERENCES orders(order_num),--주문번호
  prod_code varchar2(30) REFERENCES product(prod_code), --상품코드
  qty number not null, --주문수량
  price_qty number not null --가격*주문수량
);

select*from order_line;

-------------------------------------------------------------------
drop table stock;
--재고
create table stock(
  prod_code VARCHAR2(30) REFERENCES product(prod_code) primary key,--상품코드
  prod_stock number --재고
);

insert into stock values('A02', 7);
insert into stock values('A03', 10);
insert into stock values('A04', 9);
insert into stock values('A05', 10);
insert into stock values('B02', 8);
insert into stock values('B03', 10);
insert into stock values('B04', 15);
insert into stock values('B05', 5);
insert into stock values('C02', 0);
insert into stock values('C03', 7);
insert into stock values('C04', 20);
insert into stock values('C05', 3);
insert into stock values('D02', 0);
insert into stock values('D03', 7);
insert into stock values('D04', 9);
insert into stock values('D05', 20);

select*from stock;

commit;

