package hh.tehtava.kirjakauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByIsbn(String isbn);

    List<Book> findByTitle(@Param("title") String title);
}
