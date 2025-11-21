package gio.hobist.Exception;

public class AutenticationException extends RuntimeException {
    public AutenticationException(String message) {
        super("Error with autentication: "+message);
    }

}
