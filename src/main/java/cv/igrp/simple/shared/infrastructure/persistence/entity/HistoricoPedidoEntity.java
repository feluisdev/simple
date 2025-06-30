package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Audited
@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos_historico")
public class HistoricoPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="historico_uuid")
    private String historicoUuid;

  
    @NotNull(message = "statusId is mandatory")
    @Column(name="status_id", nullable = false)
    private Integer statusId;

  
    @Column(name="etapa_id")
    private Integer etapaId;

  
    @NotNull(message = "userId is mandatory")
    @Column(name="user_id", nullable = false)
    private Integer userId;

  
    @NotNull(message = "dataRegistro is mandatory")
    @Column(name="data_registro", nullable = false)
    private LocalDate dataRegistro;

  
    @Column(name="observacao")
    private String observacao;

     @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "pedido_id")
   private PedidoEntity pedidoId;


}