package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface StatusPedidoRepository {


     /* Salva (cria ou atualiza) uma instância de StatusPedido.
            *
            * @param statusPedido A entidade StatusPedido a ser salva.
     */
     StatusPedido save(StatusPedido statusPedido);

    /**
     * Busca um StatusPedido pelo seu Identificador UUID.
     *
     * @param statusPedidoUuid O Identificador UUID do StatusPedido.
     * @return Um {@link Optional} contendo o StatusPedido se encontrado, ou vazio caso contrário.
     */
    Optional<StatusPedido> getById(Identificador statusPedidoUuid);

    /**
     * Busca um StatusPedido pelo seu código.
     * O código é um identificador de negócio e espera-se que seja único.
     *
     * @param codigo O código do StatusPedido.
     * @return Um {@link Optional} contendo o StatusPedido se encontrado, ou vazio caso contrário.
     */
    Optional<StatusPedido> getByCodigo(String codigo);


    boolean existByCodigo(String codigo);
    /**
     * Busca todos os StatusPedido cadastrados.
     *
     * @return Uma {@link List} contendo todos os StatusPedido. Pode retornar uma lista vazia se não houver nenhum.
     */
    List<StatusPedido> getAll();

    Optional<StatusPedido> getById(Integer id);

    List<StatusPedido> getByAtivo(boolean ativo);
}
