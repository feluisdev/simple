package cv.igrp.simple.licenciamento.domain.license2.repository;

import cv.igrp.simple.licenciamento.domain.license2.filter.CategoryFilter;
import cv.igrp.simple.licenciamento.domain.license2.models.Category;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(Identificador id);

    List<Category> findAll();

    List<Category> findAll(CategoryFilter filter);

    void delete(Identificador id);

    boolean existsByCode(String code);

}
