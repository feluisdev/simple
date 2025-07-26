package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.configuracoes.infrastructure.mappers.TipoServicoMapper;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;
import cv.igrp.simple.pedidos.domain.models.*;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {


    private final UtenteMapper utenteMapper;
    private final StatusPedidoMapper statusPedidoMapper;
    private final TipoServicoMapper tipoServicoMapper;
    private final AvaliacaoMapper avaliacaoMapper;
    private final HistoricoPedidoMapper historicoPedidoMapper;
    private final PagamentoPedidoMapper pagamentoPedidoMapper;
    private final DocumentoPedidoMapper documentoPedidoMapper;


    public Pedido toLightDomain(PedidoEntity entity) {

        return Pedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getPedidoUuid()),
                CodigoAcompanhamento.of(entity.getCodigoAcompanhamento()),
                entity.getObservacao(),
                statusPedidoMapper.toDomain(entity.getStatusId()),
                tipoServicoMapper.toDomain(entity.getTipoServicoId()),
                utenteMapper.toDomain(entity.getUtenteId() != null ? entity.getUtenteId() : null),
                entity.getEtapaAtualId() != null ? entity.getEtapaAtualId().getId() : null,
                entity.getDataInicio(),
                entity.getDataPrevisao(),
                entity.getOrigem(),
                entity.getPrioridade(),
                entity.getValorTotal(),
                null,
                null,
                null,
                null
        );
    }

    public Pedido toDomain(PedidoEntity entity) {

        var pedido = Pedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getPedidoUuid()),
                CodigoAcompanhamento.of(entity.getCodigoAcompanhamento()),
                entity.getObservacao(),
                statusPedidoMapper.toDomain(entity.getStatusId()),
                tipoServicoMapper.toDomain(entity.getTipoServicoId()),
                utenteMapper.toDomain(entity.getUtenteId() != null ? entity.getUtenteId() : null),
                entity.getEtapaAtualId() != null ? entity.getEtapaAtualId().getId() : null,
                entity.getDataInicio(),
                entity.getDataPrevisao(),
                entity.getOrigem(),
                entity.getPrioridade(),
                entity.getValorTotal(),
                null,
                null,
                entity.getPagamento()!=null ? pagamentoPedidoMapper.toDomain(entity.getPagamento()): null,
                null
        );

        if (entity.getAvaliacoes() != null) {
            List<Avaliacao> avaliacoes = entity.getAvaliacoes().stream()
                    .map(avaliacaoEntity -> avaliacaoMapper.toDomainWithPedido(avaliacaoEntity, pedido))
                    .toList();
            pedido.getAvaliacoes().addAll(avaliacoes);
        }
        if (entity.getHistoricopedidos() != null) {
            List<HistoricoPedido> historicoPedidos = entity.getHistoricopedidos().stream()
                    .map(historicoPedidoEntity -> historicoPedidoMapper.toDomainWithPedido(historicoPedidoEntity, pedido))
                    .toList();
            pedido.getHistoricoPedido().addAll(historicoPedidos);
        }


        if (entity.getPagamento() != null) {
           var  pagamento = pagamentoPedidoMapper.toDomainWithPedido(entity.getPagamento(), pedido);
           pedido.adicionarPagamento(pagamento);
        }

        if (entity.getDocumentos() != null) {
            List<Documento> documentos = entity.getDocumentos().stream()
                    .map(documentoEntity -> documentoPedidoMapper.toDomainWithPedido(documentoEntity, pedido))
                    .toList();
            pedido.getDocumentos().addAll(documentos);
        }

        return pedido;
    }

    public PedidoEntity toEntity(Pedido domain) {
        var entity = new PedidoEntity();

        if (domain.getId() != null)
            entity.setId(domain.getId());
        entity.setPedidoUuid(domain.getPedidoUuid().getValor());
        entity.setCodigoAcompanhamento(domain.getCodigoAcompanhamento().getValor());
        entity.setObservacao(domain.getObservacao());
        entity.setStatusId(statusPedidoMapper.toEntity(domain.getStatus()));
        entity.setTipoServicoId(tipoServicoMapper.toEntity(domain.getTipoServico()));
        entity.setUtenteId(utenteMapper.toEntity(domain.getUtente()));
        entity.setEtapaAtualId(null); // todo resolve this later
        entity.setDataInicio(domain.getDataSolicitacao());
        entity.setDataPrevisao(domain.getDataPrevisaoConclusao());
        entity.setOrigem(domain.getOrigem());
        entity.setPrioridade(domain.getPrioridade());
        entity.setValorTotal(domain.getValorTotal());
        entity.setUserCriacaoId(1); // todo resolve this later

        if (domain.getAvaliacoes() != null) {
            List<AvaliacaoPedidoEntity> avaliacaoEntities = domain.getAvaliacoes().stream()
                    .map(avaliacao -> avaliacaoMapper.toEntity(avaliacao, entity))
                    .toList();
            entity.setAvaliacoes(avaliacaoEntities);
        }

        if (domain.getHistoricoPedido() != null) {
            List<HistoricoPedidoEntity> historicoPedidoEntities = domain.getHistoricoPedido().stream()
                    .map(historico -> historicoPedidoMapper.toEntity(historico, entity))
                    .toList();
            entity.setHistoricopedidos(historicoPedidoEntities);
        }


       /* if (domain.getPagamento() != null) {
            Pagamento pagamento = domain.getPagamento();
            PagamentoPedidoEntity pagamentoEntity = pagamentoPedidoMapper.toEntity(pagamento, entity);
            entity.setPagamento(pagamentoEntity);
        }*/

        if (domain.getDocumentos() != null) {
            List<DocumentoPedidoEntity> documentoEntities = domain.getDocumentos().stream()
                    .map(documento -> documentoPedidoMapper.toEntity(documento, entity))
                    .toList();
            entity.setDocumentos(documentoEntities);
        }


        return entity;
    }

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setId(pedido.getId());
        dto.setPedidoId(pedido.getPedidoUuid() != null ? pedido.getPedidoUuid().getValor().toString() : null);
        dto.setCodigoAcompanhamento(pedido.getCodigoAcompanhamento() != null ? pedido.getCodigoAcompanhamento().getValor() : null);

        if (pedido.getTipoServico() != null) {
            dto.setTipoServicoId(pedido.getTipoServico().getTipoServicoUuid().getStringValor());
            dto.setTipoServicoNome(pedido.getTipoServico().getNome());
        }

        if (pedido.getUtente() != null) {
            dto.setUtenteId(pedido.getUtente().getId() != null ? pedido.getUtente().getId().toString() : null);
            dto.setUtenteNome(pedido.getUtente().getNome());
        }

        dto.setEtapaAtualId(pedido.getEtapaAtualId() != null ? pedido.getEtapaAtualId().toString() : null);
        dto.setEtapaAtualNome(null); // TODO: Ajustar quando o domínio tiver essa informação

        if (pedido.getStatus() != null) {
            dto.setStatusId(pedido.getStatus().getId() != null ? pedido.getStatus().getId().toString() : null);
            dto.setStatusNome(pedido.getStatus().getNome());
            dto.setStatusCor(pedido.getStatus().getCor());
        }

        dto.setDataInicio(pedido.getDataSolicitacao());
        dto.setDataPrevisao(pedido.getDataPrevisaoConclusao());
        dto.setDataConclusao(null); // TODO: Ajustar quando o domínio tiver essa informação

        dto.setObservacoes(pedido.getObservacao());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setOrigem(pedido.getOrigem());
        dto.setPrioridade(pedido.getPrioridade() != null ? pedido.getPrioridade().toString() : null);
        dto.setDataCriacao(pedido.getDataSolicitacao());

        return dto;
    }
}
