package cv.igrp.simple.pedido.infrastructure.persistence.entity;

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
@Table(name = "pedido")
public class PedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "tipoPedidoId is mandatory")
  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_pedido_id", referencedColumnName = "id")
    private TipoPedidoEntity tipoPedidoId;


    @Column(name="utente_id")
    private Integer utenteId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapa_atual_id", referencedColumnName = "id")
    private EtapaProcessoEntity etapaAtualId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusPedidoEntity statusId;


    @Column(name="data_inicio")
    private LocalDate dataInicio;

  
    @Column(name="data_previsao")
    private LocalDate dataPrevisao;

  
    @Column(name="data_conclusao")
    private LocalDate dataConclusao;

  
    @Column(name="observacao")
    private String observacao;

  
    @Column(name="origem")
    private String origem;

  
    @Column(name="prioridade")
    private Integer prioridade;

  
}