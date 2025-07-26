package cv.igrp.simple.configuracoes.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoServicoFilter {
    private String nome;
    private String codigo;
    private Boolean estado;
    private String categoriaId;
    private Integer pageNumber;
    private Integer pageSize;
}
