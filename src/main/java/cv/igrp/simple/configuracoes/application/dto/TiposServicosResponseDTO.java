package cv.igrp.simple.configuracoes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TiposServicosResponseDTO {
    private Integer id;
    private Integer categoriaId;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer prazoEstimado;
    private Double valorBase;
    private Boolean requerVistoria;
    private Boolean requerAnaliseTec;
    private Boolean requerAprovacao;
    private Boolean disponivelPortal;
    private Boolean ativo;
    private CategoriasServicosResponseDTO categoria;
}