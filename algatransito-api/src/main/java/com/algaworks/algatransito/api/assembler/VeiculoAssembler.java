package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.assembler.base.BaseAssembler;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VeiculoAssembler extends BaseAssembler<Veiculo, VeiculoInput, VeiculoModel> {
    public VeiculoAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Class<Veiculo> getClassOfEntity() {
        return Veiculo.class;
    }

    @Override
    protected Class<VeiculoModel> getClassOfOutputModel() {
        return VeiculoModel.class;
    }
}
