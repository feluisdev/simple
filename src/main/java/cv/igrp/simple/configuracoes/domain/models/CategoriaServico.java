package cv.igrp.simple.configuracoes.domain.models;

import cv.igrp.simple.configuracoes.domain.valueobject.CategoriaUuid;
import lombok.Getter;
import org.springframework.util.Assert;

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
        // Construtor privado para JPA e frameworks de mapeamento
    }

    private CategoriaServico(Integer id, String nome, String descricao, String icone,
                             String cor, Integer ordem,
                             boolean estado, CategoriaUuid categoriaUuid,
                             List<TipoServico> tiposServico, String codigo) {

        validarCamposObrigatorios(nome, codigo);

        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.icone = icone;
        this.cor = cor;
        this.ordem = ordem;
        this.estado = estado;
        this.categoriaUuid = categoriaUuid;
        this.tiposServico = tiposServico != null ? tiposServico : new ArrayList<>();
    }

    public static CategoriaServico criar(String nome,
                                         String descricao, String icon,
                                         String cor, String codigo) {

        validarCamposObrigatorios(nome, codigo);
        var ordem = 1; // Ordem padrão para novas categorias

        return new CategoriaServico(
                null, // ID será atribuído pela persistência
                nome,
                descricao,
                icon,
                cor,
                ordem,
                true, // Nova categoria sempre ativa
                CategoriaUuid.gerar(),
                new ArrayList<>(), // Inicializa a lista de tipos de serviço vazia
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

        validarCamposObrigatorios(nome, codigo);
        Assert.notNull(id, "ID não pode ser nulo ao reconstruir CategoriaServico");
        Assert.notNull(categoriaUuid, "CategoriaUuid não pode ser nulo ao reconstruir CategoriaServico");

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

    public void inativar() {
        if (this.tiposServico != null && !this.tiposServico.isEmpty()) {
            this.tiposServico.forEach(tipo -> {
                if (tipo.isEstado()) {
                    tipo.inativar();
                }
            });
        }
        this.estado = false;
    }


    public void ativar() {
        this.estado = true;
    }

    public void atualizarDados(String nome, String descricao, String icone, String cor, Integer ordem) {
        //validarCamposObrigatorios(nome, this.codigo); // Valida nome, mas usa o código existente
        this.nome = nome;
        this.descricao = descricao;
        this.icone = icone;
        this.cor = cor;
        this.ordem = ordem;
        this.codigo = codigo;
    }


    private static void validarCamposObrigatorios(String nome, String codigo) {
        Assert.hasText(nome, "Nome da categoria não pode ser vazio ou nulo.");
        Assert.hasText(codigo, "Código da categoria não pode ser vazio ou nulo.");
    }

}
