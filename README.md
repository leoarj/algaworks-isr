# ISR - Ignição Spring REST (algatransito-api)

Um curso inicial sobre o básico de Spring, que aborda um pequeno projeto de uma API REST para um sistema de autuações de trânsito.

O projeto consiste basicamente em definir 3 entidades no banco de dados e 4 recursos na API com seus endpoints executando diferentes ações utilizando a semântica do protocolo HTTP e seus verbos.

Este projeto aborda modelagem de operações CRUD e operações não-CRUD.

## Modelo de domínio (Banco de dados)
As 3 entidades são:
- **proprietario**
>Tabela para o armazenamento de informações de um proprietário.
- **veiculo**
>Tabela para o armazenamento de informações de um veículo.
- **autuacao**
>Tabela para o armazenamento de infrações de um determinado veículo.

## Recursos da API

### `/proprietarios`

Recurso para manutenção de proprietários de veículos no backend.

Documentação da API: https://documenter.getpostman.com/view/30781350/2s9YXo1f7V

### `/veiculos`

Recurso para manutenção de veículos no backend e sub-recurso de apreensão.

#### `/veiculos/{veiculoId}/apreensao`
Sub-recurso para manutenção de aprensão relacionada a um veículo no backend.

Documentação da API: https://documenter.getpostman.com/view/30781350/2s9YXo1f7X

#### `/autuacoes/{veiculoId}/autuacoes`

Sub-recurso para manutenção de autuações relacionadas a um veículo no backend.

Documentação da API: https://documenter.getpostman.com/view/30781350/2s9YXo1f7Y

## Tecnologias e ferramentas utilizadas
- Plataforma: Java 17 (LTS) - OpenJDK Temurin
- Spring:
    - Spring Framework
        - Jakarta Bean Validation (Hibernate Validators)
    - Spring Web
    - Spring Data JPA
    - Spring Boot DevTools
- Suporte a métodos padrão: Lombok
- Versionamento de DB e migrações: Flyway
- Mapeamento DTO: ModelMapper
- Build e empacotamento: Maven
- DB: MySQL Community 8
- IDE: IntelliJ
- Testes e doc. de API: Postman
- Versionamento: Git
    - Modelo de braching: Git Flow

## Como Executar
### Localmente
- Clonar repositório git
- Construir o projeto:
```console
./mvnw clean package
```
- Executar:
```console
java -jar algatransito-api/target/algatransito-api-0.0.1-SNAPSHOT.jar
```
- Acesso:
```console
localhost:8080
```

## Resumo de alguns tópicos abordados

### API
*Application Programming Interface.*

APIs se baseiam no conceito básico de haver:

*Software consumidor -> **API** <- Software Provedor*

*Softwares **consumidores** que se comunicam com um contrato padronizado e de outro lado há um ou vários sistemas **provedores** da API que respondem pelo contrato padronizado.*

### WebServices vs APIs

**API** = É um termpo bem amplo, onde não temos apenas o conceito do WEB-APIs mas APIs a nível de sistema operacional (OS), SDKs, frameworks.

No caso de uma API WEB, também pode ser considerada um **WebService**, já que expôe sua **inteface** pela WEB.

### REST = *Representational State Transfer*

Em resumo é uma especificação que define a forma de comunicação entre sistemas na WEB.

REST possui **Constraints**, que são as restrições, regras, melhores práticas.

REST API = Uma WEB API que segue as regras:

- Separação entre cliente e servidor.
- Escalabilidade.
- Independência de linguagem.
- Mercado (integração entre informações)

#### Constraints
- Cliente-servidor
>Consumidor (client) e servidor (API), podem evoluir separadamente, sem dependência.

- Stateless
>Sem estado, não deve possuir estado.

- Cache
>Respostas pode ser cacheadas.

- Interface uniforme
>(Operações bem definidas) URIs bem definidas.

- Sistema em camadas
>Possibilidade de existirem outros sistema no meio de forma transparente (cliente não deve necessariamente saber disso).

- Código sob demanda
>Retornar código que pode ser executado no client (pouco utilizado).

## Protocolo HTTP
O modelo arquitetural REST independe de tecnologia.
Mas para ser colocado em prática necessita de um protocolo, e nesse caso HTTP é o mais comum.

### HTTP:
Requisição-Resposta

- Requisição
    - Método (Vebo) = Ação a ser executada.
    - URI = /recurso.
    - HTTP/Versão.
    - Cabeçalhos = Informações sobre a requisição.
    - Corpo/PayLoad = Depende do método.

- Resposta
    - HTTP/Versão.
    - Status.
    - Cabeçalhos.
    - Corpo.

#### Classes Códigos de Status HTTP
- 1xx - Informacional
- 2xx - Sucesso
- 3xx - Redirecionamento
- 4xx - Erros do cliente
- 5xx - Erros do servidor

#### Content negotiation
É o meio de um client e um server negociarem o formato de representação dos recursos por meio de um header (cabeçalho) na requisição.

Podem haver vários tipo de representação para um único recurso (JSON, XML etc).

Exemplo:
```console
Accept application/json
```

Segue o padrão dos headers:
- Headers
    - Key
    - Value

## Recursos REST
Um recurso é qualquer "coisa" exposta via uma API REST.

Entende-se que um recurso não representa necessariamente uma entidade de banco de dados exposta pelo backend, mas um ou mais agrupamento de recursos que interagem por meio de diferentes representações de dados reais, muitas vezes não expondo sua real estrutura para os consumidores de API.

### Tipos de recursos
- Singleton resource
>Recurso único, geralmente possui um identificador pelo qual pode ser buscado.

- Collection resource
>Coleção de recursos, que podem ser obtidos ou não mediante um identificador informado.

#### Modelo de Representação
Representa o estado do recurso (corpo JSON), não é o recurso (é a transformação do resultado, poderia ser JSON ou XML entre outros formatos).

### Identificando recursos
#### URI Uniform Resource Identifier
É um endereço (conjunto de caracteres) para identificar recursos de forma não ambígua.

#### URI vs URL

- URL = Um tipo de URI.
>Não apenas o identificador, mas a localização (como ao chegar), mecanismo para acessar.

Exemplo:
```console
/listarProdutos = URI
https://...../listarProdutos = URL
```
#### Boas práticas de nomeação:
- Sempre um substantivo (Coisas possuem propriedades, verbos não).

Exemplo:
```console
/produtos
```

Exemplo (singular, fora da convenção):
```console
/produto/{codigo} -> recurso único
https://...../produto/331
```

- Consenso = sempre no plural.

Exemplo:
```console
/produtos/331
```

### Métodos e códigos de status HTTP no contexto de recursos
Métodos/vebos = Ditam a semântica da operação (tipo da operação).

- GET = Obter representação de recurso.
- POST = Criar novo recurso.
- PUT = Autualizar/colocar recurso.
- DELETE = Destruir recurso.

>Verbos indempotentes (GET, PUT, DELETE).
Uma operação indempontente se refere à uma ação que executadas inúmeras vezes sempre manterá o estado no servidor).
O código de status retornado pode variar, mas o resultado da operação desde a primeira e seguidas requisições permanece o mesmo.

#### Boas práticas

##### Implementando endpoint de criação de recurso
Utiliza-se POST, ou seja, quando deixa a cargo da API como vai ser a URI do recurso e geração do identificador único.

Código de status HTTP (sucesso) = 201 - CREATED.

##### Implementando endpoint de atualização de recurso
Utiliza-se PUT, ou seja, quando já se sabe a URI do recurso e para modificar informações em recurso existente.

Código de status HTTP (sucesso) = 200 - OK.

##### Implementando endpoint de exclusão de recurso
Utiliza-se DELETE para destruir um recurso, passando sua identificação a partir de uma URI.

Código de status HTTP (sucesso) = 204 - NO CONTENT (Operações executadas com sucesso mas que não retornam um conteúdo, que é o caso do DELETE).

## Spring REST
Termo para dizer que tem uma REST API desenvolvida com Spring.

Spring = Ecossistema de tecnologias.

O objetivo é focar nas regras de negócio, e não se preocupar demasiadamente com infra-estrutura.

>*Não que isso não seja importante, pelo contrário, é ideal conhecer as tecnologias que o Spring trabalha por "baixo" das abstrações, mas uma vez tendo esse entendimento, é muito válido prosseguir utilizando uma tecnlogia sólida com abstrações que facilitam o uso e principalmente não "reinventar a roda", além de ganhar velocidade e melhor entrega de valor para desenvolvedores e usuários finais de sistemas.*

Spring Framework = base/core do projeto Spring.

Spring possui diversos projetos e subprojetos.

Um deles que é utilizado no projeto é o **Spring Boot**, que é focado na autoconfiguração e facilidade de se iniciar um projeto com suas dependências necessárias.

## Flyway
É um projeto que permite o gerenciamento dos objetos do banco de dados, como o versionamento e manutenção de migrações (Migrations), controlando a execução de scripts.

## Criando migração
- resources
    - db.migration
        - nome arquivo: V00X__descricao-migracao.sql

Erro de checksum: Deletar linha de histórico.
Obs.: Somente durante o desenvolvimento.

## JPA - Jakarta Persistence

Especificação do Java para persistência de dados.

Implementação utilizada no projeto foi o Hibernate.

### Spring Data JPA
Projeto do ecossistema Spring que cria uma camada de abstração facilitando ainda mais o uso do JPA.

Não é uma boa prática utilizar Jakarta Persistence diretamente em um controlador (visando manutenibilidade).

#### Anotações básicas
`@Repository`
>Marca uma classe como um componente de acesso a dados.

## Jarkarta Bean Validation
Especificação para validação de objetos.

Hibernate Validators = Implementação de referência.

### Validação de entrada de dados
- Anotar propriedades da classe de modelo e/ou representação com as anotações do Jakarta Bean Validation.
- Adicionar `@Valid` no argumento do endpoint no controlador, para dizer que o objeto precisa ser validado.

## Implementando Domain Services
Domain Services encapsulam Regras de negócio.

Regras de negócio que envolvem coisas mais simples e que somente se referem à entidade (regras específicas para deixar o modelo rico), então podem ser implementadas na própria entidade.

Quando envolve processos mais complexos e mais classes,
então é criada uma classe de serviço.

### Anotações básicas
`@Service`
>Anota a classe como um componente, com semântica de que é um serviço.

## Captura de erros do controlador com `@ExceptionHandler`
Método para capturar exceções específicas e com isso poder retornar código de status apropriado juntamente com mensagem reduzida de erro.

### Boas práticas
`@RestControllerAdvice`
>Anota uma classe como um componente do Spring para tratamento centralizado de exceções.

### Objetivo
- Tratar exceções globais, independente do controller que lançou.
- Evitar duplicação de código para tratamento de exceções.

## RFC 7807 (Problem Details for HTTP APIs)
Especificação que padroniza o retorno de descrição de erros para APIs que utilizam HTTP.

O que foi aplicado neste projeto em relação a essa RFC:
- Customização das informações do ProblemDetail.
- Campos customizados.
- Mensagens de validação customizadas.
- Tratamento de exceções customizadas de forma global.

Mais informações: https://www.rfc-editor.org/rfc/rfc7807

## ISO 8601 (Boas práticas com Data e Hora)
A ISO 8601 padroniza os formatos de data e hora mundialmente, estabelecento um padrão uniforme de representação de objetos temporias.

É considerada uma boa prática retornar as datas com offset (Deslocamento de tempo em relação ao UTC), para quem for consumir a API saber o deslocamento de tempo e poder processar corretamente o que for necessário em sua hora local.

- Exemplos válidos
    - 2023-11-06T21:10:00-03:00
    - 2023-11-07T00:10:00-00:00
    - 2023-11-07T00:10:00Z

Classe utilizada na implementação: `java.time.OffsetDateTime`.

Mais informações: https://www.iso.org/iso-8601-date-and-time-format.html

## Resource Representation Model
É uma boa prática separar o Domain Model do Representation model.

Existem vários motivos, como:
- Não expor todas as propriedades do modelo.
- Evitar quebra da API caso alguma coisa seja alterada no modelo.
- Criar uma representação mais simples do recurso e não ter que alterar o modelo.
- Criar uma representação com agrupamento de propriedades de um ou mais recursos.
- Separar a estrutura do modelo de sua representação em si, que às vezes pode ser exatamente igual ou outras vezes ser diferente.

Para isso, é utilizado o padrão **DTO** *(Data Transfer Object)*.

## ModelMapper
Biblioteca para automatizar o mapeamento do domain model para o representation model.

## Assembler
Classes para encapsular o uso do model mapper e deixar o controller livre dessa dependência,
além de reaproveitamento de código de conversão dos objetos.

## Composição de objetos no Representation Model
É possível criar uma representação resumida de um recurso para ser usada em outra representação de outro recurso.

Exemplo (request-body para inclusão de Veículo):
```json
{
    "proprietario": {
        "id": 1
    },
    "marca": "Volkswagen",
    "modelo": "VOYAGE",
    "placa": "PPP0000"
}
```
> Onde o objeto `proprietario` é uma representação resumida do proprietário somente com o ID (necessário apenas para criação do recurso de véiculo e vinculação com o proprietário).

## Modelo de representação de entrada de dados
São modelos personalizados referentes aos recursos, onde se determina apenas as propriedades necessárias para inclusão/alteração de um recurso.

Por via de regra, esses modelos repetem as validações das entidades (preferível).

Essas validações podem ser removidadas das entidades, mas deve-se ter certeza de que o consumo do sistema será exclusivamente pela interface da API WEB(endpoints) definidos.

É considerada uma boa prática já que se garante expor apenas a estrutura necessária e ajuda a remover a necessidade de criar algumas validações adicionais caso estivesse utilizando diretamente a entidade.

## Modelando sub-recursos
Realiza-se a modelagem de um recurso como sub-recurso quando o mesmo possui forte relacionamento com outro e que só existe enquanto o outro recurso existir.

Exemplo:
```console
recurso/1/subrecurso
```

No caso o recurso de **Autuacões** foi modelado como um sub-recurso de **Veículo**, já que uma autação só pode existir **se um veículo exitir**.

No caso o recurso de **Apreensão** foi modelado como um sub-recurso de **Veículo**, já que a apreensão aplica modificações diretas no recurso de *Veículo*.

Dispensa também ter que passar o **id** do recurso principal no corpo da requisição.

## Endpoints não-CRUD

Deve-se procurar modelar conceitos *abstratos* de negócio como recursos na API.

Um exemplo foi o recurso de **Apreensão**, em que:
- Chamada PUT em `/veiculos/{veiculoId}/apreensao` torna o status de um veículo como **APREENDIDO** e registra a **data de apreensão**.
- Chamada DELETE em `/veiculos/{veiculoId}/apreensao` torna o status de um veículo como **REGULAR** e remove a **data de apreensão**.

Em relação ao sub-recurso de `/apreensao`, ele foi nomeado no singular porque representa uma ação única para o recurso de `/veiculos`, porém, apesar de ser uma ação, não foi nomeada como `/apreender` para não perder a semântica de que é um recurso e que possui propriedades, por ser um substativo.

Obs.: Sugestão de retorno quando veículo já estiver ou não apreendido = 404 - CONFLICT.

____

## Considerações finais
Este é um projeto básico e introdutório à construção de APIs REST com Spring.

Não foram implementados recursos de segurança entre outras funcionalidades.

### Melhorias futuras pretendidas:
- Persistência com Postgres.
- Geração de imagem docker.

____
