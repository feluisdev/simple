package cv.igrp.simple.pedido.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria_pedido")
public class CategoriaPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotBlank(message = "nome is mandatory")
    @Column(name="nome", nullable = false)
    private String nome;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="icone")
    private String icone;

  
    @Column(name="cor")
    private String cor;

  
    @Column(name="ordem")
    private Integer ordem;

  
    @Column(name="ativo")
    private boolean ativo;

     @OneToMany(mappedBy = "categoriaId")
private List<TipoPedidoEntity> tipospedidos;


}