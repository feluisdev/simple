package cv.igrp.simple.licenciamento.infrastructure.mappers;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.LicencaResponseLigthDTO;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.models.LicencaComercial;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.LicencaComercialEntity;
import cv.igrp.simple.utente.infrastructure.mappers.UtenteMapper;
import org.springframework.stereotype.Component;

@Component
public class LicencaComercialMapper {

    private final EstabelecimentoMapper estabelecimentoMapper;
    private final UtenteMapper utenteMapper;

    public LicencaComercialMapper(EstabelecimentoMapper estabelecimentoMapper, UtenteMapper utenteMapper) {
        this.estabelecimentoMapper = estabelecimentoMapper;
        this.utenteMapper = utenteMapper;
    }

    public LicencaComercial toDomain(LicencaComercialEntity entity) {
        if (entity == null) return null;

        Estabelecimento estabelecimentoDomain = estabelecimentoMapper.toDomain(entity.getIdEstabelecimento());

        return LicencaComercial.reconstruir(
                entity.getId(),
                Identificador.from(entity.getExternalId()),
                entity.getAlvara(),
                entity.getDataInicioLicenca(),
                entity.getDataFimLicenca(),
                entity.getDataRenovacaoLicenca(),
                entity.getHorarioInicioFuncionamento(),
                entity.getHorarioFimFuncionamento(),
                entity.getDesignacao(),
                entity.getEstado(),
                entity.getUtenteId() != null ? utenteMapper.toDomain(entity.getUtenteId()) : null,
                estabelecimentoDomain
        );
    }

    public LicencaComercialEntity toEntity(LicencaComercial licenca) {
        if (licenca == null) return null;

        var entity = new LicencaComercialEntity();

        if (licenca.getId() != null) {
            entity.setId(licenca.getId());
        }

        entity.setExternalId(licenca.getIdLicenca().getValor());
        entity.setAlvara(licenca.getAlvara());
        entity.setDataInicioLicenca(licenca.getDataInicioLicenca());
        entity.setDataFimLicenca(licenca.getDataFimLicenca());
        entity.setDataRenovacaoLicenca(licenca.getDataRenovacaoLicenca());
        entity.setHorarioInicioFuncionamento(licenca.getHorarioInicioFuncionamento());
        entity.setHorarioFimFuncionamento(licenca.getHorarioFimFuncionamento());
        entity.setDesignacao(licenca.getDesignacao());
        entity.setEstado(licenca.getEstado());
        entity.setUtenteId(licenca.getUtente()!=null ? utenteMapper.toEntity(licenca.getUtente()) : null);

        entity.setIdEstabelecimento(estabelecimentoMapper.toEntity(licenca.getEstabelecimento()));

        return entity;
    }

    public LicencaResponseDTO toDTO(LicencaComercial licenca) {
        if (licenca == null) return null;

        var dto = new LicencaResponseDTO();

        dto.setIdLicenca(licenca.getIdLicenca().getStringValor());
        dto.setAlvara(licenca.getAlvara());
        dto.setDataInicioLicenca(licenca.getDataInicioLicenca());
        dto.setDataFimLicenca(licenca.getDataFimLicenca());
        dto.setDataRenovacaoLicenca(licenca.getDataRenovacaoLicenca());
        dto.setDesignacao(licenca.getDesignacao());
        dto.setHorarioInicioFuncionamento(licenca.getHorarioInicioFuncionamento());
        dto.setHorarioFimFuncionamento(licenca.getHorarioFimFuncionamento());

        dto.setEstadoLicenca(licenca.getEstado() != null ? licenca.getEstado().name() : null);
        dto.setEstadoLicencaDesc(licenca.getEstado() != null ? licenca.getEstado().getDescription() : null);

        dto.setEstabelecimento(licenca.getEstabelecimento() != null ?
                estabelecimentoMapper.toDTO(licenca.getEstabelecimento()) : null);
        dto.setIdUtente(licenca.getUtente() != null && licenca.getUtente().getId()!=null ? licenca.getUtente().getId().toString(): "");
        dto.setNomeUtente(licenca.getUtente() != null ? licenca.getUtente().getNome() : "");
        dto.setNumeroUtente(licenca.getUtente() != null ? licenca.getUtente().getNumero() : "");


        return dto;
    }


    public LicencaResponseLigthDTO toLightDTO(LicencaComercial licenca) {
        if (licenca == null) return null;

        var dto = new LicencaResponseLigthDTO();

        dto.setIdLicenca(licenca.getIdLicenca().getStringValor());
        dto.setAlvara(licenca.getAlvara());
        dto.setDataInicioLicenca(licenca.getDataInicioLicenca());
        dto.setDataFimLicenca(licenca.getDataFimLicenca());
        dto.setDataRenovacaoLicenca(licenca.getDataRenovacaoLicenca());
        dto.setDesignacao(licenca.getDesignacao());
        dto.setHorarioInicioFuncionamento(licenca.getHorarioInicioFuncionamento());
        dto.setHorarioFimFuncionamento(licenca.getHorarioFimFuncionamento());

        dto.setEstadoLicenca(licenca.getEstado() != null ? licenca.getEstado().name() : null);
        dto.setEstadoLicencaDesc(licenca.getEstado() != null ? licenca.getEstado().getDescription() : null);

        dto.setEstabelecimentoId(licenca.getEstabelecimento() != null ? licenca.getEstabelecimento().getIdEstabelecimento().getStringValor(): null);
        dto.setEstabelecimentoNome(licenca.getEstabelecimento() != null ? licenca.getEstabelecimento().getNome() : null);
        dto.setEstabelecimentoGerente(licenca.getEstabelecimento() != null ? licenca.getEstabelecimento().getGerente() : null);
        dto.setIdUtente(licenca.getUtente() != null && licenca.getUtente().getId()!=null ? licenca.getUtente().getId().toString(): "");
        dto.setNomeUtente(licenca.getUtente() != null ? licenca.getUtente().getNome() : "");
        dto.setNumeroUtente(licenca.getUtente() != null ? licenca.getUtente().getNumero() : "");
        return dto;
    }

}
