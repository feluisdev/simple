package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarConfiguracaoCommand implements Command {

    @NotNull(message = "O campo id é obrigatório")
    private Integer id;
    
    private String chave;
    private String valor;
    private String descricao;
    private String tipo;
    private String grupo;
    private Boolean editavel;
    private Estado estado;
}