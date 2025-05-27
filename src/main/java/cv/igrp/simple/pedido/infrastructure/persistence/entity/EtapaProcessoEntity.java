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
@Table(name = "etapa_processo")
public class EtapaProcessoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_pedido_id", referencedColumnName = "id")
    private TipoPedidoEntity tipoPedidoId;


    @NotBlank(message = "codigo is mandatory")
    @Column(name = "codigo", nullable = false)
    private String codigo;


    @Column(name = "nome")
    private String nome;


    @Column(name = "descricao")
    private String descricao;


    @Column(name = "ordem")
    private Integer ordem;


    @Column(name = "tempo_estimado")
    private Integer tempoEstimado;


    @Column(name = "requer_documento")
    private boolean requerDocumento;


    @Column(name = "requer_pagamento")
    private boolean requerPagamento;


    @Column(name = "requer_aprovacao")
    private boolean requerAprovacao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapa_anterior_id", referencedColumnName = "id")
    private EtapaProcessoEntity etapaAnteriorId;

    @OneToMany(mappedBy = "etapaAnteriorId")
    private List<EtapaProcessoEntity> etapasanteriores;

    @OneToMany(mappedBy = "etapaAtualId")
    private List<PedidoEntity> pedidos;


}