package fr.formation.afpa.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.afpa.domain.AppUser;
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

		if (target.getClass() == AppUser.class) {
			dataBinder.setValidator(userValidator);
		}

	}
 
    @ResponseBody
    @PostMapping("/sendEmail")
    public ModelAndView sendSimpleEmail( ModelAndView model, @ModelAttribute("appuser") @Validated AppUser appuser,BindingResult bindingResult,  @RequestParam("photos") MultipartFile photos) {
    	
    	System.out.println("Error count : " + bindingResult.getErrorCount());
		System.out.println("Field Error count : " + bindingResult.getFieldErrorCount());
		System.out.println(" GlobalError count : " + bindingResult.getAllErrors());
    	
    	if (bindingResult.hasErrors()) {
    	
    		
    		model.setViewName("inscription");
			return model;
		}
    	
    	model.setViewName("confirmregister");
    
    	String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
    	Random random = new Random();
        int code;
        appuser.setPhotos(fileName);
      System.out.println("password in the send mail : " + appuser.getEncrytedPassword());
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
    }

 
