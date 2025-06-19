package cv.igrp.simple.configuracoes.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaFilter {

    private String nome;
    private Integer pageNumber;
    private Integer pageSize;
}
