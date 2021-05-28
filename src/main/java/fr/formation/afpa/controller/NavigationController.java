package fr.formation.afpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.afpa.domain.Utilisateur;

@Controller
public class NavigationController {
	

	
//	Methode de redirection à l'acceuil par défaut
	@RequestMapping(value = "/")
	public String index(Model model) {
		return "index";
	}
//	Methode qui est lancée pour l'obtention du formulaire d'inscription
	@RequestMapping(value = "/getform")
	public String getForm(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "inscription";
	}
//	Methode qui est lancée pour l'obtention de la page de gestion de la colocation
	@RequestMapping(value = "/getgestion")
	public String getGestion(Model model) {
		return "gestionColoc";
	}
	


	
	@RequestMapping(path = "/fiche", method  = RequestMethod.GET)
	public String getFiche() {
		return "fiche";
	}
	

	
	@RequestMapping(path = "/connexion", method  = RequestMethod.GET)
	public String getConnexion() {
		return "connexion";
	}
	
	@RequestMapping(path = "/ajout", method  = RequestMethod.GET)
	public String getAjout() {
		return "ajout";
	}
	
	@RequestMapping(path = "/modif", method  = RequestMethod.GET)
	public String getModif() {
		return "modif";
	}
	
	@RequestMapping(path = "/messagerie", method  = RequestMethod.GET)
	public String getMessagerie() {
		return "messagerie";
	}


}
