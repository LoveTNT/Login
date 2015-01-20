package cn.yun.usermng.domain;

public class User {
	private String username;
	private String password;
	private String repassword;
	private String email;
	private String verifyCode;
	private String registtime;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getRegisttime() {
		return registtime;
	}
	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", repassword=" + repassword + ", email=" + email
				+ ", verifyCode=" + verifyCode + ", registtime=" + registtime
				+ "]";
	}
	
	
}
