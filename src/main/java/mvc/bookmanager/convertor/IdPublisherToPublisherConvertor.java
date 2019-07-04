package mvc.bookmanager.convertor;

import mvc.bookmanager.controller.BookController;
import mvc.bookmanager.model.Genre;
import mvc.bookmanager.model.Publisher;
import mvc.bookmanager.service.GenreService;
import mvc.bookmanager.service.PublisherService;
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
public class IdPublisherToPublisherConvertor implements Converter<Object, Publisher> {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private PublisherService publisherService;

    @Autowired(required = true)
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public Publisher convert(Object element) {
        logger.info("convert: " + element);
        Integer id = Integer.parseInt((String) element);
        Publisher publisher = publisherService.gePublisherById(id);
        logger.info("convert successeful: " + element);
        return publisher;
    }
}
