package cv.igrp.simple.pedido.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoriaPedido {

    private  Integer id;
    private String nome;
    private String descricao;
    private String icone;
    private String cor;
    private Integer ordem;
    private boolean ativo;
}
