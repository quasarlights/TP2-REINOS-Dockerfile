package com.neoris.turnosrotativos.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        logger.error("Se ha producido una Exception", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error interno. Por favor, inténtalo de nuevo más tarde.", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotUnderageException.class)
    public ResponseEntity<ErrorResponse> handleNotUnderageException(NotUnderageException ex) {
        logger.error("Se ha producido una NotUnderageException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "La edad del empleado no puede ser menor a 18 años.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SameNroDocumentoException.class)
    public ResponseEntity<ErrorResponse> handleSameNroDocumentoException(SameNroDocumentoException ex){
        logger.error("Se ha producido una SameNroDocumentoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Ya existe un empleado con el documento ingresado.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SameEmailException.class)
    public ResponseEntity<ErrorResponse> handleSameEmailException(SameEmailException ex){
        logger.error("Se ha producido una SameEmailException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Ya existe un empleado con el email ingresado.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFechaIngresoException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFechaIngresoException(InvalidFechaIngresoException ex){
        logger.error("Se ha producido una InvalidFechaIngresoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "La fecha de ingreso no puede ser posterior al día de la fecha.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFechaNacimientoException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFechaNacimientoException(InvalidFechaNacimientoException ex){
        logger.error("Se ha producido una InvalidFechaNacimientoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "La fecha de nacimiento no puede ser posterior al día de la fecha.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleIInvalidEmailException(InvalidEmailException ex){
        logger.error("Se ha producido una InvalidEmailException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El email ingresado no es correcto.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCampoInvalidoException(CampoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //EXCEPCION DE CAMPOS NULOS O VACIOS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldErrors().get(0);

        String campo = fieldError.getField();
        String mensaje = "'" + campo + "' es obligatorio.";

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                mensaje,
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
