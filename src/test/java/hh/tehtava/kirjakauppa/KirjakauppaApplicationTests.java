package hh.tehtava.kirjakauppa;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.tehtava.kirjakauppa.web.BookController;
import hh.tehtava.kirjakauppa.web.CategoryController;

@SpringBootTest
public class KirjakauppaApplicationTests {

	@Autowired
	private BookController bcontroller;

	@Autowired
	private CategoryController ccontroller;

	@Test
	public void controllerTest() throws Exception {
		assertThat(bcontroller).isNotNull();
		assertThat(ccontroller).isNotNull();
	}

}
