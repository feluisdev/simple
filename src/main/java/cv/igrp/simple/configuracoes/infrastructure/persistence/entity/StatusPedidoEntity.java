package cv.igrp.simple.configuracoes.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cm_status_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class StatusPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cor", length = 20)
    private String cor;

    @Column(name = "icone", length = 50)
    private String icone;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    @Column(name = "visivel_portal", nullable = false)
    private Boolean visivelPortal;
}