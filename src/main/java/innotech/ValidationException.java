package innotech;

public class ValidationException extends Exception {
    public ValidationException(String message, int count) {
        System.out.println(message + " " + count);
    }
}