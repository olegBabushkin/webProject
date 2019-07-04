package mvc.bookmanager.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Oleg
 * 15.06.2019
 */
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="seq",allocationSize=1 , sequenceName = "genre_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
