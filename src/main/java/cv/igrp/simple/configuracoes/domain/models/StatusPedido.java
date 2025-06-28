package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

@Getter
public class StatusPedido {

    private Integer id;
    private Identificador statusPedidoUuid;
    private String codigo;
    private String descricao;
    private boolean ativo;

    // Construtor privado para controlar a criação de instâncias via métodos de factory.
    // Agora recebe um Identificador.
    private StatusPedido(Integer id, Identificador statusPedidoUuid, String codigo, String descricao, boolean ativo) {
        if (statusPedidoUuid == null) {
            throw new IllegalArgumentException("uuid não pode ser nulo.");
        }
        validarCodigo(codigo);
        validarDescricao(descricao);
        this.id = id;
        // O atributo 'codigo' é mantido como String por simplicidade inicial.
        // Poderia ser refatorado para um Objeto de Valor (ex: CodigoStatusPedido)
        // se regras de formato mais complexas ou comportamentos específicos fossem necessários.
        // Por exemplo, para garantir que o código seja sempre maiúsculo ou siga um padrão específico.
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // Métodos de validação privados
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
     * O ID é gerado automaticamente e o status é definido como ativo por padrão.
     *
     * @param codigo O código do status do pedido (ex: "PENDENTE", "EM_PROCESSAMENTO").
     * @param descricao Uma descrição mais detalhada do status.
     * @return Uma nova instância de StatusPedido.
     */
    public static StatusPedido criarNovo(String codigo, String descricao) {
        // As validações de 'codigo' e 'descricao' são tratadas no construtor privado.
        Identificador novoId = Identificador.gerarNovo(); // Usa o VO para gerar novo ID
        return new StatusPedido(null, novoId, codigo, descricao, true);
    }

    /**
     * Reconstrói uma instância de StatusPedido a partir de dados existentes.
     * Este método é útil ao recriar a entidade a partir de dados de persistência.
     *
     * @param id O ID existente do status.
     * @param codigo O código existente do status.
     * @param descricao A descrição existente do status.
     * @param ativo O estado de ativação existente.
     * @return Uma instância reconstruída de StatusPedido.
     */
    // O método reconstruir agora espera um Identificador.
    // Se estivermos reconstruindo a partir de uma String ou UUID puro vindo da persistência,
    // a camada de infraestrutura (repositório) será responsável por converter para Identificador
    // antes de chamar este método.
    public static StatusPedido reconstruir(Integer id, Identificador statusPedidoUuid, String codigo, String descricao, boolean ativo) {
        // As validações de 'codigo' e 'descricao' são tratadas no construtor privado,
        // garantindo a consistência dos dados reconstruídos.
        // A validação de 'id' (não nulo) também é feita no construtor.
        return new StatusPedido(id, statusPedidoUuid, codigo, descricao, ativo);
    }


}
