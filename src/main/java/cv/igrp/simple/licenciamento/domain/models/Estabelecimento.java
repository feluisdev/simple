package cv.igrp.simple.licenciamento.domain.models;

import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

@Getter
public class Estabelecimento {

    private final Integer id;               // novo campo id da base, pode ser null se ainda não persistido
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
            Estado estado
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
    }

    public static Estabelecimento criarNovo(
            String gerente,
            String descricao,
            boolean flagVistoria,
            boolean licRetalho,
            String endereco,
            String telefone,
            String email,
            String nif
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
                Estado.ATIVO
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
            Estado estado
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
                estado
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
            String nif
    ) {
        this.gerente = gerente;
        this.descricao = descricao;
        this.flagVistoria = flagVistoria;
        this.licRetalho = licRetalho;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.nif = nif;
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
