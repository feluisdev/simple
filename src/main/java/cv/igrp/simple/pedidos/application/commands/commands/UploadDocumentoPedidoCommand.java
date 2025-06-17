package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.UploadDocumentoPedidoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadDocumentoPedidoCommand implements Command {

    private final UploadDocumentoPedidoDTO uploadDocumentoPedidoDTO;
    private final Integer pedidoId;

}