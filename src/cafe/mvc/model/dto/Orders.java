package cafe.mvc.model.dto;

import java.util.List;

public class Orders {
	 private int orderNum; //주문번호
	 private String userTel; //전화번호
	 private int stateCode; //주문상태코드
	 private String payMethod;// 결제방법
	 private int payPoint; //적립금사용액수
	 private int totalPrice; //총결제금액
	 private String orderDate; //주문일자
	 private int takeOut; //테이크아웃여부
	 
	 private List<OrderLine> ordelLineList;

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
		public List<OrderLine> getOrdelLineList() {
			return ordelLineList;
		}
		public void setOrdelLineList(List<OrderLine> ordelLineList) {
			this.ordelLineList = ordelLineList;
		}

        
}
