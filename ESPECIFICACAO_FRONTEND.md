# Especificação Técnica – Projeto Frontend (React/Next.js)

## 1. Visão Geral e Stack Tecnológico

Este documento detalha a especificação técnica para o frontend da aplicação "Simple", que servirá como interface para os módulos de Utentes, Pedidos e Configurações do backend.

**Stack Tecnológico Principal:**
*   **Framework Principal:** React 19 com Next.js (App Router)
*   **Linguagem:** TypeScript
*   **Build Tool:** Vite (Nota: Next.js tem seu próprio sistema de build baseado em SWC/Webpack. Se Vite for um requisito estrito, a estrutura do projeto seria diferente de um projeto Next.js padrão. Esta especificação assume um projeto Next.js padrão, que é mais comum para a combinação com React Router App Router). *Correção: A especificação seguirá Next.js com seu build system, não Vite, dado o uso do App Router.*
*   **Roteamento:** Next.js App Router
*   **Estilização:** TailwindCSS
*   **Componentes Primitivos:** Radix UI (headless UI components)
*   **Ícones:** Lucide React
*   **Cliente HTTP:** Axios
*   **Manipulação de Datas:** date-fns
*   **Testes:** Vitest com React Testing Library
*   **Linting:** ESLint
*   **Formatação:** Prettier

## 2. Arquitetura Geral do Frontend

A arquitetura será baseada no Next.js App Router, promovendo uma estrutura modular e organizada.

**2.1. Estrutura de Pastas (`src/`)**

```
src/
├── app/                                # Next.js App Router
│   ├── (auth)/                         # Rotas e lógica de autenticação
│   │   └── login/page.tsx
│   ├── (private)/                      # Rotas protegidas
│   │   ├── layout.tsx                  # Layout para rotas privadas
│   │   ├── dashboard/page.tsx
│   │   │
│   │   ├── utentes/
│   │   │   ├── page.tsx                # Listagem (/utentes)
│   │   │   ├── novo/page.tsx           # Novo (/utentes/novo)
│   │   │   └── [id]/
│   │   │       ├── page.tsx            # Detalhe/Edição (/utentes/[id])
│   │   │       └── servicos/page.tsx   # Serviços do Utente (/utentes/[id]/servicos)
│   │   │
│   │   ├── pedidos/
│   │   │   ├── page.tsx                # Listagem (/pedidos)
│   │   │   ├── novo/page.tsx           # Novo (/pedidos/novo)
│   │   │   └── [id]/
│   │   │       ├── page.tsx            # Detalhe/Edição (/pedidos/[id])
│   │   │       ├── documentos/page.tsx
│   │   │       ├── pagamentos/page.tsx
│   │   │       └── avaliacao/page.tsx
│   │   │
│   │   ├── configuracoes/
│   │   │   ├── page.tsx                # Dashboard (/configuracoes)
│   │   │   ├── categorias-servicos/    # CRUD para Categorias de Serviços
│   │   │   │   ├── page.tsx
│   │   │   │   ├── novo/page.tsx
│   │   │   │   └── [id]/page.tsx
│   │   │   ├── tipos-servicos/         # CRUD para Tipos de Serviços
│   │   │   │   ├── page.tsx
│   │   │   │   ├── novo/page.tsx
│   │   │   │   └── [id]/page.tsx
│   │   │   ├── status-pedidos/         # CRUD para Status de Pedidos
│   │   │   │   ├── page.tsx
│   │   │   │   ├── novo/page.tsx
│   │   │   │   └── [id]/page.tsx
│   │   │   └── gerais/                 # CRUD para Configurações Gerais
│   │   │       ├── page.tsx
│   │   │       ├── novo/page.tsx
│   │   │       └── [id]/page.tsx
│   │   │
│   │   └── perfil/page.tsx             # Perfil do usuário
│   │
│   ├── api/                            # Next.js API Routes (BFF)
│   │   ├── auth/[...nextauth]/route.ts # Exemplo com NextAuth.js (opcional)
│   │   └── proxy/[...path]/route.ts    # Proxy para API backend
│   │
│   ├── globals.css                     # Estilos globais (TailwindCSS)
│   ├── layout.tsx                      # Layout raiz
│   └── page.tsx                        # Página inicial pública
│
├── components/
│   ├── ui/                             # Componentes Radix + Tailwind (Button.tsx, Input.tsx, etc.)
│   ├── layout/                         # MainLayout.tsx, Navbar.tsx, Sidebar.tsx
│   ├── shared/                         # DataTable.tsx, ConfirmDialog.tsx
│   └── icons/Icon.tsx                  # Wrapper Lucide React (opcional)
│
├── lib/
│   ├── axios.ts                        # Configuração da instância Axios
│   ├── hooks/                          # Hooks customizados (useAuth.ts)
│   ├── utils.ts                        # Utilitários (date-fns wrappers)
│   └── constants.ts
│
├── services/
│   ├── utente.service.ts
│   ├── pedido.service.ts
│   ├── configuracao.service.ts
│   └── auth.service.ts
│
├── store/                              # Gerenciamento de estado global (Context API ou Zustand)
│   └── auth.store.ts                 # Ex: AuthContext ou store Zustand
│
├── types/
│   ├── index.ts
│   ├── api.types.ts                    # DTOs da API Backend
│   └── igrp.config.ts                  # Tipos específicos IGRP
│
└── public/                             # Arquivos estáticos
```

**2.2. Principais Arquivos e Propósitos:**
*   **`src/app/layout.tsx`**: Layout raiz para provedores globais (Autenticação, Tema).
*   **`src/app/(private)/layout.tsx`**: Layout para seções autenticadas, incluindo navegação principal (Sidebar, Navbar) e lógica de proteção de rota.
*   **`src/app/api/proxy/[...path]/route.ts`**: BFF para chamadas à API backend, gerenciando tokens e evitando CORS.
*   **`src/components/ui/`**: Coleção de componentes de UI primitivos e reutilizáveis, construídos com Radix UI e estilizados com TailwindCSS.
*   **`src/lib/axios.ts`**: Instância Axios configurada com `baseURL` e interceptors para tokens JWT e tratamento de erros.
*   **`src/services/*.service.ts`**: Módulos que encapsulam a lógica de chamada da API para cada entidade do backend.
*   **`src/types/api.types.ts`**: Definições TypeScript para todos os DTOs da API backend.
*   **`src/store/auth.store.ts`**: Gerenciamento do estado de autenticação (usuário, token, status).

## 3. Componentes Reutilizáveis (UI Kit)

Baseado em Radix UI para acessibilidade e TailwindCSS para estilização.

**3.1. Abordagem de Estilização:**
*   **TailwindCSS:** Configuração em `tailwind.config.ts` com tema customizado (cores, fontes, espaçamentos). Uso de classes utilitárias.
*   **Radix UI:** Estilização dos primitivos Radix aplicando classes TailwindCSS.

**3.2. Lista de Componentes (`src/components/ui/`):**
*   `Button.tsx` (variantes: default, primary, destructive, outline, ghost; tamanhos; isLoading)
*   `Input.tsx`, `Label.tsx`, `Textarea.tsx`
*   `Select.tsx` (baseado em Radix UI Select)
*   `Checkbox.tsx`, `RadioGroup.tsx`, `Switch.tsx` (baseados em Radix UI)
*   `Card.tsx` (com `Card.Header`, `Card.Title`, `Card.Content`, `Card.Footer`)
*   `Dialog.tsx` (Modal, baseado em Radix UI Dialog)
*   `AlertDialog.tsx` (baseado em Radix UI AlertDialog)
*   `DropdownMenu.tsx` (baseado em Radix UI DropdownMenu)
*   `Avatar.tsx` (baseado em Radix UI Avatar)
*   `Table.tsx` (primitivo para tabelas; `DataTable.tsx` em `shared/` para funcionalidades avançadas)
*   `Badge.tsx` (para status)
*   `Tooltip.tsx` (baseado em Radix UI Tooltip)
*   `DatePicker.tsx` (usando `react-day-picker` com Radix Popover, `date-fns` para lógica)
*   `Tabs.tsx` (baseado em Radix UI Tabs)
*   `Skeleton.tsx` (para estados de carregamento)
*   `Progress.tsx` (baseado em Radix UI Progress)
*   `Accordion.tsx` (baseado em Radix UI Accordion)
*   **Ícones:** `lucide-react` para ícones SVG.

## 4. Integração com a API Backend

**4.1. Cliente HTTP: Axios (`src/lib/axios.ts`)**
*   Instância configurada com `baseURL` (apontando para o BFF Next.js `/api/proxy` ou diretamente para a API backend via `NEXT_PUBLIC_API_BASE_URL`).
*   **Interceptors:**
    *   **Requisição:** Adicionar `Authorization: Bearer <token>` aos headers.
    *   **Resposta:** Tratamento global de erros HTTP (e.g., 401 redireciona para login, log de erros 5xx).

**4.2. Camada de Serviço (`src/services/`)**
*   Arquivos dedicados por módulo (`utente.service.ts`, `pedido.service.ts`, `configuracao.service.ts`, `auth.service.ts`).
*   Cada serviço exporta funções assíncronas que fazem chamadas Axios para os endpoints REST correspondentes do backend.
*   Utilizarão os tipos definidos em `src/types/api.types.ts` para tipagem de requisições e respostas.

**4.3. Gerenciamento de Estado**
*   **Estado Local:** `useState`, `useReducer` para estado de componentes.
*   **Context API (`src/store/auth.store.ts` ou `src/contexts/AuthContext.tsx`):**
    *   Gerenciar estado de autenticação (token, perfil do usuário, status `isAuthenticated`, `isLoading`).
    *   Prover funções `login()` e `logout()`.
*   **Estado de Servidor (Dados da API):**
    *   Para Server Components: busca de dados diretamente no servidor.
    *   Para Client Components: `useEffect` com `fetch` (via serviços Axios) e `useState` para dados simples. Para cenários mais complexos (cache, revalidação, otimismo), considerar a adoção de React Query (TanStack Query) ou SWR no futuro.

**4.4. Tipos TypeScript (`src/types/api.types.ts`)**
*   Definição de interfaces para todos os DTOs do backend (e.g., `UtenteResponseDTO`, `CreatePedidoDTO`, `Page<T>`).
*   Garantir type safety entre o frontend e o backend.

**4.5. Backend for Frontend (BFF) (`src/app/api/proxy/`)**
*   Rotas Next.js API (`route.ts`) que fazem proxy para a API Spring Boot.
*   **Benefícios:** Evitar CORS, ocultar URL do backend, gerenciar tokens/sessões de forma segura no lado do servidor Next.js.

## 5. Roteamento da Aplicação (Next.js App Router)

A estrutura de pastas em `src/app/` define as rotas.

**5.1. Principais Grupos de Rotas:**
*   **`/` (raiz):** Página inicial pública ou redirecionamento.
*   **(auth) `/login`, `/callback`, etc.:** Rotas para o fluxo de autenticação.
*   **(private) `/dashboard`, `/utentes`, `/pedidos`, `/configuracoes`, `/perfil`:** Rotas protegidas, acessíveis apenas após login.

**5.2. Proteção de Rotas:**
*   O layout `src/app/(private)/layout.tsx` verificará o estado de autenticação (via `AuthContext`).
*   Usuários não autenticados serão redirecionados para `/login`.
*   Controle de acesso baseado em roles (e.g., para `/configuracoes`) pode ser implementado no layout específico do módulo ou via Next.js Middleware (`middleware.ts`).

**5.3. Rotas Detalhadas (Exemplos):**
*   `/utentes`: Listagem de utentes.
*   `/utentes/novo`: Formulário para criar novo utente.
*   `/utentes/[id]`: Detalhe e edição de um utente específico.
*   `/utentes/[id]/servicos`: Listagem/gerenciamento de serviços de um utente.
*   `/pedidos`: Listagem de pedidos.
*   `/pedidos/novo`: Formulário para criar novo pedido.
*   `/pedidos/[id]`: Detalhe e edição de um pedido.
*   `/pedidos/[id]/documentos`: Gerenciamento de documentos do pedido.
*   `/configuracoes/categorias-servicos`: CRUD para categorias de serviços.
*   (Estruturas similares para outros sub-módulos de `pedidos` e `configuracoes`).

## 6. Qualidade de Código e Desenvolvimento

**6.1. Testes:**
*   **Vitest:** Framework de testes unitários e de componentes.
*   **React Testing Library:** Testar componentes focando no comportamento do usuário.
*   **Tipos:** Unitários (utils, hooks), Componentes (UI, páginas), Integração (fluxos de formulário).
*   **Mocking:** `vi.mock` para simular chamadas de API e dependências.

**6.2. Linting:**
*   **ESLint:** Com plugins para React, TypeScript, JSX A11y, e Prettier para evitar conflitos.
*   Configuração em `.eslintrc.js`.

**6.3. Formatação:**
*   **Prettier:** Para formatação automática de código.
*   Configuração em `.prettierrc.js`.

**6.4. Convenções:**
*   **Nomenclatura:** `kebab-case` para arquivos/pastas, `PascalCase` para componentes/tipos, `camelCase` para variáveis/funções.
*   **Git:** Conventional Commits, branches descritivas, PRs com code review.

**6.5. Hooks de Pré-Commit:**
*   **Husky + lint-staged:** Para rodar ESLint e Prettier automaticamente antes de cada commit nos arquivos modificados.

**6.6. Revisão de Código:**
*   Processo obrigatório de Pull Request para todo código novo.

Esta especificação técnica serve como um guia para o desenvolvimento do frontend, garantindo consistência, qualidade e alinhamento com as melhores práticas do ecossistema React/Next.js.

---

## Parte 2 – Especificação Funcional (Frontend)

Esta seção descreve as funcionalidades do frontend da aplicação "Simple" do ponto de vista do usuário e suas interações com a interface.

### 1. Principais Casos de Uso do Frontend

Os casos de uso do frontend refletem as interações necessárias para consumir as funcionalidades expostas pela API backend.

**Módulo de Autenticação (Grupo de Rotas `(auth)`)**

*   **CUF01: Realizar Login**
    *   **Ator:** Qualquer Usuário (Administrador, Usuário Interno, Utente)
    *   **Descrição:** O usuário informa suas credenciais para acessar as áreas privadas da aplicação.
    *   **Interação Frontend:**
        1.  Usuário acessa a página `/login`.
        2.  Visualiza um formulário com campos para "Email" (ou "Nome de Usuário") e "Senha".
        3.  Preenche os campos e clica no botão "Entrar".
        4.  Frontend envia as credenciais para o `auth.service.ts` (que chama o BFF ou API de autenticação).
        5.  Em caso de sucesso:
            *   Token JWT e dados do usuário são armazenados (via `AuthContext`/`auth.store.ts`).
            *   Usuário é redirecionado para o `/dashboard` ou página solicitada anteriormente.
        6.  Em caso de falha: Exibe mensagem de erro no formulário (e.g., "Credenciais inválidas").

**Módulo de Dashboard (Rota `/dashboard`)**

*   **CUF02: Visualizar Dashboard Principal**
    *   **Ator:** Usuário Autenticado
    *   **Descrição:** Após o login, o usuário visualiza uma página inicial com informações relevantes ou atalhos.
    *   **Interação Frontend:**
        1.  Usuário é redirecionado para `/dashboard` após login.
        2.  Visualiza um layout com `Navbar` e `Sidebar`.
        3.  O conteúdo do dashboard pode incluir:
            *   Resumo de pedidos recentes (para utentes).
            *   Pedidos pendentes de ação (para usuários internos).
            *   Atalhos para os módulos de Utentes, Pedidos, Configurações (conforme permissões).
            *   Gráficos ou estatísticas (se aplicável e definido posteriormente).

**Módulo de Utentes (Rotas em `(private)/utentes`)**

*   **CUF03: Listar e Filtrar Utentes**
    *   **Ator:** Usuário Interno, Administrador
    *   **Descrição:** Visualizar uma lista paginada de utentes cadastrados, com capacidade de busca e filtro.
    *   **Interação Frontend:**
        1.  Usuário navega para `/utentes`.
        2.  Visualiza uma tabela (`DataTable.tsx`) exibindo colunas como Nome, NIF, Email, Tipo, Estado.
        3.  Utiliza campos de filtro (e.g., input para nome, select para tipo/estado) para refinar a lista.
        4.  Utiliza controles de paginação para navegar entre as páginas de resultados.
        5.  Clica em um utente para navegar para a página de detalhe/edição (`/utentes/[id]`).
        6.  Clica no botão "Novo Utente" para ir para `/utentes/novo`.

*   **CUF04: Criar Novo Utente**
    *   **Ator:** Usuário Interno, Administrador
    *   **Descrição:** Cadastrar um novo utente no sistema.
    *   **Interação Frontend:**
        1.  Usuário navega para `/utentes/novo`.
        2.  Visualiza um formulário com todos os campos necessários para `CriarUtenteDTO` (Nome, NIF, Email, Tipo Utente, Tipo Identificação, etc.).
        3.  Campos obrigatórios são indicados. Validações de formato (email, NIF se houver) são aplicadas client-side.
        4.  Ao submeter, o frontend envia os dados para `utente.service.ts`.
        5.  Em caso de sucesso: Exibe mensagem de sucesso e redireciona para a lista de utentes ou para a página de detalhe do novo utente.
        6.  Em caso de erro (validação backend ou outros): Exibe mensagens de erro apropriadas nos campos ou globalmente no formulário.

*   **CUF05: Visualizar e Editar Utente**
    *   **Ator:** Usuário Interno, Administrador
    *   **Descrição:** Visualizar os detalhes de um utente existente e modificar suas informações.
    *   **Interação Frontend:**
        1.  Usuário navega para `/utentes/[id]`.
        2.  Visualiza um formulário pré-preenchido com os dados do utente. Alguns campos podem ser apenas leitura.
        3.  Modifica os campos desejados.
        4.  Ao submeter, o frontend envia os dados atualizados (`UpdateUtenteDTO`) para `utente.service.ts`.
        5.  Feedback de sucesso ou erro é exibido.
        6.  Pode haver uma aba ou seção para "Serviços Associados" que leva a `/utentes/[id]/servicos`.

*   **CUF06: Inativar Utente**
    *   **Ator:** Usuário Interno, Administrador
    *   **Descrição:** Marcar um utente como inativo.
    *   **Interação Frontend:**
        1.  Na página de detalhe do utente (`/utentes/[id]`) ou na lista de utentes, há um botão/opção "Inativar".
        2.  Ao clicar, um `AlertDialog.tsx` é exibido para confirmação.
        3.  Ao confirmar, `utente.service.ts` é chamado.
        4.  Feedback de sucesso ou erro é exibido. O estado do utente na UI é atualizado.

*   **CUF07: Gerenciar Serviços Associados ao Utente**
    *   **Ator:** Usuário Interno, Administrador
    *   **Descrição:** Listar, adicionar e visualizar detalhes dos serviços/objetos associados a um utente.
    *   **Interação Frontend:**
        1.  Usuário navega para `/utentes/[id]/servicos`.
        2.  Visualiza uma lista dos serviços/objetos associados.
        3.  Funcionalidade para adicionar um novo serviço/objeto (pode ser um modal ou uma nova página), chamando `utente.service.ts`.
        4.  Visualizar detalhes de um serviço/objeto específico.

**Módulo de Pedidos (Rotas em `(private)/pedidos`)**

*   **CUF08: Listar e Filtrar Pedidos**
    *   **Ator:** Usuário Interno, Administrador, Utente (visualiza apenas os seus)
    *   **Descrição:** Visualizar uma lista paginada de pedidos, com filtros.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos`.
        2.  Visualiza `DataTable.tsx` com colunas como Código Acompanhamento, Tipo de Serviço, Solicitante, Status, Data Início.
        3.  Filtros disponíveis (Tipo de Serviço, Status, Período, etc.).
        4.  Paginação.
        5.  Clique em um pedido para `/pedidos/[id]`.
        6.  Botão "Novo Pedido" para `/pedidos/novo`.

*   **CUF09: Criar Novo Pedido**
    *   **Ator:** Utente, Usuário Interno
    *   **Descrição:** Registrar uma nova solicitação de serviço.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos/novo`.
        2.  Formulário para `CreatePedidoDTO`:
            *   Seleção do Tipo de Serviço (usando `Select.tsx` populado via API de configurações).
            *   Seleção/Identificação do Cidadão (se usuário interno; pode ser um campo de busca/seleção).
            *   Campos para Origem, Prioridade (pode ter valor padrão), Observações.
        3.  Validações client-side.
        4.  Submissão para `pedido.service.ts`.
        5.  Feedback e redirecionamento.

*   **CUF10: Visualizar e Gerenciar Detalhes do Pedido**
    *   **Ator:** Usuário Interno, Administrador, Utente (visualização limitada e ações específicas)
    *   **Descrição:** Ver todos os detalhes de um pedido, atualizar informações (se permitido), e navegar para sub-seções.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos/[id]`.
        2.  Exibe informações chave do pedido (código, tipo, solicitante, status, datas).
        3.  Seções/abas para:
            *   **Detalhes/Atualização:** Formulário para `UpdatePedidoDTO` (mudar status, etapa, responsável - para usuários internos).
            *   **Histórico:** Lista de `HistoricoPedidoResponseDTO`.
            *   **Documentos:** Navega para `/pedidos/[id]/documentos`.
            *   **Pagamentos:** Navega para `/pedidos/[id]/pagamentos`.
            *   **Avaliação:** Navega para `/pedidos/[id]/avaliacao`.
        4.  Ações contextuais (e.g., "Adicionar Histórico", "Atualizar Status" - para usuários internos).

*   **CUF11: Gerenciar Documentos de um Pedido**
    *   **Ator:** Usuário Interno, Administrador, Utente (upload para seus pedidos, se permitido pelo fluxo)
    *   **Descrição:** Anexar novos documentos e visualizar/baixar documentos existentes de um pedido.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos/[id]/documentos`.
        2.  Lista de documentos existentes (`DocumentoPedidoResponseDTO`), com opção de download.
        3.  Formulário/Botão para upload de novo documento (`UploadDocumentoPedidoDTO`):
            *   Input de arquivo.
            *   Campos para nome do documento, tipo.
        4.  Chamada a `pedido.service.ts` para upload.
        5.  Atualização da lista de documentos.

*   **CUF12: Gerenciar Pagamentos de um Pedido**
    *   **Ator:** Usuário Interno, Administrador, Utente (registrar seus pagamentos, se aplicável)
    *   **Descrição:** Registrar novos pagamentos e visualizar o histórico de pagamentos de um pedido.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos/[id]/pagamentos`.
        2.  Lista de pagamentos existentes (`PagamentoPedidoResponseDTO`).
        3.  Formulário/Modal para registrar novo pagamento (`CreatePagamentoPedidoDTO`):
            *   Data, método, referência, valor, observações.
        4.  Chamada a `pedido.service.ts` ou `pagamento.service.ts`.
        5.  Usuários internos podem ter a opção de atualizar o status de um pagamento.

*   **CUF13: Avaliar Pedido Concluído**
    *   **Ator:** Utente
    *   **Descrição:** Fornecer feedback sobre um serviço concluído.
    *   **Interação Frontend:**
        1.  Usuário navega para `/pedidos/[id]/avaliacao` (ou a partir da página de detalhe do pedido, se concluído).
        2.  Formulário para `CreateAvaliacaoPedidoDTO`:
            *   Seleção de nota (e.g., estrelas, radio buttons).
            *   Campo para comentário.
        3.  Submissão para `pedido.service.ts` (ou `avaliacao.service.ts`).
        4.  Feedback. A UI deve impedir múltiplas avaliações.

**Módulo de Configurações (Rotas em `(private)/configuracoes`)**
*Acesso restrito a Administradores.*

*   **CUF14: Gerenciar (CRUD) Categorias de Serviços**
    *   **Ator:** Administrador
    *   **Interação Frontend:**
        1.  Navega para `/configuracoes/categorias-servicos`.
        2.  Visualiza `DataTable.tsx` de categorias. Botão "Nova Categoria".
        3.  Navega para `/configuracoes/categorias-servicos/novo` para formulário de criação (`CreateCategoriasServicosCommand`).
        4.  Navega para `/configuracoes/categorias-servicos/[id]` para formulário de edição.
        5.  Opção de inativar/ativar na lista ou no formulário de edição.
        6.  Chamadas para `configuracao.service.ts`.

*   **CUF15: Gerenciar (CRUD) Tipos de Serviços**
    *   **Ator:** Administrador
    *   **Interação Frontend:** Similar ao CUF14, mas para as rotas e DTOs de Tipos de Serviços. Inclui seleção de categoria.

*   **CUF16: Gerenciar (CRUD) Status de Pedidos**
    *   **Ator:** Administrador
    *   **Interação Frontend:** Similar ao CUF14, mas para as rotas e DTOs de Status de Pedidos.

*   **CUF17: Gerenciar (CRUD) Configurações Gerais**
    *   **Ator:** Administrador
    *   **Interação Frontend:** Similar ao CUF14, mas para as rotas e DTOs de Configurações Gerais (chave-valor).

**Módulo de Perfil de Usuário (Rota `(private)/perfil`)**

*   **CUF18: Visualizar e Editar Perfil do Usuário Logado**
    *   **Ator:** Usuário Autenticado
    *   **Descrição:** Permitir que o usuário visualize e edite algumas de suas informações de perfil (e.g., nome, senha).
    *   **Interação Frontend:**
        1.  Usuário navega para `/perfil`.
        2.  Visualiza informações do seu perfil (obtidas do `AuthContext` ou chamada de API específica).
        3.  Formulário para alterar dados permitidos (e.g., nome, contato, alterar senha).
        4.  Submissão para serviço apropriado (pode ser `usuario.service.ts` ou `auth.service.ts`).

### 2. Funcionalidades Esperadas por Tipo de Usuário (Frontend)

*   **Administrador do Sistema:**
    *   Acesso e controle total sobre todas as funcionalidades de CRUD nos módulos de Configurações, Utentes e Pedidos.
    *   Capacidade de visualizar e gerenciar todos os dados.
    *   Interface clara para todas as opções de configuração.
*   **Usuário Interno/Funcionário:**
    *   Interface para listar, criar e editar Utentes.
    *   Interface para listar, criar (em nome de utentes) e gerenciar o ciclo de vida dos Pedidos (atualizar status, etapas, documentos, pagamentos).
    *   Visualização das configurações do sistema (sem permissão de edição).
*   **Utente/Cidadão (Usuário Externo):**
    *   Interface intuitiva para criar novos Pedidos.
    *   Painel para acompanhar seus Pedidos (status, histórico, documentos).
    *   Capacidade de anexar documentos e registrar pagamentos para seus Pedidos, conforme o fluxo do serviço.
    *   Interface para avaliar Pedidos concluídos.
    *   Gerenciamento do seu próprio Perfil.

### 3. Principais Regras de Negócio (Visíveis/Validadas no Frontend)

*   **Validação de Formulários:**
    *   Campos obrigatórios devem ser preenchidos antes da submissão.
    *   Validação de formato (email, NIF se houver máscara/regex, datas).
    *   Validação de tamanho/comprimento de campos de texto.
    *   Feedback visual claro para erros de validação em cada campo.
*   **Controle de Acesso Visual:**
    *   Botões e opções de menu devem ser exibidos/ocultados ou habilitados/desabilitados com base no role e permissões do usuário logado (e.g., botão "Configurações" visível apenas para Admin).
*   **Consistência de Dados em Selects:**
    *   Dropdowns (e.g., para Tipo de Serviço, Categoria) devem ser populados com dados atuais da API.
*   **Fluxo de Pedido:**
    *   A UI deve refletir o status atual do pedido e permitir apenas ações válidas para esse status (e.g., não permitir avaliar um pedido não concluído).
*   **Prevenção de Dupla Submissão:**
    *   Desabilitar botões de submit após o primeiro clique enquanto a requisição está em processamento.
*   **Feedback ao Usuário:**
    *   Mensagens claras de sucesso, erro e carregamento para todas as operações assíncronas. (Usar Toasts/Notifications).

### 4. Validações e Comportamentos Esperados nos Formulários e Interações da UI

*   **Responsividade:** Todos os formulários e listas devem ser responsivos para diferentes tamanhos de tela (desktop, tablet).
*   **Acessibilidade (A11y):**
    *   Uso correto de semântica HTML.
    *   Labels associadas a inputs.
    *   Navegação por teclado funcional.
    *   Contraste de cores adequado.
    *   Radix UI ajuda significativamente com ARIA attributes e comportamento.
*   **Indicadores de Carregamento:**
    *   Skeletons (`Skeleton.tsx`) para carregamento de seções de dados.
    *   Spinners/Loaders em botões ou globalmente durante chamadas de API.
*   **Tratamento de Erros:**
    *   Exibição de mensagens de erro amigáveis e informativas, tanto para erros de validação client-side quanto para erros retornados pela API.
*   **Confirmações:**
    *   Uso de `AlertDialog.tsx` para ações destrutivas (e.g., inativar utente, excluir item de configuração).
*   **Tabelas de Dados (`DataTable.tsx`):**
    *   Paginação (client-side ou server-side, dependendo do volume de dados e da API).
    *   Filtros por coluna (se aplicável).
    *   Ordenação por colunas clicáveis.
    *   Busca global na tabela.
*   **Navegação:**
    *   Clara e intuitiva usando `Sidebar` e `Navbar`.
    *   Breadcrumbs para indicar a localização do usuário na hierarquia de páginas, se necessário.
*   **Formulários:**
    *   Autofoco no primeiro campo do formulário.
    *   Submissão via tecla "Enter" em campos de input (quando aplicável).
    *   Limpeza de campos após submissão bem-sucedida (se for um formulário de criação).
    *   Persistência de estado em formulários longos pode ser considerada (embora não prioritária inicialmente).

---

## Parte 3 – Plano de Execução do Projeto (Frontend)

Este plano de execução foca no desenvolvimento do frontend React/Next.js para a aplicação "Simple".

**Fase 1: Setup Inicial e Arquitetura Base**
*   **Objetivo:** Configurar o ambiente de desenvolvimento, a estrutura do projeto Next.js, e as ferramentas de qualidade de código. Implementar a arquitetura base de layout e autenticação.
*   **Entregáveis Esperados:**
    *   Repositório Git com projeto Next.js inicializado (usando TypeScript).
    *   Configuração do TailwindCSS.
    *   Configuração de ESLint, Prettier, Husky e lint-staged.
    *   Estrutura de pastas inicial (conforme definido na especificação técnica).
    *   Layout raiz (`src/app/layout.tsx`) e layout privado (`src/app/(private)/layout.tsx`) básicos.
    *   Configuração inicial do `AuthContext` ou store de autenticação.
    *   Página de Login (`src/app/(auth)/login/page.tsx`) com lógica básica de chamada ao `auth.service.ts` (mockado inicialmente se o backend não estiver pronto).
    *   Configuração do `axios.ts` e do proxy BFF inicial.
    *   CI/CD pipeline básico configurado (lint, test, build).
*   **Recursos/Perfis Envolvidos:** Desenvolvedor Frontend Líder, Desenvolvedores Frontend.
*   **Duração Estimada:** 1-2 semanas.
*   **Critérios de Aceitação:**
    *   Projeto Next.js roda localmente.
    *   Ferramentas de linting e formatação funcionam e são aplicadas nos commits.
    *   Estrutura de pastas e layouts básicos implementados.
    *   Fluxo de login pode ser simulado, e rotas privadas são protegidas.
    *   Pipeline de CI executa build e lint com sucesso.

**Fase 2: Desenvolvimento do UI Kit (Componentes Primitivos e Compartilhados)**
*   **Objetivo:** Implementar os componentes reutilizáveis definidos na especificação técnica, utilizando Radix UI e TailwindCSS.
*   **Entregáveis Esperados:**
    *   Implementação dos componentes listados em `src/components/ui/` (Button, Input, Card, Modal, Select, Table, DatePicker, etc.).
    *   Implementação de componentes compartilhados básicos em `src/components/shared/` (e.g., `PageHeader.tsx`).
    *   (Opcional, mas recomendado) Storybook configurado com histórias para cada componente do UI Kit.
    *   Testes unitários e de componente para o UI Kit.
*   **Recursos/Perfis Envolvidos:** Desenvolvedores Frontend.
*   **Duração Estimada:** 2-3 semanas (pode ocorrer em paralelo com o final da Fase 1 e início da Fase 3).
*   **Critérios de Aceitação:**
    *   Componentes implementados conforme especificado, responsivos e acessíveis.
    *   Componentes visualmente alinhados com o design proposto (se houver).
    *   Cobertura de testes adequada para os componentes.
    *   Componentes utilizáveis nas futuras telas da aplicação.

**Fase 3: Desenvolvimento por Módulo (Ciclos Iterativos)**
*   **Objetivo:** Desenvolver as interfaces e funcionalidades para cada módulo principal (Utentes, Pedidos, Configurações), utilizando o UI Kit e integrando com os serviços da API.
*   **Entregáveis Esperados (por módulo/sprint):**
    *   Telas de listagem, criação, edição e visualização para o módulo.
    *   Integração completa com os respectivos `*.service.ts` para chamadas à API.
    *   Gerenciamento de estado local e global (via Context/Store) para o módulo.
    *   Formulários com validação client-side (e feedback de validação server-side).
    *   Testes de componente e de integração para as telas e fluxos do módulo.
    *   Atualização da documentação de componentes específicos do módulo, se houver.
*   **Ordem Sugerida dos Módulos:**
    1.  **Módulo de Utentes:** Listagem, formulário de criação/edição, visualização de detalhes, gerenciamento de serviços associados.
    2.  **Módulo de Configurações:** (Requer role de Admin) CRUD para Categorias, Tipos de Serviço, Status de Pedido, Configurações Gerais.
    3.  **Módulo de Pedidos:** Listagem, formulário de criação/edição, visualização de detalhes (incluindo histórico), gerenciamento de documentos, pagamentos e avaliações.
    4.  **Dashboard e Perfil do Usuário.**
*   **Recursos/Perfis Envolvidos:** Desenvolvedores Frontend, QA/Testadores (para testes funcionais).
*   **Duração Estimada:** 6-10 semanas (dividido em sprints de 2 semanas por módulo ou conjunto de funcionalidades).
*   **Critérios de Aceitação (por módulo/sprint):**
    *   Funcionalidades do módulo implementadas conforme os casos de uso e requisitos.
    *   Integração com a API backend funcionando corretamente.
    *   Validações de formulário e feedback ao usuário implementados.
    *   Testes passam e cobertura mínima é mantida.
    *   Demonstração bem-sucedida das funcionalidades do módulo.

**Fase 4: Testes de Integração e E2E**
*   **Objetivo:** Garantir que todos os módulos funcionam corretamente em conjunto e que os fluxos de ponta a ponta estão corretos.
*   **Entregáveis Esperados:**
    *   Plano de testes de integração e E2E.
    *   Scripts de teste E2E (usando ferramentas como Playwright ou Cypress, se o projeto comportar).
    *   Relatórios de execução de testes.
    *   Correção de bugs identificados.
*   **Recursos/Perfis Envolvidos:** QA/Testadores, Desenvolvedores Frontend.
*   **Duração Estimada:** 2-3 semanas (pode sobrepor com o final da Fase 3).
*   **Critérios de Aceitação:**
    *   Principais fluxos de usuário testados de ponta a ponta.
    *   Integração entre diferentes módulos validada.
    *   Nível de defeitos críticos/altos zerado ou aceitável.

**Fase 5: Otimização e Polimento**
*   **Objetivo:** Revisar a aplicação para otimizações de performance, melhorias de UX, e garantir a qualidade final antes do deploy.
*   **Entregáveis Esperados:**
    *   Relatório de performance (Lighthouse, Web Vitals).
    *   Aplicação com performance otimizada (code splitting, lazy loading de imagens/componentes já são bem gerenciados pelo Next.js, mas pode haver otimizações específicas).
    *   Revisão final de acessibilidade.
    *   Correções de bugs menores e ajustes de UI/UX.
*   **Recursos/Perfis Envolvidos:** Desenvolvedores Frontend, Designer UX/UI (se houver), QA/Testadores.
*   **Duração Estimada:** 1-2 semanas.
*   **Critérios de Aceitação:**
    *   Métricas de performance dentro dos limites aceitáveis.
    *   Aplicação estável e com boa experiência de usuário.
    *   Checklist de qualidade final aprovado.

**Fase 6: Preparação para Deploy e Documentação Final**
*   **Objetivo:** Preparar a aplicação para o deploy em produção e finalizar a documentação.
*   **Entregáveis Esperados:**
    *   Build de produção da aplicação Next.js.
    *   Configuração de variáveis de ambiente para produção.
    *   Documentação do usuário final (se aplicável).
    *   Documentação técnica final do frontend atualizada.
    *   Plano de deploy.
*   **Recursos/Perfis Envolvidos:** Desenvolvedor Frontend Líder, DevOps (se houver).
*   **Duração Estimada:** 1 semana.
*   **Critérios de Aceitação:**
    *   Build de produção gerado com sucesso.
    *   Documentação completa e revisada.
    *   Plano de deploy aprovado.

**Fase 7: Deploy e Pós-Deploy (Suporte Inicial)**
*   **Objetivo:** Implantar a aplicação em produção e fornecer suporte inicial.
*   **Entregáveis Esperados:**
    *   Aplicação frontend implantada em produção.
    *   Monitoramento inicial da aplicação em produção.
    *   Resolução de problemas urgentes pós-deploy.
*   **Recursos/Perfis Envolvidos:** Desenvolvedor Frontend Líder, DevOps, Equipe de Suporte.
*   **Duração Estimada:** Contínuo (suporte inicial focado nas primeiras 1-2 semanas pós-deploy).
*   **Critérios de Aceitação:**
    *   Aplicação funcionando corretamente em produção.
    *   Usuários conseguem acessar e utilizar a aplicação.
    *   Plano de rollback testado (se aplicável).

Este plano de execução é uma sugestão e deve ser adaptado à metodologia de desenvolvimento do projeto (e.g., Scrum, Kanban) e aos recursos disponíveis. As fases podem ter sobreposições. A comunicação constante com a equipe de backend é crucial, especialmente durante a Fase 3, para alinhar a integração com a API.
