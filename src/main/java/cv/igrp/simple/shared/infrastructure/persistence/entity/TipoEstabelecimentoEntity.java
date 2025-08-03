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
import java.util.List;
import java.util.Set;

@Audited
@Getter
@Setter
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

  
    @NotNull(message = "externalId is mandatory")
    @Column(name="externalid", nullable = false)
    private UUID externalId;

  
    @Column(name="codigo")
    private String codigo;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="flag_vistoria")
    private Integer flagVistoria;

  
    @Column(name="licretalho")
    private boolean licRetalho;

  


  @OneToMany(mappedBy = "tpEstabelecimentoId", fetch = FetchType.LAZY)
private List<LicencaComercialEntity> licencascomercias;


  
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CML_TP_ESTAB_CLASSE",
            joinColumns = @JoinColumn(name = "tipoestabelecimento_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
private Set<ClasseEntity> classes;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_tp_atividade")
   private TipoAtividadeEntity idTpAtividade;


}