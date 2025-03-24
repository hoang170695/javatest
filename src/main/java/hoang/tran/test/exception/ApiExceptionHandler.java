package hoang.tran.test.exception;

import hoang.tran.test.code.ApiResponseCode;
import hoang.tran.test.response.ApiResponse;
import hoang.tran.test.response.ApiResponseGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e,
                                                       HttpServletRequest request) {
        String message = e.toString();

        log.error("ApiExceptionHandler > Exception > exception:{}", e);
        log.error("ApiExceptionHandler > Exception > userMessage:{}", message);
        log.error("ApiExceptionHandler > Exception > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.SYSTEM_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(KeyCannotBeFoundException.class)
    public ResponseEntity<ApiResponse> handleException(KeyCannotBeFoundException e,
                                                       HttpServletRequest request) {
        String message = MessageFormat.format("This API does not support {0}.", request.getMethod());

        log.error("ApiExceptionHandler > KeyCannotBeFoundException > userMessage:{}", message);
        log.error("ApiExceptionHandler > KeyCannotBeFoundException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.NOT_FOUND_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleException(IllegalArgumentException e,
                                                       HttpServletRequest request) {
        String message = MessageFormat.format("This API does not support {0}.", request.getMethod());

        log.error("ApiExceptionHandler > IllegalArgumentException > userMessage:{}", message);
        log.error("ApiExceptionHandler > IllegalArgumentException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
