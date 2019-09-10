package mvc.bookmanager.convertor;

import mvc.bookmanager.controller.BookController;
import mvc.bookmanager.model.Author;
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
public class IdAuthorsToAuthorsConvertor implements Converter<String, Author> {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private AuthorService authorService;

    @Autowired(required = true)
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Author convert(String element) {
        logger.info("convert: " + element);
        Integer id = Integer.parseInt(element);
        Author author = authorService.getAuthorById(id);
        logger.info("convert successeful: " + element);
        return author;
    }
}
