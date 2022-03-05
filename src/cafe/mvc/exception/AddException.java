package cafe.mvc.exception;

/**
 * insert 실패 시 발생하는 예외
 * */
public class AddException extends Exception {
	public  AddException() {
	}
	
	public AddException(String message) {
		super(message);
	}

}
