package cafe.mvc.session;

import java.util.HashMap;
import java.util.Map;

/**
 * 로그인된 사용자의 정보를 관리하는 객체
 * 싱글톤으로 생성할 것
 * */
public class Session {
	private String sessionId; // 세션에서 전화번호로 사용자를 식별
	private Map<String, Object> attributes; // 전화번호를 key로 사용자의 정보를 입력
	
	public Session() {}

	public Session(String sessionId) {
		super();
		this.sessionId = sessionId;
		attributes = new HashMap<>();
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public Object getAttributes(String name) {
		return attributes.get(name);
	}
		
	/**
	 * 정보 추가
	 * @param: name - key, data - Map<k, v>로 구성된 정보(현재에는 장바구니만 사용)
	 * */
	public void setAttribute(String name, Object data) { // 정보를 추가함
		attributes.put(name,data);
	}
	
	/**
	 * 정보 조회
	 * @param: 조회할 키 값
	 * */
	public Object getAttribute(String name) { // name을 가지고 data를 가져옴
		return attributes.get(name);
	}
	
	/**
	 * 정보 삭제
	 * @param: 삭제할 키 값
	 * */
	public void removeAttribute(String name) { // name을 통해 Map 삭제
		attributes.remove(name);
	}
	
	/**
	 * 해시 코드 값을 리턴하는 메소드
	 * @return: sessionId의 해시코드 값
	 * */
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	
	/**
	 * sessionId 중복 확인 메소드
	 * */
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		
		// 매개 변수로 받은 Session 객체와 현재 객체의 sessionId의 hashcode() 값을 비교함
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}
		
	}
}