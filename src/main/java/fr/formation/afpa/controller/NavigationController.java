package fr.formation.afpa.controller;

import java.util.ArrayList;
import java.util.List;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fr.formation.afpa.domain.Location;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.service.LocationService;
import fr.formation.afpa.service.UtilisateurService;
import fr.formation.afpa.utils.WebUtils;

@Controller
public class NavigationController {

	@Autowired
	LocationService service;

	@Autowired
	UserRepository utilisateurService;

	@Autowired
	UtilisateurService userService;

	private List<Location> listLoc = new ArrayList<Location>();
	private List<AppUser> listUser = new ArrayList<AppUser>();

	// ********************** NAVIGATION GENERALE
	// ********************************************

	// Methode de redirection à l'acceuil par défaut
	@RequestMapping(value = "/")
	public String index(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "index";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "error";
	}

	// Methode de redirection à l'acceuil depuis un lien
	@RequestMapping(value = "/index")
	public String getIndex(Model model, Principal principal) {
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "index";
	}

	// Methode qui est lancée pour l'obtention du formulaire d'inscription
	@RequestMapping(value = "/inscription")
	public String getForm(Model model, Principal principal) {

		if (principal != null) {
			return "redirect:/index";
		} else {
			model.addAttribute("appuser", new AppUser());

			return "inscription";
		}
	}

	// Methode qui est lancée pour l'obtention de la page de gestion de la
	// colocation
	@RequestMapping(value = "/getgestion")
	public String getGestion(Model model, Principal principal, Authentication auth) {
		if (principal != null) {
			System.out.println(principal.getName());
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			AppUser user = utilisateurService.findByUserName(auth.getName());

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			listLoc = service.findByProprietaireUserIdLike(user.getUserId());
			model.addAttribute("listLoc", listLoc);
		}
		return "gestionColoc";
	}

	@RequestMapping(path = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(Model model) {
		System.out.println("Déconnexion");
		model.addAttribute("title", "Logout");
		return "redirect:/index";
	}

	@RequestMapping(path = "/connexion", method = RequestMethod.GET)
	public String getConnexion(Model model, Principal principal) {
		if (principal != null) {
			return "redirect:/index";
		}

		return "connexion";
	}

	@RequestMapping(path = "/ajoutbien", method = RequestMethod.GET)
	public String getAjout(Model model, Principal principal) {
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		model.addAttribute("location", new Location());
		return "ajout";
	}

	@RequestMapping(path = "/Messagerie", method = RequestMethod.GET)
	public String getMessagerie(Model model, Principal principal) {
		if (principal != null) {
			String userName = principal.getName();

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);


			System.out.println("User Name: " + userName);

			listUser = userService.findAll();

			AppUser remove = utilisateurService.findByUserName(principal.getName());
			int toBeRemoved = listUser.indexOf(remove);

			listUser.remove(toBeRemoved);

			model.addAttribute("username", userInfo);
			model.addAttribute("listUser", listUser);

		}
		return "messagerie";
	}

	@RequestMapping(path = "/rechercheLocation", method = RequestMethod.GET)
	public String getRechercheLocation(Model model, Principal principal) {
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}

		listLoc = service.findAll();
		model.addAttribute("locations", listLoc);
		return "rechercheLocation";
	}

	@RequestMapping(path = "/recherche", method = RequestMethod.GET)
	public String recherche(Model model, Principal principal, @RequestParam("maxColocataire") Integer maxColocataire,
			@RequestParam("loyer") Integer loyer, @RequestParam("superfice") Integer superficie) {
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}

		listLoc = service.findBymaxColocataireLessThanEqualAndLoyerLessThanEqualAndSuperficeLessThanEqual(
				maxColocataire, loyer, superficie);
		System.out.println("---------------------------");
		System.out.println(listLoc);
		System.out.println("---------------------------");
		model.addAttribute("maxColocataire", maxColocataire);
		model.addAttribute("loyer", loyer);
		model.addAttribute("superfice", superficie);
		model.addAttribute("locations", listLoc);
		return "rechercheLocation";
	}

	// methode de redirection pour les tests de google map
	@RequestMapping(value = "/getMap")
	public String getMap(Model model, Principal principal) {
		model.addAttribute("listloc", service.findAll());
		if (principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "maptest";
	}

	@RequestMapping(path = "/contact", method = RequestMethod.GET)
	public String getContact(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		return "contact";
	}
	

}
