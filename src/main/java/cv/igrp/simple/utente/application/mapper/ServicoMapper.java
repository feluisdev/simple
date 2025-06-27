package cv.igrp.simple.utente.application.mapper;

import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.domain.models.UtenteServico;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {


    public ServicoResponseDTO mapToDTO(UtenteServico servico) {
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
