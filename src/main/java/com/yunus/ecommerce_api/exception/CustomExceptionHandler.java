package com.yunus.ecommerce_api.exception;

import com.yunus.ecommerce_api.response.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

//@ResponseBody

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUESTSS";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
//        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage1 = error.getDefaultMessage();
            errors.put(fieldName, errorMessage1);
        });

//        ErrorResponse apiErrorVO = new ErrorResponse(errorMessage);
//        apiErrorVO.setDetails(validationList);
        return new ResponseEntity<>(errors, status);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request ";
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status).withError_code("BAD_DATA")
                .withMessage(ex.getLocalizedMessage()).withDetail(error+ex.getMessage()) .build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    protected ResponseEntity<Object> handleCustomAPIException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withDetail("Something went wrong..")
                .withMessage(ex.getLocalizedMessage())
                .withError_code("502")
                .withError_code(status.BAD_GATEWAY.name())
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<Object> handleCustomBadCredentialException(BadCredentialsException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withDetail("Something went wrong..")
                .withMessage(ex.getLocalizedMessage())
                .withError_code("403")
                .withError_code(status.BAD_GATEWAY.name())
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }



//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<ErrorResponse> handleConstraintViolation(
//            ConstraintViolationException ex,
//            WebRequest request)
//    {
//        List<String> details = ex.getConstraintViolations()
//                .parallelStream()
//                .map(e -> e.getMessage())
//                .collect(Collectors.toList());
//
//        System.out.println(" erooor bad request ---------------------");
//
//        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }


}
