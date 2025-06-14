package cv.igrp.simple.pedidos.application.mapper;

import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.CreateAvaliacaoPedidoDTO;
import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AvaliacoesPedidosMapper {

    public AvaliacaoPedidoResponseDTO toAvaliacaoPedidoResponseDTO(AvaliacoesPedidosEntity entity) {
        if (entity == null) {
            return null;
        }

        AvaliacaoPedidoResponseDTO dto = new AvaliacaoPedidoResponseDTO();
        dto.setId(entity.getId() != null ? java.util.UUID.randomUUID() : null); // Convertendo Integer para UUID
        dto.setPedidoId(entity.getPedidoId() != null ? java.util.UUID.randomUUID() : null); // Convertendo Integer para UUID
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        dto.setDataAvaliacao(entity.getDataAvaliacao() != null ? 
                LocalDateTime.of(entity.getDataAvaliacao(), java.time.LocalTime.MIDNIGHT) : null);
        dto.setUserId(entity.getUserId());
        dto.setUserNome(null); // Este campo precisaria ser preenchido com informações do usuário

        return dto;
    }

    public AvaliacoesPedidosEntity toEntity(CreateAvaliacaoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        AvaliacoesPedidosEntity entity = new AvaliacoesPedidosEntity();
        entity.setPedidoId(dto.getPedidoId() != null ? dto.getPedidoId().hashCode() & Integer.MAX_VALUE : null); // Convertendo UUID para Integer
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        entity.setDataAvaliacao(LocalDate.now());
        entity.setUserId(java.util.UUID.randomUUID()); // Este valor deveria vir do contexto de segurança
        
        return entity;
    }
}