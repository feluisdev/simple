package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para criação de pagamentos de pedidos
 */
@IgrpDTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePagamentoPedidoDTO {

    @NotNull(message = "O ID do pedido é obrigatório")
    private Integer pedidoId;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private Double valor;

    @NotBlank(message = "O método de pagamento é obrigatório")
    @Size(min = 2, max = 50, message = "O método de pagamento deve ter entre 2 e 50 caracteres")
    private String metodoPagamento;

    @Size(max = 100, message = "A referência de pagamento deve ter no máximo 100 caracteres")
    private String referenciaPagamento;

    @Size(max = 255, message = "A observação deve ter no máximo 255 caracteres")
    private String observacao;
}