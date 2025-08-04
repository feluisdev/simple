/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import cv.igrp.simple.shared.application.constants.Estado;
import java.util.List;

@Audited
@Getter
@Setter
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cml_tipo_atividade")
public class TipoAtividadeEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="externalid")
    private UUID externalId;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="codigo")
    private String codigo;

  
    @Column(name="descricao")
    private String descricao;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  


  @OneToMany(mappedBy = "idTipoAtividade", fetch = FetchType.LAZY)
private List<EstabelecimentoEntity> estabelecimentos;
}