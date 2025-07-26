package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoEstabelecimentoEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
@Tag(name = "Tipo Estabelecimento", description = "API para gestão de Tipo Estabelecimento")
@RepositoryRestResource(path = "tipo-estabelecimento")
public interface TipoEstabelecimentoEntityRepository extends
    JpaRepository<TipoEstabelecimentoEntity, Integer>,
    JpaSpecificationExecutor<TipoEstabelecimentoEntity>
{

}