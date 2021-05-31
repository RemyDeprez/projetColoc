package fr.formation.afpa.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getGestion(Model model) throws IOException {
		System.out.println("in the find all");
		
	for (Location location : listLoc) {
		System.out.println(location.getLocationID());
	}
		model.addAttribute("listLoc", service.findAll());
	
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
