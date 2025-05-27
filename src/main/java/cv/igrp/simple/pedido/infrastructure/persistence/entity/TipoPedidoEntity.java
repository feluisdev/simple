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
@Table(name = "tipo_pedido")
public class TipoPedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoriaPedidoEntity categoriaId;


    @NotBlank(message = "nome is mandatory")
    @Column(name = "nome", nullable = false)
    private String nome;


    @NotBlank(message = "codigo is mandatory")
    @Column(name = "codigo", nullable = false)
    private String codigo;


    @Column(name = "descricao")
    private String descricao;


    @Column(name = "prazo_estimado")
    private Integer prazoEstimado;


    @Column(name = "valor_base")
    private Integer valorBase;


    @Column(name = "requer_vistoria")
    private boolean requerVistoria;


    @Column(name = "requer_analise_tecnica")
    private boolean requerAnaliseTecnica;


    @Column(name = "requer_aprovacao")
    private boolean requerAprovacao;


    @Column(name = "disponivel_portal")
    private boolean disponivelPortal;


    @Column(name = "ativo")
    private boolean ativo;

    @OneToMany(mappedBy = "tipoPedidoId")
    private List<PedidoEntity> pedidos;

    @OneToMany(mappedBy = "tipoPedidoId")
    private List<EtapaProcessoEntity> tipospedidos;


}