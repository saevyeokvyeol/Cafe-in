package cafe.mvc.model.dto;

/**
 * DB에서 select를 통해 매출 통계를 계산한 뒤 그 결과를 Statistics 객체로 만들면 어떨까요?
 * */
public class Statistics {
	private String prodCode;
	private String prodName;
	private int prodPrice;
	private int salesQty;
	private int salesPrice;
	
	public Statistics() {}

	public Statistics(String prodCode, String prodName, int prodPrice, int salesQty, int salesPrice) {
		super();
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.salesQty = salesQty;
		this.salesPrice = salesPrice;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
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

	public int getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(int salesQty) {
		this.salesQty = salesQty;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}
	
}
