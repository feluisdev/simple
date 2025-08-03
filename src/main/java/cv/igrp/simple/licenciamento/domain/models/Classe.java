package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Classe {

    private final Identificador idClasse;
    private String classe;
    private String descricao;
    private Estado estado;

    private Classe(Identificador idClasse, String classe, String descricao, Estado estado) {
        this.idClasse = idClasse;
        this.classe = classe;
        this.descricao = descricao;
        this.estado = estado;
    }

    public static Classe criarNovo(String classe, String descricao) {
        return new Classe(Identificador.gerarNovo(), classe, descricao, Estado.ATIVO);
    }

    public static Classe reconstruir(Identificador idClasse, String classe, String descricao, Estado estado) {
        return new Classe(idClasse, classe, descricao, estado);
    }

    public void atualizar(String classe, String descricao) {
        Objects.requireNonNull(classe, "Classe não pode ser nula");
        this.classe = classe;
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