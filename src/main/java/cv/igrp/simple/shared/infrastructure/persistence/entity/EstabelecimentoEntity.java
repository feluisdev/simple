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
import cv.igrp.simple.shared.application.constants.Estado;
import java.util.Set;
import java.util.List;

@Audited
@Getter
@Setter
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cml_tipo_estabelecimento")
public class EstabelecimentoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "externalId is mandatory")
    @Column(name="externalid", nullable = false)
    private UUID externalId;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="gerente")
    private String gerente;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="flag_vistoria")
    private boolean flagVistoria;

  
    @Column(name="licretalho")
    private boolean licRetalho;

  
    @Column(name="endereco")
    private String endereco;

  
    @Column(name="telefone")
    private String telefone;

  
    @Column(name="email")
    private String email;

  
    @Column(name="nif")
    private String nif;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  


  
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CML_TP_ESTAB_CLASSE",
            joinColumns = @JoinColumn(name = "estabelecimento_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
private Set<ClasseEntity> classes;


  @OneToMany(mappedBy = "idEstabelecimento", fetch = FetchType.LAZY)
private List<LicencaComercialEntity> licencas;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_tipo_atividade")
   private TipoAtividadeEntity idTipoAtividade;


}