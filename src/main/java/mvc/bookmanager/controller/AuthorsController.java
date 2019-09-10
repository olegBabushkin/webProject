package mvc.bookmanager.controller;

import mvc.bookmanager.dto.DTOSearch;
import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.form.validator.AuthorValidator;
import mvc.bookmanager.form.validator.BookValidator;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.Genre;
import mvc.bookmanager.model.Publisher;
import mvc.bookmanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
/*@RequestMapping(value = "/authors")*/
public class AuthorsController {
    @Autowired(required = true)
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private PublisherService publisherService;
    private AuthorValidator authorValidator;
    private LibraryService libraryService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorsController.class);

    @Autowired(required = true)
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired(required = true)
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Autowired
    public void setAuthorValidator(AuthorValidator authorValidator) {
        this.authorValidator = authorValidator;
    }


    @ModelAttribute("listBooks")
    public List<Book> booksList() {
        return bookService.listBooks();
    }


    @RequestMapping(value = "/authors",method = RequestMethod.GET)
    public String listBooks(Model model) {

        DTOSearch dto = libraryService.getDtoSearch();
        model.addAttribute("searchList", dto);
        model.addAttribute("listAuthors", this.authorService.listAuthors());

        return "authors";
    }

    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute("author") Author author, BindingResult result, Model model) {
        authorValidator.validate(author, result);
        if (result.hasErrors()) {
            return "addAuthor";
        }
        try {
            logger.info("addAuthor: " + author);
            this.authorService.addAuthor(author);
            model.addAttribute("message", "Successfully added");
        } catch (AppException e) {
            model.addAttribute("messageEx", e.getMessage());
        }
        return "addAuthor";
    }

    @RequestMapping(value = "/authors/new", method = RequestMethod.GET)
    public String redirectAddAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", this.authorService.getAuthorById(id));

        return "authordata";
    }

    @RequestMapping(value = "/authors/{id}/delete")
    public String removeBook(@PathVariable int id) {
        this.authorService.removeAuthor(id);
        return "redirect:/authors";
    }

    @RequestMapping(value = "/authors/{id}/edit")
    public String editAuthor(@PathVariable("id") int id, Model model) {

        model.addAttribute("author", this.authorService.getAuthorById(id));

        return "editAuthor";
    }

    @RequestMapping(value = "/authors/edit")
    public String editBook(@ModelAttribute("author") Author author, BindingResult result, Model model, RedirectAttributes
            redirect) {
        authorValidator.validate(author, result);
        if (result.hasErrors()) {
            return "editAuthor";
        }
        logger.info("editAuthor: " + author);
        try {
            this.authorService.editAuthor(author);
            model.addAttribute("message", "Successfully edited");
        } catch (AppException e) {
            model.addAttribute("messageEx", e.getMessage());
        }
        return "editAuthor";
    }

    @RequestMapping(value = "/authors/find", method = RequestMethod.POST)//+
    public String find(@RequestParam("text") String text, Model model, RedirectAttributes redirect) {
        try {
            List<Author> authorList = this.authorService.findAuthor(text);
            redirect.addFlashAttribute("authors", authorList);
        } catch (AppException e) {
            redirect.addFlashAttribute("messageEx", e.getMessage());
        }
        return "redirect:/authors";
    }

}
