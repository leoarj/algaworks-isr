package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Validações removidas da entity,
    // já que está utilizando representation model específico para input.

    // Relacionamento muitos-para-um
    @ManyToOne
    // Opcional, caso nome de coluna fosse diferente da convenção.
//    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    private String marca;
    private String modelo;
    private String placa;

    // Para gravar o literal do enum no banco de dados e não um código da posição.
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    // JPA indentifica camel-case automaticamente para snake-case do DB.
    // Utilizando OffsetDateTime para retornar com o offset (respeitando o padrão ISO 8601).
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    // Mapeamento inverso para as autuações do veículo
    // mappedBy = Qual propriedade na outra classe corresponde ao relacionamento.
    // CascadeType.ALL = Para aplicar em cascata todas as operações para a entidade relacionada.
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    /**
     * Regra de negócio específica do domínio para adicionar
     * uma nova autuação a um veículo.
     *
     * <br/> - Altera a data apropriada na autuação.
     * <br/>- Relaciona o veículo a autuação.
     * <br/>- Adiciona a autuação à lista de autuações do veículo.
     *
     * @param autuacao Autuação a ser relacionada ao veículo.
     * @return Autuação com data de ocorrência e veículo.
     */
    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }
}
