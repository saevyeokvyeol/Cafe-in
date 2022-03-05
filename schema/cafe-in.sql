drop table users;

--�ֹ����̺�
create table users(
  user_tel varchar2(30) primary key, --ȸ����ȭ��ȣ
  user_name varchar2(30) not null,--�̸�
  user_point number not null,--������
  reg_date date not null, -- ������
  user_pwd number not null --��й�ȣ
);

insert into  users values('010-1111-2222','yuda', 0, sysdate, '1111');
insert into  users values('010-3333-4444','jina', 0, sysdate, '2222');
insert into  users values('010-5555-6666','yong', 0, sysdate, '3333');
insert into  users values('010-7777-8888','hyo', 0, sysdate, '4444');
insert into  users values('010-9999-1111','woo', 0, sysdate, '5555');

select * from users;
-----------------------------------------------------------------
drop table order_state;

--�ֹ����̺�
create table order_state(
  state_code number primary key, --�ֹ������ڵ�
  field varchar2(30) not null--�ֹ�����
);

insert into order_state values(0, 'Ordered');
insert into order_state values(1, 'Shipped');

select * from order_state;
---------------------------------------------------------------------------
drop table orders;

--�ֹ����̺�
create table orders(
  order_num NUMBER primary key, -- �ֹ���ȣ
  user_tel varchar2(30),--��ȭ��ȣ
  state_code NUMBER not null,--�ֹ������ڵ�
  pay_method varchar2(10) not null, --�������
  pay_point number, --�����ݻ��׼�
  total_price number not null, --�Ѱ����ݾ�
  order_date date not null, --�ֹ�����
  takeout number not null, --����ũ�ƿ�����
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

--��ǰ(Ŀ��,Ƽ, ������, ����Ʈ)
create table product( 
  prod_code varchar2(30) primary key, --��ǰ�ڵ�
  prod_group varchar2(30), -- ��ǰ�з� -coffee -smoothie -tea -dessert
  prod_name varchar2(30), -- ��ǰ��
  prod_price number, -- �ǸŰ���
  prod_detail varchar2(50), -- ������
  soldout number --ǰ������  
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

--�ֹ���
create table order_line( 
  order_line_code number primary key,--�ֹ����ڵ�
  order_num number REFERENCES orders(order_num),--�ֹ���ȣ
  prod_code varchar2(30) REFERENCES product(prod_code), --��ǰ�ڵ�
  qty number not null, --�ֹ�����
  price_qty number not null --����*�ֹ�����
);

select*from order_line;

-------------------------------------------------------------------
drop table stock;
--���
create table stock(
  prod_code VARCHAR2(30) REFERENCES product(prod_code) primary key,--��ǰ�ڵ�
  prod_stock number --���
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

