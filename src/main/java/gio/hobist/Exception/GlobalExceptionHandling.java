package gio.hobist.Exception;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(NullPointerException.class)
    public String
    NullPointerException(NullPointerException e, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new String("redirect:/login");
        }

        nullPointerException(e);
        return null;
    }

    public ResponseEntity<String>
    nullPointerException(NullPointerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(FileNotFoundException.class) //M.G: tmp
//    public ResponseEntity<String>
//    FileNotFoundException(FileNotFoundException e) {
//
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//
//    }

}