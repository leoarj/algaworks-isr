package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return proprietarioRepository.findAll();
        //return proprietarioRepository.findByNome("Proprietário 2");
        //return proprietarioRepository.findByNomeContaining("3");
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

    /*
    * Endpoint para criação de recurso.
    *
    * @PostMapping = Mapeamento para verbo POST.
    *
    * @ResponseStatus = Forma simplificada de especificar o código
    * de status caso a solicitação seja processada com sucesso (no caso
    * retornando o código 201-Created),
    *
    * @RequestBody = Vincula o corpo da requição ao argumento do método
    * (Serialização do JSON será para o objeto recebido como argumento).
    *
    * Retorna o próprio recurso com a chave primmária criada pela API.
    *
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Proprietario adicionar(@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    /*
    * Endpoint para atualização de recurso.
    *
    * @PutMapping = Mapeamento para o verbo PUT.
    *
    * Deve utilizar variável de caminho para indicar qual
    * o recurso único a ser atualizado.
    *
    * Deve realizar o bind entre a variável de caminho para
    * o identificador único do recurso (chave primária)
    * e o bind do corpo da requisição para o objeto recebido como argumento.
    *
    * Deve-se verificar se o recurso já existe ou não e após
    * isso atualizar o identificador no objeto recebido e em seguida
    * aplicar as alterações no banco de dados via repository.
    * 200 = Recurso atualizado.
    * 404 = Recurso não encontrado.
    */

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    /*
    * Endpoint para exclusão de recurso.
    *
    * @DeleteMapping = Mapeamento para o verbo DELETE.
    * 204 = Executado com sucesso, mas sem conteúdo de retorno.
    * Obs.: No caso do DELETE e outras operações que quando executadas
    * com sucesso mas não produzem conteúdo de retorno, essa é a convenção.
    *
    * 404 = Recurso não encontrado.
    */

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietarioRepository.deleteById(proprietarioId);
        return ResponseEntity.noContent().build();
    }

}
