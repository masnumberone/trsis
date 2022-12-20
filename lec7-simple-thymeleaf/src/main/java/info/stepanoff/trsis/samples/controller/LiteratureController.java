/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.controller;

import info.stepanoff.trsis.samples.db.model.Literature;
import info.stepanoff.trsis.samples.service.LiteratureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LiteratureController {

    @Autowired
    LiteratureService literatureService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView literaturesRef(Model model) {
        return literatures(model);
    }

    @RequestMapping(value = "/literatures", method = RequestMethod.GET)
    @ModelAttribute("literature")
    public ModelAndView literatures(Model model) {

        if (!model.containsAttribute("literatureAdd")) {
            Literature newLiterature = new Literature();
            model.addAttribute("literatureAdd", newLiterature);
        }

        if (!model.containsAttribute("literatureDelete")) {
            Literature newLiterature = new Literature();
            model.addAttribute("literatureDelete", newLiterature);
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("literatures");
        Iterable<Literature> literatures = literatureService.findAll();

        mav.addObject("literature", literatures);
        return mav;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView saveRef(@ModelAttribute Literature literature, BindingResult errors, Model model) {
        return save(literature, errors, model);
    }

    @RequestMapping(value = "/literatures", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Literature literature, BindingResult errors, Model model) {
        literatureService.save(literature);
        return (literatures(model));
    }


    @RequestMapping(value = "/literatures/delete/{id}", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") Integer id, Model model) {
        literatureService.delete(id);
        return (literaturesRef(model));
    }
}
