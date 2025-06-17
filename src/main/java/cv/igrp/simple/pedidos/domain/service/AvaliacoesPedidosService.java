package cv.igrp.simple.pedidos.domain.service;

import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import cv.igrp.simple.pedidos.domain.repository.IAvaliacoesPedidosEntityRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvaliacoesPedidosService {

    private final IAvaliacoesPedidosEntityRepository avaliacoesPedidosRepository;

    public AvaliacoesPedidosService(IAvaliacoesPedidosEntityRepository avaliacoesPedidosRepository) {
        this.avaliacoesPedidosRepository = avaliacoesPedidosRepository;
    }

    public AvaliacoesPedidosEntity obterAvaliacaoPorId(Integer id) {
        Optional<AvaliacoesPedidosEntity> avaliacao = avaliacoesPedidosRepository.findById(id);

        return avaliacao.orElseThrow(() -> new IgrpResponseStatusException(new IgrpProblem<>(
                HttpStatus.NOT_FOUND,
                "Avaliação não encontrada com o ID: " + id,
                null)));
    }

    public AvaliacoesPedidosEntity obterAvaliacaoPorPedidoId(Integer pedidoId) {
        Optional<AvaliacoesPedidosEntity> avaliacao = avaliacoesPedidosRepository.findByPedidoId(pedidoId);

        return avaliacao.orElseThrow(() -> new IgrpResponseStatusException(new IgrpProblem<>(
                HttpStatus.NOT_FOUND,
                "Avaliação não encontrada para o pedido com ID: " + pedidoId,
                null)));
    }

    public AvaliacoesPedidosEntity criarAvaliacao(AvaliacoesPedidosEntity avaliacao) {
        // Verificar se já existe uma avaliação para este pedido
        Optional<AvaliacoesPedidosEntity> avaliacaoExistente = avaliacoesPedidosRepository.findByPedidoId(avaliacao.getPedidoId());
        
        if (avaliacaoExistente.isPresent()) {
            throw new IgrpResponseStatusException(new IgrpProblem<>(
                    HttpStatus.CONFLICT,
                    "Já existe uma avaliação para o pedido com ID: " + avaliacao.getPedidoId(),
                    null));
        }
        
        // Validações adicionais podem ser implementadas aqui
        return avaliacoesPedidosRepository.save(avaliacao);
    }
}