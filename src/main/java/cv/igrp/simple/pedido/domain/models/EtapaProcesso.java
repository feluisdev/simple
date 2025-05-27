package cv.igrp.simple.pedido.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EtapaProcesso {

    private Integer id;
    private TipoPedido tipoPedido;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer ordem;
    private Integer tempoEstimado;
    private boolean requerDocumento;
    private boolean requerPagamento;
    private boolean requerAprovacao;
    private EtapaProcesso etapaAnterior;

}
