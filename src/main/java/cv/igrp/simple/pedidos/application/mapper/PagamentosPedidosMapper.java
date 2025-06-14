package cv.igrp.simple.pedidos.application.mapper;

import cv.igrp.simple.pedidos.application.dto.CreatePagamentoPedidoDTO;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.UpdateStatusPagamentoDTO;
import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PagamentosPedidosMapper {

    public PagamentoPedidoResponseDTO toPagamentoPedidoResponseDTO(PagamentosPedidosEntity entity) {
        if (entity == null) {
            return null;
        }

        PagamentoPedidoResponseDTO dto = new PagamentoPedidoResponseDTO();
        dto.setId(entity.getId());
        dto.setPedidoId(entity.getPedidoId());
        dto.setValor(entity.getValor());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setMetodoPagamento(entity.getMetodoPagamento());
        dto.setReferenciaPagamento(entity.getReferenciaPagamento());
        dto.setStatus(entity.getStatus());
        dto.setObservacao(entity.getObservacao());

        return dto;
    }

    public void updateEntityFromDTO(UpdateStatusPagamentoDTO dto, PagamentosPedidosEntity entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setStatus(dto.getStatus());
        entity.setReferenciaPagamento(dto.getReferenciaPagamento());
        entity.setObservacao(dto.getObservacao());
    }
    
    public PagamentosPedidosEntity toEntity(CreatePagamentoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        PagamentosPedidosEntity entity = new PagamentosPedidosEntity();
        entity.setPedidoId(dto.getPedidoId());
        entity.setValor(dto.getValor());
        entity.setDataPagamento(LocalDate.now());
        entity.setMetodoPagamento(dto.getMetodoPagamento());
        entity.setReferenciaPagamento(dto.getReferenciaPagamento());
        entity.setStatus("PENDENTE"); // Status inicial padrão
        entity.setObservacao(dto.getObservacao());
        
        return entity;
    }
}