package cafe.mvc.exception;

/**
 * �ߺ� �� �߻��ϴ� ����
 * */
public class DuplicatedException extends Exception {
	public DuplicatedException() {
	
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
}
