package cv.igrp.simple.utente.infrastructure.persistence.entity;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.time.LocalDate;
import java.math.BigDecimal;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.domain.models.Utente;

import java.util.List;

@Audited
@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_utente_objeto")
public class UtenteServicoEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @Column(name="objeto_tipo")
    private String objetoTipo;

  
    @Column(name="objeto_id")
    private Integer objetoId;

  
    @Column(name="descricao")
    private String descricao;

  
    @Column(name="referencia")
    private String referencia;

  
    @Column(name="datainicio")
    private LocalDate dataInicio;

  
    @Column(name="datafim")
    private LocalDate dataFim;

  
    @Column(name="valor")
    private BigDecimal valor;

  
    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private Estado estado;

     @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "utente_id")
   private Utente utenteId;


}