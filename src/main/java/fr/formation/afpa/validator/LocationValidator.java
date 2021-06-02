package fr.formation.afpa.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.domain.LocationForm;
import fr.formation.afpa.service.LocationService;

@Component
public class LocationValidator implements Validator {
 
 
    @Autowired
    private LocationService service;
 

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == LocationForm.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        LocationForm location = (LocationForm) target;
 
        // Méthode pour rejetter le formulaire si un des champs ci-dessous est nul
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titre", "titre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress", "adress");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codePostal", "codePostal");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "ville");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "superfice", "superfice");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placeOccupe", "placeOccupe");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyer", "loyer");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "meuble", "meuble");
       
   
        
        if(location.getTitre().length() <5) {
        	errors.rejectValue("titre", "titre.lenght");
        }
        
 
 
       
    }
 
}