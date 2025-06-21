package cv.igrp.simple.configuracoes.infrastructure.controller;

import cv.igrp.simple.configuracoes.application.commands.AtualizarConfiguracaoCommand;
import cv.igrp.simple.configuracoes.application.commands.CriarConfiguracaoCommand;
import cv.igrp.simple.configuracoes.application.commands.InativarConfiguracaoCommand;
import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.CriarConfiguracoesDTO;
import cv.igrp.simple.configuracoes.application.dto.UpdateConfiguracoesDTO;
import cv.igrp.simple.configuracoes.application.queries.ListaDeConfiguracoesQuery;
import cv.igrp.simple.configuracoes.application.queries.ObterConfiguracaoQuery;
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
@RequestMapping("configuracoes/v1")
@RequiredArgsConstructor
public class ConfiguracoesController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @GetMapping
    public ResponseEntity<Page<ConfiguracoesResponseDTO>> listaDeConfiguracoes(
            @RequestParam(required = false) String chave,
            @RequestParam(required = false) String grupo,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String estado,
            Pageable pageable) {

        ListaDeConfiguracoesQuery query = new ListaDeConfiguracoesQuery(chave, grupo, tipo, estado, pageable);
        Page<ConfiguracoesResponseDTO> result = queryBus.handle(query);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracoesResponseDTO> obterConfiguracao(@PathVariable Integer id) {
        ObterConfiguracaoQuery query = new ObterConfiguracaoQuery(id);
        ConfiguracoesResponseDTO result = queryBus.handle(query);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ConfiguracoesResponseDTO> criarConfiguracao(@Valid @RequestBody CriarConfiguracoesDTO dto) {
        CriarConfiguracaoCommand command = CriarConfiguracaoCommand.builder()
                .chave(dto.getChave())
                .valor(dto.getValor())
                .descricao(dto.getDescricao())
                .tipo(dto.getTipo())
                .grupo(dto.getGrupo())
                .editavel(dto.getEditavel())
                .build();

        ConfiguracoesResponseDTO result = commandBus.send(command);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracoesResponseDTO> atualizarConfiguracao(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateConfiguracoesDTO dto) {

        AtualizarConfiguracaoCommand command = AtualizarConfiguracaoCommand.builder()
                .id(id)
                .chave(dto.getChave())
                .valor(dto.getValor())
                .descricao(dto.getDescricao())
                .tipo(dto.getTipo())
                .grupo(dto.getGrupo())
                .editavel(dto.getEditavel())
                .estado(dto.getEstado())
                .build();

        ConfiguracoesResponseDTO result = commandBus.send(command);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativarConfiguracao(@PathVariable Integer id) {
        InativarConfiguracaoCommand command = new InativarConfiguracaoCommand(id);
        String result = commandBus.send(command);
        return ResponseEntity.ok(result);
    }
}