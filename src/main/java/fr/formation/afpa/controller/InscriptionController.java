package fr.formation.afpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.afpa.domain.Utilisateur;
import fr.formation.afpa.service.IUtilisateurService;

@Controller
public class InscriptionController {
	
	@Autowired
	IUtilisateurService service ;
	
//	Methode lancée lorsque le formulaire est envoyé
	
	@RequestMapping(value = "/createaccount")
	public String index(Model model,Utilisateur utilisateur) {
		model.addAttribute("utilisateur", utilisateur );
		service.saveOrUpdate(utilisateur);
		return "index";
	}
	
//methode lancée lorsque l'on demande le formulaire d'update de profil
	@RequestMapping(value= "/getprofile")
	public String getProfile (Model model, Utilisateur utilisateur) {
		
		return "modifprofile";
	}
	
//methpode lancée lorsque l'on appuie sur le boutton "valider" de l'update
	@RequestMapping(value = "/updateaccount")
	public String updateAccount(Model model,Utilisateur utilisateur) {
		model.addAttribute("utilisateur", utilisateur );
		service.saveOrUpdate(utilisateur);
		return "index";
	}
//methode lancée lorsque l'on appuie sur le button delete de l'update
	
	@RequestMapping(value = "/deleteaccount")
	public String deleteAccount(Model model,Utilisateur utilisateur) {
		model.addAttribute("utilisateur", utilisateur );
		service.delete(utilisateur);
		return "index";
	}
}
