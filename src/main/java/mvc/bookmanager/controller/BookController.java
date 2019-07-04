package mvc.bookmanager.controller;

import mvc.bookmanager.dto.DTOSearch;
import mvc.bookmanager.exeption.AppException;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired(required = true)
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private PublisherService publisherService;
    private BookValidator bookValidator;
    private LibraryService libraryService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

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
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @Autowired
    public void setBookValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }


   @ModelAttribute("listAuthors")
   public List<Author> authorsList() {
       return authorService.listAuthors();
   }

    @ModelAttribute("listGenre")
    public List<Genre> genresList() {
        return genreService.listGenres();
    }

    @ModelAttribute("listPublisher")
    public List<Publisher> publishersList() {
        return publisherService.listPublishers();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listBooks(Model model) {

        DTOSearch dto = libraryService.getDtoSearch();

        model.addAttribute("searchList", dto);
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model,RedirectAttributes
            redirect) {
        bookValidator.validate(book, result);
        if (result.hasErrors()) {
            return "addBook";
        }
        try {
            logger.info("addBook: " + book);
            this.bookService.addBook(book);
            redirect.addFlashAttribute("message", "Successfully added");
            return "redirect:/books/new";
        } catch (AppException e) {
            model.addAttribute("messageEx", e.getMessage());
        }
        return "addBook";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String redirectAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));

        return "bookdata";
    }

    @RequestMapping(value = "/{id}/delete")
    public String removeBook(@PathVariable int id) {
        this.bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", this.bookService.getBookById(id));

        return "editBook";
    }

    @RequestMapping(value = "/edit")
    public String editBook(@ModelAttribute("book") Book book, BindingResult result, Model model, RedirectAttributes
            redirect) {
        bookValidator.validate(book, result);
        if (result.hasErrors()) {
            return "editBook";
        }
        logger.info("editBook: " + book);
        this.bookService.editBook(book);
        model.addAttribute("message", "Successfully edited");
        return "editBook";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)//+
    public String find(@ModelAttribute("searchList") DTOSearch searchList, Model model, RedirectAttributes redirect) {
        try {
            List<Book> bookList = this.bookService.findBook(searchList);
            redirect.addFlashAttribute("books", bookList);
        } catch (AppException e) {
            redirect.addFlashAttribute("messageEx", e.getMessage());
        }
        return "redirect:/books";
    }

}
