package exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException {
    public final int code;
    public final String message;
}
