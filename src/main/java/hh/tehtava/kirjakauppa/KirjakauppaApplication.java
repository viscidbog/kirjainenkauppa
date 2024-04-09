package hh.tehtava.kirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.tehtava.kirjakauppa.domain.Book;
import hh.tehtava.kirjakauppa.domain.BookRepo;
import hh.tehtava.kirjakauppa.domain.BookUser;
import hh.tehtava.kirjakauppa.domain.CateRepo;
import hh.tehtava.kirjakauppa.domain.Category;
import hh.tehtava.kirjakauppa.domain.UserRepo;

@SpringBootApplication
public class KirjakauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemoAdder(BookRepo brepository, CateRepo crepository, UserRepo urepository) {
		return (args) -> {

			Category category1 = new Category("Fantasy");
			Category category2 = new Category("Sci-fi");
			Category category3 = new Category("Non-fiction");

			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);

			log.info("put some books in the store");
			brepository
					.save(new Book("A Storm of Swords", "George R. R. Martin", 2000L, "0-00-224586-8", 50L, category1));
			brepository.save(new Book("Neuromancer", "William Gibson", 1984L, "0-441-56956-0", 15L, category2));
			brepository.save(new Book("Advanced Demonology", "Louis Cypher", 1666L, "666-666-666", 6L, category3));

			log.info("create ADMIN and USER users");

			BookUser user1 = new BookUser("admin", "admin@lol.com",
					"$2a$10$47CDOOBtMSGTH3Yuwkng4.7c9cgNxcsf9WJx7PiAEdUsRB5zBW3Jq",
					"ADMIN");

			BookUser user2 = new BookUser("user", "user@lol.com",
					"$2a$10$c76N.PIMrGwYYo.3VZmHyO4dEdvpKdvHLF0CJIkgQ/Xmde5ODTi8e",
					"USER");

			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books:");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

			log.info("fetch all categories:");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}

		};
	}

}
