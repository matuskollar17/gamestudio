package gamestudio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {

	/*
	 * Create TABLE comment (
	 * ident INTEGER PRIMARY KEY, 
	 * username VARCHAR(32) NOT NULL,
	 * game VARCHAR(32) NOT NULL,
	 * content VARCHAR(128) NOT NULL,
	 * createdOn TIMESTAMP NOT NULL
	 * );
	 */
	
	
	public Comment() {
		
	}
	
	
	public Comment(String username, String game, String content) {
				this.username = username;
		this.game = game;
		this.content = content;
		this.createdOn = new java.sql.Timestamp(new Date().getTime());
	}
	
	
	@Id
	@GeneratedValue
	private int ident;
	private String username;
	private String game;
	private String content;
	private Date createdOn;
	
	public int getIdent() {
		return ident;
	}
	public void setIdent(int ident) {
		this.ident = ident;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	@Override
	public String toString() {
		return String.format("Comment (%d %s %s %s %tF)", ident, username, game, content, createdOn);
		//return "Score [ident=" + ident + ", username=" + username + ", game=" + game + ", value=" + value + "]";
	}
	
	
	
}
