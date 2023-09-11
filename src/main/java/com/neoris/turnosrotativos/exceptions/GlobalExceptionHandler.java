package com.neoris.turnosrotativos.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
//        logger.error("Se ha producido una Exception", ex);
//        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error interno. Por favor, inténtalo de nuevo más tarde.", System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

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

    @ExceptionHandler(NotFoundEmpleado.class)
    public ResponseEntity<ErrorResponse> handleNotFoundEmpleado(NotFoundEmpleado ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullEmpleadoException.class)
    public ResponseEntity<ErrorResponse> handleINullEmpleadoException(NullEmpleadoException ex){
        logger.error("Se ha producido una NullEmpleadoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "No existe el empleado ingresado.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullConceptoException.class)
    public ResponseEntity<ErrorResponse> handleINullConceptoException(NullConceptoException ex){
        logger.error("Se ha producido una NullConceptoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "No existe el concepto ingresado.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredHsTrabajadasExeption.class)
    public ResponseEntity<ErrorResponse> handleRequiredHsTrabajadasExeption(RequiredHsTrabajadasExeption ex){
        logger.error("Se ha producido una RequiredHsTrabajadasExeption", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "‘hsTrabajadas’ es obligatorio para el concepto ingresado.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotRequiredHsTrabajadas.class)
    public ResponseEntity<ErrorResponse> handleNotRequiredHsTrabajadas(NotRequiredHsTrabajadas ex){
        logger.error("Se ha producido una NotRequiredHsTrabajadas", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El concepto ingresado no requiere el ingreso de ‘hsTrabajadas’.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //InvalidHoursRangeException
    @ExceptionHandler(InvalidHoursRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidHoursRangeException(InvalidHoursRangeException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //NotSameDiaLibreException
    @ExceptionHandler(NotSameDiaLibreException.class)
    public ResponseEntity<ErrorResponse> handleNotSameDiaLibreException(NotSameDiaLibreException ex){
        logger.error("Se ha producido una NotSameDiaLibreException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El empleado ingresado cuenta con un día libre en esa fecha.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //DuplicateConceptoException
    @ExceptionHandler(DuplicateConceptoException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateConceptoException(DuplicateConceptoException ex){
        logger.error("Se ha producido una DuplicateConceptoException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //MaxHsTrabajadasDiaException
    @ExceptionHandler(MaxHsTrabajadasDiaException.class)
    public ResponseEntity<ErrorResponse> handleMaxHsTrabajadasDiaException(MaxHsTrabajadasDiaException ex){
        logger.error("Se ha producido una MaxHsTrabajadasDiaException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El empleado no puede cargar más de 12 horas trabajadas en un día.",
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //DiaLibreVsTurnosCargadosException
    @ExceptionHandler(DiaLibreVsTurnosCargadosException.class)
    public ResponseEntity<ErrorResponse> handleDiaLibreVsTurnosCargadosException(DiaLibreVsTurnosCargadosException ex){
        logger.error("Se ha producido una DiaLibreVsTurnosCargadosException", ex);
        ErrorResponse error= new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "El empleado no puede cargar Dia Libre si cargo un turno previamente para la fecha ingresada.",

                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
