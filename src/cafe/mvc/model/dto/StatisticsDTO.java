package cafe.mvc.model.dto;

/**
 * DB에서 select를 통해 매출 통계를 계산한 뒤 그 결과를 Statistics 객체로 만들면 어떨까요?
 * */
public class StatisticsDTO {
	private String date;
	private int dailyOrderTimes;
	private int dailySalesPrice;
	private int dailySalesQty;

	public StatisticsDTO() {}
	
	public StatisticsDTO(String date, int dailyOrderTimes, int dailySalesPrice, int dailySalesQty) {
		super();
		this.date = date;
		this.dailyOrderTimes = dailyOrderTimes;
		this.dailySalesPrice = dailySalesPrice;
		this.dailySalesQty = dailySalesQty;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDailyOrderTimes() {
		return dailyOrderTimes;
	}

	public void setDailyOrderTimes(int dailyOrderTimes) {
		this.dailyOrderTimes = dailyOrderTimes;
	}

	public int getDailySalesPrice() {
		return dailySalesPrice;
	}

	public void setDailySalesPrice(int dailySalesPrice) {
		this.dailySalesPrice = dailySalesPrice;
	}

	public int getDailySalesQty() {
		return dailySalesQty;
	}

	public void setDailySalesQty(int dailySalesQty) {
		this.dailySalesQty = dailySalesQty;
	}
	
	
}
