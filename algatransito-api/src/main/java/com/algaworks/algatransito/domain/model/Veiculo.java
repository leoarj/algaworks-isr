package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid // Para validação em cascata
    @NotNull
    // Relacionamento muitos-para-um
    @ManyToOne
    // Opcional, caso nome de coluna fosse diferente da convenção.
//    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    /*
    * @JsonProperty(access = Access.READ_ONLY):
    * Protege a propriedade na desserialização
    * para que o acesso a ela seja somente leitura.
    *
    * Desse modo o consumidor da API pode até informar a propriedade
    * com algum valor no corpo da requisição, porém a mesma será ignorada
    * se sera retornada no corpo da resposta com o devido valor definido
    * pelo backend.
    * */

    @JsonProperty(access = Access.READ_ONLY)
    // Para gravar o literal do enum no banco de dados e não um código da posição.
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    // JPA indentifica camel-case automaticamente para snake-case do DB.
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCadastro;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;
}
