package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.utente.domain.models.Utente;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Pedido {

    private final Integer id;
    private Identificador pedidoUuid;
    private CodigoAcompanhamento codigoAcompanhamento;
    private String observacao;
    private StatusPedido status;
    private TipoServico tipoServico;
    private Utente utente;
    private Integer etapaAtualId;
    private LocalDate dataSolicitacao;
    private LocalDate dataPrevisaoConclusao;
    private String origem;
    private Integer prioridade;
    private BigDecimal valorTotal;

    private List<Avaliacao> avaliacoes;
    private List<HistoricoPedido> historicoPedido;
    private Pagamento pagamento;
    private List<Documento> documentos;

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
                   BigDecimal valorTotal,
                   List<Avaliacao> avaliacoes,
                   List<HistoricoPedido> historicoPedido,
                   List<Documento> documentos,
                   Pagamento pagamento) {

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
        this.avaliacoes = avaliacoes != null ? avaliacoes : new ArrayList<>();
        this.historicoPedido = historicoPedido != null ? historicoPedido : new ArrayList<>();
        this.documentos = documentos != null ? documentos : new ArrayList<>();
        this.pagamento = pagamento;
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
                valorTotal,
                null,
                null,
                null,
                null
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
                                     BigDecimal valorTotal,
                                     List<Avaliacao> avaliacoes,
                                     List<HistoricoPedido> historicoPedido,
                                     Pagamento pagamento,
                                     List<Documento> documentos
    ) {

        return new Pedido(id, pedidoUuid, codigoAcompanhamento, observacao,
                status, tipoServico, utente, etapaAtualId,
                dataSolicitacao, dataPrevisaoConclusao,
                origem,
                prioridade,
                valorTotal, avaliacoes, historicoPedido, documentos, pagamento);
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

    public void avaliarPedido(String comentario, Integer nota) {

        if (this.avaliacoes == null) {
            this.avaliacoes = new ArrayList<>();
        }

        var avaliacao = Avaliacao.criarNovo(comentario, nota, this);
        this.avaliacoes.add(avaliacao);

    }

    public void registarHistorico(String observacao, StatusPedido statusPedido) {

        if (this.historicoPedido == null) {
            historicoPedido = new ArrayList<>();
        }

        var historico = HistoricoPedido.criarNovo(observacao, this, statusPedido);
        this.historicoPedido.add(historico);

        this.status = statusPedido;
    }

    public void registrarPagamento(String metodoPagamento,
                                   String referenciaPagamento,
                                   String observacao,
                                   BigDecimal valor) {

        if (this.pagamento != null) {
            throw IgrpResponseStatusException.badRequest("Pagamento já registrado para este pedido.");
        }

        this.pagamento = Pagamento.criarNovo(
                metodoPagamento,
                referenciaPagamento,
                observacao,
                valor,
                this
        );
    }

    public void confirmarPagamento(StatusPedido statusPago) {
        if (this.pagamento == null) {
            throw IgrpResponseStatusException.badRequest("Nenhum pagamento registrado para este pedido.");
        }

        if (this.pagamento.isCancelado()) {

            throw IgrpResponseStatusException.badRequest("Pagamento se encontra cancelado.");
        }

        this.pagamento.confirmar();

        var historico = HistoricoPedido.criarNovo(observacao, this, statusPago);
        this.historicoPedido.add(historico);
        this.status = statusPago;
    }

    public void cancelarPagamento() {

        this.pagamento.cancelar();
    }

    public void adicionarDocumento(String nome, String descricao, String tipoDocumento,
                                   String caminhoArquivo) {

        if (this.documentos == null) {
            this.documentos = new ArrayList<>();
        }

        var documento = Documento.criarNovo(nome, descricao, tipoDocumento,
                caminhoArquivo, LocalDate.now(), this);

        this.documentos.add(documento);
    }

    // para reconstruir o pagamento de um pedido existente
    public void adicionarPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void atualizar(TipoServico tipoServico, Utente utente, String observacoes,
                          BigDecimal valorTotal, String origem,
                          Integer prioridade) {


        if (this.status.getCodigo() != "NOVO") {
            //todo: criar exceção específica para atualização de pedidos
            //throw IgrpResponseStatusException.badRequest("Não é possível atualizar um pedido que não está no estado 'Novo'");
        }

        this.tipoServico = Objects.requireNonNull(tipoServico, "Tipo de Serviço não pode ser nulo.");
        this.utente = Objects.requireNonNull(utente, "Utente não pode ser nulo.");
        this.observacao = observacoes;
        this.valorTotal = valorTotal != null ? valorTotal : BigDecimal.ZERO;
        this.origem = origem != null ? origem : "Desconhecida";
        this.prioridade = prioridade != null ? prioridade : 0;
    }


}
