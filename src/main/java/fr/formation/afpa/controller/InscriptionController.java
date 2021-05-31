package fr.formation.afpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.IUtilisateurService;

@Controller
public class InscriptionController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	IUtilisateurService service;

	// Encryte Password with BCryptPasswordEncoder
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

//	Methode lancée lorsque le formulaire est envoyé

	@RequestMapping(value = "/createaccount")
	public String index(Model model, AppUser appuser) {
		String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		appuser.setEncrytedPassword(encrytedPassword);
		model.addAttribute("appuser", appuser);
		service.saveOrUpdate(appuser);
		return "connexion";
	}

//methode lancée lorsque l'on demande le formulaire d'update de profil
	@RequestMapping(value = "/getprofile")
	public String getProfile(Model model, Authentication auth, AppUser appuser) {
		appuser = userRepo.findByUserName(auth.getName());
		System.out.println(appuser);
		model.addAttribute("appuser", userRepo.findByUserName(auth.getName()));

		return "modifprofile";
	}

//methode lancée lorsque l'on appuie sur le boutton "valider" de l'update
	@RequestMapping(value = "/updateaccount")
	public String updateAccount(Model model, AppUser appuser) {
		String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		appuser.setEncrytedPassword(encrytedPassword);
		service.saveOrUpdate(appuser);
		return "index";
	}
//methode lancée lorsque l'on appuie sur le button delete de l'update

	@RequestMapping(value = "/deleteaccount")
	public String deleteAccount(Model model, AppUser appuser) {
		System.out.println("ici");
		service.deleteByUserId(appuser.getUserId());
		return "index";
	}
}
