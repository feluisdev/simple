package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.configuracoes.infrastructure.mappers.TipoServicoMapper;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;
import cv.igrp.simple.pedidos.domain.models.Avaliacao;
import cv.igrp.simple.pedidos.domain.models.HistoricoPedido;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.AvaliacaoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.HistoricoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoMapper {


    private final UtenteMapper utenteMapper;
    private final StatusPedidoMapper statusPedidoMapper;
    private final TipoServicoMapper tipoServicoMapper;
    private final AvaliacaoMapper avaliacaoMapper;
    private final HistoricoPedidoMapper historicoPedidoMapper;


    public Pedido toLightDomain(PedidoEntity entity) {

        return Pedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getPedidoUuid()),
                CodigoAcompanhamento.of(entity.getCodigoAcompanhamento()),
                entity.getObservacao(),
                statusPedidoMapper.toDomain(entity.getStatusId()),
                tipoServicoMapper.toDomain(entity.getTipoServicoId()),
                utenteMapper.toDomain(entity.getUtenteId() != null ? entity.getUtenteId() : null),
                entity.getEtapaAtualId()!=null ? entity.getEtapaAtualId().getId() : null,
                entity.getDataInicio(),
                entity.getDataPrevisao(),
                entity.getOrigem(),
                entity.getPrioridade(),
                entity.getValorTotal(),
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
                entity.getEtapaAtualId()!=null ? entity.getEtapaAtualId().getId() : null,
                entity.getDataInicio(),
                entity.getDataPrevisao(),
                entity.getOrigem(),
                entity.getPrioridade(),
                entity.getValorTotal(),
                null,
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

        return pedido;
    }

    public PedidoEntity toEntity(Pedido domain) {
        var entity = new PedidoEntity();

        if(domain.getId() != null)
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
                    .map(av -> {
                        AvaliacaoPedidoEntity avEntity = new AvaliacaoPedidoEntity();

                        if(av.getIdDb() != null)
                           avEntity.setId(av.getIdDb());

                        avEntity.setAvaliacaoUuid(av.getAvaliacaoUuid().getValor());
                        avEntity.setNota(av.getNota());
                        avEntity.setComentario(av.getComentario());
                        avEntity.setDataAvaliacao(av.getDataAvaliacao());
                        avEntity.setUserId(av.getUserId());
                        avEntity.setPedidoId(entity);

                        return avEntity;
                    }).toList();

            entity.setAvaliacoes(avaliacaoEntities);
        }

        if (domain.getHistoricoPedido() != null) {
            List<HistoricoPedidoEntity> historicoPedidoEntities = domain.getHistoricoPedido().stream()
                    .map(h -> {
                        var historicoPedidoEntity = new HistoricoPedidoEntity();
                        if(h.getIdDb() != null)
                            historicoPedidoEntity.setId(h.getIdDb());
                        historicoPedidoEntity.setHistoricoUuid(h.getHistoricoUuid().getValor());
                        historicoPedidoEntity.setUserId(h.getUserId());
                        historicoPedidoEntity.setDataRegistro(h.getDataRegistro());
                        historicoPedidoEntity.setObservacao(h.getObservacao());
                        historicoPedidoEntity.setStatusId(statusPedidoMapper.toEntity(h.getStatus()));
                        historicoPedidoEntity.setPedidoId(entity);

                        return historicoPedidoEntity;
                    }).toList();

            entity.setHistoricopedidos(historicoPedidoEntities);
        }

        return entity;
    }

    public  PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
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
