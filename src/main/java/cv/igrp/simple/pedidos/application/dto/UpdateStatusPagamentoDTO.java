package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IgrpDTO
public class UpdateStatusPagamentoDTO {

    @NotBlank(message = "O status é obrigatório")
    @Size(min = 2, max = 20, message = "O status deve ter entre 2 e 20 caracteres")
    private String status;
    
    @Size(max = 100, message = "A referência de pagamento deve ter no máximo 100 caracteres")
    private String referenciaPagamento;
    
    @Size(max = 255, message = "A observação deve ter no máximo 255 caracteres")
    private String observacao;
}