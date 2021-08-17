package ru.murt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murt.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {

private PersonDAO personDAO;
@Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        // получим всех людей из DAO и передадим на отображение в представление
model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}") // айди извлекается из URL и вствляется в метод через @PathVariable
    public String show(@PathVariable("id") int id, Model model){
// Получим одного человека по айдит из ДАО и передадим на отображение в представлении
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
}
