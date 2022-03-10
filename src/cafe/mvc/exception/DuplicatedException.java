package cafe.mvc.exception;

/**
 * 중복 시 발생하는 예외
 * */
public class DuplicatedException extends Exception {
	public DuplicatedException() {
	
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
}
