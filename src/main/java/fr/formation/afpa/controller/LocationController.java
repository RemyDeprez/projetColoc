package fr.formation.afpa.controller;



import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.Location;
import fr.formation.afpa.domain.LocationForm;
import fr.formation.afpa.domain.Reservation;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.LocationService;
import fr.formation.afpa.service.ReservationService;
import fr.formation.afpa.service.UtilisateurService;
import fr.formation.afpa.utils.WebUtils;
import fr.formation.afpa.validator.LocationValidator;

@Controller

public class LocationController implements WebMvcConfigurer {
	private static final Log log = LogFactory.getLog(LocationController.class);

	@Autowired
	LocationService service;

	private List<Location> listLoc = new ArrayList<Location>();

	@Autowired
	private LocationValidator locationValidator;
	
	@Autowired
	private UserRepository utilisateurService;

	@Autowired
	private ReservationService reservationService;
	
	// Méthode pour configurer le validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {

		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == LocationForm.class) {
			dataBinder.setValidator(locationValidator);
		}

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/ajoutbien").setViewName("ajout");
	}

	@PostMapping(value = "/ajoutbien")
	public String add(@ModelAttribute("location") @Validated LocationForm locationForm, BindingResult bindingResult,
			@RequestParam("photos") MultipartFile photos, Principal principal, Model model, Authentication auth) throws IOException {
		
		
		
		// Méhodes pour récupérer les erreurs dans la console
		System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());

		if (bindingResult.hasErrors()) {
			return "ajout";
		}
		
		String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
		
		AppUser user = utilisateurService.findByUserName(auth.getName());
		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		System.out.println(loginedUser.getUsername());
		String role = loginedUser.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		user = utilisateurService.findByUserName(auth.getName());
		
		Location location = new Location();
		location.setAdress(locationForm.getAdress());
		location.setSuperfice(locationForm.getSuperfice());
		location.setPlaceOccupe(locationForm.getPlaceOccupe());
		location.setLoyer(locationForm.getLoyer());
		location.setVille(locationForm.getVille());
		location.setCodePostal(locationForm.getCodePostal());
		location.setTitre(locationForm.getTitre());
		location.setDescription(locationForm.getDescription());
		location.setMeuble(locationForm.getMeuble());
		location.setPhotos(fileName);
		location.setProprietaire(user);
		
		service.saveOrUpdate(location);
		
		if(fileName.length() > 0) {
		String uploadDir = "photos/" + location.getLocationID();

		ImageController.saveFile(uploadDir, fileName, photos);
		}

		return "redirect:/getgestion";

	}

	@RequestMapping(value = "reservation", method = RequestMethod.GET)
	public String reservation(@RequestParam("locationID") Integer id, Model model, Authentication auth) {
		Reservation res = new Reservation();
		Location loc = service.findById(id).get();
		System.out.println("-------------------------------------");
		AppUser user = utilisateurService.findByUserName(auth.getName());
		res.setLocation(loc);
		res.setStatut((byte) 0);
		res.setIsPermutable((byte) 1);
		res.setColocataire(user);
		System.out.println("-------------------------------------");
		List<Reservation> reservations = reservationService.findAll();
		for(Reservation r : reservations) {
			if(r.getLocation() == res.getLocation() && r.getColocataire() == res.getColocataire() && r.getStatut() == (byte) 0) {
				return "redirect:/rechercheLocation";
			}
		}
		reservationService.saveOrUpdate(res);
		return "redirect:/reservations";
	}
	
	@RequestMapping(value = "reservations", method = RequestMethod.GET)
	public String reservations(Model model, Principal principal, Authentication auth) {
		if(principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			
			AppUser user = utilisateurService.findByUserName(auth.getName());
			
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			List <Reservation> reservations = reservationService.findByColocataireUserIdLike(user.getUserId());
			model.addAttribute("reservations", reservations);
		}
		return "reservations";
	}
	
	@RequestMapping(value = "reservationsproprietaire", method = RequestMethod.GET)
	public String reservationsProprietaire(Model model, Principal principal, Authentication auth) {
		if(principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			
			AppUser user = utilisateurService.findByUserName(auth.getName());
			
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			List <Reservation> reservations = reservationService.findAll();
			model.addAttribute("reservations", reservations);
		}
		return "reservations";
	}

	@GetMapping("/modif/{locationID}")
	public String showUpdateForm(@PathVariable("locationID") Integer id, Model model, Principal principal) {
		Location loc = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		if(loc.getProprietaire() != utilisateurService.findByUserName(principal.getName())) {
			return "redirect:/index";
		} else {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String role = loginedUser.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("location", loc);
		return "modif";
		}
	}

	@PostMapping(value = "/modifbien/{locationID}")
	public String update(Model model, @Validated LocationForm locationForm, BindingResult bindingResult,
			@RequestParam("photos") MultipartFile photos ) throws IOException {

		// Méhodes pour récupérer les erreurs dans la console
		System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());

		if (bindingResult.hasErrors()) {
			return "modifError";
		}

		String fileName = StringUtils.cleanPath(photos.getOriginalFilename());

		
		
		Location location = service.findById(locationForm.getLocationID()).get();
		location.setAdress(locationForm.getAdress());
		location.setSuperfice(locationForm.getSuperfice());
		location.setPlaceOccupe(locationForm.getPlaceOccupe());
		location.setLoyer(locationForm.getLoyer());
		location.setVille(locationForm.getVille());
		location.setCodePostal(locationForm.getCodePostal());
		location.setTitre(locationForm.getTitre());
		location.setDescription(locationForm.getDescription());
		location.setMeuble(locationForm.getMeuble());
		if(fileName.length() > 0 ) {
		location.setPhotos(fileName);
		

		String uploadDir = "photos/" + location.getLocationID();

		ImageController.saveFile(uploadDir, fileName, photos);
		
		service.saveOrUpdate(location);

		listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);


		return "redirect:/index";
		}
		service.saveOrUpdate(location);

		listLoc = service.findAll();
		model.addAttribute("listLoc", listLoc);


		return "redirect:/index";
	}

	@GetMapping("/supprbien")
	public String delete(@RequestParam("locationID") Integer id, Model model) {
		service.deleteById(id);

		return "redirect:/index";
	}

	@RequestMapping(path = "/fiche", method = RequestMethod.GET)
	public String ficheColocation(Model model, @RequestParam("locationID") Integer id, Principal principal) {
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}

		Location location = service.findById(id).get();
		String description = location.getDescription().replace("\n", "<br>");
		model.addAttribute("location", location);
		model.addAttribute("description", description);
		return "fiche";
	}


}
