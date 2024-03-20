package com.cursos.springsecurity.common.exception_handler;

//import com.ecommerce.signatureback.auth.auth.exception.AuthenticationFailedException;
//import com.ecommerce.signatureback.auth.auth.exception.RefreshTokenException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
/**
 * Clase que maneja las excepciones de la aplicación y proporciona respuestas de error adecuadas.
 */
public class AppExceptionHandler {

    private final HttpSession httpSession;

    public AppExceptionHandler(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    /**
     * Maneja la excepción de recurso no encontrado.
     *
     * @param exception la excepción ResourceNotFoundException
     * @return un mapa con el mensaje de error y el mensaje de la excepción
     */
    public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", "Not found");
        map.put("message", exception.getMessage());
        return map;
    }

    /**
     * Maneja la excepción de argumento ilegal.
     *
     * @param exception la excepción IllegalArgumentException
     * @return un mapa con el mensaje de error y el mensaje de la excepción
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", "Bad Request");
        map.put("message", exception.getMessage());
        return map;
    }

    /**
     * Maneja la excepción de autenticación fallida.
     *
     * @param exception la excepción AuthenticationFailedException
     * @return un mapa con el mensaje de error y el mensaje de la excepción
     */
//    @ExceptionHandler(value = AuthenticationFailedException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Map<String, String> handleAuthenticationException(AuthenticationFailedException exception) {
//        Map<String, String> map = new HashMap<>();
//        map.put("error", "Authentication failed");
//        map.put("message", exception.getMessage());
//        return map;
//    }

    /**
     * Maneja la excepción de acceso denegado.
     *
     * @param exception la excepción AccessDeniedException
     * @return un mapa con el mensaje de error y el nombre del permiso
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleAccessDeniedException(AccessDeniedException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Acceso denegado");
        map.put("permission_name", exception.getMessage());
        return map;
    }

    /**
     * Maneja la excepción de actualización de token fallida.
     *
     * @param exception la excepción RefreshTokenException
     * @return un mapa con el mensaje de error y el mensaje de la excepción
     */
//    @ExceptionHandler(value = RefreshTokenException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Map<String, String> handleTokenRefreshException(RefreshTokenException exception) {
//        Map<String, String> map = new HashMap<>();
//        map.put("error", "Refresh token");
//        map.put("message", exception.getMessage());
//        return map;
//    }

    /**
     * Mapea un error de objeto a una respuesta de error de campo.
     *
     * @param obj el error de objeto a mapear
     * @return una instancia de FieldErrorResponse que representa el error de campo mapeado
     */
//    private FieldErrorResponse mapErrorBadRequest(ObjectError obj) {
//
//        if (obj instanceof FieldError fieldError) {
//            return new FieldErrorResponse(
//                    UtilString.fieldConvertName(fieldError.getField()),
//                    fieldError.getDefaultMessage()
//            );
//        }
//        return new FieldErrorResponse(
//                obj.getObjectName(),
//                obj.getDefaultMessage()
//        );
//    }

    /**
     * Maneja la excepción de validación de argumentos de método no válidos.
     * @param ex
     * @return una lista de respuestas de error de campo
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleExceptionBadRequest(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        if(ex.getBindingResult().getFieldError() != null){
            map.put("field", ex.getBindingResult().getFieldError().getField());
            map.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());
            return map;
        }
        map.put("message", ex.getBindingResult().getGlobalError().getDefaultMessage());
        return map;
    }

    @ExceptionHandler(value = ServerErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleServerErrorException(ServerErrorException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> constraintViolationException(ConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return map;
    }
}
