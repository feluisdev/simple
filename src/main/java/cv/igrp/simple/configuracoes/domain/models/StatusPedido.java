package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

@Getter
public class StatusPedido {

    private Integer id; // ID sequencial, geralmente gerenciado pelo banco de dados
    private Identificador statusPedidoUuid; // Identificador UUID único para o status do pedido
    private String nome;
    private String codigo;
    private String descricao;
    private String cor;
    private String icone;
    private boolean fim;
    private boolean ativo;
    private boolean visivelPortal;
    private Integer ordem;

    // Construtor privado para controlar a criação de instâncias via métodos de factory.
    private StatusPedido(Integer id, Identificador statusPedidoUuid, String nome, String codigo, String descricao,
                         String cor, String icone,
                         boolean fim, boolean ativo,
                         boolean visivelPortal, Integer ordem) {
        if (statusPedidoUuid == null) {
            throw new IllegalArgumentException("O Identificador UUID (statusPedidoUuid) não pode ser nulo.");
        }
        validarNome(nome);
        validarCodigo(codigo);
        validarDescricao(descricao);
        // Cor e Icone podem ser nulos ou vazios, dependendo das regras de negócio. Por enquanto, não há validação estrita.

        this.id = id;
        this.statusPedidoUuid = statusPedidoUuid;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.cor = cor;
        this.icone = icone;
        this.fim = fim;
        this.ativo = ativo;
        this.ordem = ordem;
        this.visivelPortal = visivelPortal;
    }

    // Métodos de validação privados
    private static void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
    }

    private static void validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código não pode ser nulo ou vazio.");
        }
        // Adicionar aqui outras validações específicas para o formato do código, se necessário.
        // Ex: if (!codigo.matches("^[A-Z_]+$")) {
        // throw new IllegalArgumentException("Código deve conter apenas letras maiúsculas e underscores.");
        // }
    }

    private static void validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }
    }

    /**
     * Cria uma nova instância de StatusPedido.
     * O id sequencial (Integer) é inicializado como null.
     * Um novo statusPedidoUuid (Identificador) é gerado.
     * O status é definido como ativo por padrão.
     *
     * @param nome O nome do status do pedido.
     * @param codigo O código do status do pedido.
     * @param descricao Uma descrição mais detalhada do status.
     * @param cor A cor associada ao status (ex: hexadecimal).
     * @param icone O ícone associado ao status.
     * @param fim Indica se este é um status final de pedido.
     * @return Uma nova instância de StatusPedido.
     */
    public static StatusPedido criarNovo(String nome, String codigo, String descricao,
                                         String cor, String icone, boolean fim,
                                         boolean visivelPortal, Integer ordem) {
        Identificador novoUuid = Identificador.gerarNovo();
        // O 'id' (Integer) é null aqui, pois seria atribuído pela camada de persistência.
        // 'ativo' é true por padrão para novos status.
        return new StatusPedido(null, novoUuid, nome, codigo, descricao, cor, icone, fim, true, visivelPortal, ordem);
    }

    /**
     * Reconstrói uma instância de StatusPedido a partir de dados existentes.
     *
     * @param id O ID sequencial existente do status.
     * @param statusPedidoUuid O Identificador UUID existente do status.
     * @param nome O nome existente do status.
     * @param codigo O código existente do status.
     * @param descricao A descrição existente do status.
     * @param cor A cor existente do status.
     * @param icone O ícone existente do status.
     * @param fim O estado 'fim' existente do status.
     * @param ativo O estado de ativação existente.
     * @return Uma instância reconstruída de StatusPedido.
     */
    public static StatusPedido reconstruir(Integer id, Identificador statusPedidoUuid,
                                           String nome, String codigo,
                                           String descricao, String cor, String icone,
                                           boolean fim, boolean ativo, boolean visivelPortal, Integer ordem) {
        return new StatusPedido(id, statusPedidoUuid, nome, codigo, descricao, cor, icone, fim, ativo, visivelPortal, ordem);
    }


    public void inativar() {
        this.ativo = false;
        this.visivelPortal = false;
    }

    public void ativar() {
        this.ativo = true;
        this.visivelPortal = false;
    }


    public void atualizarDetalhes(String nome, String descricao, String cor, String icone,
                                  boolean fim, boolean visivelPortal, boolean ativo, Integer ordem) {
        validarNome(nome); // Reutiliza a validação existente
        validarDescricao(descricao); // Reutiliza a validação existente
        // Cor e Icone podem ser nulos ou vazios, conforme definido anteriormente, sem validação estrita aqui.

        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
        this.icone = icone;
        this.fim = fim;
        this.visivelPortal = visivelPortal;
        this.ativo = ativo;
        this.ordem = ordem;

    }


}
