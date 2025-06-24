# Documentação Técnica e Funcional - Projeto "Simple"

## Parte 1 – Especificação Técnica

### 1. Arquitetura da Aplicação

A aplicação "Simple" é construída utilizando uma arquitetura de microsserviços ou modularizada, com base no framework Java Spring Boot. A estrutura do código sugere uma separação clara de responsabilidades por módulos de domínio (`configuracoes`, `pedidos`, `utente`).

**Padrões Utilizados:**
*   **MVC (Model-View-Controller):** Utilizado pelo Spring Boot para a separação de responsabilidades na camada de apresentação (embora seja uma API REST, o padrão é conceitualmente aplicado).
*   **Repository Pattern:** Para abstrair o acesso a dados, com interfaces como `CategoriasServicosRepository`, `PedidosRepository`, etc.
*   **DTO (Data Transfer Object):** Usado extensivamente para transferir dados entre camadas, especialmente para entrada e saída de dados nos endpoints REST (e.g., `CategoriasServicosResponseDTO`, `CreatePedidoDTO`).
*   **CQRS (Command Query Responsibility Segregation):** A aplicação utiliza `CommandBus` e `QueryBus` para separar operações de escrita (Commands) das operações de leitura (Queries). Isso é evidente nos controladores que injetam e utilizam esses buses.
*   **Dependency Injection:** Gerenciado pelo Spring Framework.
*   **RESTful API:** Os serviços são expostos através de endpoints REST.

**Pacotes Principais (Estrutura Modular):**
O projeto está organizado em módulos principais, cada um contendo suas próprias camadas de aplicação, domínio e infraestrutura:
*   `cv.igrp.simple.configuracoes`: Módulo para gerenciamento de configurações gerais do sistema, como categorias de serviços, tipos de serviços e status de pedidos.
    *   `application`: Contém DTOs, comandos, queries e seus handlers.
    *   `domain`: Contém as entidades JPA (`models`) e possivelmente lógica de domínio.
    *   `infrastructure`: Contém controladores REST (`controller`) e implementações de repositórios (`persistence`).
*   `cv.igrp.simple.pedidos`: Módulo para o gerenciamento de pedidos, incluindo seu ciclo de vida, documentos, pagamentos e avaliações.
    *   Estrutura similar ao módulo `configuracoes`.
*   `cv.igrp.simple.utente`: Módulo para o gerenciamento de utentes (usuários/cidadãos) e seus serviços associados.
    *   Estrutura similar ao módulo `configuracoes`.
*   `cv.igrp.simple.shared`: Contém código compartilhado entre os módulos.
    *   `config`: Configurações globais como auditoria (`AuditEntity`, `ApplicationAuditorAware`).
    *   `security`: Configuração de segurança da aplicação (`SecurityConfig`).
    *   `domain`: Exceções de domínio compartilhadas, eventos.
    *   `infrastructure`: Componentes de infraestrutura compartilhados.

A presença de arquivos `.igrpstudio` sugere o uso de uma ferramenta específica (IGRP Studio) que pode ter influenciado a estrutura e geração de código inicial.

### 2. Tecnologias e Bibliotecas Utilizadas

*   **Linguagem:** Java 21
*   **Framework Principal:** Spring Boot 3.4.5
    *   `spring-boot-starter-web`: Para aplicações web e RESTful APIs.
    *   `spring-boot-starter-data-jpa`: Para persistência de dados com JPA (Java Persistence API).
    *   `spring-boot-starter-data-rest`: Para expor rapidamente repositórios JPA como endpoints REST (usado com `detection-strategy=annotated`).
    *   `spring-boot-starter-validation`: Para validação de DTOs e entidades.
    *   `spring-boot-starter-security`: Para funcionalidades de segurança.
    *   `spring-boot-starter-oauth2-resource-server`: Para configurar a aplicação como um servidor de recursos OAuth2 (validação de tokens JWT).
    *   `spring-boot-starter-oauth2-client`: Para funcionalidades de cliente OAuth2.
    *   `spring-boot-starter-actuator`: Para monitoramento e gerenciamento da aplicação (endpoints como `/health`, `/metrics`).
*   **ORM:** Hibernate (implementação JPA padrão do Spring Data JPA).
    *   Hibernate Envers (`spring-data-envers`): Para auditoria e versionamento de entidades.
*   **Banco de Dados:** PostgreSQL (driver `org.postgresql:postgresql`).
*   **Documentação da API:** Springdoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` versão 2.8.5) para geração de documentação Swagger.
*   **Utilitários:**
    *   Lombok (`org.projectlombok:lombok`): Para reduzir código boilerplate (e.g., getters, setters, construtores).
    *   Apache Commons Lang3 (`org.apache.commons:commons-lang3`): Biblioteca de utilitários.
*   **Serialização/Deserialização:**
    *   Jackson (padrão do Spring Boot para JSON).
    *   Jackson Dataformat XML (`com.fasterxml.jackson.dataformat:jackson-dataformat-xml`): Para manipulação de XML.
*   **Framework Específico (IGRP):**
    *   `cv.igrp.framework:core`: Núcleo do framework IGRP.
    *   `cv.igrp.framework:stereotype`: Estereótipos do framework IGRP (e.g., `@IgrpEntity`, `@IgrpController`).
    *   `cv.igrp.platform:file-manager-core` e `cv.igrp.platform:file-manager-minio`: Sugerem integração com um sistema de gerenciamento de arquivos, possivelmente usando MinIO.
*   **Observabilidade:**
    *   Micrometer (`io.micrometer:micrometer-registry-prometheus`): Para exportar métricas para Prometheus.
    *   Micrometer Tracing (`io.micrometer:micrometer-tracing-bridge-otel`): Bridge para OpenTelemetry.
    *   OpenTelemetry Exporter Zipkin (`io.opentelemetry:opentelemetry-exporter-zipkin`): Para exportar traces para Zipkin (ou compatíveis como Tempo).
*   **Testes:**
    *   `spring-boot-starter-test`: JUnit, Mockito, Spring Test.
    *   `spring-security-test`: Para testes de segurança.
*   **Build Tool:** Maven
*   **Containerização:** Docker (presença de `Dockerfile` e `docker-compose.yml`).
*   **Orquestração:** Kubernetes (presença de arquivos na pasta `k8s/`).
*   **Monitoramento (Infraestrutura):** Prometheus, Promtail, Tempo (configurações na pasta `monitoring/`).

### 3. Entidades JPA

Todas as entidades herdam de `cv.igrp.simple.shared.config.AuditEntity`, que fornece campos de auditoria (`createdBy`, `createdDate`, `lastModifiedBy`, `lastModifiedDate`). Muitas entidades também são anotadas com `@IgrpEntity`.

**Módulo `configuracoes`:**

*   **`CategoriasServicosEntity` (`cm_categorias_servicos`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `nome` (String, 100, NotNull): Nome da categoria.
    *   `descricao` (String): Descrição da categoria.
    *   `icone` (String, 50): Ícone associado.
    *   `cor` (String, 20): Cor associada.
    *   `ordem` (Integer, NotNull): Ordem de exibição.
    *   `ativo` (Boolean, NotNull): Indica se a categoria está ativa.

*   **`ConfiguracoesEntity` (`cm_configuracoes`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `chave` (String, 100, NotNull): Chave da configuração.
    *   `valor` (String, NotNull): Valor da configuração.
    *   `descricao` (String): Descrição da configuração.
    *   `tipo` (String, 20, NotNull): Tipo de dado da configuração (e.g., TEXT, NUMBER, BOOLEAN).
    *   `grupo` (String, 50, NotNull): Grupo ao qual a configuração pertence.
    *   `editavel` (Boolean, NotNull): Indica se a configuração pode ser editada via UI.
    *   `estado` (Enum `Estado`): Estado da configuração (e.g., ATIVO, INATIVO).

*   **`StatusPedidoEntity` (`cm_status_pedido`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `codigo` (String, 20, NotNull): Código do status.
    *   `nome` (String, 100, NotNull): Nome do status.
    *   `descricao` (String): Descrição do status.
    *   `cor` (String, 20): Cor associada ao status.
    *   `icone` (String, 50): Ícone associado ao status.
    *   `ordem` (Integer, NotNull): Ordem de exibição/processamento.
    *   `visivelPortal` (Boolean, NotNull): Indica se o status é visível no portal do utente.

*   **`TiposServicosEntity` (`cm_tipos_servicos`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `categoriaId` (Integer, NotNull): FK para `CategoriasServicosEntity.id`.
    *   `codigo` (String, 20, NotNull): Código do tipo de serviço.
    *   `nome` (String, 100, NotNull): Nome do tipo de serviço.
    *   `descricao` (String): Descrição.
    *   `prazoEstimado` (Integer): Prazo estimado para conclusão (em dias/horas).
    *   `valorBase` (Double): Valor base do serviço.
    *   `requerVistoria` (Boolean, NotNull): Indica se requer vistoria.
    *   `requerAnaliseTec` (Boolean, NotNull): Indica se requer análise técnica.
    *   `requerAprovacao` (Boolean, NotNull): Indica se requer aprovação.
    *   `disponivelPortal` (Boolean, NotNull): Indica se está disponível no portal do utente.
    *   `ativo` (Boolean, NotNull): Indica se o tipo de serviço está ativo.
    *   **Relacionamentos:**
        *   `@ManyToOne` `categoria` (LAZY) com `CategoriasServicosEntity` (join em `categoria_id`).

**Módulo `pedidos`:**

*   **`AvaliacoesPedidosEntity` (`cm_pedidos_avaliacoes`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `pedidoId` (Integer, NotNull, Unique): FK para `PedidosEntity.id`. Indica que um pedido só pode ter uma avaliação.
    *   `nota` (Integer, NotNull): Nota da avaliação (e.g., 1 a 5).
    *   `comentario` (String, 500): Comentário da avaliação.
    *   `dataAvaliacao` (LocalDate, NotNull): Data da avaliação.
    *   `userId` (UUID, NotNull): ID do usuário que fez a avaliação.

*   **`DocumentosPedidosEntity` (`cm_pedidos_documentos`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `pedidoId` (Integer, NotNull): FK para `PedidosEntity.id`.
    *   `nome` (String, 100, NotNull): Nome do documento.
    *   `descricao` (String): Descrição do documento.
    *   `tipoDocumento` (String, 50, NotNull): Tipo do documento (e.g., PDF, JPG).
    *   `caminhoArquivo` (String, NotNull): Caminho ou referência para o arquivo (possivelmente no MinIO).
    *   `tamanhoArquivo` (Integer): Tamanho do arquivo em bytes.
    *   `dataUpload` (LocalDate, NotNull): Data do upload.
    *   `userId` (UUID, NotNull): ID do usuário que fez o upload.

*   **`EtapasPedidosEntity` (`cm_pedidos_etapas`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `codigo` (String, 20, NotNull, Unique): Código da etapa.
    *   `nome` (String, 100, NotNull): Nome da etapa.
    *   `descricao` (String): Descrição da etapa.
    *   `ordem` (Integer, NotNull, Default '0'): Ordem da etapa no fluxo.
    *   `tipoServicoId` (Integer): FK (implícita) para `TiposServicosEntity.id`, indicando se a etapa é específica de um tipo de serviço.
    *   `prazoEstimado` (Integer): Prazo estimado para esta etapa.
    *   `requerAprovacao` (boolean, NotNull, Default 'false'): Indica se esta etapa requer aprovação.
    *   `ativo` (boolean, NotNull, Default 'true'): Indica se a etapa está ativa.

*   **`HistoricoPedidosEntity` (`pedidos_historico`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `pedidoId` (Integer, NotNull): FK para `PedidosEntity.id`.
    *   `statusId` (Integer, NotNull): FK para `StatusPedidoEntity.id`.
    *   `etapaId` (Integer): FK para `EtapasPedidosEntity.id`.
    *   `userId` (UUID, NotNull): ID do usuário que causou a alteração.
    *   `dataRegistro` (LocalDate, NotNull): Data da alteração.
    *   `observacao` (String): Observações sobre a alteração.

*   **`PagamentosPedidosEntity` (`cm_pedidos_pagamentos`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `pedidoId` (Integer): FK para `PedidosEntity.id`.
    *   `dataPagamento` (LocalDate, NotNull): Data do pagamento.
    *   `metodoPagamento` (String, 50, NotNull): Método de pagamento (e.g., Cartão, Transferência).
    *   `referenciaPagamento` (String, 100): Referência do pagamento (e.g., ID da transação).
    *   `status` (String, 20, NotNull): Status do pagamento (e.g., PAGO, PENDENTE).
    *   `observacao` (String): Observações.
    *   `valor` (Double): Valor pago.

*   **`PedidosEntity` (`cm_pedidos`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `codigoAcompanhamento` (String, 20, NotNull, Unique): Código para acompanhamento público do pedido.
    *   `tipoServicoId` (Integer, NotNull): FK para `TiposServicosEntity.id`.
    *   `cidadaoId` (Integer, NotNull): FK para `UtenteEntity.id` (o solicitante).
    *   `userCriacaoId` (Integer, NotNull): ID do usuário (interno ou cidadão) que criou o pedido.
    *   `userResponsavelId` (Integer): ID do usuário interno responsável pelo atendimento do pedido.
    *   `etapaAtualId` (Integer): FK para `EtapasPedidosEntity.id` (etapa atual do pedido).
    *   `statusId` (Integer, NotNull): FK para `StatusPedidoEntity.id` (status atual do pedido).
    *   `dataInicio` (LocalDate, NotNull): Data de criação do pedido.
    *   `dataPrevisao` (LocalDate): Data prevista para conclusão.
    *   `dataConclusao` (LocalDate): Data de conclusão efetiva.
    *   `origem` (String, 20, NotNull): Origem do pedido (e.g., PORTAL, PRESENCIAL).
    *   `prioridade` (Integer, NotNull, Default '0'): Nível de prioridade.
    *   `observacao` (String): Observações gerais sobre o pedido.
    *   `valorTotal` (Double): Valor total do pedido.

**Módulo `utente`:**

*   **`TipoUtenteEntity` (`cm_tp_utente`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `codigo` (String, NotNull): Código do tipo de utente (e.g., CIDADAO, EMPRESA).
    *   `descricao` (String): Descrição do tipo de utente.

*   **`UtenteEntity` (`cm_utente`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `tipoUtente` (Enum `TipoUtente`): Tipo de utente (e.g., SINGULAR, COLETIVO).
    *   `tipoIdentificacao` (Enum `TipoIdentificacao`): Tipo de documento de identificação (e.g., BI, CNI, PASSAPORTE).
    *   `identificacao` (String): Número da identificação.
    *   `numero` (String): Número do utente (pode ser um número de registro).
    *   `nome` (String): Nome completo ou razão social.
    *   `nif` (String): Número de Identificação Fiscal.
    *   `email` (String): Endereço de email.
    *   `dataNascimento` (LocalDate, NotNull): Data de nascimento ou de constituição.
    *   `nomeMae` (String, NotNull): Nome da mãe (para pessoa física).
    *   `nomePai` (String, NotNull): Nome do pai (para pessoa física).
    *   `endereco` (String, NotNull): Endereço completo.
    *   `telefone` (String, NotNull): Número de telefone principal.
    *   `estado` (Enum `Estado`): Estado do utente (e.g., ATIVO, INATIVO, PENDENTE).
    *   `caixaPostal` (String): Caixa postal.
    *   `departamentoResponsavel` (String): Departamento responsável (para pessoa coletiva).
    *   `telemovel` (String): Número de telemóvel.
    *   `genero` (Enum `GeneroTipo`): Gênero (para pessoa física).
    *   `nacionalidade` (String): Nacionalidade.
    *   **Relacionamentos:**
        *   `@OneToMany` `servicos` (LAZY, CascadeType.ALL) com `UtenteServicoEntity` (mapeado por `utenteId` em `UtenteServicoEntity`).

*   **`UtenteServicoEntity` (`cm_utente_objeto`)**
    *   `id` (Integer, PK, Identity): Identificador único.
    *   `objetoTipo` (String): Tipo do objeto/serviço associado (e.g., CONTRATO, LICENCA).
    *   `objetoId` (Integer): ID do objeto/serviço na sua respectiva tabela (se aplicável).
    *   `descricao` (String): Descrição do serviço/objeto.
    *   `referencia` (String): Referência do serviço/objeto.
    *   `dataInicio` (LocalDate): Data de início da validade/prestação.
    *   `dataFim` (LocalDate): Data de fim da validade/prestação.
    *   `valor` (BigDecimal): Valor associado.
    *   `estado` (Enum `Estado`): Estado do serviço/objeto para o utente.
    *   `utenteId` (UtenteEntity): FK para `UtenteEntity.id`.
    *   **Relacionamentos:**
        *   `@ManyToOne` `utenteId` (LAZY) com `UtenteEntity`. Auditado com `RelationTargetAuditMode.NOT_AUDITED` para não auditar o utente ao auditar esta entidade.

### 4. Endpoints REST Disponíveis

A aplicação expõe diversos endpoints REST, organizados por módulos e versionados (`/v1/`). Utiliza CQRS, com `CommandBus` e `QueryBus`.

**Módulo `configuracoes`:**

*   **`CategoriasServicosController` - Base URL: `/configuracoes/v1/categorias-servicos`**
    *   `POST /`: Cria uma nova categoria de serviço.
        *   Entrada: `CreateCategoriasServicosCommand`.
        *   Saída: ID da nova categoria (Integer), HTTP 201.
    *   `PUT /{id}`: Atualiza uma categoria de serviço existente.
        *   Entrada: `UpdateCategoriasServicosCommand`, `id` (Path).
        *   Saída: HTTP 204.
    *   `DELETE /{id}`: Inativa (soft delete) uma categoria de serviço.
        *   Entrada: `id` (Path).
        *   Saída: HTTP 204.
    *   `GET /{id}`: Obtém uma categoria de serviço pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `CategoriasServicosResponseDTO`, HTTP 200.
    *   `GET /`: Lista categorias de serviço com paginação e filtros.
        *   Entrada: `nome` (QueryParam), `ativo` (QueryParam), `Pageable`.
        *   Saída: `Page<CategoriasServicosResponseDTO>`, HTTP 200.

*   **`ConfiguracoesController` - Base URL: `/configuracoes/v1` (Nota: o path é `/configuracoes/v1`, não `/configuracoes/v1/configuracoes`)**
    *   `GET /`: Lista configurações do sistema com paginação e filtros.
        *   Entrada: `chave`, `grupo`, `tipo`, `estado` (QueryParams), `Pageable`.
        *   Saída: `Page<ConfiguracoesResponseDTO>`, HTTP 200.
    *   `GET /{id}`: Obtém uma configuração pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `ConfiguracoesResponseDTO`, HTTP 200.
    *   `POST /`: Cria uma nova configuração.
        *   Entrada: `CriarConfiguracoesDTO`.
        *   Saída: `ConfiguracoesResponseDTO`, HTTP 201.
    *   `PUT /{id}`: Atualiza uma configuração existente.
        *   Entrada: `UpdateConfiguracoesDTO`, `id` (Path).
        *   Saída: `ConfiguracoesResponseDTO`, HTTP 200.
    *   `DELETE /{id}`: Inativa (soft delete) uma configuração.
        *   Entrada: `id` (Path).
        *   Saída: String (mensagem de sucesso), HTTP 200.

*   **`StatusPedidoController` - Base URL: `/configuracoes/v1/status-pedido`**
    *   `POST /`: Cria um novo status de pedido.
        *   Entrada: `CreateStatusPedidoDTO`.
        *   Saída: ID do novo status (Integer), HTTP 201.
    *   `PUT /{id}`: Atualiza um status de pedido existente.
        *   Entrada: `UpdateStatusPedidoDTO`, `id` (Path).
        *   Saída: HTTP 204.
    *   `DELETE /{id}`: Inativa (soft delete) um status de pedido. (Comando `InativarStatusPedidoCommand` sugere inativação, não exclusão física).
        *   Entrada: `id` (Path).
        *   Saída: HTTP 204.
    *   `GET /{id}`: Obtém um status de pedido pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `StatusPedidoResponseDTO`, HTTP 200.
    *   `GET /`: Lista status de pedido com paginação e filtros.
        *   Entrada: `codigo`, `nome`, `visivelPortal` (QueryParams), `Pageable`.
        *   Saída: `Page<StatusPedidoResponseDTO>`, HTTP 200.

*   **`TiposServicosController` - Base URL: `/configuracoes/v1/tipos-servicos`**
    *   `POST /`: Cria um novo tipo de serviço.
        *   Entrada: `CreateTiposServicosCommand`.
        *   Saída: ID do novo tipo de serviço (Integer), HTTP 201.
    *   `PUT /{id}`: Atualiza um tipo de serviço existente.
        *   Entrada: `UpdateTiposServicosCommand`, `id` (Path).
        *   Saída: HTTP 204.
    *   `DELETE /{id}`: Inativa (soft delete) um tipo de serviço.
        *   Entrada: `id` (Path).
        *   Saída: HTTP 204.
    *   `GET /{id}`: Obtém um tipo de serviço pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `TiposServicosResponseDTO`, HTTP 200.
    *   `GET /`: Lista tipos de serviço com paginação e filtros.
        *   Entrada: `codigo`, `nome`, `categoriaId`, `disponivelPortal`, `ativo` (QueryParams), `Pageable`.
        *   Saída: `Page<TiposServicosResponseDTO>`, HTTP 200.

**Módulo `pedidos`:**

*   **`AvaliacoesPedidosController` - Base URL: `/pedidos/v1/avaliacoes`**
    *   `POST /`: Cria uma avaliação para um pedido.
        *   Entrada: `CreateAvaliacaoPedidoDTO`.
        *   Saída: `ResponseEntity<?>` (provavelmente ID ou DTO da avaliação), HTTP 201.
    *   `GET /{pedidoId}`: Obtém a avaliação de um pedido específico.
        *   Entrada: `pedidoId` (Path).
        *   Saída: `AvaliacaoPedidoResponseDTO`, HTTP 200.

*   **`PagamentosPedidosController` - Base URL: `/pedidos/v1/pagamentos`**
    *   `GET /{id}`: Obtém um pagamento pelo seu ID.
        *   Entrada: `id` (Path).
        *   Saída: `PagamentoPedidoResponseDTO`, HTTP 200.
    *   `PUT /{id}/status`: Atualiza o status de um pagamento.
        *   Entrada: `UpdateStatusPagamentoDTO`, `id` (Path).
        *   Saída: HTTP 204.
    *   `DELETE /{id}`: Exclui um pagamento.
        *   Entrada: `id` (Path).
        *   Saída: HTTP 204.
    *   `POST /`: Registra um novo pagamento para um pedido.
        *   Entrada: `CreatePagamentoPedidoDTO`.
        *   Saída: ID do pagamento (Integer), HTTP 201.

*   **`PedidosController` - Base URL: `/pedidos/v1`**
    *   `POST /`: Cria um novo pedido.
        *   Entrada: `CreatePedidoDTO`.
        *   Saída: ID do pedido (Integer), HTTP 201.
    *   `GET /`: Lista pedidos com paginação e filtros avançados.
        *   Entrada: Diversos QueryParams (`codigoAcompanhamento`, `tipoServicoId`, etc.), `Pageable`.
        *   Saída: `Page<PedidoResponseDTO>`, HTTP 200.
    *   `GET /{id}`: Obtém um pedido pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `PedidoResponseDTO`, HTTP 200.
    *   `PUT /{id}`: Atualiza um pedido existente.
        *   Entrada: `UpdatePedidoDTO`, `id` (Path).
        *   Saída: HTTP 204.
    *   `GET /{id}/historico`: Lista o histórico de alterações de um pedido.
        *   Entrada: `id` (Path).
        *   Saída: `List<HistoricoPedidoResponseDTO>`, HTTP 200.
    *   `POST /{id}/historico`: Adiciona uma nova entrada ao histórico do pedido.
        *   Entrada: `CreateHistoricoPedidoDTO`, `id` (Path, UUID).
        *   Saída: HTTP 201.
    *   `GET /{id}/documentos`: Lista os documentos associados a um pedido.
        *   Entrada: `id` (Path).
        *   Saída: `List<DocumentoPedidoResponseDTO>`, HTTP 200.
    *   `POST /{id}/documentos`: Faz upload de um novo documento para o pedido.
        *   Entrada: `UploadDocumentoPedidoDTO`, `id` (Path).
        *   Saída: ID do documento (UUID), HTTP 201.
    *   `GET /{id}/pagamentos`: Lista os pagamentos associados a um pedido.
        *   Entrada: `id` (Path).
        *   Saída: `List<PagamentoPedidoResponseDTO>`, HTTP 200.
    *   `POST /{id}/pagamentos`: Registra um novo pagamento para um pedido (duplicidade com `PagamentosPedidosController`? Verificar se este é específico para o contexto do pedido).
        *   Entrada: `CreatePagamentoPedidoDTO`, `id` (Path).
        *   Saída: ID do pagamento (UUID), HTTP 201.
    *   `GET /{id}/avaliacao`: Obtém a avaliação de um pedido.
        *   Entrada: `id` (Path).
        *   Saída: `AvaliacaoPedidoResponseDTO`, HTTP 200.
    *   `POST /{id}/avaliacao`: Cria uma avaliação para um pedido.
        *   Entrada: `CreateAvaliacaoPedidoDTO`, `id` (Path, UUID).
        *   Saída: HTTP 201.

**Módulo `utente`:**

*   **`ServicoController` - Base URL: `/utentes/v1`**
    *   `GET /{utenteId}/servicos`: Lista os serviços associados a um utente com paginação e filtros.
        *   Entrada: `utenteId` (Path), `Tipo`, `Estado`, `dataInicio`, `dataFim` (QueryParams), `Pageable`.
        *   Saída: `Page<ServicoResponseDTO>`, HTTP 200.
    *   `POST /{utenteId}/servicos/{servicoId}`: Associa um serviço existente a um utente.
        *   Entrada: `AdicionarServicoDTO`, `utenteId` (Path), `servicoId` (Path).
        *   Saída: `ServicoAssociadoResponseDTO`, HTTP 201.
    *   `GET /{utenteId}/servicos/{servicoId}`: Obtém detalhes de um serviço específico associado a um utente.
        *   Entrada: `utenteId` (Path), `servicoId` (Path).
        *   Saída: `ServicoResponseDTO`, HTTP 200.

*   **`UtenteController` - Base URL: `/utentes/v1`**
    *   `GET /`: Lista utentes com paginação e filtros.
        *   Entrada: `tipo`, `numeroUtente`, `nome`, `nif`, `documento`, `estado` (QueryParams), `Pageable`.
        *   Saída: `Page<UtenteResponseDTO>`, HTTP 200.
    *   `GET /{id}`: Obtém um utente pelo ID.
        *   Entrada: `id` (Path).
        *   Saída: `UtenteResponseDTO`, HTTP 200.
    *   `POST /`: Cria um novo utente.
        *   Entrada: `CriarUtenteDTO`.
        *   Saída: `UtenteResponseDTO`, HTTP 201.
    *   `PUT /{id}`: Atualiza um utente existente.
        *   Entrada: `UpdateUtenteDTO`, `id` (Path).
        *   Saída: `UtenteResponseDTO`, HTTP 200.
    *   `DELETE /{id}`: Inativa (soft delete) um utente.
        *   Entrada: `id` (Path).
        *   Saída: String (mensagem de sucesso), HTTP 200.

### 5. Configurações Relevantes

*   **Banco de Dados:**
    *   Configurado via `application-<profile>.properties`.
    *   Usa PostgreSQL.
    *   `spring.jpa.hibernate.ddl-auto`: `update` em desenvolvimento, `validate` em produção.
    *   `spring.jpa.show-sql=true` em desenvolvimento.
*   **Segurança (`SecurityConfig.java`):**
    *   **CORS:** Configuração permissiva (`AllowedOriginPattern(CorsConfiguration.ALL)`), `AllowedMethod` (todos comuns), `AllowedHeader(CorsConfiguration.ALL)`, `AllowCredentials(true)`.
    *   **Autenticação:** OAuth2 Resource Server com JWT.
        *   Emissor JWT configurado via `auth.jwt.issuer` (variável de ambiente).
        *   `JwtDecoder` (Nimbus) para validar tokens em produção.
        *   `JwtAuthenticationConverter` para extrair authorities.
    *   **Autorização:**
        *   Em desenvolvimento/staging: Segurança desabilitada (todas as requisições permitidas, CSRF desabilitado).
        *   Em produção:
            *   `GET` requests são `permitAll()`.
            *   Todas as outras requests (`anyRequest()`) são `authenticated()`.
            *   Sessão `STATELESS`.
    *   **CSRF:** Desabilitado em desenvolvimento/staging. Habilitado por padrão pelo Spring Security em produção (mas o comportamento com APIs stateless e JWT pode variar; a configuração explícita de CSRF para produção não está visível, o que é comum para APIs JWT onde tokens são enviados em headers).
*   **Auditoria:**
    *   **Spring Data JPA Auditing (`SimpleApplication.java`):**
        *   `@EnableJpaAuditing(auditorAwareRef = "auditAware", dateTimeProviderRef = "auditDateTimeProvider")`.
        *   `ApplicationAuditorAware`: Implementação para buscar o usuário logado (provavelmente do contexto de segurança).
        *   `AuditEntity`: Superclasse para entidades auditáveis com campos `createdBy`, `createdDate`, `lastModifiedBy`, `lastModifiedDate`.
    *   **Hibernate Envers (configurado em `application-development.properties`):**
        *   `spring.jpa.properties.org.hibernate.envers.track_entities_changed_in_revision=true`
        *   `spring.jpa.properties.org.hibernate.envers.global_with_modified_flag=true`
        *   `spring.jpa.properties.org.hibernate.envers.default_schema=audit_schema` (tabelas de auditoria vão para este schema).
        *   Comentado em `application-production.properties`, indicando que pode não ser usado ou ter configuração diferente em produção.
*   **Logs:**
    *   Não há configuração explícita de logging nos arquivos principais, indicando o uso do padrão Spring Boot (Logback).
    *   SLF4J é utilizado nos controllers para logging.
*   **Documentação da API (Swagger/OpenAPI):**
    *   Habilitada em desenvolvimento (`springdoc.swagger-ui.enabled=true` em `application-development.properties`).
    *   Desabilitada em produção (`springdoc.swagger-ui.enabled=false` em `application-production.properties`).
    *   Operações ordenadas por método (`springdoc.swagger-ui.operationsSorter=method`).
*   **Spring Data REST:**
    *   `spring.data.rest.detection-strategy=annotated`: Repositórios precisam ser anotados (e.g., com `@RepositoryRestResource`) para serem expostos automaticamente.
*   **Observabilidade (Actuator, Micrometer):**
    *   `management.endpoints.web.exposure.include=*`: Expõe todos os endpoints do Actuator.
    *   Métricas para Prometheus (`micrometer-registry-prometheus`).
    *   Tracing (OTel bridge, Zipkin exporter) configurado mas desabilitado (`management.tracing.enabled=false`).
*   **Perfis Spring:**
    *   `development` (padrão), `production`, `staging`.
    *   Configurações específicas por perfil em `application-<profile>.properties`.

### 6. Estratégia de Versionamento e Modularização

*   **Versionamento da API:**
    *   Os endpoints REST incluem `/v1/` em suas URLs, indicando uma estratégia de versionamento de API na URL.
*   **Versionamento do Código:**
    *   O `pom.xml` define a versão do artefato como `0.0.1-SNAPSHOT`, um padrão para projetos Maven em desenvolvimento.
    *   Não há uma estratégia de versionamento de código mais elaborada visível nos arquivos de configuração.
*   **Modularização:**
    *   O projeto é claramente modularizado por contexto de negócio:
        *   `configuracoes`
        *   `pedidos`
        *   `utente`
    *   Cada módulo possui sua própria estrutura interna de pacotes (`application`, `domain`, `infrastructure`).
    *   Existe um módulo `shared` para código comum.
    *   A presença da pasta `.igrpstudio` e subpastas com JSONs de controllers, DTOs e models por módulo reforça a ideia de uma abordagem modular, possivelmente auxiliada por ferramentas de geração de código ou um framework específico (IGRP).
    *   O `pom.xml` não mostra módulos Maven explícitos (`<modules>`), então a modularização é lógica dentro de um único artefato Maven, mas a estrutura de pacotes é bem definida.

---

## Parte 2 – Especificação Funcional

### 1. Descrição dos Principais Casos de Uso do Sistema

**CU01: Gerenciar Categorias de Serviços**
*   **Ator:** Administrador do Sistema
*   **Descrição:** O administrador pode criar, visualizar, atualizar e inativar (soft delete) categorias que agrupam tipos de serviços.
*   **Fluxo Principal:**
    1.  Administrador acessa a funcionalidade de gerenciamento de categorias.
    2.  Para criar: informa nome, descrição (opcional), ícone (opcional), cor (opcional), ordem e se está ativa. Sistema salva a categoria.
    3.  Para visualizar: Sistema exibe lista paginada de categorias com filtros (nome, ativo).
    4.  Para atualizar: Administrador seleciona uma categoria, modifica os campos desejados e salva.
    5.  Para inativar: Administrador seleciona uma categoria e confirma a inativação. Sistema marca a categoria como inativa.

**CU02: Gerenciar Tipos de Serviços**
*   **Ator:** Administrador do Sistema
*   **Descrição:** O administrador pode criar, visualizar, atualizar e inativar (soft delete) os tipos de serviços oferecidos, associando-os a categorias.
*   **Fluxo Principal:**
    1.  Administrador acessa a funcionalidade de gerenciamento de tipos de serviços.
    2.  Para criar: informa categoria, código, nome, descrição (opcional), prazo estimado, valor base, e flags (requer vistoria, análise técnica, aprovação, disponível no portal, ativo). Sistema salva o tipo de serviço.
    3.  Para visualizar: Sistema exibe lista paginada de tipos de serviços com filtros (código, nome, categoria, disponível no portal, ativo).
    4.  Para atualizar: Administrador seleciona um tipo de serviço, modifica os campos desejados e salva.
    5.  Para inativar: Administrador seleciona um tipo de serviço e confirma a inativação.

**CU03: Gerenciar Status de Pedidos**
*   **Ator:** Administrador do Sistema
*   **Descrição:** O administrador pode definir os diferentes status pelos quais um pedido pode passar durante seu ciclo de vida.
*   **Fluxo Principal:**
    1.  Administrador acessa a funcionalidade de gerenciamento de status de pedidos.
    2.  Para criar: informa código, nome, descrição (opcional), cor (opcional), ícone (opcional), ordem e se é visível no portal. Sistema salva o status.
    3.  Para visualizar: Sistema exibe lista paginada de status com filtros (código, nome, visível no portal).
    4.  Para atualizar: Administrador seleciona um status, modifica os campos desejados e salva.
    5.  Para inativar/excluir: Administrador seleciona um status e confirma a operação.

**CU04: Gerenciar Configurações Gerais do Sistema**
*   **Ator:** Administrador do Sistema
*   **Descrição:** O administrador pode gerenciar parâmetros de configuração chave-valor do sistema.
*   **Fluxo Principal:**
    1.  Administrador acessa a funcionalidade de gerenciamento de configurações.
    2.  Para criar: informa chave, valor, descrição (opcional), tipo, grupo e se é editável. Sistema salva a configuração.
    3.  Para visualizar: Sistema exibe lista paginada de configurações com filtros (chave, grupo, tipo, estado).
    4.  Para atualizar: Administrador seleciona uma configuração, modifica os campos desejados (se editável) e salva.
    5.  Para inativar: Administrador seleciona uma configuração e confirma a inativação.

**CU05: Criar Pedido de Serviço**
*   **Atores:** Utente/Cidadão, Usuário Interno
*   **Descrição:** Um utente ou um usuário interno (em nome de um utente) pode solicitar um novo serviço.
*   **Fluxo Principal:**
    1.  Usuário (utente ou interno) inicia a criação de um novo pedido.
    2.  Seleciona o tipo de serviço desejado.
    3.  Informa os dados do solicitante (se usuário interno, busca/informa o cidadão).
    4.  Sistema registra o pedido com um status inicial (e.g., "Submetido"), data de início, e gera um código de acompanhamento.
    5.  (Opcional) Usuário anexa documentos iniciais.
    6.  Sistema notifica o usuário da criação do pedido e fornece o código de acompanhamento.

**CU06: Acompanhar Pedido de Serviço**
*   **Atores:** Utente/Cidadão, Usuário Interno, Administrador
*   **Descrição:** Usuários podem visualizar o estado atual e o histórico de um pedido.
*   **Fluxo Principal:**
    1.  Usuário busca o pedido pelo ID ou código de acompanhamento.
    2.  Sistema exibe os detalhes do pedido, incluindo status atual, etapa atual, datas relevantes, documentos e histórico de tramitação.

**CU07: Processar Pedido de Serviço (Workflow Interno)**
*   **Atores:** Usuário Interno, Administrador
*   **Descrição:** Usuários internos gerenciam o fluxo do pedido, atualizando status, etapas, atribuindo responsáveis e adicionando informações.
*   **Fluxo Principal (varia conforme o tipo de serviço):**
    1.  Usuário interno localiza um pedido pendente.
    2.  Atribui o pedido a um responsável (opcional).
    3.  Analisa o pedido e os documentos.
    4.  Atualiza o status e/ou etapa do pedido (e.g., "Em Análise", "Aguardando Pagamento", "Concluído"). Sistema registra no histórico.
    5.  Adiciona documentos relevantes (e.g., pareceres técnicos, comprovantes).
    6.  Registra informações de pagamento, se aplicável.
    7.  Comunica-se com o utente se necessário (fora do escopo direto da API, mas pode ser um requisito).

**CU08: Gerenciar Documentos de um Pedido**
*   **Atores:** Utente/Cidadão (limitado aos seus pedidos), Usuário Interno, Administrador
*   **Descrição:** Anexar e visualizar documentos relacionados a um pedido.
*   **Fluxo Principal:**
    1.  Usuário acessa um pedido específico.
    2.  Para anexar: seleciona o arquivo, informa nome e tipo de documento. Sistema armazena o documento (possivelmente via IGRP File Manager/MinIO) e associa ao pedido.
    3.  Para visualizar: Sistema lista os documentos do pedido, permitindo o download ou visualização.

**CU09: Gerenciar Pagamentos de um Pedido**
*   **Atores:** Utente/Cidadão (registrar pagamento para seus pedidos), Usuário Interno (confirmar/registrar pagamentos)
*   **Descrição:** Registrar e acompanhar o status dos pagamentos de um pedido.
*   **Fluxo Principal:**
    1.  Usuário acessa um pedido que requer pagamento.
    2.  Para registrar: informa data do pagamento, método, referência, valor e observações. Sistema salva o pagamento.
    3.  Para atualizar status (interno): Usuário interno localiza o pagamento e atualiza seu status (e.g., "Confirmado", "Recusado").

**CU10: Avaliar Pedido Concluído**
*   **Atores:** Utente/Cidadão
*   **Descrição:** O utente pode fornecer feedback sobre um pedido que foi concluído.
*   **Fluxo Principal:**
    1.  Utente acessa um pedido concluído.
    2.  Sistema permite que o utente informe uma nota (e.g., de 1 a 5) e um comentário.
    3.  Sistema salva a avaliação. Só pode haver uma avaliação por pedido.

**CU11: Gerenciar Utentes (Cidadãos/Empresas)**
*   **Atores:** Usuário Interno, Administrador
*   **Descrição:** Criar, visualizar, atualizar e inativar registros de utentes.
*   **Fluxo Principal:**
    1.  Usuário acessa a funcionalidade de gerenciamento de utentes.
    2.  Para criar: informa todos os dados cadastrais relevantes (tipo de utente, identificação, nome, NIF, contatos, endereço, etc.). Sistema salva o utente.
    3.  Para visualizar: Sistema exibe lista paginada de utentes com filtros.
    4.  Para atualizar: Usuário seleciona um utente, modifica os campos desejados e salva.
    5.  Para inativar: Usuário seleciona um utente e confirma a inativação.

**CU12: Gerenciar Serviços/Objetos Associados ao Utente**
*   **Atores:** Usuário Interno, Administrador
*   **Descrição:** Associar e gerenciar serviços específicos ou objetos (como contratos, licenças) a um utente.
*   **Fluxo Principal:**
    1.  Usuário acessa o cadastro de um utente.
    2.  Para associar serviço: informa tipo de objeto, ID do objeto (se aplicável), descrição, referência, datas, valor e estado. Sistema salva a associação.
    3.  Para visualizar: Sistema lista os serviços/objetos associados ao utente.

### 2. Funcionalidades Esperadas por Tipo de Usuário

*   **Administrador do Sistema:**
    *   Acesso total ao módulo de Configurações (CRUD em Categorias, Tipos de Serviço, Status de Pedido, Configurações Gerais).
    *   CRUD completo no módulo de Utentes.
    *   CRUD completo no módulo de Pedidos (incluindo visualizar todos, alterar qualquer aspecto, gerenciar documentos e pagamentos de qualquer pedido).
    *   Visualizar todas as avaliações.
    *   Acesso a logs e monitoramento (via Actuator).
*   **Usuário Interno/Funcionário:**
    *   Visualizar configurações (categorias, tipos de serviço, status).
    *   CRUD em Utentes (ou pelo menos criar e atualizar).
    *   Criar pedidos para utentes.
    *   Processar pedidos: visualizar pedidos atribuídos ou em filas, atualizar status/etapas, anexar/gerenciar documentos internos, registrar/confirmar pagamentos.
    *   Atribuir pedidos a outros usuários internos (se a funcionalidade existir).
    *   Visualizar histórico e detalhes dos pedidos que gerencia.
    *   Visualizar avaliações de pedidos.
*   **Utente/Cidadão (Usuário Externo, via portal ou app):**
    *   Criar novos pedidos para os tipos de serviços disponíveis no portal.
    *   Acompanhar o status e histórico dos seus próprios pedidos.
    *   Anexar documentos aos seus pedidos (conforme permitido pelo fluxo do serviço).
    *   Registrar informações de pagamento para seus pedidos.
    *   Avaliar seus pedidos concluídos.
    *   Visualizar e atualizar seus próprios dados cadastrais (se permitido).
    *   Visualizar seus serviços/objetos associados.

### 3. Regras de Negócio Identificadas no Código (Resumo)

*   **Unicidade:**
    *   `PedidosEntity.codigoAcompanhamento` deve ser único.
    *   `EtapasPedidosEntity.codigo` deve ser único.
    *   Um pedido (`AvaliacoesPedidosEntity.pedidoId`) só pode ter uma avaliação.
*   **Obrigatoriedade e Validações de Dados:**
    *   Diversos campos são obrigatórios em entidades e DTOs (e.g., nome de categoria, código de tipo de serviço, dados básicos de um pedido, dados de utente).
    *   Valores de enums são restritos aos definidos (e.g., `Estado`, `TipoUtente`).
*   **Relacionamentos:**
    *   `TiposServicosEntity` deve ter uma `CategoriasServicosEntity`.
    *   `PedidosEntity` deve ter um `TiposServicosEntity`, um `UtenteEntity` (cidadão), um `StatusPedidoEntity`.
    *   `HistoricoPedidosEntity` registra transições de status/etapa, associado a um usuário e data.
*   **Fluxo de Pedido:**
    *   Um pedido é criado com um status inicial.
    *   O status e a etapa de um pedido podem ser alterados, gerando um registro no histórico.
    *   Certas etapas podem requerer aprovação (`EtapasPedidosEntity.requerAprovacao`).
*   **Soft Delete:**
    *   A inativação de categorias, tipos de serviço, configurações e utentes é geralmente um soft delete (marcando um campo `ativo` como `false` ou `estado` como `INATIVO`).
*   **Segurança de Acesso:**
    *   Requisições `GET` são geralmente públicas, outras (`POST`, `PUT`, `DELETE`) requerem autenticação (exceto em dev/staging).
*   **Auditoria:**
    *   Criação e modificação de entidades auditáveis registram o usuário e a data/hora.
    *   Hibernate Envers (em dev) permite rastrear o histórico de alterações das entidades.
*   **Pagamentos:**
    *   Pagamentos são associados a pedidos e possuem seu próprio status.
*   **Avaliações:**
    *   Apenas uma avaliação por pedido.
    *   Nota e comentário são registrados.

### 4. Validações e Comportamentos Esperados nos Formulários ou APIs

*   **Criação/Edição de Categorias de Serviço:**
    *   Nome: obrigatório, string, max 100.
    *   Ordem: obrigatório, inteiro.
    *   Ativo: obrigatório, booleano.
    *   Ícone, Cor, Descrição: opcionais.
*   **Criação/Edição de Tipos de Serviço:**
    *   Categoria: obrigatório, referência válida.
    *   Código: obrigatório, string, max 20.
    *   Nome: obrigatório, string, max 100.
    *   Flags (requer vistoria, análise tec, aprovação, disponível portal, ativo): obrigatórios, booleanos.
    *   Prazo, Valor, Descrição: opcionais.
*   **Criação de Pedido:**
    *   Tipo de Serviço: obrigatório, referência válida.
    *   Cidadão (Solicitante): obrigatório, referência válida.
    *   Origem: obrigatório (e.g., "PORTAL", "PRESENCIAL").
    *   API deve retornar o código de acompanhamento.
    *   Status inicial e data de início devem ser definidos automaticamente.
*   **Atualização de Pedido:**
    *   Permitir alteração de status, etapa, responsável, observações.
    *   Validação se o status/etapa de destino é válido no fluxo.
*   **Upload de Documento:**
    *   Arquivo: obrigatório.
    *   Nome do documento: obrigatório.
    *   Tipo do documento: obrigatório.
    *   Associação com Pedido ID: obrigatório.
*   **Registro de Pagamento:**
    *   Pedido ID: obrigatório.
    *   Data Pagamento: obrigatório.
    *   Método Pagamento: obrigatório.
    *   Status: obrigatório.
    *   Valor: obrigatório (ou opcional se for apenas registro de intenção).
*   **Avaliação de Pedido:**
    *   Pedido ID: obrigatório, deve ser um pedido concluído.
    *   Nota: obrigatório, inteiro (e.g., dentro de um range 1-5).
    *   Comentário: opcional.
    *   API deve impedir mais de uma avaliação por pedido.
*   **Criação/Edição de Utente:**
    *   Campos como nome, tipo de identificação, número de identificação, data de nascimento, endereço, telefone são obrigatórios.
    *   NIF deve ter formato válido (se houver validação específica).
    *   Email deve ter formato válido.
*   **Geral:**
    *   Todas as entradas obrigatórias devem ser validadas.
    *   Mensagens de erro claras devem ser retornadas para dados inválidos ou falhas de regra de negócio.
    *   Respostas de sucesso devem indicar o resultado da operação (e.g., ID do recurso criado, HTTP 201/200/204).
    *   Paginação e filtros devem funcionar conforme especificado nos endpoints GET.

---

## Parte 3 – Plano de Execução do Projeto

Este plano de execução é uma sugestão e pode ser adaptado conforme as prioridades e recursos disponíveis.

**Fase 1: Levantamento e Análise Detalhada**
*   **Objetivo:** Validar e refinar o entendimento da arquitetura, funcionalidades e requisitos existentes. Identificar gaps e pontos críticos.
*   **Entregáveis Esperados:**
    *   Documento de arquitetura atualizado (baseado nesta análise inicial).
    *   Lista detalhada de requisitos funcionais e não funcionais validados.
    *   Backlog inicial do projeto priorizado (Épicos, User Stories).
    *   Matriz de rastreabilidade de requisitos (inicial).
*   **Recursos/Perfis Envolvidos:** Arquiteto de Software, Analista de Negócios, Líder Técnico, (opcional) Desenvolvedores Sênior.
*   **Duração Estimada:** 1-2 semanas.
*   **Critérios de Aceitação:**
    *   Documentação validada pelas partes interessadas (stakeholders).
    *   Backlog com itens suficientes para iniciar o primeiro ciclo de desenvolvimento.

**Fase 2: Arquitetura e Modelagem (Refinamento)**
*   **Objetivo:** Definir/refinar a arquitetura de solução, incluindo integrações, segurança detalhada e modelo de dados canônico, caso necessário. Planejar a infraestrutura.
*   **Entregáveis Esperados:**
    *   Diagramas de arquitetura atualizados (componentes, sequência, implantação).
    *   Modelo de dados lógico e físico refinado (se houver alterações significativas).
    *   Especificação detalhada de APIs (refinamento do Swagger/OpenAPI).
    *   Plano de segurança detalhado.
    *   Plano de infraestrutura e deploy.
*   **Recursos/Perfis Envolvidos:** Arquiteto de Software, Líder Técnico, Especialista em Segurança, DevOps.
*   **Duração Estimada:** 1-2 semanas (pode sobrepor com Fase 1).
*   **Critérios de Aceitação:**
    *   Arquitetura e modelos aprovados.
    *   Plano de infraestrutura e segurança considerados viáveis.

**Fase 3: Desenvolvimento de Funcionalidades Core (Ciclos Iterativos)**
*   **Objetivo:** Desenvolver as funcionalidades centrais da aplicação, priorizando os módulos e casos de uso mais críticos.
*   **Entregáveis Esperados (por ciclo/sprint):**
    *   Incremento de software funcional e testado.
    *   Testes unitários e de integração para as funcionalidades desenvolvidas.
    *   Atualização da documentação técnica (Javadoc, Swagger).
    *   Builds automatizados e passando.
*   **Módulos Prioritários (sugestão):**
    1.  Configurações (base para outros módulos).
    2.  Utentes (necessário para Pedidos).
    3.  Pedidos (funcionalidades de criação, acompanhamento básico).
    4.  Pedidos (funcionalidades avançadas: documentos, pagamentos, avaliações).
*   **Recursos/Perfis Envolvidos:** Líder Técnico, Desenvolvedores (Backend/Frontend se houver UI), QA/Testadores.
*   **Duração Estimada:** 6-12 semanas (dividido em sprints de 2-3 semanas).
*   **Critérios de Aceitação (por ciclo/sprint):**
    *   Funcionalidades entregues conforme os critérios de aceitação das User Stories.
    *   Cobertura de testes mínima atingida.
    *   Demonstração bem-sucedida das funcionalidades.

**Fase 4: Integrações**
*   **Objetivo:** Implementar e testar integrações com sistemas externos (e.g., IGRP File Manager/MinIO, sistema de autenticação Keycloak, gateways de pagamento se houver).
*   **Entregáveis Esperados:**
    *   Integrações funcionais e testadas.
    *   Documentação das integrações.
    *   Testes de integração end-to-end.
*   **Recursos/Perfis Envolvidos:** Desenvolvedores, Líder Técnico, Especialistas dos sistemas a serem integrados.
*   **Duração Estimada:** 2-4 semanas (pode ocorrer em paralelo com a Fase 3).
*   **Critérios de Aceitação:**
    *   Fluxos de dados entre sistemas validados.
    *   Tratamento de erros de integração implementado.

**Fase 5: Testes (Globais)**
*   **Objetivo:** Realizar testes abrangentes para garantir a qualidade e conformidade da aplicação.
*   **Entregáveis Esperados:**
    *   Plano de Testes (incluindo testes de sistema, aceitação, performance, segurança).
    *   Relatórios de execução de testes.
    *   Lista de defeitos corrigidos e pendentes.
    *   Software aprovado para homologação/deploy.
*   **Tipos de Testes:**
    *   Testes de Sistema: Validar o sistema como um todo.
    *   Testes de Aceitação do Usuário (UAT): Realizados pelos stakeholders ou usuários finais.
    *   Testes de Performance e Carga: Validar o desempenho sob carga esperada.
    *   Testes de Segurança (Pentest, análise de vulnerabilidades).
*   **Recursos/Perfis Envolvidos:** QA/Testadores, Analista de Negócios, Desenvolvedores (para correção de bugs), Especialista em Segurança, Usuários Chave.
*   **Duração Estimada:** 2-4 semanas.
*   **Critérios de Aceitação:**
    *   Todos os casos de teste críticos e de alta prioridade aprovados.
    *   Nível de defeitos aceitável conforme definido.
    *   Resultados dos testes de performance e segurança dentro dos limites aceitáveis.

**Fase 6: Deploy e Homologação**
*   **Objetivo:** Implantar a aplicação no ambiente de homologação e, posteriormente, produção. Obter aprovação final dos stakeholders.
*   **Entregáveis Esperados:**
    *   Aplicação implantada nos ambientes de Staging/Homologação e Produção.
    *   Scripts de deploy automatizados (IaC, CI/CD pipelines).
    *   Documentação de implantação (Runbook).
    *   Termo de aceite da homologação.
*   **Recursos/Perfis Envolvidos:** DevOps, Líder Técnico, Administradores de Sistema, Usuários Chave (para homologação).
*   **Duração Estimada:** 1-2 semanas.
*   **Critérios de Aceitação:**
    *   Implantação bem-sucedida em todos os ambientes.
    *   Checklist de pós-implantação verificado.
    *   Aplicação funcional e estável em produção.
    *   Aprovação formal dos stakeholders.

**Fase 7: Operação e Monitoramento (Pós-GoLive)**
*   **Objetivo:** Garantir a estabilidade da aplicação em produção, monitorar seu desempenho e coletar feedback para futuras melhorias.
*   **Entregáveis Esperados:**
    *   Sistema de monitoramento configurado e ativo (logs centralizados, métricas, alertas).
    *   Plano de suporte e manutenção.
    *   Relatórios de desempenho e utilização.
    *   Backlog de melhorias e correções (se necessário).
*   **Recursos/Perfis Envolvidos:** Equipe de Operações/Suporte, DevOps, Desenvolvedores (para suporte N3).
*   **Duração Estimada:** Contínuo.
*   **Critérios de Aceitação:**
    *   SLAs (Acordos de Nível de Serviço) definidos e monitorados.
    *   Processos de gestão de incidentes e problemas estabelecidos.
    *   Monitoramento proativo da saúde da aplicação.

**Diagramas de Apoio (Opcional - a serem criados com ferramentas adequadas):**

*   **Diagrama de Componentes UML:** Para visualizar os módulos principais e suas dependências.
*   **Diagrama de Sequência UML:** Para ilustrar fluxos importantes (e.g., criação de um pedido, processamento de pagamento).
*   **Diagrama de Arquitetura de Implantação:** Mostrar como os componentes da aplicação serão implantados nos servidores/containers (incluindo banco de dados, Keycloak, etc.).
*   **Diagrama de Fluxo de Dados:** Para os principais processos de negócio.

Este plano é genérico e precisa ser detalhado com estimativas de esforço mais precisas e alocação de recursos específica do contexto do projeto.
