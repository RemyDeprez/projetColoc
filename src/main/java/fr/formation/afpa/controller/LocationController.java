package fr.formation.afpa.controller;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.metadata.aggregated.ValidatableParametersMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.Validate;

import fr.formation.afpa.FileUploadUtils;
import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;
import fr.formation.afpa.validator.LocationValidator;

@Controller

public class LocationController implements WebMvcConfigurer {
	private static final Log log = LogFactory.getLog(LocationController.class);

	@Autowired
	LocationService service;

	private List<Location> listLoc = new ArrayList<Location>();
	
	@Autowired
	private LocationValidator locationValidator;
	
	//Méthode pour configurer le validator
	@InitBinder
	   protected void initBinder(WebDataBinder dataBinder) {
	      
	      Object target = dataBinder.getTarget();
	      if (target == null) {
	         return;
	      }
	      System.out.println("Target=" + target);
	 
	      if (target.getClass() == Location.class) {
	         dataBinder.setValidator(locationValidator);
	      }
	   
	   }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/ajoutbien").setViewName("ajout");
	}

	@PostMapping(value = "/ajoutbien")
	public String add( @ModelAttribute("location") @Validated Location location, BindingResult bindingResult,
			@RequestParam("photos") MultipartFile photos) throws IOException {

		// Méhodes pour récupérer les erreurs dans la console
		System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());

//		ValidationUtils.rejectIfEmpty(bindingResult, "adress", location.getAdress());
		
		if (bindingResult.hasErrors()) {

			return "ajout";
	
	}
			String fileName = StringUtils.cleanPath(photos.getOriginalFilename());

			location.setPhotos(fileName);

			service.saveOrUpdate(location);
			String uploadDir = "photos/" + location.getLocationID();

			ImageController.saveFile(uploadDir, fileName, photos);

			return "redirect:/index";
	}

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

		listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);
		return "redirect:/index";
	}

	@GetMapping("/supprbien/{locationID}")
	public String delete(@PathVariable("locationID") Integer id, Model model) {
		service.deleteById(id);

		return "redirect:/index";
	}

}
