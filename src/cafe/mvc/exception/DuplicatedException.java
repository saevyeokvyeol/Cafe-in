package cafe.mvc.exception;

/**
 * �ߺ� �� �߻��ϴ� ����
 * */
public class DuplicatedException extends AddException {
	public DuplicatedException() {
	
	}
	
	public DuplicatedException(String message) {
		super(message);
	}
}
