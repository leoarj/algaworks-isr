package com.algaworks.algatransito.api.assembler.base;

import org.modelmapper.ModelMapper;
import java.util.List;

/*
 * Classe para encapsular o uso do model mapper e deixar o controller livre da dependência.
 * Também com o objetivo de prover reutilização de código das conversões de objetos.
 */

/**
 * Classe base para assemblers.
 * <br/><br/>
 * Estender essa classe para reaproveitar métodos de mapeamento dos objetos de
 * entidade, modelo de representação de entrada e modelo de representaçao de saída.
 *
 * @param <T> Tipo parametrizado para a classe da Entidade.
 * @param <U> Tipo parametrizado para a classe do modelo de representação de input de dados.
 * @param <V> Tipo parametrizado para a classe do modelo de representação de output de dados.
 */
public abstract class BaseAssembler<T, U, V> {

    private final ModelMapper modelMapper;

    public BaseAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public T toEntity(U inputModel) {
        return modelMapper.map(inputModel, getClassOfEntity());
    }

    public V toModel(T entity) {
        return modelMapper.map(entity, getClassOfOutputModel());
    }

    public List<V> toCollectionModel(List<T> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }

    /*
    * Para forçar o fornecimento da class de cada tipo no assembler concreto.
    */

    /**
     * Método para obter a class do tipo da entidade.<br/>
     * Utilizado no método toEntity(U inputModel).
     *
     * @return Class do tipo da entidade.
     */
    protected abstract Class<T> getClassOfEntity();

    /**
     * Método para obter a class do tipo do modelo de representação de saida.<br/>
     * Utilizado no método toModel(T entity).
     *
     * @return Class do tipo do modelo de representação de saída.
     */
    protected abstract Class<V> getClassOfOutputModel();
}
