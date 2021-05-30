package fr.formation.afpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.service.IUtilisateurService;

@Controller
public class InscriptionController {
	
	@Autowired
	IUtilisateurService service ;
	
//	Methode lancée lorsque le formulaire est envoyé
	
	@RequestMapping(value = "/createaccount")
	public String index(Model model,AppUser appuser) {
		model.addAttribute("appuser", appuser );
		service.saveOrUpdate(appuser);
		return "index";
	}
	
//methpode lancée lorsque l'on appuie sur le boutton "valider" de l'update
	@RequestMapping(value = "/updateaccount")
	public String updateAccount(Model model,AppUser appuser) {
		model.addAttribute("appuser", appuser );
		service.saveOrUpdate(appuser);
		return "index";
	}
//methode lancée lorsque l'on appuie sur le button delete de l'update
	
	@RequestMapping(value = "/deleteaccount")
	public String deleteAccount(Model model,AppUser appuser) {
		model.addAttribute("appuser", appuser );
		service.delete(appuser);
		return "index";
	}
}
