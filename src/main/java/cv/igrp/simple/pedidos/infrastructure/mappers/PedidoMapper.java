package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.configuracoes.infrastructure.mappers.TipoServicoMapper;
import cv.igrp.simple.shared.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoServicoEntity;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.models.Utente;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final UtenteMapper utenteMapper;
    private final StatusPedidoMapper statusPedidoMapper;
    private final TipoServicoMapper tipoServicoMapper;

    public Pedido toDomain(PedidoEntity entity) {

        return Pedido.reconstruir(
                entity.getId(),
                Identificador.from(entity.getPedidoUuid()),
                CodigoAcompanhamento.of(entity.getCodigoAcompanhamento()),
                entity.getObservacao(),
                statusPedidoMapper.toDomain(entity.getStatusId()),
                tipoServicoMapper.toDomain(entity.getTipoServicoId()),
                utenteMapper.toDomain(entity.getUtenteId() != null ? entity.getUtenteId() : null),
                entity.getEtapaAtualId(),
                entity.getDataInicio(),
                entity.getDataPrevisao(),
                entity.getOrigem(),
                entity.getPrioridade(),
                entity.getValorTotal()
        );
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
        entity.setEtapaAtualId(domain.getEtapaAtualId());
        entity.setDataInicio(domain.getDataSolicitacao());
        entity.setDataPrevisao(domain.getDataPrevisaoConclusao());
        entity.setOrigem(domain.getOrigem());
        entity.setPrioridade(domain.getPrioridade());
        entity.setValorTotal(domain.getValorTotal());
        entity.setUserCriacaoId(1); // todo resolve this later
        entity.setEtapaAtualId(1);// todo resolve this later
        return entity;
    }
}
