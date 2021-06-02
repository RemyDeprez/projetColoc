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
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titre", "titre");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress", "adress");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codePostal", "codePostal");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "ville");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "superfice", "superfice");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placeOccupe", "placeOccupe");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyer", "loyer");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "meuble", "meuble");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photos", "photos");
//        

		//Pour chaque champ du formulaire, une méthode if pour checker chaque condition et renvoyer le message d'erreur approprié.
		
		if (location.getTitre().length() == 0 || location.getTitre().length() > 0 && location.getTitre().length() < 3
				|| location.getTitre().length() > 50) {
			if (location.getTitre().length() == 0) {
				errors.rejectValue("titre", "titre");

			} else {
				errors.rejectValue("titre", "titre.lenght");
			}
		}

		if (location.getAdress().length() == 0 || location.getAdress().length() > 0 && location.getAdress().length() < 3
				|| location.getAdress().length() > 100) {
			if (location.getAdress().length() == 0) {
				errors.rejectValue("adress", "adress");
			} else {
				errors.rejectValue("adress", "adress.lenght");

			}
		}

		if (location.getVille().length() == 0 || location.getVille().length() > 85) {
			if (location.getVille().length() == 0) {
				errors.rejectValue("ville", "ville");
			} else {

				errors.rejectValue("ville", "ville.lenght");

			}

			if (location.getCodePostal() == null || location.getCodePostal() < 10000
					|| location.getCodePostal() > 99999) {
				if (location.getCodePostal() == null) {
					errors.rejectValue("codePostal", "codePostal");
				} else {
					errors.rejectValue("codePostal", "codePostal.format");
				}
			}

			if (location.getLoyer() == null || location.getLoyer() < 0) {
				if (location.getLoyer() == null) {
					errors.rejectValue("loyer", "loyer");
				} else {
					errors.rejectValue("loyer", "loyer.negative");

				}
			}

			if (location.getSuperfice() == null || location.getSuperfice() < 0) {
				if (location.getSuperfice() == null) {
					errors.rejectValue("superfice", "superfice");
				} else {
					errors.rejectValue("superfice", "superfice.negative");
				}
			}

			if (location.getPlaceOccupe() == null || location.getPlaceOccupe() < 0) {
				if (location.getPlaceOccupe() == null) {
					errors.rejectValue("placeOccupe", "placeOccupe");
				} else {
					errors.rejectValue("placeOccupe", "occupant.negative");
				}
			}

			if (location.getDescription().length() > 5) {

				errors.rejectValue("description", "description.length");
			}

		}
	}
}