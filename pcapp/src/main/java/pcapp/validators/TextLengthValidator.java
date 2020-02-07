package pcapp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextLengthValidator implements ConstraintValidator<TextLength, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.length()>20;
	}

}
