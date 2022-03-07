package cafe.mvc.session;

import java.util.HashSet;
import java.util.Set;

/**
 * ���� �α����� ����ڸ� �����ϴ� Set
 * �̱������� ������ ��
 * */
public class SessionSet {

	private static SessionSet ss = new SessionSet(); // �̱������� �����ϱ� ���� private�� ��ü ����
	
	private Set<Session> set;
	private SessionSet() {
		set = new HashSet<>();
	}
	
	// public static���� �����ϰ� ��ü ������ ��� ������ ���� ��ü�� ���
	public static SessionSet getInstance() {
		return ss;
	}
	
	/**
	 * ���� �α����� ����� ��ȸ
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
	 * �α��ε� ����ڸ� set�� �߰�
	 * */
	public void add(Session session) {
		set.add(session);
	}

	/**
	 * �α׾ƿ��� ����ڸ� set���� ����
	 * */
	public void remove(Session session) {
		set.remove(session);
	}
}
