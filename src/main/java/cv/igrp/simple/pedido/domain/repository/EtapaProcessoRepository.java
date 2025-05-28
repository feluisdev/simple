package cv.igrp.simple.pedido.domain.repository;

import cv.igrp.simple.pedido.domain.models.EtapaProcesso;

public interface EtapaProcessoRepository {

    EtapaProcesso getById(Integer etapaAnteriorId);

    EtapaProcesso save(EtapaProcesso etapaProcesso);
}
