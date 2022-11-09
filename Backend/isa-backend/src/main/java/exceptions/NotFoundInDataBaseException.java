package exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundInDataBaseException extends RuntimeException{
    private String message;
}
