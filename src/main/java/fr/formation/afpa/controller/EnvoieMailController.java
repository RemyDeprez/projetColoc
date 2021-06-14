package fr.formation.afpa.controller;

import java.security.Principal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Array;

import java.util.Random;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.AppUserForm;
import fr.formation.afpa.utils.WebUtils;
import fr.formation.afpa.validator.UserValidator;
 
@Controller
public class EnvoieMailController {
	
	@Autowired
	private UserValidator userValidator;
 
    @Autowired
    public JavaMailSender emailSender;
    
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {

		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == AppUserForm.class) {
			dataBinder.setValidator(userValidator);
		}

	}
 
    @ResponseBody
    @PostMapping("/sendEmail")
    public ModelAndView sendSimpleEmail( ModelAndView model, @ModelAttribute("appuser") @Validated AppUserForm appuserForm,BindingResult bindingResult,  @RequestParam("photos") MultipartFile photos) {
    	
    	System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());
		String uploadDir = "photos/profile/" + appuserForm.getUserId();
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
		    
		    
		
		
		
		
		
		
		
    	
    	if (bindingResult.hasErrors()) {
    		model.setViewName("inscription");
			return model;
		}
    	AppUser appuser = new AppUser();
    	model.setViewName("confirmregister");
    
    	String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
    	Random random = new Random();
        int code;
        
        appuser.setAttributeprenom(appuserForm.getAttributeprenom());
        appuser.setNom(appuserForm.getNom());
        appuser.setUserName(appuserForm.getUserName());
        appuser.setMail(appuserForm.getMail());
        appuser.setEncrytedPassword(appuserForm.getEncrytedPassword());
        appuser.setDate(appuserForm.getDate());
        appuser.setTelephone(appuserForm.getTelephone());
        appuser.setPhotos(fileName);
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        code = random.nextInt(999);
        message.setTo(appuser.getMail());
        message.setSubject("Test Simple Email");
        message.setText("Hello " + appuser.getAttributeprenom()+" ! Here is your confirmation code : "+ code);
        appuser.setCode(code);
        System.out.println("password in the send mail after code: " + appuser.getEncrytedPassword());
        model.addObject("appuser", appuser);
        model.addObject("photos", photos);
 
        // Send Message!
        this.emailSender.send(message);
 
        return model;
    }
    
	@RequestMapping(path = "/contact", method = RequestMethod.POST)
	public String contact(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("email") String email, @RequestParam("description") String description, Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String role = loginedUser.getAuthorities().iterator().next().getAuthority();
			model.addAttribute("userInfoAuthorities", loginedUser.getAuthorities().iterator().next().getAuthority());
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
		}
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(email);
		message.setSubject("Test");
		message.setTo("colocation.afpa@gmail.com");
		message.setText("Message de " + nom + " " + prenom + " " + email + "\n" + description);
		
		this.emailSender.send(message);
		
		String sendMessage = "envoyé";
		model.addAttribute("sendMessage", sendMessage);
		return "contact";
	}
    }

 
