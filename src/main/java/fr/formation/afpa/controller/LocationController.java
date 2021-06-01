package fr.formation.afpa.controller;

<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import java.io.IOException;
import java.util.List;

>>>>>>> Stashed changes
=======
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

>>>>>>> b07711514a4629cf64e8bdc1178b0bb650b08742
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
>>>>>>> Stashed changes
=======
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
>>>>>>> b07711514a4629cf64e8bdc1178b0bb650b08742

import fr.formation.afpa.FileUploadUtils;
import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService service;


	@RequestMapping(value = "/ajoutbien")
	public String emp(Model model, Location location, String address, Integer superfice, Integer placeOccupe,
			Integer loyer, String ville, Integer codePostal, String titre, String description, boolean meuble,
			String photos) {

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
<<<<<<< HEAD
<<<<<<< Updated upstream
=======

=======
	
>>>>>>> b07711514a4629cf64e8bdc1178b0bb650b08742
	@GetMapping("/modif/{locationID}")
	public String showUpdateForm(@PathVariable("locationID") Integer id, Model model) {
		Location loc = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("location", loc);
		return "modif";
	}
<<<<<<< HEAD

	@PostMapping(value = "/modifbien/{locationID}")
	public String update(Model model, Location location, BindingResult bindingResult, String adress, Integer superfice,
			Integer placeOccupe, Integer loyer, String ville, Integer codePostal, String titre, String description,
			Boolean meuble, @RequestParam("photos") MultipartFile photos) throws IOException {

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

		List<Location>listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);
		return "redirect:/index";
	}

=======
	
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
	
>>>>>>> b07711514a4629cf64e8bdc1178b0bb650b08742
	@GetMapping("/supprbien/{locationID}")
	public String delete(@PathVariable("locationID") Integer id, Model model) {
		service.deleteById(id);

		return "redirect:/index";
	}
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
	

>>>>>>> b07711514a4629cf64e8bdc1178b0bb650b08742

}
