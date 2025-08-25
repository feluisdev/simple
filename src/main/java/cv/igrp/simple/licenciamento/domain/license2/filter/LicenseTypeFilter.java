package cv.igrp.simple.licenciamento.domain.license2.filter;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LicenseTypeFilter {

    private Identificador categoryId;
    private String licensingModel;
    private boolean active;
    private boolean renewable;
    private String name;
    private String code;
    private Integer pageNumber;
    private Integer pageSize;
}
