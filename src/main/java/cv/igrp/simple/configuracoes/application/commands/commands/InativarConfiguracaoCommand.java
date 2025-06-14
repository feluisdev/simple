package cv.igrp.simple.configuracoes.application.commands.commands;

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
public class InativarConfiguracaoCommand implements Command {

    @NotNull(message = "O campo id é obrigatório")
    private Integer id;
}