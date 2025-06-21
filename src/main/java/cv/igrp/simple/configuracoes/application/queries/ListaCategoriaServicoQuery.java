package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaCategoriaServicoQuery implements Query {

  private String nome; // Opcional
  private String codigo; // Opcional
  private Boolean ativo; // Opcional (true para ativo, false para inativo, null para todos)

  @Min(value = 0, message = "Número da página deve ser maior ou igual a 0.")
  private Integer pageNumber = 0; // Default 0

  @Min(value = 1, message = "Tamanho da página deve ser maior ou igual a 1.")
  private Integer pageSize = 10; // Default 10

  // Construtores/Getters/Setters gerados por Lombok
  // Construtor explícito para facilidade, se necessário
    public ListaCategoriaServicoQuery(String nome, String codigo, Boolean ativo, Integer pageNumber, Integer pageSize) {
        this.nome = nome;
        this.codigo = codigo;
        this.ativo = ativo;
        if (pageNumber != null) this.pageNumber = pageNumber;
        if (pageSize != null) this.pageSize = pageSize;
    }
}