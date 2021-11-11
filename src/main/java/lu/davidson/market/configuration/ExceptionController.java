package lu.davidson.market.configuration;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import lu.davidson.market.exception.InvalidInputException;
import lu.davidson.market.json.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = {InvalidInputException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidLoginException(Exception e){
        log.error("Error {}", e.getMessage() );
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentTypeMismatchException(Exception e){
        log.error("Bad request", e);
        return new ErrorResponse("Wrong input");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse uncaughtException(Exception e){
        log.error("Bad request", e);
        return new ErrorResponse("Unknown Error");
    }
}
