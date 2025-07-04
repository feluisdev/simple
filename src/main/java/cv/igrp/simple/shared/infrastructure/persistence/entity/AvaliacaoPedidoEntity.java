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
@Table(name = "cm_pedidos_avaliacoes")
public class AvaliacaoPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="avaliacao_uuid")
    private UUID avaliacaoUuid;

  
    @NotNull(message = "nota is mandatory")
    @Column(name="nota", nullable = false)
    private Integer nota;

  
    @Column(name="comentario", length=500)
    private String comentario;

  
    @NotNull(message = "dataAvaliacao is mandatory")
    @Column(name="data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;

  
    @Column(name="user_id")
    private Integer userId;

     @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "pedido_id")
   private PedidoEntity pedidoId;


}