package com.algaworks.algatransito.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* @RestController indica que a classe é um controlador para REST APIs.
* Já transforma a classe em um componente Spring.
*/

@RestController
public class TesteController {

    /*
    * @GetMapping realiza o mapeamento do método da classe
    * para uma requisição HTTP GET.
     */
    @GetMapping("/teste")
    public String testar() {
        return "Testando 123";
    }
}
