package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor // Para criar construtor com todos os argumemtos.
@RestController
// Para mapear a rota padrão dos endpoints e não ter que repetir em cada um.
@RequestMapping("/proprietarios")
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

    @GetMapping
    public List<Proprietario> listar() {
        //return proprietarioRepository.findAll();
        //return proprietarioRepository.findByNome("Proprietário 2");
        return proprietarioRepository.findByNomeContaining("3");
    }

    /*
    * Método para implementar endpoint de busca de recurso único.
    * Verbo GET com variável de caminho {proprietarioId}
    * que é vinculada ao argumento do método via anotação @PathVariable.
    *
    * Retornando um ResponseEntity para melhor manipulação do retorno,
    * como o código de status do HTTP.
    */

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
