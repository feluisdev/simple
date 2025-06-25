package cv.igrp.simple.configuracoes.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_status_pedido")
public class StatusPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="status_uuid")
    private UUID statusUuid;

  
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

  
    @Column(name="visivelportal")
    private boolean visivelPortal;

  
}