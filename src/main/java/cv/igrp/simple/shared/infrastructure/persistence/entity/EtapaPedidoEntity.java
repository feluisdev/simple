package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Audited
@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_pedidos_etapas")
public class EtapaPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="etapa_uuid")
    private UUID etapaUuid;

  
    @NotBlank(message = "codigo is mandatory")
    @Column(name="codigo", unique = true, nullable = false, length=20)
    private String codigo;

  
    @NotBlank(message = "nome is mandatory")
    @Column(name="nome", nullable = false, length=100)
    private String nome;

  
    @Column(name="descricao")
    private String descricao;

  
    @NotNull(message = "ordem is mandatory")
    @ColumnDefault("'0'")
    @Column(name="ordem", nullable = false)
    private Integer ordem;

  
    @Column(name="tipo_servico_id")
    private Integer tipoServicoId;

  
    @Column(name="prazo_estimado")
    private Integer prazoEstimado;

  
    @NotNull(message = "requerAprovacao is mandatory")
    @ColumnDefault("'false'")
    @Column(name="requer_aprovacao", nullable = false)
    private boolean requerAprovacao;

  
    @NotNull(message = "ativo is mandatory")
    @ColumnDefault("'true'")
    @Column(name="ativo", nullable = false)
    private boolean ativo;

  
}