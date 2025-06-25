package cv.igrp.simple.pedidos.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_pedidos_avaliacoes")
public class AvaliacaoPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "pedidoId is mandatory")
    @Column(name="pedido_id", unique = true, nullable = false)
    private Integer pedidoId;

  
    @NotNull(message = "nota is mandatory")
    @Column(name="nota", nullable = false)
    private Integer nota;

  
    @Column(name="comentario", length=500)
    private String comentario;

  
    @NotNull(message = "dataAvaliacao is mandatory")
    @Column(name="data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;

  
    @NotNull(message = "userId is mandatory")
    @Column(name="user_id", nullable = false)
    private Integer userId;

  
}