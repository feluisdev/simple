package cv.igrp.simple.configuracoes.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaDeStatusPedidoQuery implements Query {
    private String codigo;
    private String nome;
    private Boolean visivelPortal;
    
    @NotNull(message = "A paginação é obrigatória")
    private Pageable pageable;
}