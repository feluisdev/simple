package cv.igrp.simple.configuracoes.api;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateTiposServicosCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.InativarTiposServicosCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.UpdateTiposServicosCommand;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeTiposServicosQuery;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterTipoServicosQuery;
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
@RequestMapping("/api/configuracoes/tipos-servicos")
@RequiredArgsConstructor
public class TiposServicosController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping
    public ResponseEntity<Integer> criar(@Valid @RequestBody CreateTiposServicosCommand command) {
        Integer id = commandBus.send(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody UpdateTiposServicosCommand command) {
        command.setId(id);
        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Integer id) {
        InativarTiposServicosCommand command = new InativarTiposServicosCommand(id);
        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiposServicosResponseDTO> obter(@PathVariable Integer id) {
        ObterTipoServicosQuery query = new ObterTipoServicosQuery(id);
        TiposServicosResponseDTO dto = queryBus.handle(query);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<TiposServicosResponseDTO>> listar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Boolean disponivelPortal,
            @RequestParam(required = false) Boolean ativo,
            Pageable pageable) {
        ListaDeTiposServicosQuery query = new ListaDeTiposServicosQuery(codigo, nome, categoriaId, disponivelPortal, ativo, pageable);
        Page<TiposServicosResponseDTO> page = queryBus.handle(query);
        return ResponseEntity.ok(page);
    }
}