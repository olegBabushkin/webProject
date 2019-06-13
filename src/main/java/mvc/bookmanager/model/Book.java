package mvc.bookmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@NamedQueries(value = {@NamedQuery(name = "BOOK_EQUALS", query = "SELECT b FROM Book b WHERE b.bookTitle=:bookTitle" +
        " AND b.bookAuthor=:bookAuthor")})
public class Book {
    public static final String BOOK_EQUALS = "Book.get";
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="seq",allocationSize=7 , sequenceName = "books_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "BOOK_AUTHOR")
    private String bookAuthor;

    @Column(name = "BOOK_PRICE")
    private int price;

    public boolean isNew(){
        return this.id == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", price=" + price +
                '}';
    }
}
