package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.domain.models.Documento;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.DocumentoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentoPedidoMapper {


    public DocumentoPedidoEntity toEntity(Documento documento, PedidoEntity pedidoEntity) {
        DocumentoPedidoEntity entity = new DocumentoPedidoEntity();

        if(documento.getIdDb()!=null){
            entity.setId(documento.getIdDb());
        }
        entity.setDocumentoUuid(documento.getDocumentoUuid().getValor());
        entity.setNome(documento.getNome());
        entity.setDescricao(documento.getDescricao());
        entity.setTipoDocumento(documento.getTipoDocumento());
        entity.setCaminhoArquivo(documento.getCaminhoArquivo());
        entity.setDataUpload(documento.getDataUpload());
        entity.setPedidoId(pedidoEntity);
        return entity;
    }

    public Documento toDomainWithPedido(DocumentoPedidoEntity entity, Pedido pedido) {
        return Documento.reconstruir(
                entity.getId(),
                Identificador.from(entity.getDocumentoUuid()),
                entity.getNome(),
                entity.getDescricao(),
                entity.getTipoDocumento(),
                entity.getCaminhoArquivo(),
                entity.getDataUpload(),
                pedido
        );
    }

    public DocumentoPedidoResponseDTO toResponseDTO(Documento documento) {
        if (documento == null) {
            return null;
        }

        return new DocumentoPedidoResponseDTO(
                documento.getDocumentoUuid().getStringValor(),
                documento.getPedido().getPedidoUuid().getStringValor(),
                documento.getNome(),
                documento.getDescricao(),
                documento.getTipoDocumento(),
                documento.getCaminhoArquivo(),
                documento.getDataUpload(),
                null, // userId não disponível, deixar null
                null// userNome não disponível, deixar null
        );
    }


}
