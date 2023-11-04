package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
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

    // Para reaproveitamento de código em outros services.
    public Proprietario buscar(Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietário não encontrado"));
    }

    /*
    * Métodos que serão usados pelo controlador.
    *
    * @Transactional (Do Spring) torna o método transacional,
    * sendo assim, acontece dentro de uma transação do banco de dados
    * e se alguma coisa der errado tudo é revertido (rollback).
    * */

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {

        /*
        * Regra de negócio para verificar se o e-mail já é utilizado por outro proprietário,
        * para validar do lado da aplicação e não deixar apenas a cargo do banco de dados.
        */

        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException(
                    String.format("Já existe um proprietário cadastrado com este e-mail%nEmail: %s",
                            proprietario.getEmail()
                    )
            );
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }
}
