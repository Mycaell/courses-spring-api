package com.mycaell.crudspring.application.shared.exception.handler;

import com.mycaell.crudspring.application.shared.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(FieldError fieldError) {
        return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
    }

    public List<String> getErrors(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(e -> errors.add(getMessage(e)));
        return errors;
    }

    public ResponseEntity<Object> handleException(Exception exception, HttpStatus status, WebRequest request, String key) {
        List<String> errors = List.of(getMessage(key));
        return handleExceptionInternal(exception, errors, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = getErrors(exception.getBindingResult());
        return handleExceptionInternal(exception, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, "resource.invalid-operation");
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, "resource.invalid-operation");
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        List<String> errors = exception.getConstraintViolations().stream()
                .map(e -> MessageFormat.format(e.getMessage(), e.getPropertyPath())).collect(Collectors.toList());

        return handleExceptionInternal(exception, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(ResourceNotFoundException exception, WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, "resource.not-found");
    }

}
