package cv.igrp.simple.configuracoes.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cm_tipos_servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class TiposServicosEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prazo_estimado")
    private Integer prazoEstimado;

    @Column(name = "valor_base")
    private Double valorBase;

    @Column(name = "requer_vistoria", nullable = false)
    private Boolean requerVistoria;

    @Column(name = "requer_analise_tec", nullable = false)
    private Boolean requerAnaliseTec;

    @Column(name = "requer_aprovacao", nullable = false)
    private Boolean requerAprovacao;

    @Column(name = "disponivel_portal", nullable = false)
    private Boolean disponivelPortal;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoriasServicosEntity categoria;
}