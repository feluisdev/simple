package cv.igrp.simple.configuracoes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusPedidoResponseDTO {
    private Integer id;
    private String codigo;
    private String nome;
    private String descricao;
    private String cor;
    private String icone;
    private Integer ordem;
    private Boolean visivelPortal;
}