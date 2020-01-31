package alexander.ivanov.polls.frontend.util;

import alexander.ivanov.polls.frontend.controller.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

public class ControllerUtils {
    public static <T> ResponseEntity<?> getResponseEntity(Supplier<T> supplier) {
        T value = null;
        try {
            value = supplier.get();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
        return ResponseEntity.ok(value);
    }
}
