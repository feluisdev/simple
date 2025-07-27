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
        context.disableDefaultConstraintViolation(); // Disable default violation

        String nif = dto.getNif().trim();

        // NIF length and digit check
        if (nif.length() != 9 || !nif.matches("\\d{9}")) {
            context.buildConstraintViolationWithTemplate("O NIF deve conter 9 dígitos.")
                    .addPropertyNode("nif")
                    .addConstraintViolation();
            isValid = false;
        } else {
            // Validate first digit based on tipoUtente
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

        // Validate genero only if tipoUtente is CIDADAO (or other that require it)
        if (dto.getTipoUtente() == TipoUtente.CIDADAO && dto.getGenero() == null) {
            context.buildConstraintViolationWithTemplate("O campo <genero> é obrigatório para cidadãos.")
                    .addPropertyNode("genero")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }


}
