package fr.formation.afpa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.Utilisateur;
import fr.formation.afpa.service.LocationService;
import fr.formation.afpa.utils.WebUtils;

@Controller
public class NavigationController {

	@Autowired
	LocationService service;

	//	********************** NAVIGATION GENERALE ********************************************

	//	Methode de redirection à l'acceuil par défaut
	@RequestMapping(value = "/")
	public String index(Model model) {
		return "index";
	}

	//	Methode de redirection à l'acceuil depuis un lien
	@RequestMapping(value = "/index")
	public String getIndex(Model model, Principal principal) {
		if(principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "index";
	}
	//	Methode qui est lancée pour l'obtention du formulaire d'inscription
	@RequestMapping(value = "/getform")
	public String getForm(Model model) {
		AppUser appuser = new AppUser();
		model.addAttribute("appuser", appuser);
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

	@RequestMapping(path = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(Model model) {
		System.out.println("Déconnexion");
        model.addAttribute("title", "Logout");
		return "redirect:/index";
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

	@RequestMapping(path = "/rechercheLocation", method  = RequestMethod.GET)
	public String getRechercheLocation() {
		return "rechercheLocation";
	}


}
