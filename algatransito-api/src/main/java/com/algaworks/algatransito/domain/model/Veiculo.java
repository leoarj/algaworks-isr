package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
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
    private LocalDateTime dataCadastro;
    private LocalDateTime dataApreensao;
}
