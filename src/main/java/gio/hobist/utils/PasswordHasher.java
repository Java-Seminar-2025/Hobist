package gio.hobist.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public String hashPassword(String password){
        password= BCrypt.withDefaults().hashToString(12,password.toCharArray());

        return password;
    }

    public static boolean verifyPassword(String password,String hashedPassword){
//        password= BCrypt.withDefaults().hashToString(12,password.toCharArray());

        BCrypt.Result result= BCrypt.verifyer().verify(password.toCharArray(),hashedPassword.toCharArray());

        return result.verified;
    }
}
