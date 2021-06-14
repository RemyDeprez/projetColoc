package fr.formation.afpa.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.AppUserForm;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.IUtilisateurService;
import fr.formation.afpa.utils.WebUtils;
import fr.formation.afpa.validator.UserUpdateValidator;
import fr.formation.afpa.validator.UserValidator;

@Controller
public class InscriptionController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserUpdateValidator updateValidator;

	@Autowired
	UserRepository userRepo;
	@Autowired
	IUtilisateurService service;
	
	// Méthode pour configurer le validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {

		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == AppUserForm.class) {
			dataBinder.setValidator(updateValidator);
		}

	}

	// Encryte Password with BCryptPasswordEncoder
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

//	Methode lancée lorsque le formulaire est envoyé

	@PostMapping(value = "/createaccount")
	public String index( Model model, AppUser appuser, BindingResult bindingResult,
			@RequestParam("photos") String photos, @RequestParam("usercode") Integer usercode)
			throws IOException {
		
		
		

		appuser.setStatus("Colocataire");
		if (appuser.getCode().equals(usercode)) {
			System.out.println ("password : " + appuser.getEncrytedPassword());
			String encrytedPassword = encrytePassword(appuser.getEncrytedPassword());
	
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
	public String updateAccount(Model model, @ModelAttribute("appuser") @Validated AppUserForm appuserForm, BindingResult bindingResult,
			@RequestParam("photos") MultipartFile photos) throws IOException {
		System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());
		
		if(bindingResult.hasErrors()) {
		
			return "modifprofile";
		}
		
		String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
		String encryptedPassword = encrytePassword(appuserForm.getEncrytedPassword());
		AppUser appuser = service.findByUserId(appuserForm.getUserId());
		  appuser.setAttributeprenom(appuserForm.getAttributeprenom());
	        appuser.setNom(appuserForm.getNom());
	        appuser.setUserName(appuserForm.getUserName());
	        appuser.setMail(appuserForm.getMail());
	        
	        if(appuserForm.getEncrytedPassword().length() > 0) {
	        appuser.setEncrytedPassword(encryptedPassword);
	        }
	        appuser.setDate(appuserForm.getDate());
	        appuser.setTelephone(appuserForm.getTelephone());
	        appuser.setStatus(appuserForm.getStatus());
	        if(fileName.length() > 0) {
	        appuser.setPhotos(fileName);
	    	String uploadDir = "photos/profile/" + appuser.getUserId();
	    	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "dpo9zpe78",
					"api_key", "558394821365119",
					"api_secret", "AatWPh2rvaj3JByS6nwyR5OmdLA"));
			
			File file = new File(uploadDir);
			 
	        try {
	        	byte[] photoByte = photos.getBytes();
	            OutputStream os = new FileOutputStream(file);
	            os.write(photoByte);
	            System.out.println("Write bytes to file.");
	            os.close();
	            cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        }
	
		model.addAttribute("appuser", appuser);
		service.saveOrUpdate(appuser);
	

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
