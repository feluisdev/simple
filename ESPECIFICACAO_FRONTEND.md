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
