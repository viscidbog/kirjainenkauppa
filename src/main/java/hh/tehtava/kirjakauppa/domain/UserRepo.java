package hh.tehtava.kirjakauppa.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<BookUser, Long> {
    BookUser findByUsername(String username);
}
