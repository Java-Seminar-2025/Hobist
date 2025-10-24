package gio.hobist.Controller;
import gio.hobist.Entity.User;
import gio.hobist.Interface.baseConnection;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utils.security;

@Controller
public class LoginController {

    @RequestMapping(path="/")//in case of no session redirects to login
    public String redirect(HttpSession session){
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "main.html";

    }

    @RequestMapping(path="/login")
    public String login(){
        return "login.html";
    }

    @Autowired
    private baseConnection dataReciver;


    @PostMapping(path="/login")//i wanted to set this as @GetMapping but coulnd't resolve error with parameters
    public String loginPage(@RequestParam String e_mail, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession session){


        try {
            User user = dataReciver.getByEmail(e_mail);
            if (security.verifyPassword(password, user.getPassword())){

                session.setAttribute("userName",user.getName());
                session.setAttribute("userId",user.getId());

                return "main.html";
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


}
