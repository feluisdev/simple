package cv.igrp.simple.configuracoes.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTiposServicosCommand implements Command {

    @NotNull(message = "O ID é obrigatório")
    private Integer id;

    @Positive(message = "O ID da categoria deve ser um número positivo")
    private Integer categoriaId;

    @Size(min = 2, max = 20, message = "O código deve ter entre 2 e 20 caracteres")
    private String codigo;

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    private String descricao;

    private Integer prazoEstimado;

    private Double valorBase;

    private Boolean requerVistoria;

    private Boolean requerAnaliseTec;

    private Boolean requerAprovacao;

    private Boolean disponivelPortal;

    private Boolean ativo;
}