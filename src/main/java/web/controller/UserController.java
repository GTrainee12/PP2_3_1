package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {


    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users.isEmpty() ? null : users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());

        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        userServiceImpl.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User created successfully!");
        return "redirect:/users";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        Optional<User> userOptional = userServiceImpl.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user-update";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        userServiceImpl.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/users";
    }
}

