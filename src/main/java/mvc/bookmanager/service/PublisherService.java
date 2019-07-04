package mvc.bookmanager.service;

import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Genre;
import mvc.bookmanager.model.Publisher;

import java.util.List;

public interface PublisherService {
    public void addPublisher(Publisher publisher) throws AppException;

    public void editPublisher(Publisher publisher) throws AppException;

    public void updatePublisher(Publisher publisher);

    public void removePublisher(int id);

    public Publisher gePublisherById(int id);

    public List<Publisher> listPublishers();
/*
    public List<Book> findBook(DTOSearch search);

    public void findBookByAuthor(String name);*/
}
