package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.UpdateStatusPagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarStatusPagamentoCommand implements Command {

    private UpdateStatusPagamentoDTO updateStatusPagamento;
    private Integer id;
}