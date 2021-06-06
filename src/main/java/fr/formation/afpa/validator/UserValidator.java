package fr.formation.afpa.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.domain.AppUserForm;
import fr.formation.afpa.repository.UserRepository;
import fr.formation.afpa.service.UserDetailsServiceImpl;
import fr.formation.afpa.service.UtilisateurService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository service;
	
	//Validateur pour la validation des formats d'email
    private EmailValidator emailValidator = EmailValidator.getInstance();



	public void validate(Object obj, Errors e) {

		AppUserForm appuser = (AppUserForm) obj;

		if (appuser.getAttributeprenom().length() < 2) {
			e.rejectValue("attributeprenom", "attributeprenom.tooshort");
		} else if (appuser.getAttributeprenom().length() > 30) {
			e.rejectValue("attributeprenom", "attributeprenom.toolong");
		}

		if (appuser.getNom().length() < 2) {
			e.rejectValue("nom", "attributeprenom.tooshort");
		} else if (appuser.getNom().length() > 30) {
			e.rejectValue("nom", "attributeprenom.toolong");
		}
		
	     ValidationUtils.rejectIfEmptyOrWhitespace(e, "userName", "login");
	    
	     
		if(service.findByUserName(appuser.getUserName()) != null) {
			AppUser isDoublon = service.findByUserName(appuser.getUserName());
			if(isDoublon.getUserId() != appuser.getUserId()) {
			
			e.rejectValue("userName", "login.doublon");
		}
		}
	     

        if (!this.emailValidator.isValid(appuser.getMail())) {
            e.rejectValue("mail", "mail.format");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "encrytedPassword", "password");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "confirmPassword", "password.confirm");
        
        if(!appuser.getEncrytedPassword().contentEquals(appuser.getConfirmPassword())) {
        	  e.rejectValue("confirmPassword", "password.match");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "date", "date");
       
        
        if(appuser.getTelephone().length() != 10) {

        	e.rejectValue("telephone", "telephone");
        }

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AppUserForm.class.equals(clazz);
	}

//	//Méthode pour déterminer si le login rentré dans par l'utilisateur est déjà présent dans la base de données.
//	private boolean hasDoublon(String login) {
//		for (AppUser appUser : listUser) {
//			if (login.contentEquals(appUser.getUserName())) {
//				return true;
//			}
//		}
//		return false;
//	}
//	

}
