package petit.jochem.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import petit.jochem.demo.model.Snack;
import petit.jochem.demo.model.SnackDAO;

import javax.validation.Valid;
import java.io.PipedOutputStream;

@Controller
public class IndexController {
    @Autowired
    SnackDAO dao;
    @ModelAttribute(value = "allSnacks")
    public Iterable<Snack> getAllSnacks() {
        return dao.findAll();
    }
    @ModelAttribute(value = "newSnack")
    public Snack snackToSave() {
        return new Snack();
    }

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map) {
        return "index";
    }
    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.POST)
    public String saveSnack(@ModelAttribute("newSnack") @Valid Snack newSnack, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "index";
        dao.save(newSnack);
        return "redirect:/index";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSnack(@PathVariable(value = "id")int id){
        dao.deleteById(id);
        return "redirect:/index";
    }
}
