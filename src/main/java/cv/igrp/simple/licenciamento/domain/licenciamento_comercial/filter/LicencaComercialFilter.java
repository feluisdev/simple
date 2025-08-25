package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter;

import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LicencaComercialFilter {

    private String alvara;
    private Identificador idEstabalecimento;
    private EstadoLicenca estadoLicenca;
    private Integer pageNumber;
    private Integer pageSize;
}
