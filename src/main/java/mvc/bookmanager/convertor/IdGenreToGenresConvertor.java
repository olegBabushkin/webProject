package mvc.bookmanager.convertor;

import mvc.bookmanager.controller.BookController;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Genre;
import mvc.bookmanager.service.AuthorService;
import mvc.bookmanager.service.GenreService;
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
public class IdGenreToGenresConvertor implements Converter<Object, Genre> {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private GenreService genreService;

    @Autowired(required = true)
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    public Genre convert(Object element) {
        logger.info("convert: " + element);
        Integer id = Integer.parseInt((String) element);
        Genre genre = genreService.getGenreById(id);
        logger.info("convert successeful: " + element);
        return genre;
    }
}
