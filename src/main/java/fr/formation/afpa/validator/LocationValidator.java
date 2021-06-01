package fr.formation.afpa.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.formation.afpa.domain.Location;
import fr.formation.afpa.service.LocationService;

@Component
public class LocationValidator implements Validator {
 
 
    @Autowired
    private LocationService service;
 
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Location.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        Location location = (Location) target;
 
        // Check the fields of AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titre", "titre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress", "adress");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codePostal", "codePostal");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "ville");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "superfice", "superfice");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placeOccupe", "placeOccupe");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyer", "loyer");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "meuble", "meuble");
        
        if(location.getTitre()==null) {
        	errors.rejectValue("titre", "titre");
        }
 
       
    }
 
}