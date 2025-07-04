package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
public class Pedido {

    private final Integer id;
    private final Identificador pedidoUuid;
    private final CodigoAcompanhamento codigoAcompanhamento;
    private final String observacao;
    private final StatusPedido status;
    private final TipoServico tipoServico;
    private final Utente utente;
    private final Integer etapaAtualId;
    private final LocalDate dataSolicitacao;
    private final LocalDate dataPrevisaoConclusao;
    private String origem;
    private Integer prioridade;
    private BigDecimal valorTotal;

    private List<Avaliacao> avaliacoes;

    private Pedido(Integer id,
                   Identificador pedidoUuid,
                   CodigoAcompanhamento codigoAcompanhamento,
                   String observacao,
                   StatusPedido status,
                   TipoServico tipoServico,
                   Utente utente,
                   Integer etapaAtualId,
                   LocalDate dataSolicitacao,
                   LocalDate dataPrevisaoConclusao,
                   String origem,
                   Integer prioridade,
                   BigDecimal valorTotal) {

        this.id = id;
        this.pedidoUuid = Objects.requireNonNull(pedidoUuid);
        this.codigoAcompanhamento = Objects.requireNonNull(codigoAcompanhamento);
        this.observacao = observacao;
        this.status = Objects.requireNonNull(status);
        this.tipoServico = Objects.requireNonNull(tipoServico);
        this.utente = Objects.requireNonNull(utente);
        this.etapaAtualId = etapaAtualId;
        this.dataSolicitacao = Objects.requireNonNull(dataSolicitacao);
        this.dataPrevisaoConclusao = dataPrevisaoConclusao;
        this.origem = origem;
        this.prioridade = prioridade;
        this.valorTotal = valorTotal;
    }

    public static Pedido criarNovo(StatusPedido status,
                                   TipoServico tipoServico,
                                   Utente utente,
                                   String observacao,
                                   Integer etapaAtualId,
                                   String origem,
                                   Integer prioridade,
                                   BigDecimal valorTotal
                                   ) {

        LocalDate hoje = LocalDate.now();
        LocalDate previsao = calcularDataPrevisao(hoje, tipoServico.getPrazoEstimado());

        return new Pedido(
                null,
                Identificador.gerarNovo(),
                CodigoAcompanhamento.gerar(),
                observacao,
                status,
                tipoServico,
                utente,
                etapaAtualId,
                hoje,
                previsao,
                 origem,
                 prioridade,
                 valorTotal
        );
    }

    public static Pedido reconstruir(Integer id,
                                     Identificador pedidoUuid,
                                     CodigoAcompanhamento codigoAcompanhamento,
                                     String observacao,
                                     StatusPedido status,
                                     TipoServico tipoServico,
                                     Utente utente,
                                     Integer etapaAtualId,
                                     LocalDate dataSolicitacao,
                                     LocalDate dataPrevisaoConclusao,
                                     String origem,
                                     Integer prioridade,
                                     BigDecimal valorTotal) {

        return new Pedido(id, pedidoUuid, codigoAcompanhamento, observacao,
                status, tipoServico, utente, etapaAtualId,
                dataSolicitacao, dataPrevisaoConclusao,
                 origem,
                 prioridade,
                 valorTotal);
    }
    private static LocalDate calcularDataPrevisao(LocalDate dataInicio, Integer prazoEstimado) {
        if (prazoEstimado == null || prazoEstimado <= 0) {
            return dataInicio;
        }

        int semanasCompletas = prazoEstimado / 5;
        int diasRestantes = prazoEstimado % 5;

        LocalDate data = dataInicio.plusDays(semanasCompletas * 7L);

        while (diasRestantes > 0) {
            data = data.plusDays(1);
            if (data.getDayOfWeek() != DayOfWeek.SATURDAY && data.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasRestantes--;
            }
        }

        return data;
    }

    public void avaliarPedido(String comentario, Integer nota){

        if (avaliacoes==null){
            avaliacoes = new ArrayList<>();
        }

        var avaliacao = Avaliacao.criarNovo(comentario, nota, this);
        this.avaliacoes.add(avaliacao);

    }

    public List<Avaliacao> getAvaliacoes() {
        return Collections.unmodifiableList(avaliacoes);
    }


}
