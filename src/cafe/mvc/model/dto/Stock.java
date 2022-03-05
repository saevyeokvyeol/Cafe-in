package cafe.mvc.model.dto;

public class Stock {
	private String prodCode;
	private int prodStock;

	public Stock() {}
	 public Stock(String prodCode, int prodStock) {
		super();
		 this.prodCode= prodCode;
		 this.prodStock= prodStock;
		}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public int getProdStock() {
		return prodStock;
	}
	public void setProdStock(int prodStock) {
		this.prodStock = prodStock;
	}
		

}
