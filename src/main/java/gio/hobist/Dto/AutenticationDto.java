package gio.hobist.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticationDto {
    private UUID id;
    private String name;
    private String surname;
    private String e_mail;
   private String password;
   private String confirmPassword;

    public void cleanOldData(){//cleans unnecessary data for better security
        id=null;
        name=null;
        surname=null;
        e_mail=null;
        password=null;
        confirmPassword=null;
    }
}
