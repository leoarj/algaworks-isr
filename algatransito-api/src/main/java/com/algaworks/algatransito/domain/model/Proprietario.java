package com.algaworks.algatransito.domain.model;

import lombok.Getter;
import lombok.Setter;

/*
* Anotações do Lombok ajudam em tempo de desenvolvimento a reduzir
* código repetitivo padrão (boilerplate).
* No caso está habilitando getters e setters para os atributos da classe.
*/

@Getter
@Setter
public class Proprietario {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}