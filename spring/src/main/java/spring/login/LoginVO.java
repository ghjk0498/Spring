package spring.login;

public class LoginVO {

	private String email;
	private String password;
	private String redirectURL;
	
	public LoginVO() {
		
	}
	
	public LoginVO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LoginVO(String email, String password, String redirectURL) {
		this(email, password);
		this.redirectURL = redirectURL;
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


	public String getRedirectURL() {
		return redirectURL;
	}


	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	@Override
	public String toString() {
		return "LoginVO [email=" + email + ", password=" + password + ", redirectURL=" + redirectURL + "]";
	}
	
}
