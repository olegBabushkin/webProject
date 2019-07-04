package mvc.bookmanager.convertor;

import mvc.bookmanager.controller.BookController;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.service.AuthorService;
import mvc.bookmanager.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Oleg
 * 20.06.2019
 */
@Component
public class IdBooksToBooksConvertor implements Converter<Object, Book> {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;

    @Autowired(required = true)
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public Book convert(Object element) {
        logger.info("convert: " + element);
        Integer id = Integer.parseInt((String) element);
        Book book = bookService.getBookById(id);
        logger.info("convert successeful: " + element);
        return book;
    }
}
