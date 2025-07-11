package cv.igrp.simple.configuracoes.application.dto;

import cv.igrp.simple.configuracoes.application.constants.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateConfiguracoesDTO {

    private String chave;
    private String valor;
    private String descricao;
    private String tipo;
    private String grupo;
    private Boolean editavel;
    private Estado estado;
}