package cv.igrp.simple.configuracoes.api;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.InativarCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.application.commands.commands.UpdateCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeCategoriasServicosQuery;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterCategoriaServicosQuery;
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
@RequestMapping("/api/configuracoes/categorias-servicos")
@RequiredArgsConstructor
public class CategoriasServicosController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping
    public ResponseEntity<Integer> criar(@Valid @RequestBody CreateCategoriasServicosCommand command) {
        Integer id = commandBus.send(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody UpdateCategoriasServicosCommand command) {
        command.setId(id);
        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Integer id) {
        InativarCategoriasServicosCommand command = new InativarCategoriasServicosCommand(id);
        commandBus.send(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasServicosResponseDTO> obter(@PathVariable Integer id) {
        ObterCategoriaServicosQuery query = new ObterCategoriaServicosQuery(id);
        CategoriasServicosResponseDTO dto = queryBus.handle(query);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriasServicosResponseDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Boolean ativo,
            Pageable pageable) {
        ListaDeCategoriasServicosQuery query = new ListaDeCategoriasServicosQuery(nome, ativo, pageable);
        Page<CategoriasServicosResponseDTO> page = queryBus.handle(query);
        return ResponseEntity.ok(page);
    }
}