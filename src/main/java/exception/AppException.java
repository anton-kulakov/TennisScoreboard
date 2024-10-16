package exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends Exception {
    public final int code;
    public final String message;
}
