package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.util.Objects;

@Getter
public class TipoAtividade {

    private final Integer id; // auto increment ID (pode ser null se ainda não persistido)
    private final Identificador idTipoAtividade;
    private String nome;       // novo campo
    private String codigo;
    private String descricao;
    private Estado estado;

    private TipoAtividade(Integer id, Identificador idTipoAtividade, String nome, String codigo, String descricao, Estado estado) {
        this.id = id;
        this.idTipoAtividade = idTipoAtividade;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estado = estado;
    }

    public static TipoAtividade criarNovo(String nome, String codigo, String descricao) {
        Objects.requireNonNull(nome, "Nome não pode ser nulo");
        return new TipoAtividade(null, Identificador.gerarNovo(), nome, codigo, descricao, Estado.ATIVO);
    }

    public static TipoAtividade reconstruir(Integer id, Identificador idTipoAtividade, String nome, String codigo, String descricao, Estado estado) {
        return new TipoAtividade(id, idTipoAtividade, nome, codigo, descricao, estado);
    }

    public void atualizar(String nome, String codigo, String descricao) {
        Objects.requireNonNull(nome, "Nome não pode ser nulo");
        Objects.requireNonNull(codigo, "Código não pode ser nulo");
        this.nome = nome;
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
