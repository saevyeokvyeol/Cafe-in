package cafe.mvc.session;

import java.util.HashMap;
import java.util.Map;

/**
 * �α��ε� ������� ������ �����ϴ� ��ü
 * �̱������� ������ ��
 * */
public class Session {
	private String sessionId; // ���ǿ��� ��ȭ��ȣ�� ����ڸ� �ĺ�
	private Map<String, Object> attributes; // ��ȭ��ȣ�� key�� ������� ������ �Է�
	
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
	 * ���� �߰�
	 * @param: name - key, data - Map<k, v>�� ������ ����(���翡�� ��ٱ��ϸ� ���)
	 * */
	public void setAttribute(String name, Object data) { // ������ �߰���
		attributes.put(name,data);
	}
	
	/**
	 * ���� ��ȸ
	 * @param: ��ȸ�� Ű ��
	 * */
	public Object getAttribute(String name) { // name�� ������ data�� ������
		return attributes.get(name);
	}
	
	/**
	 * ���� ����
	 * @param: ������ Ű ��
	 * */
	public void removeAttribute(String name) { // name�� ���� Map ����
		attributes.remove(name);
	}
	
	/**
	 * �ؽ� �ڵ� ���� �����ϴ� �޼ҵ�
	 * @return: sessionId�� �ؽ��ڵ� ��
	 * */
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	
	/**
	 * sessionId �ߺ� Ȯ�� �޼ҵ�
	 * */
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		
		// �Ű� ������ ���� Session ��ü�� ���� ��ü�� sessionId�� hashcode() ���� ����
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}
		
	}
}