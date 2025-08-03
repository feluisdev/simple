package cv.igrp.simple.licenciamento.domain.filter;

import cv.igrp.simple.shared.application.constants.Estado;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipoAtividadeFilter {

    private String nome;
    private String codigo;
    private Estado estado;
    private Integer pageNumber;
    private Integer pageSize;
}
