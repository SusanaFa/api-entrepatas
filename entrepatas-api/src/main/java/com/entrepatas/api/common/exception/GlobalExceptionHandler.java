package com.entrepatas.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//anotacion que aplica a todos los controller, y que devuelve como respuesta un json @ControllerAdvice + @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
    // validaciones (@Valid)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) { // este metodo se llama cuando el body no cumple las anotaciones
                                                  // (@NotNull, @NotBlack, etc)
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors() // trae el resuletado del binding. o sea el proceso de convertir json ->
                                               // objeto
                // getFieldErrors= lista de errores por campo
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        // forEach recorre cada error
        //// error.getField -> nombre del campo que falló
        /// error.getDefaultMessage -> el mensaje que existe en la notación o el default

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Enums inválidos (valueOf)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument( // RespondeEntity permite controlar status code
                                                                      // (400, 404, etc) body json
                                                                      // Map<String, String> devuelve un json {"name":
                                                                      // "name es
            // requerido", "specie": "specie es requerido"}
            IllegalArgumentException ex) { // suele ocurrir si se hace PetStatus.valueOf("asdf").. esto revienta la
                                           // consulta. para eso spring envia este método
        Map<String, String> error = new HashMap<>(); // guarda clave = nombre del campo (name, ageYears) y valor =
                                                     // mensaje del error ("name es requerido")
        error.put("error", "Valor inválido en parámetros o body"); // mensaje amigable
        error.put("detail", ex.getMessage()); // mensaje tecnico. util para depurar

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // devuelve el htto 400 bad request
                .body(error); // esto es para que react pueda mostrar que campo falló directamente
    }

    // Fallback simple
    @ExceptionHandler(RuntimeException.class) // esto captura cualquier RunTimeException que no haya sido capturada
                                              // antes
    public ResponseEntity<Map<String, String>> handleRuntime( // ejemplo en el PetService.java
            RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    // u fallback es una alternativa de respaldo que se ejecuta cuando nada más pudo
    // manejar el problema
    // si nadie supo que hacer con el error, el fallback se encarga en ultima
    // instancia
    // RunTimeException es muy generico, captura muchas excepciones, por eso se usan
    // handler más específicos antes
    // MethodArgumentNotValidException -> handler de validación
    // IllegalArgumentException -> handler de enums
    // RuntimeException es una excepcion que ocurre mientra el programa está
    // corriendo, no al compilar
}
