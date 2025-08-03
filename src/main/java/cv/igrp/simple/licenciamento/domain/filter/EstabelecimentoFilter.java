package cv.igrp.simple.licenciamento.domain.filter;

import cv.igrp.simple.shared.application.constants.Estado;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EstabelecimentoFilter {

    private String gerente;
    private Estado estado;
    private Integer pageNumber;
    private Integer pageSize;
}
