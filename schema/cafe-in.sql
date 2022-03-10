drop table stock;
drop table order_line;
drop table product;
drop table orders;
drop table order_state;
drop table users;
------------------------------------------------------
drop sequence orders_seq;
drop sequence orderline_seq;
--�������̺�
create table users(
  user_tel varchar2(30) primary key, --ȸ����ȭ��ȣ
  user_name varchar2(30) not null,--�̸�
  user_point number default 0 check( user_point > -1 ) not null,--������
  reg_date date default sysdate not null, -- ������
  user_pwd number not null --��й�ȣ
);
insert into  users values('999-9999-9999','������', 99999, '2020-12-25', '9999');
insert into  users values('010-1111-2222','������', 15000, '2021-01-11', '1111');
insert into  users values('010-3333-4444','������', 14000, '2021-03-01', '2222');
insert into  users values('010-5555-6666','�Ͽ���', 20000, '2021-03-15', '3333');
insert into  users values('010-7777-8888','��ȿ��', 34000, '2021-05-05', '4444');
insert into  users values('010-9999-1111','������', 500, '2021-09-02', '5555');

select * from users;
-----------------------------------------------------------------

--�ֹ����̺�
create table order_state(
  state_code number primary key, --�ֹ������ڵ� �������, �ֹ�����, ��ǰ�غ���, ��ǰ�غ�Ϸ�, ��ǰ�Ⱦ��Ϸ�, �ֹ����
  field varchar2(30) not null--�ֹ�����
);

insert into order_state values(0, '�������');
insert into order_state values(1, '�ֹ�����');
insert into order_state values(2, '��ǰ�غ���');
insert into order_state values(3, '��ǰ�غ�Ϸ�');
insert into order_state values(4, '��ǰ�Ⱦ��Ϸ�');
insert into order_state values(5, '�ֹ����');

select * from order_state;
---------------------------------------------------------------------------

--�ֹ����̺�
create table orders(
  order_num NUMBER primary key, -- �ֹ���ȣ
  user_tel varchar2(30),--��ȭ��ȣ
  state_code NUMBER not null,--�ֹ������ڵ�
  pay_method varchar2(10) not null check(pay_method in('cash', 'card')), --�������
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


select * from orders;
---------------------------------------------------------------------------

--��ǰ(Ŀ��,Ƽ, ������, ����Ʈ)
create table product( 
  prod_code varchar2(30) primary key, --��ǰ�ڵ�
  prod_group varchar2(30), -- ��ǰ�з� -coffee -smoothie -tea -dessert
  prod_name varchar2(50), -- ��ǰ��
  prod_price number, -- �ǸŰ���
  prod_detail varchar2(1000), -- ������
  prod_state number default 1 check (prod_state in(0, 1, 2))--��ǰ���� (0-�Ǹ�����,1-�Ǹ���,2-�Ͻ�ǰ��)
);

insert into product values('C', null, 'Ŀ��', null, null, null);
insert into product values('C01', 'C', '�Ƹ޸�ī��', 1000, '�����̾� ���θ� �̿��Ͽ� ����ϰ� ��ŭ�� ��', 1);
insert into product values('C02', 'C', '����������', 2000, '��� ������ ��! ��������� ��� ��ô� �� ��õ', 1);
insert into product values('C03', 'C', 'īǪġ��', 3000, 'ǳ���ϰ� ���� ���������ҿ� �������� ���� ��ǰ�� 1:1 ������ ��췯�� �ε巯�� Ŀ��', 1);
insert into product values('C04', 'C', '��ü��', 4000, '���� Ŀ���� ���� ����� ������ ������ �ε巯�� ��ü �÷��� �� ������ Ŭ���� ��', 1);
insert into product values('C05', 'C', 'ī���ī', 4000, '���� ���ݸ� ��ī �÷��� ǳ���� ���������Ҹ� ���� ��ũ�� ���� ����ũ������ �������� ����', 1);

insert into product values('S', null, '������', null, null, null);
insert into product values('S01', 'S', '���⽺����', 5000, '������� ����ִ� ���� ���Ʈ�� ǳ���� ��� ���� ������ ��ŭ�ϰ� ��췯�� ���⽺����', 1);
insert into product values('S02', 'S', '�ٳ���������', 6000, '�ż��� �ٳ��� 1���� ��°�� �� ������ �ٳ���������', 1);
insert into product values('S03', 'S', '������ġ������', 7000, '�����ƿ� ����, ������ ������ ������ ��ȭ�ο� ���� �����', 1);
insert into product values('S04', 'S', '�����Ƽ������', 8000, '�����ֽ��� �� Ƽ�� ����ϰ� ��췯�� ���� �����', 1);
insert into product values('S05', 'S', '��Ʈ���ڽ�����', 8000, '���ʴ� ��! �� �� ���� ������ ��Ʈ ���ݸ��� �ε巯�� ��ü �ҽ��� ��췯�� ���ݸ� �����', 1);

insert into product values('T', null, 'Ƽ', null, null, null);
insert into product values('T01', 'T', '�ױ۸��� ���۽�Ʈ Ƽ', 1000, '�ε� �ƻ�, ���ֵ� ����� ȫ���� �����Ǿ� ���� ������� ������ ��Ʈ���� Ư¡�� �� Ƽ', 1);
insert into product values('T02', 'T', '��׷���Ƽ', 2000, '���� ������ �󺥴��� �������� ���� ���� ȫ���� ������ ����� �� Ƽ', 1);
insert into product values('T03', 'T', '���ֳ���', 3000, '����� ǰ���� ���ֵ� ����� ������ ���� ���� ������ �ż��� ��, ���� �پ ����', 1);
insert into product values('T04', 'T', '���̹�ũƼ', 4000, '�����̽��� ��� ��Ư�� ������, ������ ���̷� ���� �ε巯�� Ƽ ��', 1);
insert into product values('T05', 'T', 'ĳ����Ƽ', 4000, 'ĳ���ϰ� ���� �׶�, �����, ����Ŀ�� �� �����Ǿ� �����ϰ� ������ ���� ����� �����ϴ� ��� Ƽ', 1);

insert into product values('D', null, '����Ʈ', null, null, null);
insert into product values('D01', 'D', 'Ŭ���Ľ���', 1000, '�������� ��� ���ͷ� ���� ����� ����', 1);
insert into product values('D02', 'D', '������ũ������ũ', 5000, '������ ������ ����ũ ��Ʈ ���̿� ����ϰ� ������ ������ ũ���� ������ ����Ǿ� �ִ� ����ũ', 1);
insert into product values('D03', 'D', '��纣����ī��', 2000, '���� �ٻ��ϰ� ���� �̱��� ���� ���� ��纣�� ���� ��ī��', 1);
insert into product values('D04', 'D', '��ȣ�ڿ��׻�����ġ', 6000, '�ǰ� ��� �Ļ� �ӿ� ��ȣ��, �ް�, �丶��, ġ�� ���� ���� �ݵ� ������ġ', 1);
insert into product values('D05', 'D', '��ũ������Ű', 6000, '���� ��ũ ���ݸ��� ����� ��ĭ�� ����ִ� ��Ű', 1);



select * from product
order by prod_code;
SELECT level, prod_code, prod_group, prod_name
 FROM product
 START WITH prod_group IS NULL
CONNECT BY PRIOR prod_code = prod_group
ORDER SIBLINGS BY prod_code;


------------------------------------------------------------

create sequence orderline_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCYCLE
    NOCACHE
    NOORDER;

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

--���
create table stock(
  prod_code VARCHAR2(30) REFERENCES product(prod_code) primary key ,--��ǰ�ڵ�
  prod_stock number check(0 <= prod_stock)  --���
);
alter table stock add check(prod_code like 'D%');

insert into stock values('D01', 10);
insert into stock values('D02', 15);
insert into stock values('D03', 5);
insert into stock values('D04', 9);
insert into stock values('D05', 15);

select*from stock;

commit;
