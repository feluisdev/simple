package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class UploadDocumentoPedidoDTO {

  
  
  private String pedidoId;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private String tipoDocumento;
  
  
  private MultipartFile arquivo;

}