package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class DocumentoPedidoResponseDTO {

  
  
  private String documentoId;
  
  
  private String pedidoId;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private String tipoDocumento;
  
  
  private String caminhoArquivo;
  
  
  private Integer tamanhoArquivo;
  
  
  private LocalDate dataUpload;
  
  
  private Integer userId;
  
  
  private String userNome;

}