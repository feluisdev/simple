package cv.igrp.simple.shared.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import cv.igrp.simple.utente.application.constants.TipoUtente;
import cv.igrp.simple.utente.application.constants.TipoIdentificacao;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import cv.igrp.simple.utente.application.constants.Estado;
import java.util.List;
import cv.igrp.simple.utente.application.constants.GeneroTipo;

@Audited
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

  
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_utente")
    private TipoUtente tipoUtente;

  
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_identificacao")
    private TipoIdentificacao tipoIdentificacao;

  
    @Column(name="identificacao")
    private String identificacao;

  
    @Column(name="numero")
    private String numero;

  
    @Column(name="nome")
    private String nome;

  
    @Column(name="nif")
    private String nif;

  
    @Column(name="email")
    private String email;

  
    @NotNull(message = "dataNascimento is mandatory")
    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataNascimento;

  
    @NotBlank(message = "nomeMae is mandatory")
    @Column(name="nome_mae", nullable = false)
    private String nomeMae;

  
    @NotBlank(message = "nomePai is mandatory")
    @Column(name="nome_pai", nullable = false)
    private String nomePai;

  
    @NotBlank(message = "endereco is mandatory")
    @Column(name="endereco", nullable = false)
    private String endereco;

  
    @NotBlank(message = "telefone is mandatory")
    @Column(name="telefone", nullable = false)
    private String telefone;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

  


  @OneToMany(mappedBy = "utenteId", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
private List<UtenteServicoEntity> servicos;
    @Column(name="caixa_postal")
    private String caixaPostal;

  
    @Column(name="departamento_responsavel")
    private String departamentoResponsavel;

  
    @Column(name="telemovel")
    private String telemovel;

  
    @Enumerated(EnumType.STRING)
    @Column(name="genero")
    private GeneroTipo genero;

  
    @Column(name="nacionalidade")
    private String nacionalidade;

  


  @OneToMany(mappedBy = "utenteId", fetch = FetchType.LAZY)
private List<PedidoEntity> pedidos;
}