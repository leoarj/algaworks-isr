package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Validações removidas da entity,
    // já que está utilizando representation model específico para input.

    // Relacionamento muitos-para-um
    @ManyToOne
    // Opcional, caso nome de coluna fosse diferente da convenção.
//    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    private String marca;
    private String modelo;
    private String placa;

    // Para gravar o literal do enum no banco de dados e não um código da posição.
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    // JPA indentifica camel-case automaticamente para snake-case do DB.
    // Utilizando OffsetDateTime para retornar com o offset (respeitando o padrão ISO 8601).
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
