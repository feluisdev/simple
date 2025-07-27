package cv.igrp.simple.shared.interfaces.rest;

import cv.igrp.framework.core.data.EnumItem;
import cv.igrp.framework.core.utils.object.EnumUtils;
import cv.igrp.simple.utente.application.constants.GeneroTipo;
import cv.igrp.simple.utente.application.constants.TipoIdentificacao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parameterizacao")
@Tag(name = "Parameterização", description = "Gestão de Parameterizações")
public class ParametrizacaoController {


    @GetMapping("/tipo-identificacao")
    public ResponseEntity<List<EnumItem<String>>> getTipoIdentificacao() {
        return ResponseEntity.ok(EnumUtils.mapEnumToItems(TipoIdentificacao.class));
    }

    @GetMapping("/sexo")
    public ResponseEntity<List<EnumItem<String>>> getSexo() {
        return ResponseEntity.ok(EnumUtils.mapEnumToItems(GeneroTipo.class));
    }


}
