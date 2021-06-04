package fr.formation.afpa.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.IUtilisateurService;
import fr.formation.afpa.utils.WebUtils;

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

	@PostMapping(value = "/createaccount")
	public String index(Model model, AppUser appuser, BindingResult bindingResult,
			@RequestParam("photos") String photos, @RequestParam("usercode") Integer usercode)
			throws IOException {
		System.out.println ("password avant setStatus : " + appuser.getEncrytedPassword());
		appuser.setStatus("Colocataire");
		if (appuser.getCode().equals(usercode)) {
			System.out.println ("password : " + appuser.getEncrytedPassword());
			String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		System.out.println(encrytedPassword);
			appuser.setEncrytedPassword(encrytedPassword);
			appuser.setPhotos(photos);
			appuser.setEnabled(1);
			appuser.setCode(1111);
			model.addAttribute("appuser", appuser);
			service.saveOrUpdate(appuser);
			String uploadDir = "photos/profile/" + appuser.getUserId();

			
			
			

			return "connexion";
		} else {
			model.addAttribute("appuser", appuser);
			return "errorConfirm";
		}
		
	}

//methode lancée lorsque l'on demande le formulaire d'update de profil
	@RequestMapping(value = "/getprofile")
	public String getProfile(Model model, Authentication auth, AppUser appuser, Principal principal) {
		if(principal != null) {
			String userName = principal.getName();

			System.out.println("User Name: " + userName);

			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		appuser = userRepo.findByUserName(auth.getName());
		System.out.println(appuser);
		model.addAttribute("appuser", userRepo.findByUserName(auth.getName()));

		return "modifprofile";
	}

//methode lancée lorsque l'on appuie sur le boutton "valider" de l'update
	@PostMapping(value = "/updateaccount")
	public String updateAccount(Model model, AppUser appuser, BindingResult bindingResult,
			@RequestParam("photos") MultipartFile photos) throws IOException {
		String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		appuser.setEncrytedPassword(encrytedPassword);

		String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
		appuser.setEnabled(1);
		appuser.setPhotos(fileName);
		model.addAttribute("appuser", appuser);
		service.saveOrUpdate(appuser);
		String uploadDir = "photos/profile/" + appuser.getUserId();

		ImageController.saveFile(uploadDir, fileName, photos);

		model.addAttribute("modifications", "Les modifications ont été enregistrées.");
		
		return "modifprofile";
	}
//methode lancée lorsque l'on appuie sur le button delete de l'update

	@RequestMapping(value = "/deleteaccount")
	public String deleteAccount(Model model, AppUser appuser) {
		System.out.println("ici");
		service.deleteByUserId(appuser.getUserId());
		return "index";
	}

}
