package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
* Classe de serviço para registro de Proprietario.
*
* @Service = Anotação que diz que a classe é um componente,
* mas com a semântica de que ela é um Service.
*
* @AllArgsConstructor = Já gera um construtor com argumentos, para a injeção do repository.
*/

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    /*
    * Métodos que serão usados pelo controlador.
    *
    * @Transactional (Do Spring) torna o método transacional,
    * sendo assim, acontece dentro de uma transação do banco de dados
    * e se alguma coisa der errado tudo é revertido (rollback).
    * */

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }
}
