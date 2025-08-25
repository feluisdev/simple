/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Audited
@Getter
@Setter
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_sector")
public class SectorEntity extends AuditEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

  
    @NotBlank(message = "name is mandatory")
    @Column(name="name", nullable = false)
    private String name;

  
    @Column(name="description")
    private String description;

  
    @Column(name="sector_type_key")
    private String sectorTypeKey;

  
    @Column(name="code", unique = true)
    private String code;

  
    @Column(name="active")
    private boolean active;

  
    @Column(name="sort_order")
    private Integer sortOrder;

  
    @Lob
    @Column(name="metadata", columnDefinition="TEXT")
    private String metadata;

  


  @OneToMany(mappedBy = "sectorId", fetch = FetchType.LAZY)
private List<CategoryEntity> categories;
}