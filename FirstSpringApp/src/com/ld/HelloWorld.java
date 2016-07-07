package com.ld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ld.model.User;

@Controller
public class HelloWorld {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(Model model) {
		System.out.println("Redirecting to login...");
		User user = new User();    
        model.addAttribute("user", user);
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.POST)
	public ModelAndView helloWorld(@ModelAttribute("user") User user, Model model) {
		System.out.println("Logging in:: " + user);
		return new ModelAndView("welcome");
	}
	
}
