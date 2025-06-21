package cv.igrp.simple.configuracoes.domain.models;
import cv.igrp.simple.configuracoes.domain.valueobject.CategoriaUuid;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoriaServico {

    private Integer id;
    private String nome;
    private String descricao;
    private String codigo;
    private String icone;
    private String cor;
    private Integer ordem;
    private boolean estado;

    private CategoriaUuid categoriaUuid;

    private List<TipoServico> tiposServico;

    private CategoriaServico() {
    }
    private CategoriaServico(Integer id, String nome, String descricao, String icone,
                             String cor, Integer ordem,
                             boolean estado, CategoriaUuid categoriaUuid,
                             List<TipoServico> tiposServico, String codigo ) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.icone= icone;
        this.cor = cor;
        this.ordem = ordem;
        this.estado = estado;
        this.categoriaUuid = categoriaUuid;
        this.tiposServico = tiposServico!=null ? tiposServico : new ArrayList<>();
    }

    public static CategoriaServico criar(String nome,
                                         String descricao, String icon,
                                         String cor, String codigo) {

        var ordem = 1;

        return new CategoriaServico(
                null, // ID será atribuído pela persistência
                nome,
                descricao,
                icon,
                cor,
                ordem,
                true,
                CategoriaUuid.gerar(),
                null,
                codigo
        );
    }


    public static CategoriaServico reconstruir(Integer id,
                                               String nome,
                                               String descricao,
                                               String icon,
                                               String cor,
                                               Integer ordem,
                                               boolean estado,
                                               CategoriaUuid categoriaUuid,
                                               List<TipoServico> tiposServico,
                                               String codigo) {
        return new CategoriaServico(
                id,
                nome,
                descricao,
                icon,
                cor,
                ordem,
                estado,
                categoriaUuid,
                tiposServico,
                codigo
        );
    }



}
