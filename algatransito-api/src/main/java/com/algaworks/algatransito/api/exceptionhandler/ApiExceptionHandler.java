package com.algaworks.algatransito.api.exceptionhandler;

import com.algaworks.algatransito.domain.exception.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
* Ponto único para tratamento de exceções de forma global.
*
* ResponseEntityExceptionHandler = estende essa classe que por padrão
* já trata as mensagens de erro de acordo com a RFC 7807.
* Ref.: https://www.rfc-editor.org/rfc/rfc7807
*/

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * Método para capturar exceções específicas de negócio,
     * a fim de poder retornar um código de status apropriado,
     * juntamente com mensagem de erro reduzida.
     *
     * Em resumo, especificar que o erro é do consumidor da API
     * e não do backend.
     *
     * @ExceptionHandler(Classe.class) = Indica que o método
     * é um capturador de erros no controlador de uma determinada exception.
     *
     * Aqui além de fornecer um status apropriado, também seria possível
     * registrar um log, notificações entre outras ações.
     */

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
