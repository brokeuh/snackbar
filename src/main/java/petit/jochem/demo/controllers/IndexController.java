package petit.jochem.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import petit.jochem.demo.model.Snack;
import petit.jochem.demo.model.SnackDAO;

@Controller
public class IndexController {

    @Autowired
    SnackDAO dao;

    @ModelAttribute(value = "allsnacks")
    public Iterable<Snack> getAllSnacks(){
        return dao.findAll();
    }

    @RequestMapping(value = {"","/", "/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";

    }


}
