package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.LicencaComercialEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;


@Repository
@Tag(name = "Licenca Comercial", description = "API para gestão de Licenca Comercial")
@RepositoryRestResource(path = "licencas-comerciais")
public interface LicencaComercialEntityRepository extends
    JpaRepository<LicencaComercialEntity, Integer>,
    JpaSpecificationExecutor<LicencaComercialEntity>
{

}