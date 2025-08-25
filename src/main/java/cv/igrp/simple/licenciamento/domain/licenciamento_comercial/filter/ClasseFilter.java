package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter;

import cv.igrp.simple.shared.application.constants.Estado;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClasseFilter {

    private String classe;
    private String descricao;
    private Estado estado;
    private Integer pageNumber;
    private Integer pageSize;
}
