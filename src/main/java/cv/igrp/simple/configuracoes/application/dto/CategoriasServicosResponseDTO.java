package cv.igrp.simple.configuracoes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriasServicosResponseDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String icone;
    private String cor;
    private Integer ordem;
    private Boolean ativo;
}