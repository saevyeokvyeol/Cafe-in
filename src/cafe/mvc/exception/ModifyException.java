package cafe.mvc.exception;

/**
 * 수정 및 변경 오류 시 발생하는 예외
 * */
public class ModifyException extends Exception {
	public  ModifyException() {
	}
	
	public ModifyException(String message) {
		super(message);
	}

}
