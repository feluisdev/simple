package cv.igrp.simple.pedidos.domain.service;

import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import cv.igrp.simple.pedidos.domain.repository.IPagamentosPedidosEntityRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentosPedidosService {

    private final IPagamentosPedidosEntityRepository pagamentosPedidosRepository;

    public PagamentosPedidosService(@Lazy IPagamentosPedidosEntityRepository pagamentosPedidosRepository) {
        this.pagamentosPedidosRepository = pagamentosPedidosRepository;
    }

    public PagamentosPedidosEntity obterPagamentoPorId(Integer id) {
        Optional<PagamentosPedidosEntity> pagamento = pagamentosPedidosRepository.findById(id);

        return pagamento.orElseThrow(() -> new IgrpResponseStatusException(new IgrpProblem<>(
                HttpStatus.NOT_FOUND,
                "Pagamento não encontrado com o ID: " + id,
                null)));
    }

    public PagamentosPedidosEntity atualizarStatusPagamento(Integer id, PagamentosPedidosEntity pagamentoAtualizado) {
        PagamentosPedidosEntity pagamentoExistente = obterPagamentoPorId(id);
        
        pagamentoExistente.setStatus(pagamentoAtualizado.getStatus());
        pagamentoExistente.setReferenciaPagamento(pagamentoAtualizado.getReferenciaPagamento());
        pagamentoExistente.setObservacao(pagamentoAtualizado.getObservacao());
        
        return pagamentosPedidosRepository.save(pagamentoExistente);
    }

    public void excluirPagamento(Integer id) {
        // Verifica se o pagamento existe antes de excluir
        obterPagamentoPorId(id);
        pagamentosPedidosRepository.deleteById(id);
    }
    
    public PagamentosPedidosEntity registrarPagamento(PagamentosPedidosEntity pagamento) {
        // Validações adicionais podem ser implementadas aqui
        return pagamentosPedidosRepository.save(pagamento);
    }
}