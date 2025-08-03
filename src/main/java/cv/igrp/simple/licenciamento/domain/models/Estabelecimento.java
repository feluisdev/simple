package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Estabelecimento {

    private final Integer id;
    private final Identificador idEstabelecimento;

    private String gerente;
    private String descricao;
    private boolean flagVistoria;
    private boolean licRetalho;
    private String endereco;
    private String telefone;
    private String email;
    private String nif;
    private Estado estado;

    private TipoAtividade tipoAtividade;
    private Set<Classe> classes;

    private Estabelecimento(
            Integer id,
            Identificador idEstabelecimento,
            String gerente,
            String descricao,
            boolean flagVistoria,
            boolean licRetalho,
            String endereco,
            String telefone,
            String email,
            String nif,
            Estado estado,
            TipoAtividade tipoAtividade,
            Set<Classe> classes
    ) {
        this.id = id;
        this.idEstabelecimento = idEstabelecimento;
        this.gerente = gerente;
        this.descricao = descricao;
        this.flagVistoria = flagVistoria;
        this.licRetalho = licRetalho;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.nif = nif;
        this.estado = estado;
        this.tipoAtividade = tipoAtividade;
        this.classes = classes != null ? new HashSet<>(classes) : new HashSet<>();
    }

    public static Estabelecimento criarNovo(
            String gerente,
            String descricao,
            boolean flagVistoria,
            boolean licRetalho,
            String endereco,
            String telefone,
            String email,
            String nif,
            TipoAtividade tipoAtividade
    ) {
        return new Estabelecimento(
                null,
                Identificador.gerarNovo(),
                gerente,
                descricao,
                flagVistoria,
                licRetalho,
                endereco,
                telefone,
                email,
                nif,
                Estado.ATIVO,
                tipoAtividade,
                new HashSet<>()
        );
    }

    public static Estabelecimento reconstruir(
            Integer id,
            Identificador idEstabelecimento,
            String gerente,
            String descricao,
            boolean flagVistoria,
            boolean licRetalho,
            String endereco,
            String telefone,
            String email,
            String nif,
            Estado estado,
            TipoAtividade tipoAtividade,
            Set<Classe> classes
    ) {
        return new Estabelecimento(
                id,
                idEstabelecimento,
                gerente,
                descricao,
                flagVistoria,
                licRetalho,
                endereco,
                telefone,
                email,
                nif,
                estado,
                tipoAtividade,
                classes
        );
    }

    public void atualizar(
            String gerente,
            String descricao,
            boolean flagVistoria,
            boolean licRetalho,
            String endereco,
            String telefone,
            String email,
            String nif,
            TipoAtividade tipoAtividade
    ) {
        this.gerente = gerente;
        this.descricao = descricao;
        this.flagVistoria = flagVistoria;
        this.licRetalho = licRetalho;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.nif = nif;
        this.tipoAtividade = tipoAtividade;
    }


    // Método para adicionar classe
    public void adicionarClasse(Classe classe) {
        Objects.requireNonNull(classe, "Classe não pode ser nula");
        this.classes.add(classe);
    }

    // Método para remover classe
    public void removerClasse(Classe classe) {
        Objects.requireNonNull(classe, "Classe não pode ser nula");
        this.classes.remove(classe);
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
