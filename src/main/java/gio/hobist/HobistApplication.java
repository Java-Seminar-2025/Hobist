package gio.hobist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HobistApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(HobistApplication.class, args);

        System.out.println("Hobist Application Started");

    }

}
