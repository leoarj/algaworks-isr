package com.algaworks.algatransito.domain.repository;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Interface ProprietarioRepository estendendo da interface JpaRepository
* terá acesso a métodos comuns de repositório, onde a implementação
* será provida em tempo de execução pelo Spring.
*
* @Repository = Informa que é um componente Spring, no caso um componete de repositório,
* e que pode ser injetado em outros objetos no projeto.
* */

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    /*
    * Query methods são meios de extender o repositório criando
    * a assinatura do método respeitando uma convenção, de modo que
    * o Spring consegue fornecer uma implementação com base no match
    * da assinatura do método com as palavras reservadas na convenção do Spring Data JPA.
    */

    // Busca entidades pela propriedade mapeada nome (texto exato).
    //List<Proprietario> findByNome(String nome);
    // Busca entidades pela propriedade mapeada nome (texto parcial).
    List<Proprietario> findByNomeContaining(String nome);
}
