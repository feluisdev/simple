package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.shared.config.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cm_categorias_servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoriasServicosEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "icone", length = 50)
    private String icone;

    @Column(name = "cor", length = 20)
    private String cor;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}