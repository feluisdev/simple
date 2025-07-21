package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.LicencaComercialEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface LicencaComercialEntityRepository extends
    JpaRepository<LicencaComercialEntity, Integer>,
    JpaSpecificationExecutor<LicencaComercialEntity>
{

}