package mvc.bookmanager.form.validator;

import mvc.bookmanager.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "booktitle.empty",
                "Title must not be empty.");
        String bookTitle = book.getTitle();
        if ((bookTitle.length()) > 16 || (bookTitle.length()) < 1) {
            errors.rejectValue("title", "booktitle.uncorrect",
                    "Title must not more than 16 characters and not less 1.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "year.empty",
                "Year must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberOfPages", "numberOfPages.empty",
                "NumberOfPages must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authors", "author.empty",
                "Author must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "genre.empty",
                "Genre must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publisher", "publisher.empty",
                "Publisher must not be empty.");
     /*   if (!(signupForm.getPassword()).equals(signupForm
                .getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }*/

      /*  if (!EmailValidator.getInstance().isValid(signupForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }*/
    }
}
