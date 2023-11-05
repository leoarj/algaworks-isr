package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
* Anotações do Lombok ajudam em tempo de desenvolvimento a reduzir
* código repetitivo padrão (boilerplate).
* No caso está habilitando getters e setters para os atributos da classe.
*/

@Getter
@Setter
// Implementa equals() e hashCode() para apenas atributos explícitamente marcados.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Diz que a classe é um mapeamento para uma entidade.
@Entity
//@Table(name = "tb_proprietario") // Caso o nome da tabela no db fosse diferente.
public class Proprietario {

    @NotNull(groups = ValidationGroups.ProprietarioId.class)
    // Inclui id para as lógicas de equals() e hashCode().
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * Anotações do Jakarta Bean Validation para validar propriedades do bean.
    *
    * Com essas validações o código de status da resposta pode ser mudado para
    * 400-Bad request, fazendo mais sentido.
    *
    * Com essas validações do lado da aplicação, o erro não chega chega nem ao banco de dados.
    *
    * Algumas anotações:
    * @NotBlank = Conteúdo não nulo, não vazio.
    *
    * @Size = Tamanho do campo
    *   max = Tamanho máximo do campo
    *
    * @Email = Anotação pronta para validar padrão de e-mail.
    * */

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    // Mapeia propriedade "telefone" para a coluna "fone", @Column utilizada
    // justamente porque o nome difere no mapeamento.
    @Column(name = "fone")
    private String telefone;
}