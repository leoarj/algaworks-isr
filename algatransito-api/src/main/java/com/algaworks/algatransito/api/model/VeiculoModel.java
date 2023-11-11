package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/*
* Classe para separação entre o Modelo de Domínio e o Modelo de Representação do recurso de Veiculo.
*/

@Getter
@Setter
public class VeiculoModel {
    private Long id;
    //private String nomeProprietario;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
