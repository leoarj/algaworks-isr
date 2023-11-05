package com.algaworks.algatransito.api.exceptionhandler;

import com.algaworks.algatransito.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

/*
* Ponto único para tratamento de exceções de forma global.
*
* ResponseEntityExceptionHandler = estende essa classe que por padrão
* já trata as mensagens de erro de acordo com a RFC 7807.
* Ref.: https://www.rfc-editor.org/rfc/rfc7807
*/

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Para ter acesso ao recurso de mensagens personalizadas de validação (messages.properties).
    private final MessageSource messageSource;

    /*
    * Método para personalizar a resposta de acordo com a RFC 7807,
    * para a exceção de MethodArgumentNotValidException.
    */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://algatransito.com/erros/campos-invalidos"));

        // Extrai da exceção um mapa com os campos associados a suas respectivas mensagens de erro.
        // Com MessageSource: Recupera a mensagem específica de acordo com o code do objeto de erro.
        Map<String, String> fields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        //DefaultMessageSourceResolvable::getDefaultMessage));
                        objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        // Cria uma propriedade personalizada no corpo do detalhamento de erro
        // passando o mapeamento dos campos e mensagens de erro.
        problemDetail.setProperty("fields", fields);

        // Deve repassar uma instância de ProblemDetail (do pacote org.springframework.http)
        // para que a alteração tenha efeito.
        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

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
