package model;


public class Login{
	private String email;
	private String password;
	
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public boolean auth(String email,String password) {
		Login login = new Login(email, password);
		return this.email.equals(email) && this.password.equals(password);
	}
}