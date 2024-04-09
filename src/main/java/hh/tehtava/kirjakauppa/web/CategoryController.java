package hh.tehtava.kirjakauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.tehtava.kirjakauppa.domain.Category;
import hh.tehtava.kirjakauppa.domain.CateRepo;

@Controller
public class CategoryController {

    @Autowired
    private CateRepo repository;

    @RequestMapping(value = "/categorylist", method = RequestMethod.GET)
    public String showIndex(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categorylist";
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(Category category) {
        repository.save(category);
        return "redirect:categorylist";
    }

    // Delete a category by its ID value. endpoint has to be named differently from
    // book deleter, because if they're both called the same, it returns an
    // ambiguous handler error.
    @RequestMapping(value = "/deletecategory/{categoryid}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCategory(@PathVariable("categoryid") Long cateDel, Model model) throws Exception {
        repository.deleteById(cateDel);
        return "redirect:/categorylist";
    }

}