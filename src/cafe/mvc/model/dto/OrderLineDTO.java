package cafe.mvc.model.dto;

public class OrderLineDTO {
		   private int orderLineCode;
		   private int orderNum;
		   private String prodCode;
		   private int qty;
		   private int priceQty;

		public OrderLineDTO() {}
		  public OrderLineDTO(int orderLineCode, int orderNum, String prodCode, int qty, int priceQty) {
		   this.orderLineCode= orderLineCode;
		   this.orderNum= orderNum;
		   this.prodCode= prodCode;
		   this.qty= qty;
		   this.priceQty= priceQty;
		}
		    
		public int getOrderLineCode() {
			return orderLineCode;
		}
		public void setOrderLineCode(int orderLineCode) {
			this.orderLineCode = orderLineCode;
		}
		public int getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(int orderNum) {
			this.orderNum = orderNum;
		}
		public String getProdCode() {
			return prodCode;
		}
		public void setProdCode(String prodCode) {
			this.prodCode = prodCode;
		}
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		public int getPriceQty() {
			return priceQty;
		}
		public void setPriceQty(int priceQty) {
			this.priceQty = priceQty;
		}
		  
}
