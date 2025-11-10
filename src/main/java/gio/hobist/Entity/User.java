package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Entity
@Getter
@Setter
@Table(name="\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String e_mail;
    private String password;

    public User(){ super();}
    public User(@RequestParam String userName,@RequestParam String userSurname,@RequestParam String email,@RequestParam String password) {
        this.name = userName;
        this.surname = userSurname;
        this.e_mail = email;
        this.password = password;
    }
}
