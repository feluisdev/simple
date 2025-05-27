package cv.igrp.simple.pedido.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TipoPedido {

    private final String codigo;
    private final String nome;
    private final String descricao;
    private final int prazoEstimado;
    private final int valorBase;
    private final boolean requerVistoria;
    private final boolean requerAnaliseTecnica;
    private final boolean requerAprovacao;
    private final boolean disponivelPortal;
    private final boolean ativo;
    private final CategoriaPedido categoria;
}
