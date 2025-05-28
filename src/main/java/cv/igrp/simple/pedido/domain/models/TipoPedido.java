package cv.igrp.simple.pedido.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class TipoPedido {

    private Integer id;
    private final String codigo;
    private final String nome;
    private final String descricao;
    private final Integer prazoEstimado;
    private final Integer valorBase;
    private final boolean requerVistoria;
    private final boolean requerAnaliseTecnica;
    private final boolean requerAprovacao;
    private final boolean disponivelPortal;
    private final boolean ativo;
    private final CategoriaPedido categoria;

    private final List<EtapaProcesso> etapas;


    public TipoPedido(
            Integer id,
            String codigo,
            String nome,
            String descricao,
            Integer prazoEstimado,
            Integer valorBase,
            boolean requerVistoria,
            boolean requerAnaliseTecnica,
            boolean requerAprovacao,
            boolean disponivelPortal,
            boolean ativo,
            CategoriaPedido categoria,
            List<EtapaProcesso> etapas
    ) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.prazoEstimado = prazoEstimado;
        this.valorBase = valorBase;
        this.requerVistoria = requerVistoria;
        this.requerAnaliseTecnica = requerAnaliseTecnica;
        this.requerAprovacao = requerAprovacao;
        this.disponivelPortal = disponivelPortal;
        this.ativo = ativo;
        this.categoria = categoria;
        this.etapas = etapas != null ? etapas : new ArrayList<>();
    }

    public EtapaProcesso getFirstEtapa() {
        return etapas.stream()
                .filter(etapa -> etapa.getOrdem() != null && etapa.getOrdem() == 1)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma etapa com ordem 1 foi encontrada para o tipo de pedido."));
    }

}
