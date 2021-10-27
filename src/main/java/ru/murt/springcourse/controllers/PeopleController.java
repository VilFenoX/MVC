package ru.murt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murt.springcourse.dao.PersonDAO;
import ru.murt.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")    // все адреса начинаюся с people
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}") // айди извлекается из URL и вствляется в метод через @PathVariable
    public String show(@PathVariable("id") int id, Model model) {
// Получим одного человека по айдит из ДАО и передадим на отображение в представлении
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        // model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()   // @ModelAttribute автоматически создает обьект Person и и присваивает
                        // значения перменным внутри класса
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "/people/new";
        personDAO.save(person);
            return "redirect:/people"; // редирект отправляет на указанную страницу
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
