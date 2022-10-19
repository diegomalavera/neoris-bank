package com.neoris.bank.handler;

import com.neoris.bank.controller.response.ErrorResponse;
import com.neoris.bank.exception.BankException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@AllArgsConstructor
public class BankExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(Integer.toString(status.value()), status.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(BankException.class)
    public ResponseEntity<Object> handleApiException(final BankException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), getErrorMessage(e.getCode()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("ERROR_GENERAL", getErrorMessage("error.general"));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrorMessage(String errorCode) {
        try {
            String message = errorCode.toString().toLowerCase().replace("_", ".");
            return messageSource.getMessage(message, null, Locale.ENGLISH);
        } catch (NoSuchMessageException e) {
        }
        return errorCode;
    }
}
