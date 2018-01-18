package gamestudio.server;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class PexesoController {

	@RequestMapping("/pexeso")
	public String pexeso(Model model) {
		return "pexeso";

	}
}
