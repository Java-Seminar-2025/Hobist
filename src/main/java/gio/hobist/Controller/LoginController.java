package gio.hobist.Controller;
import gio.hobist.Entity.User;
import gio.hobist.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gio.hobist.utils.PasswordHasher;

@Controller
public class LoginController {

    @RequestMapping(path="/")//in case of no session redirects to login
    public String redirect(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "homePage.html";
    }

    @RequestMapping(path="/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "loginPage.html";
    }

    @Autowired
    private UserRepository dataReciver;


    @PostMapping(path="/login")//i wanted to set this as @GetMapping but coulnd't resolve error with parameters
    public String loginPage(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session){


        try {
            User validUser = dataReciver.getByEmail(user.getE_mail());
            if (PasswordHasher.verifyPassword(user.getPassword(), validUser.getPassword())){

                session.setAttribute("userName",user.getName());
                session.setAttribute("userId",user.getId());

                return "redirect:/home";
            }
            else{
               throw new Exception("");
            }
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("loginErr","Incorrect email or password");
            return "redirect:/login";
        }


    }

    @GetMapping(path="/home")
    public String home(){
        return "homePage.html";
    }

}
