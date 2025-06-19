package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.configuracoes.domain.valueobject.TipoServicoUuid;
import lombok.Getter;

@Getter
public class TipoServico {

    private Integer id;
    private TipoServicoUuid tipoServicoUuid;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer prazoEstimado;
    private Double valorBase;
    private boolean vistoria;
    private boolean analiseTecnica;
    private boolean aprovacao;
    private boolean portal;
    private boolean estado;

    private CategoriaServico categoria;

    private TipoServico(Integer id,
                        String codigo,
                        String nome,
                        String descricao,
                        Integer prazoEstimado,
                        Double valorBase,
                        boolean vistoria,
                        boolean analiseTecnica,
                        boolean aprovacao,
                        boolean portal,
                        boolean estado,
                        CategoriaServico categoria,
                        TipoServicoUuid tipoServicoUuid) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.prazoEstimado = prazoEstimado;
        this.valorBase = valorBase;
        this.vistoria = vistoria;
        this.analiseTecnica = analiseTecnica;
        this.aprovacao = aprovacao;
        this.portal = portal;
        this.estado = estado;
        this.categoria = categoria;
        this.tipoServicoUuid = tipoServicoUuid;
    }

    public static TipoServico criar(String codigo,
                                    String nome,
                                    String descricao,
                                    Integer prazoEstimado,
                                    Double valorBase,
                                    boolean vistoria,
                                    boolean analiseTecnica,
                                    boolean aprovacao,
                                    boolean portal,
                                    CategoriaServico categoria) {
        return new TipoServico(
                null, // id gerado na persistência
                codigo,
                nome,
                descricao,
                prazoEstimado,
                valorBase,
                vistoria,
                analiseTecnica,
                aprovacao,
                portal,
                true,
                categoria,
                TipoServicoUuid.gerar()
        );
    }

    public static TipoServico reconstruir(Integer id,
                                          String codigo,
                                          String nome,
                                          String descricao,
                                          Integer prazoEstimado,
                                          Double valorBase,
                                          boolean vistoria,
                                          boolean analiseTecnica,
                                          boolean aprovacao,
                                          boolean portal,
                                          boolean estado,
                                          CategoriaServico categoria,
                                          TipoServicoUuid tipoServicoUuid) {
        return new TipoServico(id, codigo, nome, descricao, prazoEstimado,
                valorBase, vistoria, analiseTecnica, aprovacao, portal, estado, categoria, tipoServicoUuid);
    }

}
