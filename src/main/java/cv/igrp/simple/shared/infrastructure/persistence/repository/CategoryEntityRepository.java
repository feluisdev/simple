package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoryEntityRepository extends
    JpaRepository<CategoryEntity, UUID>,
    JpaSpecificationExecutor<CategoryEntity>
{
    boolean existsByCode(String code);

    Optional<CategoryEntity> findByCode(String code);

    List<CategoryEntity> findAllByActiveTrue();

}