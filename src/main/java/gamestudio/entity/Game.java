package gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
	@Id
	private String ident;
	
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String name; 
	
	private String description;
	
	public Game() {
		
	}
	
	public Game(String ident, String name, String description) {
		this.ident = ident;
		this.name = name;
		this.description = description;
	}
	

}
