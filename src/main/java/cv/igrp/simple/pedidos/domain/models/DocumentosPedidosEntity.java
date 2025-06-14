package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_pedidos_documentos")
public class DocumentosPedidosEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

  
    @NotNull(message = "pedidoId is mandatory")
    @Column(name="pedido_id", nullable = false)
    private Integer pedidoId;

  

    @NotBlank(message = "nome is mandatory")
    @Column(name="nome", nullable = false, length=100)
    private String nome;

  
    @Column(name="descricao")
    private String descricao;

  

    @NotBlank(message = "tipoDocumento is mandatory")
    @Column(name="tipo_documento", nullable = false, length=50)
    private String tipoDocumento;

  

    @NotBlank(message = "caminhoArquivo is mandatory")
    @Column(name="caminho_arquivo", nullable = false)
    private String caminhoArquivo;

  
    @Column(name="tamanho_arquivo")
    private Integer tamanhoArquivo;

  
    @NotNull(message = "dataUpload is mandatory")
    @Column(name="data_upload", nullable = false)
    private LocalDate dataUpload;

  
    @NotNull(message = "userId is mandatory")
    @Column(name="user_id", nullable = false)
    private UUID userId;

  
}