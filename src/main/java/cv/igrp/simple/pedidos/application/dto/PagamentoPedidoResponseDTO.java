package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IgrpDTO
public class PagamentoPedidoResponseDTO {

    private Integer id;
    
    private Integer pedidoId;
    
    private Double valor;
    
    private LocalDate dataPagamento;
    
    private String metodoPagamento;
    
    private String referenciaPagamento;
    
    private String status;
    
    private String observacao;
}