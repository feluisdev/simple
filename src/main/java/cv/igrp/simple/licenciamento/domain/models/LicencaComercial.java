package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
public class LicencaComercial {

    private final Identificador idLicenca;
    private String alvara;
    private LocalDate dataInicioLicenca;
    private LocalDate dataFimLicenca;
    private LocalDate dataRenovacaoLicenca;
    private LocalTime horarioInicioFuncionamento;
    private LocalTime horarioFimFuncionamento;
    private String designacao;
    private EstadoLicenca estado;

    private Identificador utenteId;
    private Estabelecimento estabelecimento;

    private LicencaComercial(
            Identificador idLicenca,
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estado,
            Identificador utenteId,
            Estabelecimento estabelecimento
    ) {
        this.idLicenca = idLicenca;
        this.alvara = alvara;
        this.dataInicioLicenca = dataInicioLicenca;
        this.dataFimLicenca = dataFimLicenca;
        this.dataRenovacaoLicenca = dataRenovacaoLicenca;
        this.horarioInicioFuncionamento = horarioInicioFuncionamento;
        this.horarioFimFuncionamento = horarioFimFuncionamento;
        this.designacao = designacao;
        this.estado = estado;
        this.utenteId = utenteId;
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
            Identificador utenteId,
            Estabelecimento estabelecimento
    ) {
        return new LicencaComercial(
                Identificador.gerarNovo(),
                alvara,
                dataInicioLicenca,
                dataFimLicenca,
                dataRenovacaoLicenca,
                horarioInicioFuncionamento,
                horarioFimFuncionamento,
                designacao,
                EstadoLicenca.A,
                utenteId,
                estabelecimento
        );
    }

    public static LicencaComercial reconstruir(
            Identificador idLicenca,
            String alvara,
            LocalDate dataInicioLicenca,
            LocalDate dataFimLicenca,
            LocalDate dataRenovacaoLicenca,
            LocalTime horarioInicioFuncionamento,
            LocalTime horarioFimFuncionamento,
            String designacao,
            EstadoLicenca estado,
            Identificador utenteId,
            Estabelecimento estabelecimento
    ) {
        return new LicencaComercial(
                idLicenca,
                alvara,
                dataInicioLicenca,
                dataFimLicenca,
                dataRenovacaoLicenca,
                horarioInicioFuncionamento,
                horarioFimFuncionamento,
                designacao,
                estado,
                utenteId,
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
            Identificador utenteId,
            Estabelecimento estabelecimento
    ) {
        Objects.requireNonNull(alvara, "Alvará não pode ser nulo");
        Objects.requireNonNull(utenteId, "UtenteId não pode ser nulo");
        Objects.requireNonNull(estabelecimento, "Estabelecimento não pode ser nulo");

        this.alvara = alvara;
        this.dataInicioLicenca = dataInicioLicenca;
        this.dataFimLicenca = dataFimLicenca;
        this.dataRenovacaoLicenca = dataRenovacaoLicenca;
        this.horarioInicioFuncionamento = horarioInicioFuncionamento;
        this.horarioFimFuncionamento = horarioFimFuncionamento;
        this.designacao = designacao;
        this.utenteId = utenteId;
        this.estabelecimento = estabelecimento;
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
