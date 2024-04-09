package hh.tehtava.kirjakauppa.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.tehtava.kirjakauppa.domain.Book;
import hh.tehtava.kirjakauppa.domain.BookRepo;
import hh.tehtava.kirjakauppa.domain.CateRepo;

@Controller
public class BookController {

    @Autowired
    private BookRepo brepository;
    @Autowired
    private CateRepo crepository;

    // Login page
    @RequestMapping(value = "/login")
    public String logIn() {
        return "login";
    }

    // Show list of books
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String showIndex(Model model) {
        model.addAttribute("books", brepository.findAll());
        return "index";
    }

    // Create a new book, creating a link to categories as well
    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }

    // Save a book to list
    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    public String save(Book book) {
        brepository.save(book);
        return "redirect:index";
    }

    // Delete a book by its ID value. End point has to be unique if other
    // controllers have similar functionality.
    @RequestMapping(value = "/delete/{bookid}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("bookid") Long bookDel, Model model) throws Exception {
        brepository.deleteById(bookDel);
        return "redirect:/index";
    }

    // Edit a book
    @RequestMapping(value = "/editbook/{bookid}")
    public String editing(@PathVariable("bookid") Long bookId, Model model) {
        model.addAttribute("book", brepository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }

    // RESTful service to get all books
    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) brepository.findAll();
    }

    // RESTful service to get book by id
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return brepository.findById(bookId);
    }

}
