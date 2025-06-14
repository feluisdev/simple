package cv.igrp.simple.configuracoes.api;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateStatusPedidoCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.InativarStatusPedidoCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.UpdateStatusPedidoCommand;
import cv.igrp.simple.configuracoes.application.dto.CreateStatusPedidoDTO;
import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.UpdateStatusPedidoDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeStatusPedidoQuery;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterStatusPedidoQuery;
import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracoes/status-pedido")
@RequiredArgsConstructor
public class StatusPedidoController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping
    public ResponseEntity<Integer> criar(@Valid @RequestBody CreateStatusPedidoDTO dto) {
        CreateStatusPedidoCommand command = CreateStatusPedidoCommand.builder()
                .codigo(dto.getCodigo())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .cor(dto.getCor())
                .icone(dto.getIcone())
                .ordem(dto.getOrdem())
                .visivelPortal(dto.getVisivelPortal())
                .build();

        Integer id = commandBus.send(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody UpdateStatusPedidoDTO dto) {
        UpdateStatusPedidoCommand command = UpdateStatusPedidoCommand.builder()
                .id(id)
                .codigo(dto.getCodigo())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .cor(dto.getCor())
                .icone(dto.getIcone())
                .ordem(dto.getOrdem())
                .visivelPortal(dto.getVisivelPortal())
                .build();

        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Integer id) {
        InativarStatusPedidoCommand command = InativarStatusPedidoCommand.builder()
                .id(id)
                .build();

        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusPedidoResponseDTO> obterPorId(@PathVariable Integer id) {
        ObterStatusPedidoQuery query = ObterStatusPedidoQuery.builder()
                .id(id)
                .build();

        StatusPedidoResponseDTO dto = queryBus.handle(query);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<StatusPedidoResponseDTO>> listar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Boolean visivelPortal,
            Pageable pageable) {

        ListaDeStatusPedidoQuery query = ListaDeStatusPedidoQuery.builder()
                .codigo(codigo)
                .nome(nome)
                .visivelPortal(visivelPortal)
                .pageable(pageable)
                .build();

        Page<StatusPedidoResponseDTO> page = queryBus.handle(query);
        return ResponseEntity.ok(page);
    }
}