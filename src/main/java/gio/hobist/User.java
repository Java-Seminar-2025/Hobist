package gio.hobist;

import jakarta.persistence.*;

@Entity
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
    public User(String userName, String userSurname, String email, String password) {
        this.name = userName;
        this.surname = userSurname;
        this.e_mail = email;
        this.password = password;
    }
}
