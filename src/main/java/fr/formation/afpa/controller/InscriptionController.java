package fr.formation.afpa.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.afpa.domain.Utilisateur;
import fr.formation.afpa.service.IUtilisateurService;

@Controller
public class InscriptionController {
	
	@Autowired
	IUtilisateurService service ;
	
//	Methode lancée lorsque le formulaire est envoyé
	@RequestMapping(value = "/createaccount")
	public String index(Model model, Utilisateur utilisateur) {
		utilisateur.setDate(new Date());
		model.addAttribute("utilisateur", utilisateur );
		service.saveOrUpdate(utilisateur);
		return "index";
	}
}
