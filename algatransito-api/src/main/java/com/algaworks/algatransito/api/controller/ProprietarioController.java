package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor // Para criar construtor com todos os argumemtos.
@RestController
public class ProprietarioController {

    /*
    * Mapeamento para retornar uma coleção de recurso.
    * Será retornada alguma representação do recurso
    * (como o recurso será "visualizado"), passando por
    * uma transformação, como JSON, XML etc.
    */

    /*
    * Injetando repository que estende JpaRepository,
    * para encapsular o acesso a dados e disponibilidade de métodos padrão.
    *
    * Injetar com @Autowired = possível
    *
    * Injetar via construtor = Possíve e mais recomendado (boa prática).
    */

    //@Autowired
    private ProprietarioRepository proprietarioRepository;

//    public ProprietarioController(ProprietarioRepository proprietarioRepository) {
//        this.proprietarioRepository = proprietarioRepository;
//    }

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

}
