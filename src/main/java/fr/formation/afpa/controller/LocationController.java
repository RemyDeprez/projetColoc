package fr.formation.afpa.controller;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.afpa.FileUploadUtils;
import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;
	
	private List<Location> listLoc = new ArrayList<Location>();
	
	@PostMapping(value = "/ajoutbien")
@ResponseBody
	public String  add(Location location, BindingResult bindingResult, String address, Integer superfice, Integer placeOccupe, Integer loyer,
			String ville, Integer codePostal, String titre, String description, Boolean meuble, @RequestParam("photos") MultipartFile photos
			) throws IOException {

	    String fileName = StringUtils.cleanPath(photos.getOriginalFilename());             
 

location.setAdress(address);
location.setSuperfice(superfice);
location.setMaxColocataire(placeOccupe);
location.setLoyer(loyer);
location.setVille(ville);
location.setCodePostal(codePostal);
location.setTitre(titre);
location.setDescription(description);
location.setMeuble(meuble);
location.setPhotos(fileName);

service.saveOrUpdate(location);
String uploadDir = "photos/" + location.getLocationID();



ImageController.saveFile(uploadDir, fileName, photos);


			

		return "index";
	}
	
	@GetMapping("/modif/{locationID}")
	public String showUpdateForm(@PathVariable("locationID") Integer id, Model model) {
		Location loc = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("location", loc);
		return "modif";
	}
	
	@PostMapping(value = "/modifbien/{locationID}")
	public String update(Model model, Location location, BindingResult bindingResult, String adress, Integer superfice, Integer placeOccupe, Integer loyer,
			String ville, Integer codePostal, String titre, String description, Boolean meuble, @RequestParam("photos") MultipartFile photos) throws IOException {

		   String fileName = StringUtils.cleanPath(photos.getOriginalFilename());    

location.setAdress(adress);
location.setSuperfice(superfice);
location.setMaxColocataire(placeOccupe);
location.setLoyer(loyer);
location.setVille(ville);
location.setCodePostal(codePostal);
location.setTitre(titre);
location.setDescription(description);
location.setMeuble(meuble);
location.setPhotos(fileName);


service.saveOrUpdate(location);
String uploadDir = "photos/" + location.getLocationID();



ImageController.saveFile(uploadDir, fileName, photos);

			service.saveOrUpdate(location);

			listLoc = service.findAll();
			model.addAttribute("listLoc", listLoc);
		return "redirect:/index";
	}
	
	@GetMapping("/supprbien/{locationID}")
	public String delete(@PathVariable("locationID") Integer id, Model model) {
		service.deleteById(id);

		return "redirect:/index";
	}
	
	
	@RequestMapping(path = "/fiche", method = RequestMethod.GET)
	public String ficheColocation(Model model, @RequestParam("locationID") Integer id) {
		Location location = service.findById(id).get();
		model.addAttribute("locationID", location);
		return "fiche";
	}
	


}
