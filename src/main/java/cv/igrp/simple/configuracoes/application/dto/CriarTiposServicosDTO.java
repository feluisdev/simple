package cv.igrp.simple.configuracoes.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarTiposServicosDTO {
    @NotNull(message = "O ID da categoria é obrigatório")
    @Positive(message = "O ID da categoria deve ser um número positivo")
    private Integer categoriaId;
    
    @NotBlank(message = "O código é obrigatório")
    @Size(min = 2, max = 20, message = "O código deve ter entre 2 e 20 caracteres")
    private String codigo;
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    private String descricao;
    
    private Integer prazoEstimado;
    
    private Double valorBase;
    
    private Boolean requerVistoria;
    
    private Boolean requerAnaliseTec;
    
    private Boolean requerAprovacao;
    
    private Boolean disponivelPortal;
    
    private Boolean ativo;
}