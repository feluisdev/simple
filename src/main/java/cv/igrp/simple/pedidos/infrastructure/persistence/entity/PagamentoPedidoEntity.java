package cv.igrp.simple.pedidos.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_pedidos_pagamentos")
public class PagamentoPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="pedido_id")
    private Integer pedidoId;

  
    @NotNull(message = "dataPagamento is mandatory")
    @Column(name="data_pagamento", nullable = false)
    private LocalDate dataPagamento;

  
    @NotBlank(message = "metodoPagamento is mandatory")
    @Column(name="metodo_pagamento", nullable = false, length=50)
    private String metodoPagamento;

  
    @Column(name="referencia_pagamento", length=100)
    private String referenciaPagamento;

  
    @NotBlank(message = "status is mandatory")
    @Column(name="status", nullable = false, length=20)
    private String status;

  
    @Column(name="observacao")
    private String observacao;

  
    @Column(name="valor")
    private Double valor;

  
}