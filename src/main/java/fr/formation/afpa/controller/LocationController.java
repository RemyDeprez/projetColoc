package fr.formation.afpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;
	
	
	
	@RequestMapping(value = "/ajoutbien")
	public String emp(Model model, Location location, String address, Integer superfice, Integer placeOccupe, Integer loyer,
			String ville, Integer codePostal, String titre, String description, Boolean meuble, String photos) {


location.setAdress(address);
location.setSuperfice(superfice);
location.setMaxColocataire(placeOccupe);
location.setLoyer(loyer);
location.setVille(ville);
location.setCodePostal(codePostal);
location.setTitre(titre);
location.setDescription(description);
location.setMeuble(meuble);
location.setPhotos(photos);


			service.saveOrUpdate(location);

		return "index";
	}

}
