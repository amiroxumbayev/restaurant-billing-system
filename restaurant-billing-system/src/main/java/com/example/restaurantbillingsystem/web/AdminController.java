package com.example.restaurantbillingsystem.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.restaurantbillingsystem.Menu;
import com.example.restaurantbillingsystem.data.MenuRepository;

@Controller
@RequestMapping("/admin")
//@SessionAttributes("tacoOrder")
public class AdminController {

    private final MenuRepository menuRepo;

    @Autowired
    public AdminController(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    @GetMapping
    public String adminForm(Model model) {
        return "admin";
    }

    @GetMapping("/menueditor")
    public String menuEditorForm(Model model) {
        Iterable<Menu> list = menuRepo.findAll();

        Menu.Type[] types = Menu.Type.values();
        for (Menu.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(list, type));
        }
        return "menueditor";
    }

    private Iterable<Menu> filterByType(Iterable<Menu> ingredients, Menu.Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping("/menueditor/addmenu")
    public String showAddMenu(Model m) {
        m.addAttribute("newitem", new Menu());
        return "addmenu";
    }
    
    @GetMapping("/menueditor/updatemenu/{id}")
    public String showUpdateMenu(Model m, @PathVariable("id")int id) {
        
        System.out.println(id);
        Menu menu = menuRepo.findById(id).orElse(null);
        m.addAttribute("item", menu);
        return "updatemenu";
    }

    /*
     * @PostMapping("/menueditor/updatemenu{id}") public String edit(@PathVariable
     * int id, Model m) { Menu menu = menuRepo.findById(id).orElse(null);
     * m.addAttribute("command", menu); return "updatemenu"; }
     */

    // It updates model object.

    @PostMapping("/menueditor/addmenu")
    public String addMenu(@ModelAttribute("newitem") @Valid Menu menu, Errors errors) {
        
        if (errors.hasErrors()) {
            return "addmenu";
        }
        
        menuRepo.save(menu);
        return "redirect:/admin/menueditor";
    }

    @PostMapping("/menueditor/updatemenu/{id}")
    public String editsave(@ModelAttribute("item") @Valid Menu menu, @PathVariable("id") Integer id, Errors errors) {
        
        if(errors.hasErrors()){
            return "updatemenu";
        }
        
        System.out.println(menu.toString());
        menuRepo.update(id, menu);
        return "redirect:/admin/menueditor";
    }

    @GetMapping("/menueditor/deletemenu/{id}")
    public String delete(@PathVariable int id) {
        menuRepo.delete(id);
        return "redirect:/admin/menueditor";
    }

}
