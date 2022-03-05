package cafe.mvc.exception;

/**
 * 검색 결과가 없을 때 발생하는 예외
 * */
public class NotFoundException extends Exception {
	public  NotFoundException() {
	}
	
	public NotFoundException(String message) {
		super(message);
	}

}
