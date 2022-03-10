drop table stock;
drop table order_line;
drop table product;
drop table orders;
drop table order_state;
drop table users;
------------------------------------------------------
drop sequence orders_seq;
drop sequence orderline_seq;
--유저테이블
create table users(
  user_tel varchar2(30) primary key, --회원전화번호
  user_name varchar2(30) not null,--이름
  user_point number default 0 check( user_point > -1 ) not null,--적립금
  reg_date date default sysdate not null, -- 가입일
  user_pwd number not null --비밀번호
);
insert into  users values('999-9999-9999','관리자', 99999, '2020-12-25', '9999');
insert into  users values('010-1111-2222','김유다', 15000, '2021-01-11', '1111');
insert into  users values('010-3333-4444','김진아', 14000, '2021-03-01', '2222');
insert into  users values('010-5555-6666','하용현', 20000, '2021-03-15', '3333');
insert into  users values('010-7777-8888','김효선', 34000, '2021-05-05', '4444');
insert into  users values('010-9999-1111','정우진', 500, '2021-09-02', '5555');

select * from users;
-----------------------------------------------------------------

--주문테이블
create table order_state(
  state_code number primary key, --주문상태코드 접수대기, 주문접수, 상품준비중, 상품준비완료, 상품픽업완료, 주문취소
  field varchar2(30) not null--주문상태
);

insert into order_state values(0, '접수대기');
insert into order_state values(1, '주문접수');
insert into order_state values(2, '상품준비중');
insert into order_state values(3, '상품준비완료');
insert into order_state values(4, '상품픽업완료');
insert into order_state values(5, '주문취소');

select * from order_state;
---------------------------------------------------------------------------

--주문테이블
create table orders(
  order_num NUMBER primary key, -- 주문번호
  user_tel varchar2(30),--전화번호
  state_code NUMBER not null,--주문상태코드
  pay_method varchar2(10) not null check(pay_method in('cash', 'card')), --결제방법
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


select * from orders;
---------------------------------------------------------------------------

--상품(커피,티, 스무디, 디저트)
create table product( 
  prod_code varchar2(30) primary key, --상품코드
  prod_group varchar2(30), -- 상품분류 -coffee -smoothie -tea -dessert
  prod_name varchar2(50), -- 상품명
  prod_price number, -- 판매가격
  prod_detail varchar2(1000), -- 상세정보
  prod_state number default 1 check (prod_state in(0, 1, 2))--제품상태 (0-판매중지,1-판매중,2-일시품절)
);

insert into product values('C', null, '커피', null, null, null);
insert into product values('C01', 'C', '아메리카노', 1000, '프리미엄 원두를 이용하여 깔끔하고 상큼한 맛', 1);
insert into product values('C02', 'C', '에스프레소', 2000, '깊고 강렬한 맛! 만들어지는 즉시 드시는 것 추천', 1);
insert into product values('C03', 'C', '카푸치노', 3000, '풍부하고 진한 에스프레소에 벨벳같은 우유 거품이 1:1 비율로 어우러저 부드러운 커피', 1);
insert into product values('C04', 'C', '돌체라떼', 4000, '깊은 커피의 맛과 깔끔한 무지방 우유와 부드러운 돌체 시럽이 들어간 음료인 클래식 라떼', 1);
insert into product values('C05', 'C', '카페모카', 4000, '진한 초콜릿 모카 시럽과 풍부한 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료', 1);

insert into product values('S', null, '스무디', null, null, null);
insert into product values('S01', 'S', '딸기스무디', 5000, '유산균이 살아있는 리얼 요거트와 풍성한 논산 딸기 과육이 상큼하게 어우러진 딸기스무디', 1);
insert into product values('S02', 'S', '바나나스무디', 6000, '신선한 바나나 1개가 통째로 들어간 달콤한 바나나스무디', 1);
insert into product values('S03', 'S', '레몬피치스무디', 7000, '복숭아와 레몬, 말랑한 복숭아 젤리가 조화로운 과일 블렌디드', 1);
insert into product values('S04', 'S', '망고블랙티스무디', 8000, '망고주스와 블랙 티가 깔끔하게 어우러진 과일 블렌디드', 1);
insert into product values('S05', 'S', '민트초코스무디', 8000, '민초단 모여! 입 안 가득 상쾌한 민트 초콜릿과 부드러운 돌체 소스가 어우러진 초콜릿 블렌디드', 1);

insert into product values('T', null, '티', null, null, null);
insert into product values('T01', 'T', '잉글리쉬 블랙퍼스트 티', 1000, '인도 아삼, 제주도 유기농 홍차가 블렌딩되어 진한 벌꿀향과 그윽한 몰트향이 특징인 블랙 티', 1);
insert into product values('T02', 'T', '얼그레이티', 2000, '꽃향 가득한 라벤더와 베르가못 향이 진한 홍차와 블렌딩된 향긋한 블랙 티', 1);
insert into product values('T03', 'T', '제주녹차', 3000, '우수한 품질의 제주도 유기농 녹차로 만든 맑은 수색과 신선한 향, 맛이 뛰어난 녹차', 1);
insert into product values('T04', 'T', '차이밀크티', 4000, '스파이시한 향과 독특한 계피향, 달콤한 차이로 만든 부드러운 티 라떼', 1);
insert into product values('T05', 'T', '캐모마일티', 4000, '캐모마일과 레몬 그라스, 레몬밤, 히비스커스 등 블렌딩되어 은은하고 차분한 향이 기분을 좋게하는 허브 티', 1);

insert into product values('D', null, '디저트', null, null, null);
insert into product values('D01', 'D', '클래식스콘', 1000, '프랑스산 고급 버터로 만든 담백한 스콘', 1);
insert into product values('D02', 'D', '흑임자크림케이크', 5000, '촉촉한 흑임자 케이크 시트 사이에 고소하고 달콤한 흑임자 크림이 층층이 샌드되어 있는 케이크', 1);
insert into product values('D03', 'D', '블루베리마카롱', 2000, '겉은 바삭하고 속은 쫄깃한 새콤 달콤 블루베리 맛의 마카롱', 1);
insert into product values('D04', 'D', '단호박에그샌드위치', 6000, '건강 잡곡 식빵 속에 단호박, 달걀, 토마토, 치즈 등을 넣은 콜드 샌드위치', 1);
insert into product values('D05', 'D', '다크초코쿠키', 6000, '진한 다크 초콜릿과 고소한 피칸이 들어있는 쿠키', 1);



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

--재고
create table stock(
  prod_code VARCHAR2(30) REFERENCES product(prod_code) primary key ,--상품코드
  prod_stock number check(0 <= prod_stock)  --재고
);
alter table stock add check(prod_code like 'D%');

insert into stock values('D01', 10);
insert into stock values('D02', 15);
insert into stock values('D03', 5);
insert into stock values('D04', 9);
insert into stock values('D05', 15);

select*from stock;

commit;
