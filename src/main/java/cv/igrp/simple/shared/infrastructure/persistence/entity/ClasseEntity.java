/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.Set;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cml_classe")
public class ClasseEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "externalId is mandatory")
    @Column(name="externalid", nullable = false)
    private UUID externalId;

  
    @Column(name="classe")
    private String classe;

  
    @Column(name="descricao")
    private String descricao;

     @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY)
private Set<TipoEstabelecimentoEntity> tipoestabelecimentos;


}