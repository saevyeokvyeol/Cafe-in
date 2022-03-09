package cafe.mvc.model.dto;

public class OrdersStateDTO {
	 private int stateCode;
	 private String field;

	public OrdersStateDTO() {}
	  public OrdersStateDTO(int stateCode, String field) {
	  this.stateCode= stateCode;
	  this.field= field;
	}
		public int getStateCode() {
			return stateCode;
		}
		public void setStateCode(int stateCode) {
			this.stateCode = stateCode;
		}
	    public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
	  

	}