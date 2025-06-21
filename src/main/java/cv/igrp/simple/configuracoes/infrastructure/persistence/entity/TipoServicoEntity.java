package cv.igrp.simple.configuracoes.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import java.util.List;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_tipo_servico")
public class TipoServicoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="tipo_servico_uuid")
    private UUID tipoServicoUuid;

  
    @NotBlank(message = "codigo is mandatory")
    @Column(name="codigo", unique = true, nullable = false)
    private String codigo;

  
    @NotBlank(message = "nome is mandatory")
    @Column(name="nome", nullable = false)
    private String nome;

  
    @Column(name="descricao")
    private String descricao;

  
    @NotNull(message = "prazoEstimado is mandatory")
    @Column(name="prazo_estimado", nullable = false)
    private Integer prazoEstimado;

  
    @NotNull(message = "valorBase is mandatory")
    @Column(name="valor_base", nullable = false)
    private Double valorBase;

  
    @ColumnDefault("'false'")
    @Column(name="vistoria")
    private boolean vistoria;

  
    @ColumnDefault("'false'")
    @Column(name="analise_tecnica")
    private boolean analiseTecnica;

  
    @ColumnDefault("'false'")
    @Column(name="aprovacao")
    private boolean aprovacao;

  
    @ColumnDefault("'false'")
    @Column(name="portal")
    private boolean portal;

  
    @Column(name="estado")
    private boolean estado;

     @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "categoria_id")
   private CategoriaServicoEntity categoriaId;


}