package cafe.mvc.model.dto;

public class Orders {
	 private int orderNum; //�ֹ���ȣ
	 private String userTel; //��ȭ��ȣ
	 private int stateCode; //�ֹ������ڵ�
	 private String payMethod;// �������
	 private int payPoint; //�����ݻ��׼�
	 private int totalPrice; //�Ѱ����ݾ�
	 private String orderDate; //�ֹ�����
	 private int takeOut; //����ũ�ƿ�����
	 private String userName;//�̸�
	 private int qty;//�ֹ�����
	 private String prodName;//��ǰ��
	 private int prodPrice;//�ǸŰ���
	 private int priceQty;//����*�ֹ�����

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
	//��ȭ��ȣ,�̸�,�ֹ�����,��ǰ��,�ǸŰ���,����*�ֹ�����
	 public Orders(String userTel,String userName, int qty, String prodName, int prodPrice, int priceQty) {
		 this.userTel=userTel;
		 this.userName=userName;
		 this.qty=qty;
		 this.prodName=prodName;
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
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Orders [userTel=");
			builder.append(userTel);
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

        
}
