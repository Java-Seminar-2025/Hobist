package gio.hobist.Controller;

import gio.hobist.Dto.AutenticationDto;
import gio.hobist.Service.AutenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AutenticationController {


    @RequestMapping(path="/")//in case of no session redirects to log in
    public String redirect(HttpSession session, Model model) {
        AutenticationDto currentUser = (AutenticationDto) session.getAttribute("currentUser");
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "homePage.html";
    }

    @RequestMapping(path="/login")
    public String login(Model model) {
        model.addAttribute("user", new AutenticationDto());
        return "loginPage.html";
    }

    @RequestMapping(path="/signup")
    public String index(Model model){
        model.addAttribute("user", new AutenticationDto());
        return "signupPage.html";
    }


    private final AutenticationService autenticationService;


    @PostMapping(path="/signup")
    public String signUpPage(@ModelAttribute AutenticationDto user ) {
        try {
            autenticationService.signUpUser(user);

            return "redirect:/login";
        }
        catch (Exception e) {
        System.out.println(e.getMessage());//for testing, delete later
            //return message for ui
            return "redirect:/signup";
        }
    }

    @PostMapping(path="/login")
    public String loginPage(@ModelAttribute("user") AutenticationDto user, HttpSession session){

        try {
            AutenticationDto validUser = autenticationService.logInUser(user);

                session.setAttribute("userName",validUser.getName());
                session.setAttribute("userId",validUser.getId());

                return "redirect:/home";
        }
        catch (Exception e){
            System.out.println(e.getMessage());//for testing, delete later
            //return message for ui
        }

        return "redirect:/login";


    }
}
