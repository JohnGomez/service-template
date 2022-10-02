package br.com.template.api.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(NON_NULL)
public class ExceptionModel {
    private String field;
    private String message;

    public ExceptionModel(String message) {
        this.message = message;
    }

}
