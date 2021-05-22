package fr.formation.afpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/getform")
	public String getForm(Model model) {
		return "inscription";
	}

}
