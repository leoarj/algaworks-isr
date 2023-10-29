package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    /*
    * Mapeamento para retornar uma coleção de recurso.
    * Será retornada alguma representação do recurso
    * (como o recurso será "visualizado"), passando por
    * uma transformação, como JSON, XML etc.
    */

    /*
    * EntityManager é uma interface que é implementada pelo motor de acesso ao banco de dados (Hibernate)
    * e fornece acesso e manipulação dos objetos do banco de dados.
    *
    * A anotação @PersistenceContext diz ao Spring para injetar uma instânica de um EntityManager para uso.
    */
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {

        /*
        * Através do EntityManager é possível criar uma consulta tipada
        * e a partir dela obter objetos mapeados a partir banco.
        */

        return manager
                .createQuery("from Proprietario", Proprietario.class)
                .getResultList();

//        TypedQuery<Proprietario> query = manager.createQuery("from Proprietario", Proprietario.class);
//        return query.getResultList();
    }

}
