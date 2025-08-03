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
import cv.igrp.simple.shared.application.constants.Estado;
import java.util.List;

@Audited
@Getter
@Setter
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

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="alvara")
    private String alvara;

  
    @Column(name="data_inicio_licenca")
    private LocalDate dataInicioLicenca;

  
    @Column(name="data_fim_licenca")
    private String dataFimLicenca;

  
    @Column(name="data_renovacao_licenca")
    private LocalDate dataRenovacaoLicenca;

  
    @Column(name="morada")
    private String morada;

  
    @Column(name="telefone")
    private String telefone;

  
    @Column(name="email")
    private String email;

  
    @Column(name="nif")
    private String nif;

  
    @Column(name="designacao")
    private String designacao;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  


  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", referencedColumnName = "id")
    private UtenteEntity utenteId;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "tp_estabelecimento_id")
   private TipoEstabelecimentoEntity tpEstabelecimentoId;


}