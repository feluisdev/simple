package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcluirPagamentoCommand implements Command {

    private Integer id;
}