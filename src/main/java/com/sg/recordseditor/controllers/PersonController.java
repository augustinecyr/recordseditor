package com.sg.recordseditor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.sg.recordseditor.models.Person;
import com.sg.recordseditor.models.PersonForm;
import com.sg.recordseditor.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/person")
public class PersonController {
    private List<Person> personList = new ArrayList<Person>();

    @Autowired
    PersonService perSvc;

    @Value("${error.message}")
    private String errorMessage;

    
    @GetMapping(value = { "/", "/home", "/index" })
    public String index(Model model) {
      

        return "index";
    }

   
    @GetMapping(value = "/testRetrieve", produces = "application/json")
    public @ResponseBody List<Person> getAllPersons() {
        personList = perSvc.getPersons();

        return personList;
    }

    
    @GetMapping(value = "/personList")
    public String personList(Model model) {
        personList = perSvc.getPersons();
        model.addAttribute("persons", personList);

        return "personList";
    }

    @GetMapping(value = "/addPerson")
    public String showAddPersonPage(Model model) {
        PersonForm pForm = new PersonForm();
        model.addAttribute("personForm", pForm);

        return "addPerson";
    }

 
    @PostMapping(value = "/addPerson")
    public String savePerson(Model model,
            @ModelAttribute("personForm") PersonForm personForm) {

        String fName = personForm.getFirstName();
        String lName = personForm.getLastName();

        if (fName != null && fName.length() > 0 && lName != null && lName.length() > 0) {
            Person newPerson = new Person(fName, lName);
            perSvc.addPerson(newPerson);

            return "redirect:/person/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

    @RequestMapping(value="/personToEdit", method=RequestMethod.POST)
    public String personToEdit(@ModelAttribute(value="per") Person p, Model model) {
        model.addAttribute("per", p);
        return "editPerson";
    }

    @RequestMapping(value="/personEdit", method = RequestMethod.POST)
    public String personEdit(@ModelAttribute(value="per") Person p, Model model) {
        perSvc.updatePerson(p);
        return "redirect:/person/personList";
    }

    @RequestMapping(value="/personDelete", method = RequestMethod.POST)
    public String personDelete(@ModelAttribute(value="per") Person p, Model model) {
        perSvc.removePerson(p);
        return "redirect:/person/personList";
    }
}
