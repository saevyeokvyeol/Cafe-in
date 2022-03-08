package cafe.mvc.model.dto;

public class Users{
	private String userTel; //ȸ����ȭ��ȣ
    private String userName; //�̸�
    private int userPoint; //������
    private int userPwd; //��й�ȣ
    private String regDate;
   
    public Users() {}

    public Users(String userTel, String userName, int userPoint, int userPwd) {
	    this.userTel = userTel;
		this.userName = userName;
		this.userPoint = userPoint;
		this.userPwd = userPwd;
    }
    
    public Users(String userTel, String userName, int userPoint,String regDate, int userPwd) {
	    this.userTel = userTel;
		this.userName = userName;
		this.userPoint = userPoint;
		this.regDate = regDate;
		this.userPwd = userPwd;
    }
    
	public Users(String userTel, String userName, int userPwd) {
		super();
		this.userTel = userTel;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	
	public Users(int userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public int getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(int userPwd) {
		this.userPwd = userPwd;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
  
}
