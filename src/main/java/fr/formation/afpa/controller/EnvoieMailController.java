package fr.formation.afpa.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.afpa.domain.AppUser;
 
@Controller
public class EnvoieMailController {
 
    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @PostMapping("/sendEmail")
    public ModelAndView sendSimpleEmail(ModelAndView model, AppUser appuser,BindingResult bindingResult,  @RequestParam("photos") MultipartFile photos) {
    	
    	model.setViewName("confirmregister");
    
    	String fileName = StringUtils.cleanPath(photos.getOriginalFilename());
    	Random random = new Random();
        int code;
        appuser.setPhotos(fileName);
        
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        code = random.nextInt(999);
        message.setTo(appuser.getMail());
        message.setSubject("Test Simple Email");
        message.setText("Hello " + appuser.getAttributeprenom()+" ! Here is your confirmation code : "+ code);
        appuser.setCode(code);
        model.addObject("appuser", appuser);
        model.addObject("photos", photos);
 
        // Send Message!
        this.emailSender.send(message);
 
        return model;
    }
 
}