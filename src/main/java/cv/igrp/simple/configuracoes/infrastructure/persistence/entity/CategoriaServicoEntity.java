package cv.igrp.simple.configuracoes.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.ColumnDefault;
import java.util.List;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_categoria_servico")
public class CategoriaServicoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="categoria_uuid")
    private UUID categoriaUuid;

  
    @Column(name="nome", unique = true)
    private String nome;

  
    @NotBlank(message = "codigo is mandatory")
    @Column(name="codigo", unique = true, nullable = false)
    private String codigo;

  
    @NotBlank(message = "descricao is mandatory")
    @Column(name="descricao", nullable = false)
    private String descricao;

  
    @NotBlank(message = "icon is mandatory")
    @Column(name="icon", nullable = false)
    private String icon;

  
    @NotBlank(message = "cor is mandatory")
    @Column(name="cor", nullable = false)
    private String cor;

  
    @ColumnDefault("'1'")
    @Column(name="ordem")
    private Integer ordem;

  
    @Column(name="estado")
    private boolean estado;

  


  @OneToMany(mappedBy = "categoriaId", fetch = FetchType.LAZY)
private List<TipoServicoEntity> tiposservicos;
}