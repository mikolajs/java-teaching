package pl.edu.osp.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
	@RequestMapping("/")
	String home() {
		return "Witaj w ścieżce głównej";
	}

	 @RequestMapping("/hello")
	    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
	        model.addAttribute("name", name);
	        return "witaj";
	    }
}
