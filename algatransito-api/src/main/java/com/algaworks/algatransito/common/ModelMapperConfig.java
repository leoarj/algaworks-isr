package com.algaworks.algatransito.common;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* Classe para configurar a obtenção de um bean de ModelMapper.
*/

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        /*
        * Realiza um mapeamento personalizado da classe Veiculo para a classe VeiculoModel,
        * especificando propriedades que coincidem pelas estratégias
        * de matching da biblioteca de mapeamento.
        *
        * Para a classe Veiculo para a classe VeiculoModel
        * passa uma expressão lambda para que o mapeador em questão
        * faça um de-para de um getter específico da classe de origem
        * para um setter específico da classe de destino,
        * definindo assim o mapeamento personalizado.
        * */

        modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
                .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoModel::setNumeroPlaca));

        return modelMapper;
    }
}
