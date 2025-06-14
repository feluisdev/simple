package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos_historico")
public class HistoricoPedidosEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "pedidoId is mandatory")
    @Column(name="pedido_id", nullable = false)
    private Integer pedidoId;

  
    @NotNull(message = "statusId is mandatory")
    @Column(name="status_id", nullable = false)
    private Integer statusId;

  
    @Column(name="etapa_id")
    private Integer etapaId;

  
    @NotNull(message = "userId is mandatory")
    @Column(name="user_id", nullable = false)
    private UUID userId;

  
    @NotNull(message = "dataRegistro is mandatory")
    @Column(name="data_registro", nullable = false)
    private LocalDate dataRegistro;

  
    @Column(name="observacao")
    private String observacao;

  
}