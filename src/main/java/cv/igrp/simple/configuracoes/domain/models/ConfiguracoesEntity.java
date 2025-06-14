package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.simple.configuracoes.application.constants.Estado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cm_configuracoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ConfiguracoesEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chave", nullable = false, length = 100)
    private String chave;

    @Column(name = "valor", nullable = false)
    private String valor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "grupo", nullable = false, length = 50)
    private String grupo;

    @Column(name = "editavel", nullable = false)
    private Boolean editavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;
}