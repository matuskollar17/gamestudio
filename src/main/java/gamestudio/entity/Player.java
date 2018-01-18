package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String login;
	
	private String password;
	
	private String passowrd_check;
	
	public String getPassowrd_check() {
		return passowrd_check;
	}

	public void setPassowrd_check(String passowrd_check) {
		this.passowrd_check = passowrd_check;
	}

	public int getIdent() {
		return ident;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 

}
