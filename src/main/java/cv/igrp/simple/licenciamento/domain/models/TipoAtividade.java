package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.util.Objects;

@Getter
public class TipoAtividade {

    private final Identificador idTipoAtividade;
    private String codigo;
    private String descricao;
    private Estado estado;

    private TipoAtividade(Identificador idTipoAtividade, String codigo, String descricao, Estado estado) {
        this.idTipoAtividade = idTipoAtividade;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
    }

    public static TipoAtividade criarNovo(String codigo, String descricao) {
        return new TipoAtividade(Identificador.gerarNovo(), codigo, descricao, Estado.ATIVO);
    }

    public static TipoAtividade reconstruir(Identificador idTipoAtividade, String codigo, String descricao, Estado estado) {
        return new TipoAtividade(idTipoAtividade, codigo, descricao, estado);
    }

    public void atualizar(String codigo, String descricao) {
        Objects.requireNonNull(codigo, "Código não pode ser nulo");
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public void ativar() {
        this.estado = Estado.ATIVO;
    }

    public void desativar() {
        this.estado = Estado.INATIVO;
    }

    public boolean isAtivo() {
        return this.estado == Estado.ATIVO;
    }
}
