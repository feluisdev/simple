package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PedidoFilter {

    private Identificador tipoServicoId;
    private CodigoAcompanhamento codigoAcompanhamento;
    private Integer utenteId;
    private String utenteNome;
    private String utenteNumero;
    private LocalDate dataDe;
    private LocalDate dataAte;
    private Integer pageNumber;
    private Integer pageSize;
}
