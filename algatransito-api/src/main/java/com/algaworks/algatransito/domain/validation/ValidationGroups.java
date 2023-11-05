package com.algaworks.algatransito.domain.validation;

/*
* Interface para agrupar grupos de validação.
*
* Será utilizada nas anotações do Jakarta Bean Validation
* e principalmente na @ConvertGroup(from = Default.class, to = GrupoCustomizado.class).
*/

public interface ValidationGroups {

    public interface ProprietarioId {}
}
