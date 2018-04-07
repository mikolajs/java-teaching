package pl.edu.osp.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
	@RequestMapping("/")
	String home() {
		
		return "Ścieżka główna. Wybierz prawidłowy URL";
	}

	@RequestMapping("/list")
	public String hello(Model model, @RequestParam(value = "mail", required = true) String mail) {
		model.addAttribute("mail", mail);
		return "witaj";
	}
}
