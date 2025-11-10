package gio.hobist.Controller;
import gio.hobist.Entity.User;
import gio.hobist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gio.hobist.utils.PasswordHasher;

@Controller
public class SigninController {

    @RequestMapping(path="/signin")
    public String index(){
        return "signupPage.html";
    }


    @Autowired
   private UserRepository dataSender;

    @PostMapping(path="/signin")
    public String signinPage(User user,@RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {

//       if (user.getPassword()!=confirmPassword){
        if (!confirmPassword.equals(user.getPassword())) {
           redirectAttributes.addFlashAttribute("passErr","warning: password dosen't match");
           return "redirect:/signin";
       }

        PasswordHasher hashingObject=new PasswordHasher();
        String hashedPassword=hashingObject.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        try {
            dataSender.save(user);
            return "redirect:/login";
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("emailErr","warning: this email is already in use");
            return "redirect:/signin";
        }
    }


}
