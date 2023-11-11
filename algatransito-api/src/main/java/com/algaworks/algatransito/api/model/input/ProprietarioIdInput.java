package com.algaworks.algatransito.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/*
* Modelo de representação para entrada de dados
* de um id referente a um recurso de Proprietario.
*/

@Getter
@Setter
public class ProprietarioIdInput {

    @NotNull
    private Long id;
}
