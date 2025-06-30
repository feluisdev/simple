package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnDefault;
import java.math.BigDecimal;
import java.util.List;

@Audited
@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_pedidos")
public class PedidoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="pedido_uuid")
    private UUID pedidoUuid;

  
    @NotBlank(message = "codigoAcompanhamento is mandatory")
    @Column(name="codigo_acompanhamento", unique = true, nullable = false, length=20)
    private String codigoAcompanhamento;

  
    @NotNull(message = "userCriacaoId is mandatory")
    @Column(name="user_criacao_id", nullable = false)
    private Integer userCriacaoId;

  
    @Column(name="user_responsavel_id")
    private Integer userResponsavelId;

  
    @Column(name="etapa_atual_id")
    private Integer etapaAtualId;

  
    @NotNull(message = "dataInicio is mandatory")
    @Column(name="data_inicio", nullable = false)
    private LocalDate dataInicio;

  
    @Column(name="data_previsao")
    private LocalDate dataPrevisao;

  
    @Column(name="data_conclusao")
    private LocalDate dataConclusao;

  
    @NotBlank(message = "origem is mandatory")
    @Column(name="origem", nullable = false, length=20)
    private String origem;

  
    @NotNull(message = "prioridade is mandatory")
    @ColumnDefault("'0'")
    @Column(name="prioridade", nullable = false)
    private Integer prioridade;

  
    @Column(name="observacao")
    private String observacao;

  
    @Column(name="valor_total")
    private BigDecimal valorTotal;

  


  @OneToMany(mappedBy = "pedidoId", fetch = FetchType.LAZY)
private List<AvaliacaoPedidoEntity> avaliacoes;


  @OneToMany(mappedBy = "pedidoId", fetch = FetchType.LAZY)
private List<HistoricoPedidoEntity> historicopedidos;


  @OneToMany(mappedBy = "pedidoId", fetch = FetchType.LAZY)
private List<PagamentoPedidoEntity> pagamentos;


  @OneToMany(mappedBy = "pedidoId", fetch = FetchType.LAZY)
private List<DocumentoPedidoEntity> documentos;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "tipo_servico_id")
   private TipoServicoEntity tipoServicoId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "status_id")
   private StatusPedidoEntity statusId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "utente_id")
   private UtenteEntity utenteId;


}