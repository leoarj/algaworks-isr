package com.algaworks.algatransito.domain.repository;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
