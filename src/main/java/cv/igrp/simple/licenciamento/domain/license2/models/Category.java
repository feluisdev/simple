package cv.igrp.simple.licenciamento.domain.license2.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
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

    private Identificador parentId;       // apenas referência
    private Sector sector;                // referência completa
    private List<Category> children;  // filhos completos

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
                     Sector sector,
                     List<Category> children) {

        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.code = Objects.requireNonNull(code);
        this.active = active;
        this.level = level;
        this.sortOrder = sortOrder;
        this.metadata = metadata;
        this.path = path;
        this.parentId = parentId;
        this.sector = sector;
        this.children = children != null ? children : new ArrayList<>();

    }

    public static Category criarNovo(String name,
                                     String description,
                                     String code,
                                     Integer level,
                                     Integer sortOrder,
                                     Metadata metadata,
                                     String path,
                                     Identificador parentId,
                                     Sector sector) {
        return new Category(
                Identificador.gerarNovo(),
                name,
                description,
                code,
                true,
                level,
                sortOrder,
                metadata,
                path,
                parentId,
                sector,
                new ArrayList<>()
        );
    }

    public static Category reconstruir(
            Identificador id,
            String name,
            String description,
            String code,
            boolean active,
            Integer level,
            Integer sortOrder,
            Metadata metadata,
            String path,
            Identificador parentId,
            Sector sector,
            List<Category> children) {

        return new Category(
                id,
                name,
                description,
                code,
                active,
                level,
                sortOrder,
                metadata,
                path,
                parentId,
                sector,
                children != null ? children : new ArrayList<>()
        );
    }


    public void addChild(Category child) {
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    public void removeChild(Category child) {
        children.remove(child);
    }

    public void ativar() {
        this.active = true;
    }

    public void desativar() {
        this.active = false;
    }

    public boolean isAtivo() {
        return this.active;
    }

    public void move(Identificador newParentId) {
        this.parentId = newParentId;
    }

}
