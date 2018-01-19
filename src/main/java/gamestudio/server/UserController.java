package gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gamestudio.entity.Player;
import gamestudio.service.impl.PlayerService;

@Controller
public class UserController {
	@Autowired
	private PlayerService playerService;

	private Player loggedPlayer;
	
	
	private String chkbx;
	
	public String getChkbx() {
		return chkbx;
	}



	public void setChkbx(String chkbx) {
		this.chkbx = chkbx;
	}

	private String errormsg;

	// private Player registerPlayer;

	public String getErrormsg() {
		return errormsg;
	}



	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}



	@RequestMapping("/games")
	public String games(Model model) {
		return "games";

	}
	
	
	
	@RequestMapping("/user")
	public String user(Model model) {
		return "login";

	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		return "contact";

	}
	
	@RequestMapping("/contact-form")
	public String contactform(Model model) {
		return "contact-form";

	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		return "register";

	}
	
	
	@RequestMapping("/profile")
	public String profile(Model model) {
		return "/profile";

	}
	
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";

	}
	


	@RequestMapping("/login")
	public String login(Player player, Model model) {

		loggedPlayer = playerService.login(player.getLogin(), player.getPassword());
		// if ("janko".equals(player.getLogin()) &&
		// "hrasko".equals(player.getPassword())) {
		// loggedPlayer = player;
		//return "login";

		return isLogged() ? "index" : "login";
		//return "index";
		//return "login";
	}
	// return "login";

	// }

	@RequestMapping("/register_sub")
	public String register_sub(Player player, String password_check, String checkbox, Model model) {
		
		//System.out.println(checkbox + " " + password_check);
		
		
		
		if (password_check.equals(player.getPassword())) {
		
			if("checkbox".equals(checkbox)) {
			
		playerService.register(player);
		loggedPlayer = playerService.login(player.getLogin(), player.getPassword());
		
			} else {
				chkbx = "You have to agree with basic rules";
			}
		
		
		} else {
			errormsg = "Passwords not match! try again";
		}
		
		//return isLogged() ? "login" : "login";
		return "register";

	}

	@RequestMapping("/logout")
	public String login(Model model) {
		loggedPlayer = null;
		return "index";
	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

	public boolean isLogged() {
		return loggedPlayer != null;
	}
}
