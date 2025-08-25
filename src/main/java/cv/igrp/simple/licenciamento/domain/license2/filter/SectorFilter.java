package cv.igrp.simple.licenciamento.domain.license2.filter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SectorFilter {

    private String sectorType;
    private String name;
    private String code;
    private boolean active;
    private Integer pageNumber;
    private Integer pageSize;
}
