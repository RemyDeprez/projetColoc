package fr.formation.afpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;



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
	public String getIndex(Model model) {
		return "index";
	}
//	Methode qui est lancée pour l'obtention du formulaire d'inscription
	@RequestMapping(value = "/getform")
	public String getForm(Model model) {
		return "inscription";
	}
//	Methode qui est lancée pour l'obtention de la page de gestion de la colocation
	@RequestMapping(value = "/getgestion")
	public String getGestion(Model model) {
		
		listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);
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
	
	@RequestMapping(path = "/rechercheLocation", method  = RequestMethod.GET)
	public String getRechercheLocation() {
		return "rechercheLocation";
	}

	



}
