package cafe.mvc.model.dto;

public class Product {
	   private String prodCode;
	   private String prodGroup;
	   private String prodName;
	   private int prodPrice;
	   private String prodDetail;
	   private int prodState; //soldOut에서 변경!
	   
	   // 재고가 있는 디저트의 경우 1:1의 관계로 stock을 가지고 있음
	   private Stock stock;

	public Product() {}
	  public Product(String prodCode, String prodGroup, String prodName, int prodPrice, String prodDetail, int prodState) {
	   this.prodCode= prodCode;
	   this.prodGroup= prodGroup;
	   this.prodName= prodName;
	   this.prodPrice= prodPrice;
	   this.prodDetail= prodDetail;
	   this.prodState= prodState;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdGroup() {
		return prodGroup;
	}
	public void setProdGroup(String prodGroup) {
		this.prodGroup = prodGroup;
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
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public int getProdState() {
		return prodState;
	}
	public void setProdState(int prodState) {
		this.prodState = prodState;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	  
}
