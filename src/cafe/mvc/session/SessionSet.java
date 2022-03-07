package cafe.mvc.session;

import java.util.HashSet;
import java.util.Set;

/**
 * 현재 로그인한 사용자를 관리하는 Set
 * 싱글톤으로 생성할 것
 * */
public class SessionSet {

	private static SessionSet ss = new SessionSet(); // 싱글톤으로 관리하기 위해 private로 객체 생성
	
	private Set<Session> set;
	private SessionSet() {
		set = new HashSet<>();
	}
	
	// public static으로 생성하고 객체 리턴해 모든 곳에서 단일 객체를 사용
	public static SessionSet getInstance() {
		return ss;
	}
	
	/**
	 * 현재 로그인한 사용자 조회
	 * */
	public Session get(String sessionId) {
		for(Session session : set) {
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
	public Set<Session> getSet(){
		return set;
	}
	
	/**
	 * 로그인된 사용자를 set에 추가
	 * */
	public void add(Session session) {
		set.add(session);
	}

	/**
	 * 로그아웃한 사용자를 set에서 제외
	 * */
	public void remove(Session session) {
		set.remove(session);
	}
}
