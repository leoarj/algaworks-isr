package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
* Anotações do Lombok ajudam em tempo de desenvolvimento a reduzir
* código repetitivo padrão (boilerplate).
* No caso está habilitando getters e setters para os atributos da classe.
*/

@Getter
@Setter
// Implementa equals() e hashCode() para apenas atributos explícitamente marcados.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Diz que a classe é um mapeamento para uma entidade.
@Entity
//@Table(name = "tb_proprietario") // Caso o nome da tabela no db fosse diferente.
public class Proprietario {

    // Inclui id para as lógicas de equals() e hashCode().
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    // Mapeia propriedade "telefone" para a coluna "fone", @Column utilizada
    // justamente porque o nome difere no mapeamento.
    @Column(name = "fone")
    private String telefone;
}