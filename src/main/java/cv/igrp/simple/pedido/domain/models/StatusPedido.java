package cv.igrp.simple.pedido.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatusPedido {

    private String codigo;
    private String nome;
    private String descricao;
    private String cor;
    private String icone;
    private Integer ordem;
    private boolean visivelPortal;
}
