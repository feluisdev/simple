/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.time.LocalDate;
import java.math.BigDecimal;
import cv.igrp.simple.shared.application.constants.Estado;
import java.util.List;

@Audited
@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cml_ficha_licenca")
public class LicencaComercialEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "externalID is mandatory")
    @Column(name="externalid", nullable = false)
    private UUID externalID;

  
    @Column(name="numero")
    private String numero;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="cartao")
    private String cartao;

  
    @Column(name="especialidade")
    private String especialidade;

  
    @Column(name="data_licenca")
    private LocalDate dataLicenca;

  
    @Column(name="alvara")
    private String alvara;

  
    @Column(name="pagamento")
    private BigDecimal pagamento;

  
    @Column(name="localidade")
    private String localidade;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  
    @Column(name="abrangencia")
    private String abrangencia;

  
    @Column(name="dt_fim_lic")
    private String dtFimLic;

  
    @Column(name="tp_hor_func")
    private Integer tpHorFunc;

  
    @Column(name="dt_renovacao")
    private LocalDate dtRenovacao;

  
    @Column(name="data_lic_horario")
    private LocalDate dataLicHorario;

  
    @Column(name="dt_fim_horario")
    private LocalDate dtFimHorario;

  
    @Column(name="designacao")
    private String designacao;

  
    @Column(name="selo")
    private BigDecimal selo;

  
    @Column(name="taxa")
    private BigDecimal taxa;

  
    @Column(name="morada")
    private String morada;

  
    @Column(name="telefone")
    private String telefone;

  
    @Column(name="email")
    private String email;

  
    @Column(name="nif")
    private String nif;

  


  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", referencedColumnName = "id")
    private UtenteEntity utenteId;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "tp_estabelecimento_id")
   private TipoEstabelecimentoEntity tpEstabelecimentoId;


}