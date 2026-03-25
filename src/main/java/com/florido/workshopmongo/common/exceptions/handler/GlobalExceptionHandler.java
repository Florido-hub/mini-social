package com.florido.workshopmongo.common.exceptions.handler;

import com.florido.workshopmongo.common.exceptions.ErroDTOs.ErroCampo;
import com.florido.workshopmongo.common.exceptions.ErroDTOs.ErroResponse;
import com.florido.workshopmongo.common.exceptions.NotFoundException;
import com.florido.workshopmongo.common.exceptions.UserNotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrorList
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect((Collectors.toList()));
        return new ErroResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validacao",
                listaErros);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroResponse HandleUserNotAuthorizedException(UserNotAuthorizedException e){
        return new ErroResponse(
                HttpStatus.FORBIDDEN.value(),
                "Acesso negado!",
                List.of());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse HandleNotFoundException(NotFoundException e){
        return ErroResponse.notFound(e.getMessage());
    }
}
