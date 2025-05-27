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
@Table(name = "status_pedido")
public class StatusPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotBlank(message = "codigo is mandatory")
    @Column(name="codigo", nullable = false)
    private String codigo;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="cor")
    private String cor;

  
    @Column(name="icone")
    private String icone;

  
    @Column(name="ordem")
    private Integer ordem;

  
    @Column(name="visivel_portal")
    private boolean visivelPortal;

     @OneToMany(mappedBy = "statusId")
private List<PedidoEntity> pedidos;


}