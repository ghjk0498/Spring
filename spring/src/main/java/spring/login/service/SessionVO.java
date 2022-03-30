package spring.login.service;

public class SessionVO {

	private String email;
	private String password;
	
	public SessionVO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SessionVO [email=" + email + ", password=" + password + "]";
	}
	
}