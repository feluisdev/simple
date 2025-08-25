package cv.igrp.simple.licenciamento.domain.license2.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Category {

    private final Identificador id;
    private String name;
    private String description;
    private String code;
    private boolean active;
    private Integer level;
    private Integer sortOrder;
    private Metadata metadata;
    private String path;
    private Identificador parentId;  // Apenas referência, sem carregar objeto
    private Identificador sectorId;  // Apenas referência

    private Category(Identificador id,
                     String name,
                     String description,
                     String code,
                     boolean active,
                     Integer level,
                     Integer sortOrder,
                     Metadata metadata,
                     String path,
                     Identificador parentId,
                     Identificador sectorId) {

        this.id = Objects.requireNonNull(id, "Identificador não pode ser nulo");
        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.active = active;
        this.level = level;
        this.sortOrder = sortOrder;
        this.metadata = metadata;
        this.path = path;
        this.parentId = parentId;
        this.sectorId = sectorId;
    }

    public static Category criarNovo(String name,
                                     String description,
                                     String code,
                                     Integer level,
                                     Integer sortOrder,
                                     Metadata metadata,
                                     String path,
                                     Identificador parentId,
                                     Identificador sectorId) {
        return new Category(
                Identificador.gerarNovo(),
                name,
                description,
                code,
                true, // ativo por padrão
                level,
                sortOrder,
                metadata,
                path,
                parentId,
                sectorId
        );
    }

    public static Category reconstruir(Identificador id,
                                       String name,
                                       String description,
                                       String code,
                                       boolean active,
                                       Integer level,
                                       Integer sortOrder,
                                       Metadata metadata,
                                       String path,
                                       Identificador parentId,
                                       Identificador sectorId) {
        return new Category(id, name, description, code, active, level, sortOrder, metadata, path, parentId, sectorId);
    }

    public void atualizar(String name,
                          String description,
                          String code,
                          Integer level,
                          Integer sortOrder,
                          Metadata metadata,
                          String path,
                          Identificador parentId,
                          Identificador sectorId) {

        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.level = level;
        this.sortOrder = sortOrder;
        this.metadata = metadata;
        this.path = path;
        this.parentId = parentId;
        this.sectorId = sectorId;
    }

    public void ativar() {
        this.active = true;
    }

    public void desativar() {
        this.active = false;
    }

    public boolean isAtivo() {
        return active;
    }
}
