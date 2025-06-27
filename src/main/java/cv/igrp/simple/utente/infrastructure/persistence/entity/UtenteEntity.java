package cv.igrp.simple.utente.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import cv.igrp.simple.utente.application.constants.Estado;
import java.util.List;
import cv.igrp.simple.utente.application.constants.TipoUtente;
import cv.igrp.simple.utente.domain.models.UtenteServico;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_utente")
public class UtenteEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotBlank(message = "nrUtente is mandatory")
    @Column(name="nrutente", nullable = false)
    private String nrUtente;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="nif")
    private String nif;

  
    @Column(name="bi")
    private String bi;

  
    @Column(name="nomemae")
    private String nomeMae;

  
    @Column(name="nome_pai")
    private String nomePai;

  
    @Column(name="datanascimento")
    private LocalDate dataNascimento;

  
    @Column(name="morada")
    private String morada;

  
    @Column(name="telefone")
    private String telefone;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  


  @OneToMany(mappedBy = "utenteId", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
private List<UtenteServico> servicos;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_utente")
    private TipoUtente tipoUtente;

  
    @Column(name="email")
    private String email;

  
    @Column(name="cxpostal")
    private String cxPostal;

  
}