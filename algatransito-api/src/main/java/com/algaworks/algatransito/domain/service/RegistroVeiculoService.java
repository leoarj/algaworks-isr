package com.algaworks.algatransito.domain.service;

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

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
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
