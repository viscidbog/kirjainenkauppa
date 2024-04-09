package hh.tehtava.kirjakauppa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.tehtava.kirjakauppa.domain.Book;
import hh.tehtava.kirjakauppa.domain.BookRepo;
import hh.tehtava.kirjakauppa.domain.BookUser;
import hh.tehtava.kirjakauppa.domain.CateRepo;
import hh.tehtava.kirjakauppa.domain.Category;
import hh.tehtava.kirjakauppa.domain.UserRepo;

@DataJpaTest
public class JPATests {

    @Autowired
    private BookRepo brepo;

    @Autowired
    private CateRepo crepo;

    @Autowired
    private UserRepo urepo;

    @Test
    public void bookCreateTest() {
        // because books cannot be created without a valid category, this is also a
        // category creator test.
        Category category = new Category("unf");
        Book book = new Book("blah", "bloh", 1L, "what", 2L, category);
        assertThat(book.getIsbn()).isNotNull();
    }

    @Test
    public void bookRepositoryTest() {
        // this tests book repository search functionality.
        Category category = new Category("unf");
        crepo.save(category);
        brepo.save(new Book("blah", "bloh", 1L, "what", 2L, category));
        assertThat(brepo.findByIsbn("what")).isNotNull();
    }

    @Test
    public void bookDeleteTest() {
        // this tests book repository delete functionality.
        Category category = new Category("unf");
        crepo.save(category);
        brepo.save(new Book("blah", "bloh", 1L, "what", 2L, category));
        brepo.deleteById(5L);
        assertThat(brepo.findByIsbn("what")).isEmpty();
    }

    @Test
    public void categoryRepositoryTest() {
        // this tests category repository search functionality.
        Category category = new Category("test");
        crepo.save(category);
        assertThat(crepo.findByName("test")).isNotNull();
    }

    @Test
    public void categoryDeleteTest() {
        // this tests category repository delete functionality.
        Category category = new Category("test");
        crepo.save(category);
        crepo.deleteById(5L);
        assertThat(crepo.findByName("test")).isEmpty();
    }

    @Test
    public void userDeleteTest() {
        // user repository deletion test
        BookUser user = new BookUser("derp", "email", "häsh", "ADMIN");
        urepo.save(user);
        urepo.deleteById(2L);
        assertThat(urepo.findById(2L)).isEmpty();
    }

    @Test
    public void userCreateTest() {
        // user creation test
        BookUser user = new BookUser("name", "email", "häsh", "ADMIN");
        assertThat(user.getEmail()).isNotNull();
    }

    @Test
    public void userSaveRepoTest() {
        // user repository search test
        BookUser user = new BookUser("name", "email", "häsh", "ADMIN");
        urepo.save(user);
        assertThat(urepo.findByUsername("name")).isNotNull();
    }

}
