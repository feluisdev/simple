/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cml_tipo_estabelecimento")
public class TipoEstabelecimentoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotBlank(message = "externalId is mandatory")
    @Column(name="externalid", nullable = false)
    private String externalId;

  
    @Column(name="codigo")
    private String codigo;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="tipo_atividade")
    private String tipoAtividade;

  
    @Column(name="taxa_recolha_lixo")
    private BigDecimal taxaRecolhaLixo;

  
    @Column(name="tarifa_minima")
    private BigDecimal tarifaMinima;

  
    @Column(name="frequencia")
    private Integer frequencia;

  
    @Column(name="num_contentores")
    private Integer numContentores;

  
    @Column(name="flag_vistoria")
    private Integer flagVistoria;

  
    @Column(name="categoria")
    private String categoria;

  
    @Column(name="licretalho")
    private boolean licRetalho;

  
    @Column(name="horario_encerramento")
    private String horarioEncerramento;

  
    @Column(name="horario_funcionamento")
    private String horarioFuncionamento;

  


  @OneToMany(mappedBy = "tpEstabelecimentoId", fetch = FetchType.LAZY)
private List<LicencaComercialEntity> licencascomercias;
    @Column(name="taxa_fixa")
    private BigDecimal taxaFixa;

  
    @Column(name="taxa_quarto")
    private BigDecimal taxaQuarto;

  


  
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CML_TP_ESTAB_CLASSE",
            joinColumns = @JoinColumn(name = "tipoestabelecimento_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
private Set<ClasseEntity> classes;
}