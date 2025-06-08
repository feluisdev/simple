package cv.igrp.simple.utente.application.dto.validator;

import cv.igrp.simple.utente.application.constants.TipoUtente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import cv.igrp.simple.utente.application.dto.CriarUtenteDTO;

public class CriarUtenteDTOValidator implements ConstraintValidator<ICriarUtenteDTOValidator, CriarUtenteDTO> {

    @Override
    public void initialize(ICriarUtenteDTOValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CriarUtenteDTO dto, ConstraintValidatorContext context) {

        boolean isValid = true;
        context.disableDefaultConstraintViolation(); // Desativa a violação padrão

        String nif = dto.getNif().trim();

        // Verifica se o NIF tem 9 dígitos
        if (nif.length() != 9 || !nif.matches("\\d{9}")) {
            context.buildConstraintViolationWithTemplate("O NIF deve conter 9 dígitos.")
                    .addPropertyNode("nif")
                    .addConstraintViolation();
            isValid = false;
        } else {
            // Valida o primeiro dígito conforme o tipo de utente
            if (dto.getTipoUtente() == TipoUtente.CIDADAO && nif.charAt(0) != '1') {
                context.buildConstraintViolationWithTemplate("O NIF de um cidadão deve começar com 1.")
                        .addPropertyNode("nif")
                        .addConstraintViolation();
                isValid = false;
            } else if (dto.getTipoUtente() == TipoUtente.EMPRESA && nif.charAt(0) != '2') {
                context.buildConstraintViolationWithTemplate("O NIF de uma empresa deve começar com 2.")
                        .addPropertyNode("nif")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;
    }

}
