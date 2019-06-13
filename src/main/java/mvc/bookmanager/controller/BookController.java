package mvc.bookmanager.controller;

import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.form.validator.BookValidator;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.service.BookService;
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
public class BookController {
    private BookService bookService;
    private BookValidator bookValidator;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired(required = true)
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }

  /*  @ModelAttribute("book")
    public Book setnewbook(Model model) {
        return new Book();
    }*/

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping(value = "/books/add1", method = RequestMethod.GET)
    public String redirectAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model, RedirectAttributes redirect) {
        bookValidator.validate(book, result);
        if (result.hasErrors()) {
            return "addBook";
        }
        try {
            logger.info("addBook: " + book);
            this.bookService.addBook(book);
            model.addAttribute("message", "Successfully added");
        } catch (AppException e) {
            model.addAttribute("messageEx", e.getMessage());
        }
        return "addBook";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)//+
    public String findByTitle(@RequestParam("selectedText") String selectedText, @RequestParam("choice") String choice,
                              Model model, RedirectAttributes redirect) {
        List<Book> bookList = this.bookService.findBook(choice, selectedText);

        redirect.addFlashAttribute("books", bookList);

        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "editBook";
    }

    @RequestMapping(value = "/books/edit")
    public String editBook(@ModelAttribute("book") Book book, BindingResult result, Model model, RedirectAttributes redirect) {
        bookValidator.validate(book, result);
        if (result.hasErrors()) {
            return "editBook";
        }
        logger.info("editBook: " + book);
        this.bookService.editBook(book);
        model.addAttribute("message", "Successfully edited");
        return "editBook";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));

        return "bookdata";
    }
}
