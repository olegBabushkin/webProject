package mvc.bookmanager.form.validator;

import mvc.bookmanager.model.Author;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AuthorValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Author.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Author author = (Author) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "author.empty",
                "FirstName must not be empty.");
        String authorFirstName = author.getFirstName();
        if ((authorFirstName.length()) > 16 || (authorFirstName.length()) < 1) {
            errors.rejectValue("firstName", "author.uncorrect",
                    "AuthorName must not more than 16 characters and not less 1.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "author.empty",
                "LastName must not be empty.");
        String authorLastName = author.getLastName();
        if ((authorLastName.length()) > 16 || (authorLastName.length()) < 1) {
            errors.rejectValue("lastName", "author.uncorrect",
                    "AuthorName must not more than 16 characters and not less 1.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthplace", "year.empty",
                "Birthplace must not be empty.");


     /*   if (!(signupForm.getPassword()).equals(signupForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }*/

    /*    if (!EmailValidator.getInstance().isValid(signupForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }*/
    }
}
