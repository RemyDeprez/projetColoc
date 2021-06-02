package fr.formation.afpa.controller;


import java.util.ArrayList;
import java.util.List;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.afpa.domain.Location;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.Utilisateur;

import fr.formation.afpa.service.LocationService;
import fr.formation.afpa.utils.WebUtils;



@Controller
public class NavigationController {

	@Autowired
	LocationService service;
	
	private List<Location> listLoc = new ArrayList<Location>();

	

	

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
	@RequestMapping(value = "/inscription")
	public String getForm(Model model) {

		model.addAttribute("appuser", new AppUser());


		return "inscription";
	}
	//	Methode qui est lancée pour l'obtention de la page de gestion de la colocation
	@RequestMapping(value = "/getgestion")
	public String getGestion(Model model) {
		
		listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);
		return "gestionColoc";
	}



	@RequestMapping(path = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(Model model) {
		System.out.println("Déconnexion");
        model.addAttribute("title", "Logout");
		return "redirect:/index";
	}


	@RequestMapping(path = "/connexion", method  = RequestMethod.GET)
	public String getConnexion(Model model, Principal principal) {
		if(principal != null) {
			return "redirect:/index";
		}
		return "connexion";
	}

	@RequestMapping(path = "/ajoutbien", method  = RequestMethod.GET)
	public String getAjout(Model model, Principal principal) {
		if(principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "ajout";
	}

	

	@RequestMapping(path = "/Messagerie", method  = RequestMethod.GET)
	public String getMessagerie(Model model, Principal principal) {
		if(principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "messagerie";
	}

	@RequestMapping(path = "/rechercheLocation", method  = RequestMethod.GET)
	public String getRechercheLocation(Model model, Principal principal) {
		if(principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		
		listLoc = service.findAll();
		System.out.println(listLoc);
		model.addAttribute("locations", listLoc);
		return "rechercheLocation";
	}
	



}
