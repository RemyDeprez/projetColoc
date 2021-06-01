package fr.formation.afpa.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.formation.afpa.domain.AppUser;

public class UserValidator implements Validator {
	public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        AppUser appuser = (AppUser) obj;
        if (appuser.getAttributeprenom().length() > 2) {
            e.rejectValue("attributeprenom", "tooshort");
        } else if (appuser.getAttributeprenom().length() > 30) {
            e.rejectValue("attributeprenom", "toolong");
        }
    }

	@Override
	public boolean supports(Class<?> clazz) {
		return AppUser.class.equals(clazz);
	}
}
