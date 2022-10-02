package br.com.template.api.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotNull;

@Data
public class ClientDto {

    @NotNull(message = "O campo name não pode ser nulo")
    private String name;

    @CPF(message = "Cpf Inválido kkk")
    private String cpf;

    @NotNull(message = "Campo obrigatório")
    private String estadoCivil;
}
