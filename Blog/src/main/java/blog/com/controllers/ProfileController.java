package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ProfileController {
	@GetMapping("/blog/profile")
	public String getRegister() {
		return "profile.html";
	}
}
