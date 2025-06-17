package cv.igrp.simple.configuracoes.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarConfiguracoesDTO {

    @NotBlank(message = "O campo chave é obrigatório")
    private String chave;
    
    @NotBlank(message = "O campo valor é obrigatório")
    private String valor;
    
    private String descricao;
    
    @NotBlank(message = "O campo tipo é obrigatório")
    private String tipo;
    
    @NotBlank(message = "O campo grupo é obrigatório")
    private String grupo;
    
    @NotNull(message = "O campo editavel é obrigatório")
    private Boolean editavel;
}