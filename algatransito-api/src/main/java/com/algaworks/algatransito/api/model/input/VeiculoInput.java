package com.algaworks.algatransito.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
 * Modelo de representação para entrada de dados um recurso de Proprietario.
 * Não expondo certas propriedades que serão somente processadas
 * pelo backend (sem possibilidade de alteração pelo client)
 * e retornadas no modelo de representação de saída.
 */

@Getter
@Setter
public class VeiculoInput {

    @NotBlank
    @Size(max = 20)
    private String marca;

    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    //private Long proprietarioId;
    @Valid
    @NotNull
    private ProprietarioIdInput proprietario;
}
