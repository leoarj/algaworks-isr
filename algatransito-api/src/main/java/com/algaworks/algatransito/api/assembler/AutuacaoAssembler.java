package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

//@AllArgsConstructor
@Component
public class AutuacaoAssembler extends BaseAssembler<Autuacao, AutuacaoInput, AutuacaoModel> {

    public AutuacaoAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Class<Autuacao> getClassOfEntity() { return Autuacao.class; }

    @Override
    protected Class<AutuacaoModel> getClassOfOutputModel() {
        return AutuacaoModel.class;
    }
}
