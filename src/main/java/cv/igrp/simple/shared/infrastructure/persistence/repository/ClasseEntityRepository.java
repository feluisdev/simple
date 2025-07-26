package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.ClasseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@Tag(name = "Classes", description = "API para gestão de classes")
@RepositoryRestResource(path = "classes")
public interface ClasseEntityRepository extends
    JpaRepository<ClasseEntity, Integer>,
    JpaSpecificationExecutor<ClasseEntity>
{

}