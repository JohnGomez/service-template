package br.com.template.api.exceptions.handler;


import br.com.template.api.exceptions.NotFoundException;
import br.com.template.api.exceptions.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public final class DefaultExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ExceptionModel notFoundException(NotFoundException ex){
       return new ExceptionModel(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected List<ExceptionModel> argumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();

        return result.getFieldErrors().stream()
                .map(fieldError -> new ExceptionModel(fieldError.getField(),
                                                      fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
