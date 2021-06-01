package fr.formation.afpa.controller;

<<<<<<< Updated upstream
=======
import java.io.IOException;
import java.util.List;

>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< Updated upstream
=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======

	@GetMapping("/modif/{locationID}")
	public String showUpdateForm(@PathVariable("locationID") Integer id, Model model) {
		Location loc = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("location", loc);
		return "modif";
	}

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

	@GetMapping("/supprbien/{locationID}")
	public String delete(@PathVariable("locationID") Integer id, Model model) {
		service.deleteById(id);

		return "redirect:/index";
	}
>>>>>>> Stashed changes

}
