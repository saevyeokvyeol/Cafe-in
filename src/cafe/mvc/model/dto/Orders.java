package cafe.mvc.model.dto;

public class Orders {
	 private int orderNum; //주문번호
	 private String userTel; //전화번호
	 private int stateCode; //주문상태코드
	 private String payMethod;// 결제방법
	 private int payPoint; //적립금사용액수
	 private int totalPrice; //총결제금액
	 private String orderDate; //주문일자
	 private int takeOut; //테이크아웃여부
	 private String userName;//이름
	 private int qty;//주문수량
	 private String prodName;//상품명
	 private int prodPrice;//판매가격
	 private int priceQty;//가격*주문수량
		//int주문상태코드, String이름, String상품명, int판매가격, int가격*주문수량
	 public Orders() {}
	 public Orders(int orderNum, String userTel, int stateCode, String payMethod, int payPoint, int totalPrice, String orderDate, int takeOut) {
		 this.orderNum= orderNum;
		 this.userTel= userTel;
		 this.stateCode= stateCode;
		 this.payMethod= payMethod;
		 this.payPoint= payPoint;
		 this.totalPrice= totalPrice;
		 this.orderDate= orderDate;
		 this.takeOut= takeOut;
	 }
	//전화번호,이름,주문수량,상품명,판매가격,가격*주문수량
	 public Orders(String userTel,String userName, int qty, String prodName, int prodPrice, int priceQty) {
		 this.userTel=userTel;
		 this.userName=userName;
		 this.qty=qty;
		 this.prodName=prodName;
		 this.prodPrice=prodPrice;
		 this.priceQty=priceQty;
	 }
	//int주문상태코드, String이름, String상품명,int수량 int판매가격, int가격*주문수량
	 public Orders(int stateCode, String userName,String prodName,int qty, int prodPrice, int priceQty) {
		 this.stateCode=stateCode;
		 this.userName=userName;
		 this.prodName=prodName;
		 this.qty=qty;
		 this.prodPrice=prodPrice;
		 this.priceQty=priceQty;
	 }
	   
		public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public int getPayPoint() {
		return payPoint;
	}
	public void setPayPoint(int payPoint) {
		this.payPoint = payPoint;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getTakeOut() {
		return takeOut;
	}
	public void setTakeOut(int takeOut) {
		this.takeOut = takeOut;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getPriceQty() {
		return priceQty;
	}
	public void setPriceQty(int priceQty) {
		this.priceQty = priceQty;
	}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Orders [orderNum=");
			builder.append(orderNum);
			builder.append(", userTel=");
			builder.append(userTel);
			builder.append(", stateCode=");
			builder.append(stateCode);
			builder.append(", payMethod=");
			builder.append(payMethod);
			builder.append(", payPoint=");
			builder.append(payPoint);
			builder.append(", totalPrice=");
			builder.append(totalPrice);
			builder.append(", orderDate=");
			builder.append(orderDate);
			builder.append(", takeOut=");
			builder.append(takeOut);
			builder.append(", userName=");
			builder.append(userName);
			builder.append(", qty=");
			builder.append(qty);
			builder.append(", prodName=");
			builder.append(prodName);
			builder.append(", prodPrice=");
			builder.append(prodPrice);
			builder.append(", priceQty=");
			builder.append(priceQty);
			builder.append("]");
			return builder.toString();
		}

		//int주문상태코드, String이름, String상품명, int판매가격, int가격*주문수량
		
        
}
