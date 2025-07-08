package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
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
    private UUID historicoUuid;

  
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

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "status_id")
   private StatusPedidoEntity statusId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "etapa_id")
   private EtapaPedidoEntity etapaId;


}