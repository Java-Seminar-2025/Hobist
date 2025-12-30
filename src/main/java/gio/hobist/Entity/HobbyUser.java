//package gio.hobist.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.UUID;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "hobby_user")
//@IdClass(HobbyUserId.class)
//public class HobbyUser {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "id_user")
//    private User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "id_hobby")
//    private Hobby hobby;
//
//    public HobbyUser() { super(); }
//}
