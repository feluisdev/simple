package cv.igrp.simple.utente.infrastructure.mappers;

import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteServicoEntity;
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {


    public ServicoResponseDTO mapToDTO(UtenteServicoEntity servico) {

        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.setId(servico.getId());
        dto.setTipo(servico.getObjetoTipo());
        dto.setDescricao(servico.getDescricao());
        dto.setReferencia(servico.getReferencia());
        dto.setObjetoID(servico.getObjetoId());

        dto.setDataInicio(servico.getDataInicio() != null ? servico.getDataInicio().toString() : null);
        dto.setDataFim(servico.getDataFim() != null ? servico.getDataFim().toString() : null);

        dto.setEstado(servico.getUtenteId() != null ? servico.getUtenteId().getEstado() : null);
        dto.setValor(servico.getValor());
        dto.setEstado(servico.getEstado());

        return dto;
    }
}
