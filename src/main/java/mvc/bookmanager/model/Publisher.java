package mvc.bookmanager.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Oleg
 * 15.06.2019
 */
@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="seq",allocationSize=1 , sequenceName = "publisher_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "City")
    private String city;
    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
