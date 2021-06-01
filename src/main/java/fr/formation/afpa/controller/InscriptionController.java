package fr.formation.afpa.controller;

<<<<<<< Updated upstream
import java.io.IOException;
=======
import javax.validation.Valid;
>>>>>>> Stashed changes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< Updated upstream
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
=======
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.IUtilisateurService;
import fr.formation.afpa.validator.UserValidator;

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

<<<<<<< Updated upstream
	@PostMapping(value = "/createaccount")
	public String index(Model model, AppUser appuser,BindingResult bindingResult,  @RequestParam("photos") MultipartFile photos) throws IOException {
		String fileName = StringUtils.cleanPath(photos.getOriginalFilename()); 
=======
	@RequestMapping(value = "/createaccount")
	public String index(Model model,@Valid AppUser appuser, Errors e) {
		
>>>>>>> Stashed changes
		String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		appuser.setEncrytedPassword(encrytedPassword);
		appuser.setPhotos(fileName);
		model.addAttribute("appuser", appuser);
		service.saveOrUpdate(appuser);
		String uploadDir = "photos/profile/" + appuser.getUserId();

		ImageController.saveFile(uploadDir, fileName, photos);
		
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
	@PostMapping(value = "/updateaccount")
	public String updateAccount(Model model, AppUser appuser,BindingResult bindingResult,  @RequestParam("photos") MultipartFile photos) throws IOException {
<<<<<<< Updated upstream
=======
		String fileName = StringUtils.cleanPath(photos.getOriginalFilename()); 
>>>>>>> Stashed changes
		String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
		appuser.setEncrytedPassword(encrytedPassword);
		
<<<<<<< Updated upstream
		service.saveOrUpdate(appuser);
<<<<<<< Updated upstream
=======
		
>>>>>>> Stashed changes
		String fileName = StringUtils.cleanPath(photos.getOriginalFilename()); 
=======
		
>>>>>>> Stashed changes
		appuser.setPhotos(fileName);
		model.addAttribute("appuser", appuser);
		service.saveOrUpdate(appuser);
		String uploadDir = "photos/profile/" + appuser.getUserId();

		ImageController.saveFile(uploadDir, fileName, photos);
		
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
