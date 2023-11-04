package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        /*
        * Regras de negócio para inclusão de um novo veículo:
        * - Não deve ser informado identificador para novo veículo a ser criado.
        * - Proprietário relacionado na requisição para o novo veículo deve existir.
        * - Placa informada não deve estar em uso em outro veículo no banco de dados.
        */

        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(v -> !v.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }

        // Para verificar se o proprietário do veículo já existe (se sim, causará bad-request)
        // e preencher outras propriedades do proprietário no retorno (caso ok).
        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);

        /*
        * Propriedades Status, DataCadastro e Data Apreensão
        * não serão passadas no corpo da requisição,
        * porque serão processadas pelo backend antes de serem salvas.
        * Obs.: DataApreensao por padrão é nula.
        *
        * Propriedades do Proprietario passadas no corpo da requisição:
        * Apenas o Id do proprietário é necessário.
        */
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }

}
