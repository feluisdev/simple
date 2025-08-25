package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models;

import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
public class LicencaComercial {

    private final Integer id;               // novo campo id da base (pode ser null se não persistido)
    private final Identificador idLicenca;
    private String alvara;
    private LocalDate dataInicioLicenca;
    private LocalDate dataFimLicenca;
    private LocalDate dataRenovacaoLicenca;
    private LocalTime horarioInicioFuncionamento;
    private LocalTime horarioFimFuncionamento;
    private String designacao;
    private EstadoLicenca estado;

    private Utente utente;
    private Estabelecimento estabelecimento;

    private LicencaComercial(
            Integer id,
            Identificador idLicenca,
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estado,
            Utente utente,
            Estabelecimento estabelecimento
    ) {
        this.id = id;
        this.idLicenca = idLicenca;
        this.alvara = alvara;
        this.dataInicioLicenca = dataInicioLicenca;
        this.dataFimLicenca = dataFimLicenca;
        this.dataRenovacaoLicenca = dataRenovacaoLicenca;
        this.horarioInicioFuncionamento = horarioInicioFuncionamento;
        this.horarioFimFuncionamento = horarioFimFuncionamento;
        this.designacao = designacao;
        this.estado = estado;
        this.utente = utente;
        this.estabelecimento = estabelecimento;
    }

    public static LicencaComercial criarNovo(
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estadoLicenca,
            Utente utente,
            Estabelecimento estabelecimento
    ) {

        Objects.requireNonNull(alvara, "Alvará não pode ser nulo");
        Objects.requireNonNull(dataInicioLicenca, "Data de início da licença é obrigatória");
        Objects.requireNonNull(dataFimLicenca, "Data de fim da licença é obrigatória");
        Objects.requireNonNull(horarioInicioFuncionamento, "Horário de início do funcionamento é obrigatório");
        Objects.requireNonNull(horarioFimFuncionamento, "Horário de fim do funcionamento é obrigatório");
        Objects.requireNonNull(estabelecimento, "Estabelecimento não pode ser nulo");


        if (dataFimLicenca.isBefore(dataInicioLicenca)) {
            throw IgrpResponseStatusException.badRequest("Data de fim da licença não pode ser anterior à data de início");
        }

        if (dataRenovacaoLicenca.isBefore(dataInicioLicenca)) {
            throw IgrpResponseStatusException.badRequest("Data de renovação não pode ser anterior à data de início da licença");
        }

        if (!horarioFimFuncionamento.isAfter(horarioInicioFuncionamento)) {
            throw IgrpResponseStatusException.badRequest("Horário de fim do funcionamento deve ser posterior ao horário de início");
        }


        return new LicencaComercial(
                null,
                Identificador.gerarNovo(),
                alvara,
                dataInicioLicenca,
                dataFimLicenca,
                dataRenovacaoLicenca,
                horarioInicioFuncionamento,
                horarioFimFuncionamento,
                designacao,
                estadoLicenca,
                utente,
                estabelecimento
        );
    }

    public static LicencaComercial reconstruir(
            Integer id,
            Identificador idLicenca,
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estado,
            Utente utente,
            Estabelecimento estabelecimento
    ) {
        return new LicencaComercial(
                id,
                idLicenca,
                alvara,
                dataInicioLicenca,
                dataFimLicenca,
                dataRenovacaoLicenca,
                horarioInicioFuncionamento,
                horarioFimFuncionamento,
                designacao,
                estado,
                utente,
                estabelecimento
        );
    }

    public void atualizar(
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estado,
            Utente utente,
            Estabelecimento estabelecimento
    ) {
        Objects.requireNonNull(alvara, "Alvará não pode ser nulo");
        Objects.requireNonNull(dataInicioLicenca, "Data de início da licença é obrigatória");
        Objects.requireNonNull(dataFimLicenca, "Data de fim da licença é obrigatória");
        Objects.requireNonNull(horarioInicioFuncionamento, "Horário de início do funcionamento é obrigatório");
        Objects.requireNonNull(horarioFimFuncionamento, "Horário de fim do funcionamento é obrigatório");
        Objects.requireNonNull(estabelecimento, "Estabelecimento não pode ser nulo");


        if (dataFimLicenca.isBefore(dataInicioLicenca)) {
            throw IgrpResponseStatusException.badRequest("Data de fim da licença não pode ser anterior à data de início");
        }

        if (dataRenovacaoLicenca.isBefore(dataInicioLicenca)) {
            throw IgrpResponseStatusException.badRequest("Data de renovação não pode ser anterior à data de início da licença");
        }

        if (!horarioFimFuncionamento.isAfter(horarioInicioFuncionamento)) {
            throw IgrpResponseStatusException.badRequest("Horário de fim do funcionamento deve ser posterior ao horário de início");
        }

        this.alvara = alvara;
        this.dataInicioLicenca = dataInicioLicenca;
        this.dataFimLicenca = dataFimLicenca;
        this.dataRenovacaoLicenca = dataRenovacaoLicenca;
        this.horarioInicioFuncionamento = horarioInicioFuncionamento;
        this.horarioFimFuncionamento = horarioFimFuncionamento;
        this.designacao = designacao;
        this.utente = utente;
        this.estabelecimento = estabelecimento;
        this.estado = estado;
    }

    public void ativar() {
        this.estado = EstadoLicenca.A;
    }

    public void desativar() {
        this.estado = EstadoLicenca.S;
    }

    public boolean isAtivo() {
        return this.estado == EstadoLicenca.A;
    }
}
