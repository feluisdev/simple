package cv.igrp.simple.configuracoes.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoriasServicosCommand implements Command {

    @NotNull(message = "O ID é obrigatório")
    private Integer id;

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    private String descricao;

    @Size(max = 50, message = "O ícone deve ter no máximo 50 caracteres")
    private String icone;

    @Size(max = 20, message = "A cor deve ter no máximo 20 caracteres")
    private String cor;

    private Integer ordem;

    private Boolean ativo;
}