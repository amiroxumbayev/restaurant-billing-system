package com.example.restaurantbillingsystem.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurantbillingsystem.data.MenuRepository;
import com.example.restaurantbillingsystem.data.UserRepository;
import com.example.restaurantbillingsystem.domain.Menu;
import com.example.restaurantbillingsystem.domain.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MenuRepository menuRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(MenuRepository menuRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.menuRepo = menuRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String adminForm(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "adminPages/admin";
    }
    
    @PostMapping("/update-user/{id}")
    public String editUser(@Valid User user, Errors errors, @PathVariable("id") Integer id) {

        if (errors.hasErrors()) {
            return "adminPages/updateuser";
        }
        user.setUserId((long) id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/admin";
    }
    
    @GetMapping("/update-user/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        User user = userRepo.findById((long)id).orElseThrow(()->new IllegalArgumentException("User not found"));
        model.addAttribute("user", user);
        return "adminPages/updateuser";
    }
    
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepo.deleteById((long) id);
        return "redirect:/admin";
    }

    @GetMapping("/menueditor")
    public String menuEditorForm(Model model) {
        Iterable<Menu> list = menuRepo.findAll();
        Menu.Type[] types = Menu.Type.values();
        for (Menu.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(list, type));
        }
        return "adminPages/menueditor";
    }

    private Iterable<Menu> filterByType(Iterable<Menu> ingredients, Menu.Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping("/menueditor/addmenu")
    public String showAddMenu(Model m) {
        m.addAttribute("newitem", new Menu());
        return "adminPages/addmenu";
    }

    @GetMapping("/menueditor/updatemenu/{id}")
    public String showUpdateMenu(Model m, @PathVariable("id") int id) {
        
        Menu menu = menuRepo.findById((long) id).orElse(new Menu());
        m.addAttribute("item", menu);
        return "adminPages/updatemenu";
    }

    @PostMapping("/menueditor/addmenu")
    public String addMenu(@ModelAttribute("newitem") @Valid Menu menu, Errors errors) {
        if (errors.hasErrors()) {
            return "adminPages/addmenu";
        }

        menuRepo.save(menu);
        return "redirect:/admin/menueditor";
    }

    @PostMapping("/menueditor/updatemenu/{id}")
    public String editsave(@ModelAttribute("item") @Valid Menu menu, @PathVariable("id") Integer id, Errors errors) {
        System.out.println("dbvdbsdfbsfd");

        if (errors.hasErrors()) {
            return "adminPages/updatemenu";
        }
        menu.setMenuId((long) id);
        menuRepo.save(menu);
        return "redirect:/admin/menueditor";
    }

    @GetMapping("/menueditor/deletemenu/{id}")
    public String delete(@PathVariable int id) {
        menuRepo.deleteById((long) id);
        return "redirect:/admin/menueditor";
    }

}
