package com.algaworks.algatransito.domain.exception;

/**
 * Classe para especializar exceção de entidade não encontrada,
 * para possibilitar melhor tratamento no exception handler da API.
 */
public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
