package gio.hobist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Controler_test {

    @RequestMapping(path="/")
    public String index(){
        return "test.html";
    }

    @Autowired
   private UserRepo dataSender;

    @PostMapping(path="/")
    public @ResponseBody void test(@RequestParam String name,@RequestParam String surname,@RequestParam String e_mail,@RequestParam String password) {
        User user = new User(name,surname,e_mail,password);
        dataSender.save(user);
        System.out.println("saved successfully");
    }


}
