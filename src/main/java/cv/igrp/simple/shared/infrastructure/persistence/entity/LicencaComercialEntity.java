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

import cv.igrp.simple.shared.application.constants.EstadoLicenca;

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
    @Column(name = "externalid", nullable = false)
    private UUID externalID;


    @Column(name = "alvara", unique = true)
    private String alvara;


    @Column(name = "data_inicio_licenca")
    private LocalDate dataInicioLicenca;


    @Column(name = "data_fim_licenca")
    private LocalDate dataFimLicenca;


    @Column(name = "data_renovacao_licenca")
    private LocalDate dataRenovacaoLicenca;


    @Column(name = "horariofuncionamento")
    private String horarioFuncionamento;


    @Column(name = "designacao")
    private String designacao;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoLicenca estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", referencedColumnName = "id")
    private UtenteEntity utenteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estabelecimento")
    private EstabelecimentoEntity idEstabelecimento;


}