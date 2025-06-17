package cv.igrp.simple.configuracoes.application.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoriasServicosDTO {
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    private String descricao;
    
    private String icone;
    
    private String cor;
    
    private Integer ordem;
    
    private Boolean ativo;
}