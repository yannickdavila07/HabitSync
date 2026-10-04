package br.com.api.habitFlow.infra.exception;

import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErroValidacao(ValidationException ex){
        return ResponseEntity.status(404).body(new DadosErro(ex));
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity tratarErro500(HttpServerErrorException.InternalServerError ex){
        return ResponseEntity.internalServerError().body(ex);
    }

    private record DadosErro(String message){
        public DadosErro(ValidationException error){
            this(error.getMessage());
        }
    }


}
