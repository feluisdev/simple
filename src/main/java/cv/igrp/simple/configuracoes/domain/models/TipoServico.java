package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.configuracoes.domain.valueobject.TipoServicoUuid;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class TipoServico {

    private Integer id;
    private Identificador tipoServicoUuid;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer prazoEstimado; // Em dias úteis
    private Double valorBase;
    private boolean vistoria; // Se requer vistoria
    private boolean analiseTecnica; // Se requer análise técnica
    private boolean aprovacao; // Se requer aprovação
    private boolean portal; // Se está disponível no portal
    private boolean estado; // Ativo ou Inativo

    private CategoriaServico categoria;

    private TipoServico() {
        // Construtor privado para JPA e frameworks de mapeamento
    }

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
                        Identificador tipoServicoUuid) {

        validarCamposObrigatorios(codigo, nome, categoria);

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
                                    boolean requerVistoria,
                                    boolean requerAnaliseTec,
                                    boolean requerAprovacao,
                                    boolean disponivelPortal,
                                    CategoriaServico categoria) {

        validarCamposObrigatorios(codigo, nome, categoria);

        return new TipoServico(
                null, // id gerado na persistência
                codigo,
                nome,
                descricao,
                prazoEstimado,
                valorBase,
                requerVistoria,
                requerAnaliseTec,
                requerAprovacao,
                disponivelPortal,
                true, // Novo tipo de serviço sempre ativo
                categoria,
                Identificador.gerarNovo()
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
                                          Identificador tipoServicoUuid) {

        validarCamposObrigatorios(codigo, nome, categoria);
        Assert.notNull(id, "ID não pode ser nulo ao reconstruir TipoServico");
        Assert.notNull(tipoServicoUuid, "TipoServicoUuid não pode ser nulo ao reconstruir TipoServico");

        return new TipoServico(id, codigo, nome, descricao, prazoEstimado,
                valorBase, vistoria, analiseTecnica, aprovacao, portal, estado, categoria, tipoServicoUuid);
    }

    public void inativar() {
        this.estado = false;
    }

    public void ativar() {
        this.estado = true;
    }

    public void atualizarDados(String nome,
                               String descricao,
                               Integer prazoEstimado,
                               Double valorBase,
                               boolean requerVistoria,
                               boolean requerAnaliseTec,
                               boolean requerAprovacao,
                               boolean disponivelPortal,
                               CategoriaServico categoria) {

        validarCamposObrigatorios(this.codigo, nome, categoria); // Código não muda, mas nome e categoria são validados

        this.nome = nome;
        this.descricao = descricao;
        this.prazoEstimado = prazoEstimado;
        this.valorBase = valorBase;
        this.vistoria = requerVistoria;
        this.analiseTecnica = requerAnaliseTec;
        this.aprovacao = requerAprovacao;
        this.portal = disponivelPortal;
        this.categoria = categoria;
    }


    private static void validarCamposObrigatorios(String codigo, String nome, CategoriaServico categoria) {
        Assert.hasText(codigo, "Código do tipo de serviço não pode ser vazio ou nulo.");
        Assert.hasText(nome, "Nome do tipo de serviço não pode ser vazio ou nulo.");
        Assert.notNull(categoria, "Categoria do tipo de serviço não pode ser nula.");
    }

}
